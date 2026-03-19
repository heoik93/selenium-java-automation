package com.project.functional.mypage;

import com.project.base.BaseTest;
import com.project.constants.AppMessages;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.myinfo.AdminUseHistoryPage;
import com.project.page.myinfo.AdminUserInfoPage;
import com.project.utils.ScreenshotSoftAssert;
import config.ConfigReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminUserInfoTest extends BaseTest {

    @BeforeMethod
    public void setupLogin() {
        loginAsAdminUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToMyinfoPage();
    }

    @Test(testName = "AdminUserInfoPage ActiveTab Test")
    public void adminUserInfoPage_ActiveTabTest() {
        AdminUserInfoPage adminUserInfoPage = new AdminUserInfoPage(driver);
        adminUserInfoPage.waitForPageLoad();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        softAssert.assertTrue(adminUserInfoPage.isMyInfoTabActive(),"[FAIL]고객관리탭이 활성화 되어있지 않습니다.");

        softAssert.assertAll();
    }

    @Test(testName = "AdminUserInfoPage Tab Test")
    public void adminUserInfoPage_TabTest() {
        AdminUserInfoPage adminUserInfoPage = new AdminUserInfoPage(driver);
        adminUserInfoPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        adminUserInfoPage.clickMyInfoTab();
        AdminUserInfoPage adminUserInfoPage_2nd = new AdminUserInfoPage(driver);
        adminUserInfoPage_2nd.waitForPageLoad();

        softAssert.assertEquals(adminUserInfoPage_2nd.getPageTitle(), PageLabels.adminUserInfoPageTitle,
                "[FAIL]회원관리페이지에서 회원관리탭 클릭후의 페이지타이틀이 올바르지 않습니다.");
        softAssert.assertEquals(adminUserInfoPage_2nd.getCurrentUrl(),config.getProperty("MyInfoPageURL"),
                "[FAIL]회원관리페이지에서 회원관리탭 클릭후의 URL이 올바르지 않습니다.");

        adminUserInfoPage_2nd.clickUseHistoryTab();
        AdminUseHistoryPage adminUseHistoryPage = new AdminUseHistoryPage(driver);
        adminUseHistoryPage.waitForPageLoad();

        softAssert.assertEquals(adminUseHistoryPage.getPageTitle(), PageLabels.adminUseHistoryPageTitle,
                "[FAIL]회원관리페이지에서 주문관리탭 클릭후의 페이지타이틀이 올바르지 않습니다.");
        softAssert.assertEquals(adminUseHistoryPage.getCurrentUrl(),config.getProperty("UseHistoryPageURL"),
                "[FAIL]회원관리페이지에서 주문관리탭 클릭후의 URL이 올바르지 않습니다.");

        adminUseHistoryPage.clickMyInfoTab();

        softAssert.assertAll();
    }

    @Test(testName = "AdminUserInfoPage Search Test")
    public void adminUserInfoPage_SearchTest() {
        AdminUserInfoPage adminUserInfoPage = new AdminUserInfoPage(driver);
        adminUserInfoPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        //결과있음
        adminUserInfoPage.selectOption(0);
        adminUserInfoPage.searchKeyword(config.getProperty("username"));

        AdminUserInfoPage adminUserInfoPage_search1 = new AdminUserInfoPage(driver);
        adminUserInfoPage_search1.waitForPageLoad();

        int searchResult1 = adminUserInfoPage_search1.checkResult();
        softAssert.assertTrue(searchResult1>0, "[FAIL]첫번째 검색결과가 잘못되었습니다. (기대값 : 결과있음, 실제값 : 결과없음) ");

        adminUserInfoPage_search1.selectOption(1);
        adminUserInfoPage_search1.searchKeyword("테스트");

        AdminUserInfoPage adminUserInfoPage_search2 = new AdminUserInfoPage(driver);
        adminUserInfoPage_search2.waitForPageLoad();

        int searchResult2 = adminUserInfoPage_search2.checkResult();
        softAssert.assertTrue(searchResult2>0, "[FAIL]두번째 검색결과가 잘못되었습니다. (기대값 : 결과있음, 실제값 : 결과없음) ");

        //결과없음
        adminUserInfoPage_search2.selectOption(0);
        adminUserInfoPage_search2.searchKeyword("!@#$%");

        AdminUserInfoPage adminUserInfoPage_search3 = new AdminUserInfoPage(driver);
        adminUserInfoPage_search3.waitForPageLoad();

        int searchResult3 = adminUserInfoPage_search3.checkResult();
        softAssert.assertTrue(searchResult3==0, "[FAIL]세번째 검색결과가 잘못되었습니다. (기대값 : 결과없음, 실제값 : 결과있음) ");

        adminUserInfoPage_search3.selectOption(1);
        adminUserInfoPage_search3.searchKeyword("!@#$%");

        AdminUserInfoPage adminUserInfoPage_search4 = new AdminUserInfoPage(driver);
        adminUserInfoPage_search2.waitForPageLoad();

        int searchResult4 = adminUserInfoPage_search4.checkResult();
        softAssert.assertTrue(searchResult4==0, "[FAIL]네번째 검색결과가 잘못되었습니다. (기대값 : 결과없음, 실제값 : 결과있음) ");

        softAssert.assertAll();
    }

    //현재DF
    @Test(testName = "AdminUserInfoPage AllCheckButton Test")
    public void adminUserInfoPage_AllCheckButtonTest() {
        AdminUserInfoPage adminUserInfoPage = new AdminUserInfoPage(driver);
        adminUserInfoPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        adminUserInfoPage.clickAllCheckButton();

        int checkBoxCount = adminUserInfoPage.getCheckBoxCount();
        int checkedCount = adminUserInfoPage.getCheckedCheckBoxCount();

        softAssert.assertEquals(checkedCount, checkBoxCount, "[FAIL]전체 체크박스가 선택되지 않았습니다. (기대값 : " + checkBoxCount + "개 선택, 실제값 : " + checkedCount + "개 선택)");

        softAssert.assertAll();
    }

    @Test(testName = "AdminUserInfoPage UserTypeFilter Test")
    public void adminUserInfoPage_UserTypeFilterTest() {
        AdminUserInfoPage adminUserInfoPage = new AdminUserInfoPage(driver);
        adminUserInfoPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        String userType1 = adminUserInfoPage.selectUSerTypeFilter(1);
        AdminUserInfoPage adminUserInfoPage_Filter1 = new AdminUserInfoPage(driver);
        adminUserInfoPage_Filter1.waitForPageLoad();

        softAssert.assertTrue(adminUserInfoPage_Filter1.checkUserTypeFilterResult(userType1),
                "[FAIL]유저타입 필터(고객) 선택시의 결과가 올바르지 않습니다.");

        String userType2 = adminUserInfoPage.selectUSerTypeFilter(2);
        AdminUserInfoPage adminUserInfoPage_Filter2 = new AdminUserInfoPage(driver);
        adminUserInfoPage_Filter2.waitForPageLoad();

        softAssert.assertTrue(adminUserInfoPage_Filter2.checkUserTypeFilterResult(userType2),
                "[FAIL]유저타입 필터(관리자) 선택시의 결과가 올바르지 않습니다.");

        softAssert.assertAll();
    }

    @Test(testName = "AdminUserInfoPage ModifyButton Test")
    public void adminUserInfoPage_ModifyButtonTest() {
        AdminUserInfoPage adminUserInfoPage = new AdminUserInfoPage(driver);
        adminUserInfoPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        adminUserInfoPage.clickRandomModifyButton();
        softAssert.assertEquals(adminUserInfoPage.alertGetText(), AppMessages.adminUserInfoPage_Modify_NoSelectAlertMsg,
                "[FAIL]항목 미선택일때 변경버튼 클릭시의 Alert메세지가 잘못되었습니다.");
        adminUserInfoPage.alertAccept();

        String testTargetUserId = config.getProperty("anotherusername");

        adminUserInfoPage.searchKeyword(testTargetUserId);
        AdminUserInfoPage adminUserInfoPage_search = new AdminUserInfoPage(driver);
        adminUserInfoPage_search.waitForPageLoad();

        adminUserInfoPage_search.clickTargetCheckBox(testTargetUserId);
        adminUserInfoPage_search.clickTargetModifyButton(testTargetUserId);
        softAssert.assertEquals(adminUserInfoPage_search.alertGetText(), AppMessages.adminUserInfoPage_Modify_ChangeAdminAlertMsg,
                "[FAIL]유저타입을 관리자로 변경시의 Alert메세지가 잘못되었습니다.");
        adminUserInfoPage_search.alertAccept();
        softAssert.assertEquals(adminUserInfoPage_search.alertGetText(), AppMessages.adminUserInfoPage_Modify_AfterChangeAlertMsg,
                "[FAIL]유저타입 변경 완료시의 Alert메세지가 잘못되었습니다.");
        adminUserInfoPage_search.alertAccept();

        AdminUserInfoPage adminUserInfoPage_typeChange = new AdminUserInfoPage(driver);
        adminUserInfoPage_typeChange.waitForPageLoad();

        adminUserInfoPage_typeChange.clickTargetCheckBox(testTargetUserId);
        adminUserInfoPage_typeChange.clickTargetModifyButton(testTargetUserId);
        softAssert.assertEquals(adminUserInfoPage_typeChange.alertGetText(), AppMessages.adminUserInfoPage_Modify_ChangeUserAlertMsg,
                "[FAIL]유저타입을 고객으로 변경시의 Alert메세지가 잘못되었습니다.");
        adminUserInfoPage.alertAccept();
        softAssert.assertEquals(adminUserInfoPage_typeChange.alertGetText(), AppMessages.adminUserInfoPage_Modify_AfterChangeAlertMsg,
                "[FAIL]유저타입 변경 완료시의 Alert메세지가 잘못되었습니다.");
        adminUserInfoPage_typeChange.alertAccept();

        softAssert.assertAll();
    }


    @AfterMethod
    private void Logout() {
        AdminUserInfoPage adminUserInfoPage = new AdminUserInfoPage(driver);
        adminUserInfoPage.navi.clickLogoutLink();
    }
}
