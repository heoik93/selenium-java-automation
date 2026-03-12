package com.project.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.project.base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TestListener implements ITestListener {
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    public static ExtentTest getTest() {  return test.get();  }


    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseTest) currentClass).getDriver();
        String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

        test.get().log(Status.PASS, "테스트가 성공적으로 통과되었습니다.");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest currentTest = test.get();
        ExtentManager.getExtentReports().removeTest(currentTest);
        ExtentTest newSkipTest = ExtentManager.getInstance().createTest(result.getMethod().getMethodName());
        test.set(newSkipTest);
        test.get().skip(MarkupHelper.createLabel("TEST SKIPPED (Retrying...)", ExtentColor.ORANGE));
        test.get().skip(result.getThrowable());
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(MarkupHelper.createLabel("TEST FAILED", ExtentColor.RED));
        test.get().fail(result.getThrowable());

        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseTest) currentClass).getDriver();

        //데이터 준비
        List<String> allScreenshotPaths = new ArrayList<>();
        StringBuilder tableBuilder = new StringBuilder("\n\n*📸 스크린샷 상세 정보 (SoftAssert 포함)*\n");
        tableBuilder.append("|| 순서 || 파일명 || 에러 내용 ||\n"); // 지라 표 헤더
        int rowNum = 1;

        //SoftAssert 정보 불러와서 표 작성
        List<String[]> softFailureInfos = (List<String[]>) result.getAttribute("softFailureInfos");
        if (softFailureInfos != null) {
            for (String[] info : softFailureInfos) {
                String path = info[0];
                String msg = info[1];
                String fileName = new File(path).getName();

                // 지라 표 행 추가
                tableBuilder.append("| ").append(rowNum++).append(" | ").append(fileName)
                        .append(" | ").append(msg).append(" |\n");
                allScreenshotPaths.add(path);
            }
        }

        if (driver != null) {
            // ExtentReport용 (Base64) - 기존 유지
            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            test.get().addScreenCaptureFromBase64String(base64Screenshot, "[최종 실패] 테스트 종료 시점 화면");

            //최종 실패 스크린샷 파일 저장
            try {
                File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String finalFileName = "FINAL_" + result.getName() + "_" + System.currentTimeMillis() + ".png";
                String finalPath = "target/screenshots/" + finalFileName;
                File destFile = new File(finalPath);
                destFile.getParentFile().mkdirs();
                org.apache.commons.io.FileUtils.copyFile(srcFile, destFile);

                // 표에 최종 실패 행 추가
                tableBuilder.append("| ").append(rowNum).append(" (최종) | ")
                        .append(finalFileName).append(" | 테스트 중단 시점 스크린샷 |\n");

                allScreenshotPaths.add(finalPath);
            } catch (Exception e) {
                System.out.println("[ERROR] 스크린샷 파일 저장 실패: " + e.getMessage());
            }
        }

        //지라 티켓 생성 (표 내용 포함)
        String methodName = result.getMethod().getMethodName();
        String errorMsg = result.getThrowable().getMessage();
        ZonedDateTime nowSeoul = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        String timestamp = nowSeoul.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String issueKey = JiraClient.createJiraIssue(
                "[GitHub_Action] 자동화 테스트실패 (테스트 메서드명 : " + methodName + ")",
                "📅 발생 시간 (KST): " + timestamp + "\n\n" +
                        "❗ 상세 에러 메시지:\n" +
                        "{code:java}\n" + errorMsg + "\n{code}" +
                        tableBuilder.toString()
        );

        // 5. 모든 파일 업로드 - 기존 유지
        if (issueKey != null && !allScreenshotPaths.isEmpty()) {
            for (String path : allScreenshotPaths) {
                JiraClient.addAttachment(issueKey, path);
            }
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
        try {
            Thread.sleep(2000);
            System.out.println("리포트 저장 완료. 메일 전송을 시작합니다...");

            System.out.println("테스트코드 작성중에는 비활성화");

            String suiteName = context.getSuite().getName();
            int passed = context.getPassedTests().size();
            int failed = context.getFailedTests().size();
            int skipped = context.getSkippedTests().size();

            //디버깅용 xml은 메일스킵
            if ("Debug Suite".equals(suiteName)) {
                System.out.println("디버그 모드이므로 메일 전송을 건너뜁니다.");
                return;
            }

            EmailUtil.sendReport(suiteName, passed, failed, skipped);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

