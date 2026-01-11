package com.project.functional;

import com.project.base.BaseTest;
import com.project.page.HomePage;
import com.project.page.LoginPage;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {

    @Test(testName = "default logout test")
    public void testLogin() {

        ConfigReader config = new ConfigReader();
        driver.get(config.getUrl());

        HomePage homePage = new HomePage(driver);
        homePage.navi.goToLoginPage();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.logout();
        Assert.assertTrue(afterLogin.navi.isLoginButtonVisible(),"로그아웃 성공");
        }

}
