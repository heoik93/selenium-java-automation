package com.project.functional;

import com.project.base.BaseTest;
import com.project.constants.AppMessages;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.LoginPage;
import com.project.page.SignupPage;
import com.project.page.myinfo.MyinfoPage;
import com.project.utils.ScreenshotSoftAssert;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SignupTest extends BaseTest {

    @Test(testName = "default signup test")
    public void testSignup() {
        ConfigReader config = new ConfigReader();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        driver.get(config.getUrl());

        HomePage homePage = new HomePage(driver);
        homePage.navi.goToSignupPage();

        SignupPage SignupPage = new SignupPage(driver);
        SignupPage.agreeSignup();
        List<String> RandomUser = SignupPage.SignupInputData();
        
        SignupPage.enterAddress("06035", "서울 강남구 가로수길 5", "상세주소123", "(신사동)");
        SignupPage.clickSignupSubmit();

        softAssert.assertEquals(SignupPage.alertGetText(), AppMessages.singUpPage_singUp_AlertMsg,
                "[FAIL] 회원가입 성공 Alert 메세지가 올바르지 않습니다." );
        SignupPage.alertAccept();
        System.out.println("[INFO] 회원가입이 완료되었습니다.");
        
        //탈퇴기능 구현
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoad();
        loginPage.login(RandomUser.get(0),RandomUser.get(1));

        HomePage homepage = new HomePage(driver);
        homepage.waitForPageLoad();
        homepage.navi.goToMyinfoPage();

        MyinfoPage myInfoPage = new MyinfoPage(driver);
        myInfoPage.waitForPageLoad();

        myInfoPage.clickWithdrawButton();
        softAssert.assertEquals(myInfoPage.alertGetText(),RandomUser.get(0)+AppMessages.singUpPage_Withdraw_AlertMsg1,
                "[FAIL] 회원탈퇴 확인 Alert 메세지1이 올바르지 않습니다.");
        myInfoPage.alertAccept();

        softAssert.assertEquals(myInfoPage.alertGetText(),AppMessages.singUpPage_Withdraw_AlertMsg2,
                "[FAIL] 회원탈퇴 Alert 메세지2가 올바르지 않습니다.");
        myInfoPage.alertAccept();
        System.out.println("[INFO] 회원탈퇴가 완료되었습니다.");

        HomePage homepage_after = new HomePage(driver);
        homepage_after.waitForPageLoad();


        softAssert.assertEquals(homepage_after.getPageTitle(), PageLabels.homePageTitle,"[FAIL] 회원탈퇴후의 홈페이지 페이지타이틀이 올바르지 않습니다.");
        softAssert.assertEquals(homepage_after.getCurrentUrl(),config.getProperty("url"),"[FAIL] 회원탈퇴후의 홈페이지 URL이 올바르지 않습니다.");

        softAssert.assertAll();
    }
}
