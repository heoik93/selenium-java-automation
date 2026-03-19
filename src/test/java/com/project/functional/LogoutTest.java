package com.project.functional;

import com.project.base.BaseTest;
import com.project.page.HomePage;
import com.project.page.LoginPage;
import com.project.utils.ScreenshotSoftAssert;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {

    @Test(testName = "default logout test")
    public void testLogin() {
        ConfigReader config = new ConfigReader();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        driver.get(config.getUrl());

        HomePage homePage = new HomePage(driver);
        homePage.navi.goToLoginPage();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.logout();
        softAssert.assertTrue(afterLogin.navi.isLoginButtonVisible(),"[FAIL]로그인후에도 로그인버튼이 표시되어 있습니다.");

        softAssert.assertAll();
        }

}
