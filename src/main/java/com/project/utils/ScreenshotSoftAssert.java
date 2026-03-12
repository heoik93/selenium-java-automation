package com.project.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ScreenshotSoftAssert extends SoftAssert {
    private final WebDriver driver;

    public ScreenshotSoftAssert(WebDriver driver) {
        this.driver = driver;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {

        try {
            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            TestListener.getTest().fail(
                    "SoftAssert 실패: " + ex.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build()
            );
            System.out.println("[SoftAssert] 실패 감지! 스크린샷 캡처 완료.");

            //Jira 첨부용 파일 저장
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String methodName = Reporter.getCurrentTestResult().getName();
            String fileName = methodName + "_SOFT_" + System.currentTimeMillis() + ".png";
            String filePath = "target/screenshots/" + fileName;
            File destFile = new File(filePath);
            org.apache.commons.io.FileUtils.copyFile(srcFile, destFile);

            //ITestResult에 경로 리스트 저장 (중요!)
            ITestResult result = Reporter.getCurrentTestResult();
            List<String> paths = (List<String>) result.getAttribute("screenshotPaths");
            if (paths == null) {
                paths = new ArrayList<>();
                result.setAttribute("screenshotPaths", paths);
            }
            paths.add(filePath);

            System.out.println("[SoftAssert] 파일 저장 완료: " + filePath);

        } catch (Exception e) {
            System.out.println("[ERROR] SoftAssert 스크린샷 캡처 실패: " + e.getMessage());
        }

        super.onAssertFailure(assertCommand, ex);
    }
}

