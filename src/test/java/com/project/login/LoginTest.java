package com.project.login;

import com.project.base.BaseTest;
import com.project.page.HomePage;
import config.ConfigReader;
import com.project.page.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(testName = "default login test")
    public void testLogin() {

        ConfigReader config = new ConfigReader();
        driver.get(config.getUrl());

        HomePage homePage = new HomePage(driver);
        homePage.navi.goToLoginPage();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        HomePage afterLogin = new HomePage(driver);
        Assert.assertTrue(afterLogin.navi.isLogoutButtonVisible(), "로그인 실패: 로그아웃 버튼이 표시되지 않음");
    }
}
