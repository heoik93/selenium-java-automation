package com.project.functional.mypage;

import com.project.base.BaseTest;
import com.project.constants.AppMessages;
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

    @Test(testName = "Myinfo updateFlow test")
    public void MyinfoUpdateFlowTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.waitForVisible(myinfoPage.myinfoTab);
        beforeInfo = myinfoPage.getAllUserInfo();

        myinfoPage.clickModifyButton();
        MyinfoupdatePage MyinfoupdatePage = new MyinfoupdatePage(driver);

        backupInfo = MyinfoupdatePage.getAllUserInfo();

        //수정전 정보 일치확인
        Assert.assertEquals(beforeInfo.get("userId"), backupInfo.get("userId"), "조회 페이지와 수정 페이지의 아이디가 불일치합니다.");
        Assert.assertEquals(beforeInfo.get("email"), backupInfo.get("email"), "조회 페이지와 수정 페이지의 이메일이 불일치합니다.");
        Assert.assertEquals(beforeInfo.get("phone"), backupInfo.get("phone"), "조회 페이지와 수정 페이지의 전화번호가 불일치합니다.");
        //버그수정후 활성화예정
        // Assert.assertEquals(beforeInfo.get("address"), backupInfo.get("address"), "조회 페이지와 수정 페이지의 주소가 불일치합니다.");

        //비활성 필드 확인
        Assert.assertFalse(MyinfoupdatePage.isEnableuserIdField(), "아이디 필드가 활성화 되어 있습니다.");

        //유저정보 수정 및 저장
        MyinfoupdatePage.chageUserinfo01("testemail@test.com", "08011112222");
        //버그수정후 활성화예정
        //MyinfoupdatePage.chageUserinfo02("12345", "테스트주소", "상세주소", "참고항목");
        MyinfoupdatePage.clickSaveButton();
        MyinfoupdatePage.alertAccept();

        //수정된 정보 확인
        MyinfoPage resultPage = new MyinfoPage(driver);
        Map<String, String> afterInfo = resultPage.getAllUserInfo();

        //수정후 정보 일치확인
        Assert.assertEquals(afterInfo.get("email"), "testemail@test.com");
        Assert.assertEquals(afterInfo.get("phone"), "08011112222");
        //버그수정후 활성화예정
        //Assert.assertEquals(afterInfo.get("address"), "12345_테스트주소_상세주소_참고항목");
    }

    @Test(testName = "chagepasswordFlow test")
    public void ChangePasswordFlowTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.clickChangePasswordButton();
        ChangePasswordPage ChangePasswordPage = new ChangePasswordPage(driver);


        //잘못된 비밀번호변경
        //1.현재비밀번호오류
        ChangePasswordPage.changePassword(config.getProperty("wrongpwd"), config.getProperty("testpwd"), config.getProperty("testpwd"));
        ChangePasswordPage.clickSaveButton();
        Assert.assertEquals(ChangePasswordPage.alertGetText(), AppMessages.exiPasswordAlertMsg);
        ChangePasswordPage.alertAccept();
        ChangePasswordPage.clickClearButton();

        //2.새비밀번호불일치
        ChangePasswordPage.changePassword(config.getPassword(), config.getProperty("testpwd"), config.getProperty("mismatchpwd"));
        ChangePasswordPage.clickSaveButton();
        Assert.assertEquals(ChangePasswordPage.alertGetText(), AppMessages.chagePasswordFailAlertMsg);
        ChangePasswordPage.alertAccept();
        ChangePasswordPage.clickClearButton();

        //3.올바른 비밀번호변경
        ChangePasswordPage.changePassword(config.getPassword(), config.getProperty("testpwd"), config.getProperty("testpwd"));
        ChangePasswordPage.clickSaveButton();
        Assert.assertEquals(ChangePasswordPage.alertGetText(), AppMessages.chagePasswordSuccessAlertMsg);
        ChangePasswordPage.alertAccept();
    }

    @Test(testName = "Myinfo profile updateFlow test")
    public void MyinfoProfileUpdateFlowTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.clickModifyButton();
        MyinfoupdatePage MyinfoupdatePage = new MyinfoupdatePage(driver);

        String befoeSrc = MyinfoupdatePage.getProfileImageSrc();

        MyinfoupdatePage.uploadProfileImage();
        MyinfoupdatePage.clickSaveButton();
        MyinfoupdatePage.alertAccept();

        String afterSrc = MyinfoupdatePage.getProfileImageSrc();
        Assert.assertNotEquals(befoeSrc, afterSrc);
        Assert.assertEquals(afterSrc.split("_")[1],config.getProperty("profileImagePath").split("/")[4]);
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
            /*버그수정후 활성화예정
            updatePage.chageUserinfo02(
                    backupInfo.get("backupPostcode"),
                    backupInfo.get("backupAddress"),
                    backupInfo.get("backupDetailAddress"),
                    backupInfo.get("backupExtraAddress")
            );*/
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

        MyinfoupdatePage.backupProfileImage();
        System.out.println("프로필 이미지 원복 완료.");

        MyinfoupdatePage.clickSaveButton();
        MyinfoupdatePage.alertAccept();
    }


    private void myInfopageLogout() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.navi.logout();

    }
}






