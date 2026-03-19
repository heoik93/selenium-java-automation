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
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.project.utils.ScreenshotSoftAssert;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Map;

public class MyinfoTest extends BaseTest {

    @BeforeMethod
    public void setupLogin() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToMyinfoPage();
    }

    private Map<String, String> beforeInfo;
    private Map<String, String> backupInfo;
    private final ConfigReader config = new ConfigReader();

    @Test(testName = "MyinfoPage tab active test")
    public void MyinfoPage_TabActiveTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.waitForPageLoad();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        softAssert.assertTrue(myinfoPage.isMyInfoTabActive(), "[FAIL]회원정보 탭이 활성화 되어 있지 않습니다.");
        softAssert.assertAll();
    }

    //탭 테스트(회원정보탭)
    @Test(testName = "MyinfoPage MyInfo tab test")
    public void MyinfoPage_MyInfoTabTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        myinfoPage.clickMyInfoTab();

        String currentUrl = myinfoPage.getCurrentUrl();
        String pageTitle = myinfoPage.getPageTitle();

        softAssert.assertEquals(currentUrl, config.getProperty("MyInfoPageURL"), "[FAIL]회원정보페이지에서 회원정보탭 클릭후의 URL이 일치하지 않습니다.");
        softAssert.assertEquals(pageTitle, PageLabels.myinfoPageTitle, "[FAIL]회원정보페이지에서 회원정보탭 클릭후의 페이지타이틀이 일치하지 않습니다.");

        softAssert.assertAll();
    }

    //탭 테스트(신청내역확인탭)
    @Test(testName = "MyinfoPage UseHistory tab test")
    public void MyinfoPage_UseHistoryTabTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        myinfoPage.clickUseHistoryTab();

        String currentUrl = myinfoPage.getCurrentUrl();
        String pageTitle = myinfoPage.getPageTitle();

        softAssert.assertEquals(currentUrl, config.getProperty("UseHistoryPageURL"), "[FAIL]회원정보페이지에서 신청내역확인탭 클릭후의 URL이 일치하지 않습니다.");
        softAssert.assertEquals(pageTitle, PageLabels.useHistoryPageTittle, "[FAIL]회원정보페이지에서 신청내역확인탭 클릭후의 페이지타이틀이 일치하지 않습니다.");

        softAssert.assertAll();
    }



    @Test(testName = "Myinfopage updateFlow test")
    public void myInfoPage_UpdateFlowTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.waitForPageLoad();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        beforeInfo = myinfoPage.getAllUserInfo();

        myinfoPage.clickModifyButton();
        MyinfoupdatePage MyinfoupdatePage = new MyinfoupdatePage(driver);

        backupInfo = MyinfoupdatePage.getAllUserInfo();

        //수정전 정보 일치확인
        softAssert.assertEquals(beforeInfo.get("userId"), backupInfo.get("userId"), "[FAIL]조회 페이지와 수정 페이지의 아이디가 불일치합니다.");
        softAssert.assertEquals(beforeInfo.get("email"), backupInfo.get("email"), "[FAIL]조회 페이지와 수정 페이지의 이메일이 불일치합니다.");
        softAssert.assertEquals(beforeInfo.get("phone"), backupInfo.get("phone"), "[FAIL]조회 페이지와 수정 페이지의 전화번호가 불일치합니다.");
        softAssert.assertEquals(beforeInfo.get("address"), backupInfo.get("address"), "[FAIL]조회 페이지와 수정 페이지의 주소가 불일치합니다.");

        //비활성 필드 확인
        softAssert.assertFalse(MyinfoupdatePage.isEnableuserIdField(), "[FAIL]아이디 필드가 활성화 되어 있습니다.");

        //유저정보 수정 및 저장
        MyinfoupdatePage.chageUserinfo01("testemail@test.com", "08011112222");
        MyinfoupdatePage.clickSaveButton();
        MyinfoupdatePage.alertAccept();

        //수정된 정보 확인
        MyinfoPage resultPage = new MyinfoPage(driver);
        Map<String, String> afterInfo = resultPage.getAllUserInfo();

        //수정후 정보 일치확인
        softAssert.assertEquals(afterInfo.get("email"), "testemail@test.com", "[FAIL]회원정보 수정후의 이메일 올바르지 않습니다.");
        softAssert.assertEquals(afterInfo.get("phone"), "08011112222", "[FAIL]회원정보 수정후의 전화번호가 올바르지 않습니다.");

        softAssert.assertAll();
    }

    @Test(testName = "chagepasswordFlow test")
    public void ChangePasswordFlowTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.clickChangePasswordButton();
        ChangePasswordPage ChangePasswordPage = new ChangePasswordPage(driver);
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);


        //잘못된 비밀번호변경
        //1.현재비밀번호오류
        ChangePasswordPage.changePassword(config.getProperty("wrongpwd"), config.getProperty("testpwd"), config.getProperty("testpwd"));
        ChangePasswordPage.clickSaveButton();
        softAssert.assertEquals(ChangePasswordPage.alertGetText(), AppMessages.exiPasswordAlertMsg,
                "[FAIL]비밀번호 변경시, 기존비밀번호 불일치의 Alert메세지가 올바르지 않습니다.");
        ChangePasswordPage.alertAccept();
        ChangePasswordPage.clickClearButton();

        //2.새비밀번호불일치
        ChangePasswordPage.changePassword(config.getPassword(), config.getProperty("testpwd"), config.getProperty("mismatchpwd"));
        ChangePasswordPage.clickSaveButton();
        softAssert.assertEquals(ChangePasswordPage.alertGetText(), AppMessages.chagePasswordFailAlertMsg,
                "[FAIL]비밀번호 변경시, 새비밀번호 불일치의 Alert메세지가 올바르지 않습니다.");
        ChangePasswordPage.alertAccept();
        ChangePasswordPage.clickClearButton();

        //3.올바른 비밀번호변경
        ChangePasswordPage.changePassword(config.getPassword(), config.getProperty("testpwd"), config.getProperty("testpwd"));
        ChangePasswordPage.clickSaveButton();
        softAssert.assertEquals(ChangePasswordPage.alertGetText(), AppMessages.chagePasswordSuccessAlertMsg,
                "[FAIL]비밀번호 변경후 Alert메세지가 올바르지 않습니다.");
        ChangePasswordPage.alertAccept();

        softAssert.assertAll();
    }

    @Test(testName = "Myinfo profile updateFlow test")
    public void MyinfoProfileUpdateFlowTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.clickModifyButton();
        MyinfoupdatePage MyinfoupdatePage = new MyinfoupdatePage(driver);
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        String befoeSrc = MyinfoupdatePage.getProfileImageSrc();
        MyinfoupdatePage.uploadProfileImage();

        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        MyinfoupdatePage.clickSaveButton();
        MyinfoupdatePage.alertAccept();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(By.id("profileImage"), "src", befoeSrc)));
        } catch (TimeoutException e) {
            System.out.println("⚠️ 타임아웃 발생: 이미지가 바뀌지 않았습니다. 현재 SRC: " + MyinfoupdatePage.getProfileImageSrc());
            throw e; }

        String afterSrc = MyinfoupdatePage.getProfileImageSrc();
        softAssert.assertNotEquals(befoeSrc, afterSrc,"[FAIL]회원정보 프로필사진이 업데이트되지 않고 이전과 동일합니다.");
        softAssert.assertEquals(afterSrc.split("_")[1],config.getProperty("profileImagePath").split("/")[4],
                "[FAIL]회원정보의 업데이트된 프로필사진의 파일명이 올바르지 않습니다.");

        softAssert.assertAll();
    }


    @AfterMethod
    public void cleanupAfterTest(Method method) {
        String methodName = method.getName();
        System.out.println("테스트 종료 후 원복 프로세스 시작: " + methodName);

        switch (methodName) {
            case "MyinfoUpdateFlowTest":
                performUserinfoBackup();
                break;

            case "ChangePasswordFlowTest":
                performRestorePassword();
                break;

            case "MyinfoProfileUpdateFlowTest":
                performProfileImage();
                break;

            default:
                break;
        }
        System.out.println("원복 프로세스 완료: " + methodName);

        myInfopageLogout();
    }

    private void performUserinfoBackup() {
        if (beforeInfo != null) {
            MyinfoPage myinfoPage = new MyinfoPage(driver);
            myinfoPage.clickModifyButton();

            MyinfoupdatePage updatePage = new MyinfoupdatePage(driver);
            updatePage.chageUserinfo01(backupInfo.get("backupEmail"), backupInfo.get("backupPhone"));
            updatePage.chageUserinfo02(
                    backupInfo.get("backupPostcode"),
                    backupInfo.get("backupAddress"),
                    backupInfo.get("backupDetailAddress"),
                    backupInfo.get("backupExtraAddress")
            );
            updatePage.clickSaveButton();
            updatePage.alertAccept();
        }
    }

    private void performRestorePassword() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.clickChangePasswordButton();

        ChangePasswordPage changePasswordPage = new ChangePasswordPage(driver);
        changePasswordPage.changePassword(config.getProperty("testpwd"), config.getPassword(), config.getPassword());
        changePasswordPage.clickSaveButton();

        changePasswordPage.alertAccept();
    }

    private void performProfileImage() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.clickModifyButton();
        MyinfoupdatePage MyinfoupdatePage = new MyinfoupdatePage(driver);

        String befoeSrc = MyinfoupdatePage.getProfileImageSrc();
        MyinfoupdatePage.backupProfileImage();

        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        MyinfoupdatePage.clickSaveButton();
        MyinfoupdatePage.alertAccept();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(By.id("profileImage"), "src", befoeSrc)));
        } catch (TimeoutException e) {
            System.out.println("⚠️ 타임아웃 발생: 이미지가 바뀌지 않았습니다. 현재 SRC: " + MyinfoupdatePage.getProfileImageSrc());
            throw e; }

        System.out.println("프로필 이미지 원복 완료.");
    }


    private void myInfopageLogout() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.navi.logout();
    }
}






