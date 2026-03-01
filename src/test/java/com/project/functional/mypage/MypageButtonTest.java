package com.project.functional.mypage;

import com.project.base.BaseTest;
import com.project.constants.AppMessages;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.myinfo.ChangePasswordPage;
import com.project.page.myinfo.MyinfoPage;
import com.project.page.myinfo.MyinfoupdatePage;
import config.ConfigReader;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Map;

public class MypageButtonTest extends BaseTest {

    @BeforeMethod
    public void setupLogin() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToMyinfoPage();
    }

    private final ConfigReader config = new ConfigReader();

    @Test(testName = "clickModifyButton test")
    public void clickModifyButtonTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.clickModifyButton();
        MyinfoupdatePage MyinfoupdatePage = new MyinfoupdatePage(driver);
        String currentUrl = MyinfoupdatePage.getCurrentUrl();
        String PageTittle = MyinfoupdatePage.getPageTitle();
        Assert.assertEquals(currentUrl, config.getProperty("MyinfoupdatePageURL"));
        Assert.assertEquals(PageTittle, PageLabels.myinfoupdatePageTabTitle);
    }

    @Test(testName = "clickChangePasswordButton test")
    public void clickChangePasswordButtonTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.clickChangePasswordButton();

        ChangePasswordPage ChangePasswordPage = new ChangePasswordPage(driver);
        String currentUrl = ChangePasswordPage.getCurrentUrl();
        String PageTittle = ChangePasswordPage.getPageTitle();

        Assert.assertEquals(currentUrl, config.ChangePasswordPageURL());
        Assert.assertEquals(PageTittle, PageLabels.changePasswordPageTabTitle);
    }

    @Test(testName = "changePassword clearButton test")
    public void changePasswordClearButtonTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.clickChangePasswordButton();

        ChangePasswordPage ChangePasswordPage = new ChangePasswordPage(driver);
        ChangePasswordPage.changePassword("somepassword","newpassword","newpassword");
        ChangePasswordPage.clickClearButton();
        Assert.assertTrue(ChangePasswordPage.pwdField.getText().isEmpty());
        Assert.assertTrue(ChangePasswordPage.newPwdField.getText().isEmpty());
        Assert.assertTrue(ChangePasswordPage.newPwd2Field.getText().isEmpty());
    }

    @Test(testName = "myinfoUpatePage SaveButton test")
    public void myinfoUpdatePageSaveButtonTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.clickModifyButton();
        MyinfoupdatePage MyinfoupdatePage = new MyinfoupdatePage(driver);
        MyinfoupdatePage.clickSaveButton();
        String alertText = MyinfoupdatePage.alertGetText();
        Assert.assertEquals(alertText, AppMessages.myinfoUpdateSuccessAlertMsg);
        MyinfoupdatePage.alertAccept();
    }

    @AfterMethod
    private void myInfopageLogout() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.navi.clickLogoutLink();
    }


}





