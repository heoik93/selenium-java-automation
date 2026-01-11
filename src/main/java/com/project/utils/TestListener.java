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

public class TestListener implements ITestListener {
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();


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
        test.get().skip(MarkupHelper.createLabel("TEST SKIPPED", ExtentColor.ORANGE));
        test.get().skip(result.getThrowable());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(MarkupHelper.createLabel("TEST FAILED", ExtentColor.RED));
        test.get().fail(result.getThrowable());

        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseTest) currentClass).getDriver();

        if (driver != null) {
            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            test.get().addScreenCaptureFromBase64String(base64Screenshot, "실패 시점 스크린샷");
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
            //테스트시에는 비활성화
            EmailUtil.sendReport(suiteName, passed, failed, skipped);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

