package com.project.login;

import com.project.base.BaseTest;
import com.project.page.HomePage;
import com.project.page.myinfo.ChangePasswordPage;
import com.project.page.myinfo.MyinfoPage;
import com.project.page.myinfo.MyinfoupdatePage;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyinfoTest extends BaseTest {

    @BeforeMethod
    public void setupLogin() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.goToMyinfoPage();
    }

    @Test(testName = "Myinfo update test")
    public void MyinfoUpdateTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);

        String userId = myinfoPage.getUserId();
        String address = myinfoPage.getAddress();
        String email = myinfoPage.getEmail();
        String phone = myinfoPage.getPhone();

    }

    @Test(testName = "clickModifyButton test")
    public void clickModifyButtonTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.clickModifyButton();

        MyinfoupdatePage MyinfoupdatePage = new MyinfoupdatePage(driver);
        String currentUrl = MyinfoupdatePage.getCurrentUrl();
        String PageTittle = MyinfoupdatePage.getPageTitle();

        ConfigReader config = new ConfigReader();
        Assert.assertEquals(currentUrl, config.MyinfoupdatePageURL());
        Assert.assertEquals(PageTittle, "회원정보 수정");
    }

    @Test(testName = "clickChangePasswordButton test")
    public void clickChangePasswordButtonTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.clickChangePasswordButton();

        ChangePasswordPage ChangePasswordPage = new ChangePasswordPage(driver);
        String currentUrl = ChangePasswordPage.getCurrentUrl();
        String PageTittle = ChangePasswordPage.getPageTitle();

        ConfigReader config = new ConfigReader();
        Assert.assertEquals(currentUrl, config.ChangePasswordPageURL());
        Assert.assertEquals(PageTittle, "비밀번호 수정");

    }

    @Test(testName = "clickWithdrawButton test")
    public void clickWithdrawButtonTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        String userId = myinfoPage.getUserId();
        myinfoPage.clickWithdrawButton();

        String alertText = myinfoPage.WithdrawAlertgetText();

        Assert.assertEquals(alertText, userId+"님 탈퇴 하시겠습니까?");
        myinfoPage.WithdrawAlertAccept();

        //회원탈퇴가 아직 구현이 되지 않기에 추후코딩 예정
    }
}
