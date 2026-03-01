package com.project.ui.mypage;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.myinfo.OderDetailPage;
import com.project.page.myinfo.UseHistoryPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.project.utils.ScreenshotSoftAssert;

public class OderDetailPageTest extends BaseTest {

    @BeforeMethod
    public void setupLogin() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.gotoUseHistoryPage();
    }

    @Test(testName = "OderDetailPage LabelText Test")
    public void oderDetailPage_LabelTextTest() {
        UseHistoryPage useHistoryPage = new UseHistoryPage(driver);
        useHistoryPage.waitForPageLoad();
        useHistoryPage.clickOderDetailButton();

        OderDetailPage oderDetailPage = new OderDetailPage(driver);
        oderDetailPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        String myInfoTab = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.MYINFOTAB);
        softAssert.assertEquals(myInfoTab, PageLabels.useHistoryPage_MyInfoTabLabel);

        String useHistoryTab = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.USEHISTORYTAB);
        softAssert.assertEquals(useHistoryTab, PageLabels.useHistoryPage_UseHistoryTabLabel);

        String userInfo_Title = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.USERINFO_TITLE);
        softAssert.assertEquals(userInfo_Title, PageLabels.oderDetailPage_userInfo_Title);

        String userInfo_IdLabel = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.USERINFO_IDLABEL);
        softAssert.assertEquals(userInfo_IdLabel, PageLabels.oderDetailPage_userInfo_IdLabel);

        String userInfo_NameLabel = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.USERINFO_NAMELABEL);
        softAssert.assertEquals(userInfo_NameLabel, PageLabels.oderDetailPage_userInfo_NameLabel);

        String userInfo_PhoneLabel = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.USERINFO_PHONELABEL);
        softAssert.assertEquals(userInfo_PhoneLabel, PageLabels.oderDetailPage_userInfo_PhoneLabel);

        String userInfo_AddressLabel = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.USERINFO_ADDRESSLABEL);
        softAssert.assertEquals(userInfo_AddressLabel, PageLabels.oderDetailPage_userInfo_AddressLabel);

        String oderInfo_Title = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.ODERINFO_TITLE);
        softAssert.assertEquals(oderInfo_Title, PageLabels.oderDetailPage_oderInfo_Title);

        String oderInfo_NumberLabel = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.ODERINFO_NUMBERLABEL);
        softAssert.assertEquals(oderInfo_NumberLabel, PageLabels.oderDetailPage_oderInfo_NumberLabel);

        String oderInfo_statusLabel = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.ODERINFO_STATUSLABEL);
        softAssert.assertEquals(oderInfo_statusLabel, PageLabels.oderDetailPage_oderInfo_statusLabel);

        String oderInfo_bookingDateLabel = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.ODERINFO_BOOKINGDATELABEL);
        softAssert.assertEquals(oderInfo_bookingDateLabel, PageLabels.oderDetailPage_oderInfo_bookingDateLabel);

        String oderInfo_retrieveDateLabel = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.ODERINFO_RETRIEVEDATELABEL);
        softAssert.assertEquals(oderInfo_retrieveDateLabel, PageLabels.oderDetailPage_oderInfo_retrieveDateLabel);

        String oderInfo_addressLabel = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.ODERINFO_ADDRESSLABEL);
        softAssert.assertEquals(oderInfo_addressLabel, PageLabels.oderDetailPage_oderInfo_addressLabel);

        String oderInfo_requestLabel = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.ODERINFO_REQUESTLABEL);
        softAssert.assertEquals(oderInfo_requestLabel, PageLabels.oderDetailPage_oderInfo_requestLabel);

        String oderInfo_retrieveNumberLabel = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.ODERINFO_RETRIEVENUMBERLABEL);
        softAssert.assertEquals(oderInfo_retrieveNumberLabel, PageLabels.oderDetailPage_oderInfo_retrieveNumberLabel);

        String oderInfo_returnNumberLabel = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.ODERINFO_RETURNNUMBERLABEL);
        softAssert.assertEquals(oderInfo_returnNumberLabel, PageLabels.oderDetailPage_oderInfo_returnNumberLabel);

        String oderProduct_Title = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.ODERPRODUCT_TITLE);
        softAssert.assertEquals(oderProduct_Title, PageLabels.oderDetailPage_oderProduct_Title);

        String oderProduct_No = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.ODERPRODUCT_NO);
        softAssert.assertEquals(oderProduct_No, PageLabels.oderDetailPage_oderProduct_No);

        String oderProduct_Item = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.ODERPRODUCT_ITEM);
        softAssert.assertEquals(oderProduct_Item, PageLabels.oderDetailPage_oderProduct_Item);

        String oderProduct_Price = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.ODERPRODUCT_PRICE);
        softAssert.assertEquals(oderProduct_Price, PageLabels.oderDetailPage_oderProduct_Price);

        String oderProduct_Number = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.ODERPRODUCT_NUMBER);
        softAssert.assertEquals(oderProduct_Number, PageLabels.oderDetailPage_oderProduct_Number);

        softAssert.assertAll();
    }

    @Test(testName = "OderDetailPage Button Text Test")
    public void oderDetailPage_ButtonTextTest() {
        UseHistoryPage useHistoryPage = new UseHistoryPage(driver);
        useHistoryPage.waitForPageLoad();

        WebElement TestOder = useHistoryPage.findOrderDetailByOrderNumber("3");
        if(TestOder==null){
            Assert.fail("[FAIL]테스트 준비가 되지 않았습니다. 송장번호를 발급한 데이터를 작성하세요");
        }

        useHistoryPage.clickTestOderList(TestOder);

        OderDetailPage oderDetailPage = new OderDetailPage(driver);
        oderDetailPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        String oderDetailPage_oderInfo_retrieveCheckButton = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.ODERINFO_RETRIEVECHECKBUTTON);
        softAssert.assertEquals(oderDetailPage_oderInfo_retrieveCheckButton, PageLabels.oderDetailPage_oderInfo_retrieveCheckButton);

        String oderDetailPage_oderInfo_returnCheckButton = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.ODERINFO_RETURNCHECKBUTTON);
        softAssert.assertEquals(oderDetailPage_oderInfo_returnCheckButton, PageLabels.oderDetailPage_oderInfo_returnCheckButton);

        String oderDetailPage_listButton = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.LISTBUTTON);
        softAssert.assertEquals(oderDetailPage_listButton, PageLabels.oderDetailPage_listButton);

        softAssert.assertAll();
    }

    @AfterMethod
    private void Logout() {
        OderDetailPage oderDetailPage = new OderDetailPage(driver);
        oderDetailPage.navi.clickLogoutLink();
    }
}