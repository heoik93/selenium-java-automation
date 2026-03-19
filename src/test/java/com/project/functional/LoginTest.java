package com.project.functional;

import com.project.base.BaseTest;
import com.project.page.HomePage;
import com.project.utils.ScreenshotSoftAssert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(testName = "default login test")
    public void testLogin() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        softAssert.assertTrue(afterLogin.navi.isLogoutButtonVisible(), "[FAIL]로그인 후에도 로그아웃 버튼이 표시되지 않았습니다.");

        softAssert.assertAll();
    }
}
