package com.project.ui.mypage;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.myinfo.AdminUserInfoPage;
import com.project.utils.ScreenshotSoftAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AdminUserInfoPageTest extends BaseTest {

    @BeforeMethod
    public void setupLogin() {
        loginAsAdminUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToMyinfoPage();
    }

    @Test(testName = "AdminUserInfoPage Text Test")
    public void adminUserInfoPage_TextTest() {
        AdminUserInfoPage adminUserInfoPage = new AdminUserInfoPage(driver);
        adminUserInfoPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        //탭
        String myInfoTab = adminUserInfoPage.getLabel(AdminUserInfoPage.AdminUserInfoPageLabel.MYINFOTAB);
        softAssert.assertEquals(myInfoTab,PageLabels.adminUserPage_userInfoTab);

        String useHistoryTab = adminUserInfoPage.getLabel(AdminUserInfoPage.AdminUserInfoPageLabel.USEHISTORYTAB);
        softAssert.assertEquals(useHistoryTab,PageLabels.adminUserPage_useHistoryTab);

        //라벨
        String userIdLabel = adminUserInfoPage.getLabel(AdminUserInfoPage.AdminUserInfoPageLabel.USERIDLABEL);
        softAssert.assertEquals(userIdLabel,PageLabels.adminUserInfoPage_userIdLabel);

        String userNameLabel = adminUserInfoPage.getLabel(AdminUserInfoPage.AdminUserInfoPageLabel.USERNAMELABEL);
        softAssert.assertEquals(userNameLabel,PageLabels.adminUserInfoPage_userNameLabel);

        String emailLabel = adminUserInfoPage.getLabel(AdminUserInfoPage.AdminUserInfoPageLabel.EMAILLABEL);
        softAssert.assertEquals(emailLabel,PageLabels.adminUserInfoPage_emailLabel);

        String phoneLabel = adminUserInfoPage.getLabel(AdminUserInfoPage.AdminUserInfoPageLabel.PHONELABEL);
        softAssert.assertEquals(phoneLabel,PageLabels.adminUserInfoPage_phoneLabel);

        String signUpDateLabel = adminUserInfoPage.getLabel(AdminUserInfoPage.AdminUserInfoPageLabel.SIGNUPDATELABEL);
        softAssert.assertEquals(signUpDateLabel,PageLabels.adminUserInfoPage_signUpDateLabel);

        String searchButton = adminUserInfoPage.getLabel(AdminUserInfoPage.AdminUserInfoPageLabel.SEARCH_BUTTON);
        softAssert.assertEquals(searchButton,PageLabels.adminUserInfoPage_searchButton);

        String deleteButton = adminUserInfoPage.getLabel(AdminUserInfoPage.AdminUserInfoPageLabel.DELETEBUTTON);
        softAssert.assertEquals(deleteButton,PageLabels.adminUserInfoPage_deleteButton);

        String changeButton = adminUserInfoPage.getChangeButtonText();
        softAssert.assertEquals(changeButton,PageLabels.adminUserInfoPage_userTypeChangeButton);

        //리스트박스
        List<String> userTypeFilter = adminUserInfoPage.getAllOptions_userTypeFilter();
        softAssert.assertEquals(userTypeFilter,PageLabels.adminUserInfo_userTypeFilter);

        List<String> searchFilter = adminUserInfoPage.getAllOptions_searchFilter();
        softAssert.assertEquals(searchFilter,PageLabels.adminUserInfo_SearchFilter);

        softAssert.assertAll();
    }

    @AfterMethod
    private void Logout() {
        AdminUserInfoPage adminUserInfoPage = new AdminUserInfoPage(driver);
        adminUserInfoPage.navi.clickLogoutLink();
    }

}
