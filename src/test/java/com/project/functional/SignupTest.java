package com.project.functional;

import com.project.base.BaseTest;
import com.project.page.HomePage;
import com.project.page.SignupPage;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignupTest extends BaseTest {

    @Test(testName = "default signup test")
    public void testSignup() {

        ConfigReader config = new ConfigReader();
        driver.get(config.getUrl());

        HomePage homePage = new HomePage(driver);
        homePage.navi.goToSignupPage();

        SignupPage SignupPage = new SignupPage(driver);
        SignupPage.agreeSignup();
        SignupPage.SignupInputData();
        //다음주소찾기API는 직접입력형식
        SignupPage.enterAddress("06035", "서울 강남구 가로수길 5", "상세주소123", "(신사동)");
        SignupPage.clickSignupSubmit();

        String alertText = SignupPage.getSignupAlertText();

        Assert.assertTrue(
                alertText.contains("회원가입 되었습니다"),
                "회원가입 성공 Alert가 표시되지 않았습니다."
        );

        SignupPage.acceptSignupAlert();
    }
}
