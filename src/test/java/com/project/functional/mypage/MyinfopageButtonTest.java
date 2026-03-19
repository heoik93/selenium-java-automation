package com.project.functional.mypage;

import com.project.base.BaseTest;
import com.project.constants.AppMessages;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.myinfo.ChangePasswordPage;
import com.project.page.myinfo.MyinfoPage;
import com.project.page.myinfo.MyinfoupdatePage;
import com.project.utils.ScreenshotSoftAssert;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyinfopageButtonTest extends BaseTest {

    @BeforeMethod
    public void setupLogin() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToMyinfoPage();
    }

    private final ConfigReader config = new ConfigReader();

    @Test(testName = "myInfoPage clickModifyButton test")
    public void myInfoPage_clickModifyButtonTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        myinfoPage.clickModifyButton();
        MyinfoupdatePage MyinfoupdatePage = new MyinfoupdatePage(driver);
        String currentUrl = MyinfoupdatePage.getCurrentUrl();
        String PageTittle = MyinfoupdatePage.getPageTitle();
        softAssert.assertEquals(currentUrl, config.getProperty("MyinfoupdatePageURL"),
                "[FAIL]회원정보페이지 개인정보수정버튼 클릭후의 URL이 올바르지 않습니다.");
        softAssert.assertEquals(PageTittle, PageLabels.myinfoupdatePageTabTitle,
                "[FAIL]회원정보페이지 개인정보수정버튼 클릭후의 페이지타이틀이 올바르지 않습니다.");

        softAssert.assertAll();
    }

    @Test(testName = "myInfoPage clickChangePasswordButton test")
    public void myInfoPage_clickChangePasswordButtonTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        myinfoPage.clickChangePasswordButton();

        ChangePasswordPage ChangePasswordPage = new ChangePasswordPage(driver);
        String currentUrl = ChangePasswordPage.getCurrentUrl();
        String PageTittle = ChangePasswordPage.getPageTitle();

        softAssert.assertEquals(currentUrl, config.ChangePasswordPageURL(),
                "[FAIL]회원정보페이지 비밀번호수정버튼 클릭후의 URL이 올바르지 않습니다.");
        softAssert.assertEquals(PageTittle, PageLabels.changePasswordPageTabTitle,
                "[FAIL]회원정보페이지 비밀번호수정버튼 클릭후의 페이지 타이틀이 올바르지 않습니다.");

        softAssert.assertAll();
    }

    @Test(testName = "myInfoPage changePassword clearButton test")
    public void  myInfoPage_changePasswordClearButtonTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        myinfoPage.clickChangePasswordButton();

        ChangePasswordPage ChangePasswordPage = new ChangePasswordPage(driver);
        ChangePasswordPage.changePassword("somepassword","newpassword","newpassword");
        ChangePasswordPage.clickClearButton();
        softAssert.assertTrue(ChangePasswordPage.pwdField.getText().isEmpty(),
                "[FAIL]비밀번호 수정페이지에서 초기화버튼 클릭시 기존비밀번호 필드기 클리어되지 않았습니다.");
        softAssert.assertTrue(ChangePasswordPage.newPwdField.getText().isEmpty(),
                "[FAIL]비밀번호 수정페이지에서 초기화버튼 클릭시 새비밀번호 필드기 클리어되지 않았습니다.");
        softAssert.assertTrue(ChangePasswordPage.newPwd2Field.getText().isEmpty(),
                "[FAIL]비밀번호 수정페이지에서 초기화버튼 클릭시 새비밀번호 확인 필드기 클리어되지 않았습니다.");

        softAssert.assertAll();
    }

    @Test(testName = "myInfoUpdatePage SaveButton test")
    public void  myInfoUpdatePage_SaveButtonTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        myinfoPage.clickModifyButton();
        MyinfoupdatePage MyinfoupdatePage = new MyinfoupdatePage(driver);
        MyinfoupdatePage.clickSaveButton();
        String alertText = MyinfoupdatePage.alertGetText();
        softAssert.assertEquals(alertText, AppMessages.myinfoUpdateSuccessAlertMsg,
                "[FAIL]개인정보 수정페이지에서 저장버튼 클릭시의 Alert메세지가 올바르지 않습니다.");
        MyinfoupdatePage.alertAccept();

        softAssert.assertAll();
    }

    @AfterMethod
    private void myInfopageLogout() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.navi.clickLogoutLink();
    }


}





