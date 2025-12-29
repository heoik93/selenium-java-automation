package com.project.login;

import com.project.base.BaseTest;
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

    @Test(testName = "Myinfo updateFlow test", groups = {"infoUpdate"})
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
        MyinfoupdatePage.infoUpdateAlertAccept();

        //수정된 정보 확인
        MyinfoPage resultPage = new MyinfoPage(driver);
        Map<String, String> afterInfo = resultPage.getAllUserInfo();

        //수정후 정보 일치확인
        Assert.assertEquals(afterInfo.get("email"), "testemail@test.com");
        Assert.assertEquals(afterInfo.get("phone"), "08011112222");
        //버그수정후 활성화예정
        //Assert.assertEquals(afterInfo.get("address"), "12345_테스트주소_상세주소_참고항목");
    }

    @Test(testName = "chagepasswordFlow test", groups = {"changePassword"})
    public void ChangePasswordFlowTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.clickChangePasswordButton();
        ChangePasswordPage ChangePasswordPage = new ChangePasswordPage(driver);


        //잘못된 비밀번호변경
        //1.현재비밀번호오류
        ChangePasswordPage.changePassword("wrongpassword","testpwd123!","testpwd123!");
        ChangePasswordPage.clickSaveButton();
        Assert.assertEquals(ChangePasswordPage.alertGetText(),"기존 비밀번호가 올바르지 않습니다.");
        ChangePasswordPage.alertAccept();
        ChangePasswordPage.clickClearButton();

        //2.새비밀번호불일치
        ChangePasswordPage.changePassword(config.getPassword(),"testpwd123!","mismatch123!");
        ChangePasswordPage.clickSaveButton();
        Assert.assertEquals(ChangePasswordPage.alertGetText(),"비밀번호를 확인 하세요!");
        ChangePasswordPage.alertAccept();
        ChangePasswordPage.clickClearButton();

        //3.올바른 비밀번호변경
        ChangePasswordPage.changePassword(config.getPassword(),"testpwd123!","testpwd123!");
        ChangePasswordPage.clickSaveButton();
        Assert.assertEquals(ChangePasswordPage.alertGetText(),"비밀번호가 성공적으로 변경되었습니다.");
        ChangePasswordPage.alertAccept();
    }


    @Test(testName = "clickModifyButton test")
    public void clickModifyButtonTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.clickModifyButton();

        MyinfoupdatePage MyinfoupdatePage = new MyinfoupdatePage(driver);
        String currentUrl = MyinfoupdatePage.getCurrentUrl();
        String PageTittle = MyinfoupdatePage.getPageTitle();

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

        Assert.assertEquals(currentUrl, config.ChangePasswordPageURL());
        Assert.assertEquals(PageTittle, "비밀번호 수정");
    }

    @Test(testName = "camgePassword clearButton test")
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

    /*회원탈퇴가 아직 구현이 되지 않기에 추후코딩 예정
    @Test(testName = "clickWithdrawButton test")
    public void clickWithdrawButtonTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        String userId = myinfoPage.getUserId();
        myinfoPage.clickWithdrawButton();

        String alertText = myinfoPage.WithdrawAlertgetText();

        Assert.assertEquals(alertText, userId+"님 탈퇴 하시겠습니까?");
        myinfoPage.WithdrawAlertAccept();
    }*/

    //프로필업데이트 테스트 추가하기


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

            default:
                System.out.println("매칭되는 원복 로직이 없습니다.");
                break;
        }
        System.out.println("원복 프로세스 완료: " + methodName);

        myInfopageLogout();
    }

    private void performUserinfoBackup() {
        if (beforeInfo != null) {
            System.out.println("데이터 원복 개시");
            handlePotentialAlert(3); // 잔여 알림창 처리

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

            handlePotentialAlert(5); // 저장 알림창 처리
            System.out.println("데이터 원복 완료");
        }
    }

    private void performRestorePassword() {
        System.out.println("비밀번호 원복 개시");
        handlePotentialAlert(3); // 혹시 모를 잔여 알림창 처리

        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.clickChangePasswordButton();

        ChangePasswordPage changePasswordPage = new ChangePasswordPage(driver);
        changePasswordPage.changePassword("testpwd123!", config.getPassword(), config.getPassword());
        changePasswordPage.clickSaveButton();

        handlePotentialAlert(5); // 변경 완료 알림창 처리
        System.out.println("비밀번호 원복 완료");
    }

    private void myInfopageLogout() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.navi.clickLogoutLink();
        System.out.println("로그아웃 완료");
    }

        private void handlePotentialAlert(int timeoutSeconds) {
        try {
            Alert alert = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                    .until(ExpectedConditions.alertIsPresent());
            alert.accept();
            System.out.println("알림창 처리 완료");
        } catch (Exception e) {
            System.out.println("처리할 알림창이 없습니다.");
        }
    }


    }





