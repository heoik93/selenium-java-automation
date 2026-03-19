package com.project.ui.mypage;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.myinfo.UseHistoryPage;
import com.project.utils.ScreenshotSoftAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UseHistoryPageTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void goToBooking_UseHistoryPage() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.gotoUseHistoryPage();
    }

    @Test(testName = "UseHistoryPage Text Test")
    public void useHistoryPage_TextTest() {
        UseHistoryPage useHistoryPage = new UseHistoryPage(driver);
        useHistoryPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        String myInfoTabText = useHistoryPage.getLabel(UseHistoryPage.UseHistoryPageLabel.MYINFOTAB);
        softAssert.assertEquals(myInfoTabText, PageLabels.useHistoryPage_MyInfoTabLabel);

        String useHistoryTabText = useHistoryPage.getLabel(UseHistoryPage.UseHistoryPageLabel.USEHISTORYTAB);
        softAssert.assertEquals(useHistoryTabText, PageLabels.useHistoryPage_UseHistoryTabLabel);

        String orderNumberLabelText = useHistoryPage.getLabel(UseHistoryPage.UseHistoryPageLabel.ODERNUMBERLABEL);
        softAssert.assertEquals(orderNumberLabelText, PageLabels.useHistoryPage_OrderNumberLabel);

        String orderDetailLabelText = useHistoryPage.getLabel(UseHistoryPage.UseHistoryPageLabel.ODERDETAILLABEL);
        softAssert.assertEquals(orderDetailLabelText, PageLabels.useHistoryPage_OderDetailLabel);

        String orderPriceLabelText = useHistoryPage.getLabel(UseHistoryPage.UseHistoryPageLabel.ODERPRICELABEL);
        softAssert.assertEquals(orderPriceLabelText, PageLabels.useHistoryPage_OderPriceLabel);

        String addressLabelText = useHistoryPage.getLabel(UseHistoryPage.UseHistoryPageLabel.ADDRESSLABEL);
        softAssert.assertEquals(addressLabelText,PageLabels.useHistoryPage_AddressLabel);

        String bookingDateLabelText = useHistoryPage.getLabel(UseHistoryPage.UseHistoryPageLabel.BOKKINGDATELABEL);
        softAssert.assertEquals(bookingDateLabelText,PageLabels.useHistoryPage_BookingDateLabel);

        String retrieveDateLabelText = useHistoryPage.getLabel(UseHistoryPage.UseHistoryPageLabel.RETRIEVEDATELABEL);
        softAssert.assertEquals(retrieveDateLabelText,PageLabels.useHistoryPage_RetrieveDateLabel);

        String statusLabelText = useHistoryPage.getLabel(UseHistoryPage.UseHistoryPageLabel.STATUSLABEL);
        softAssert.assertEquals(statusLabelText,PageLabels.useHistoryPage_StatusLabel);

        String reviewLabelText = useHistoryPage.getLabel(UseHistoryPage.UseHistoryPageLabel.REVIEWLABEL);
        softAssert.assertEquals(reviewLabelText, PageLabels.useHistoryPage_ReviewLabel);

        softAssert.assertAll();
    }

    @AfterMethod
    private void Logout() {
        UseHistoryPage useHistoryPage = new UseHistoryPage(driver);
        useHistoryPage.navi.clickLogoutLink();
    }
}
