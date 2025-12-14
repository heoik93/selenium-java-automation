package com.project.login;

import com.project.base.BaseTest;
import com.project.page.HomePage;
import com.project.page.SignupPage;
import config.ConfigReader;
import org.testng.annotations.Test;

public class SignupTest extends BaseTest {

    @Test
    public void testSignup() {

        ConfigReader config = new ConfigReader();
        driver.get(config.getUrl());

        HomePage homePage = new HomePage(driver);
        homePage.navi.goToSignupPage();

        SignupPage SignupPage = new SignupPage(driver);
        SignupPage.agreeSignup();
        SignupPage.SignupInputData();
        SignupPage.enterAddress("06035", "서울 강남구 가로수길 5", "상세주소123", "(신사동)");
        SignupPage.clickSignupSubmit();
    }

}
