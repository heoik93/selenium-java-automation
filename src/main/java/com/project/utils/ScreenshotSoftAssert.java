package com.project.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

public class ScreenshotSoftAssert extends SoftAssert {
    private final WebDriver driver;

    // 생성자에서 드라이버를 받아옵니다.
    public ScreenshotSoftAssert(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        // 1. 에러가 발생한 순간 스크린샷 촬영
        try {
            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

            // 2. TestListener를 통해 현재 리포트에 스크린샷과 에러 메시지 즉시 첨부
            TestListener.getTest().fail(
                    "SoftAssert 실패: " + ex.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build()
            );

            System.out.println("[SoftAssert] 실패 감지! 스크린샷 캡처 완료.");

        } catch (Exception e) {
            System.out.println("[ERROR] SoftAssert 스크린샷 캡처 실패: " + e.getMessage());
        }

        // 3. 부모 클래스의 기능(에러 저장)은 그대로 수행해야 나중에 assertAll()에서 터집니다.
        super.onAssertFailure(assertCommand, ex);
    }
}

