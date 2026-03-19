package com.project.ui.mypage;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.myinfo.AdminUseHistoryPage;
import com.project.utils.ScreenshotSoftAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AdminUseHistoryPageTest extends BaseTest {

    @BeforeMethod
    public void setupLogin() {
        loginAsAdminUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.gotoUseHistoryPage();
    }

    @Test(testName = "AdminUseHistoryPage Text Test")
    public void adminUseHistoryPage_TextTest(){
        AdminUseHistoryPage adminUseHistoryPage = new AdminUseHistoryPage(driver);
        adminUseHistoryPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        //탭
        String myInfoTab = adminUseHistoryPage.getLabel(AdminUseHistoryPage.AdminUseHistoryPageLabel.MYINFOTAB);
        softAssert.assertEquals(myInfoTab, PageLabels.adminUserPage_userInfoTab);

        String useHistoryTab = adminUseHistoryPage.getLabel(AdminUseHistoryPage.AdminUseHistoryPageLabel.USEHISTORYTAB);
        softAssert.assertEquals(useHistoryTab,PageLabels.adminUserPage_useHistoryTab);

        //라벨
        String table_OderNumberLabel = adminUseHistoryPage.getLabel(AdminUseHistoryPage.AdminUseHistoryPageLabel.TABLE_ODERNUMBERLABEL);
        softAssert.assertEquals(table_OderNumberLabel,PageLabels.adminUseHistoryPage_table_OderNumberLabel);

        String table_UserLabel = adminUseHistoryPage.getLabel(AdminUseHistoryPage.AdminUseHistoryPageLabel.TABLE_USERLABEL);
        softAssert.assertEquals(table_UserLabel,PageLabels.adminUseHistoryPage_table_UserLabel);

        String table_PriceLabel = adminUseHistoryPage.getLabel(AdminUseHistoryPage.AdminUseHistoryPageLabel.TABLE_PRICELABEL);
        softAssert.assertEquals(table_PriceLabel,PageLabels.adminUseHistoryPage_table_PriceLabel);

        String table_BookingDateLabel = adminUseHistoryPage.getLabel(AdminUseHistoryPage.AdminUseHistoryPageLabel.TABLE_BOOKINGDATELABEL);
        softAssert.assertEquals(table_BookingDateLabel,PageLabels.adminUseHistoryPage_table_BookingDateLabel);

        String table_RequestDateLabel = adminUseHistoryPage.getLabel(AdminUseHistoryPage.AdminUseHistoryPageLabel.TABLE_REQUESTDATELABEL);
        softAssert.assertEquals(table_RequestDateLabel,PageLabels.adminUseHistoryPage_table_RequestDateLabel);

        String table_GetDeliveryNumLabel = adminUseHistoryPage.getLabel(AdminUseHistoryPage.AdminUseHistoryPageLabel.TABLE_GETDELIVERYNUMLABEL);
        softAssert.assertEquals(table_GetDeliveryNumLabel,PageLabels.adminUseHistoryPage_table_GetDeliveryNumLabel);

        String table_SendDeliveryNumLabel = adminUseHistoryPage.getLabel(AdminUseHistoryPage.AdminUseHistoryPageLabel.TABLE_SENDDELIVERYNUMLABEL);
        softAssert.assertEquals(table_SendDeliveryNumLabel,PageLabels.adminUseHistoryPage_table_SendDeliveryNumLabel);

        String table_DetailLabel = adminUseHistoryPage.getLabel(AdminUseHistoryPage.AdminUseHistoryPageLabel.TABLE_DETAILLABEL);
        softAssert.assertEquals(table_DetailLabel,PageLabels.adminUseHistoryPage_table_DetailLabel);

        //리스트박스
        List<String> searchFilter = adminUseHistoryPage.getSelectBoxOptions("SearchFilter");
        softAssert.assertEquals(searchFilter,PageLabels.adminUseHistory_searchFilterOptions);

        List<String> deliveryCompany = adminUseHistoryPage.getSelectBoxOptions("Company");
        softAssert.assertEquals(deliveryCompany,PageLabels.adminUseHistory_DeliveryCompanyOptions);

        List<String> statusFilterOption = adminUseHistoryPage.getSelectBoxOptions("StatusFilter");
        softAssert.assertEquals(statusFilterOption,PageLabels.adminUseHistory_StatusFilterOption);

        String status_modifyButton = adminUseHistoryPage.getLabel(AdminUseHistoryPage.AdminUseHistoryPageLabel.STATUS_MODIFYBUTTON);
        softAssert.assertEquals(status_modifyButton,PageLabels.adminUseHistoryPage_status_ModifyButton);

        adminUseHistoryPage.clickModifyButton();

        List<String> modifyStatusOption = adminUseHistoryPage.getSelectBoxOptions("ModifyStatus");
        softAssert.assertEquals(modifyStatusOption,PageLabels.adminUseHistory_ModifyStatusOption);

        //버튼
        String status_cancelButton = adminUseHistoryPage.getLabel(AdminUseHistoryPage.AdminUseHistoryPageLabel.STATUS_CANCELBUTTON);
        softAssert.assertEquals(status_cancelButton,PageLabels.adminUseHistoryPage_status_CancelButton);

        String status_changeButton = adminUseHistoryPage.getLabel(AdminUseHistoryPage.AdminUseHistoryPageLabel.STATUS_CHANGEBUTTON);
        softAssert.assertEquals(status_changeButton,PageLabels.adminUseHistoryPage_status_ChangeButton);

        softAssert.assertAll();
    }

    @AfterMethod
    private void Logout() {
        AdminUseHistoryPage adminUseHistoryPage = new AdminUseHistoryPage(driver);
        adminUseHistoryPage.navi.clickLogoutLink();
    }


}
