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

            String rawMessage = assertCommand.getMessage() != null ? assertCommand.getMessage() : "SoftAssert 실패";

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String methodName = Reporter.getCurrentTestResult().getName();
            String fileName = methodName + "_" + System.currentTimeMillis() + ".png";
            String filePath = "target/screenshots/" + fileName;
            File destFile = new File(filePath);
            org.apache.commons.io.FileUtils.copyFile(srcFile, destFile);

            ITestResult result = Reporter.getCurrentTestResult();
            List<String[]> softFailureInfos = (List<String[]>) result.getAttribute("softFailureInfos");

            if (softFailureInfos == null) {
                softFailureInfos = new ArrayList<>();
                result.setAttribute("softFailureInfos", softFailureInfos);
            }

            softFailureInfos.add(new String[]{filePath, rawMessage});

            System.out.println("[SoftAssert] 파일 및 정보 저장 완료: " + filePath);

        } catch (Exception e) {
            System.out.println("[ERROR] SoftAssert 처리 실패: " + e.getMessage());
        }

        super.onAssertFailure(assertCommand, ex);
    }
}

