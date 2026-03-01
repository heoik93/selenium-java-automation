package com.project.functional.mypage;

import com.project.base.BaseTest;
import com.project.constants.AppMessages;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.myinfo.AdminUseHistoryPage;
import com.project.page.myinfo.MyinfoPage;
import com.project.page.myinfo.OderDetailPage;
import com.project.page.myinfo.UseHistoryPage;
import com.project.utils.ScreenshotSoftAssert;
import config.ConfigReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Map;

public class OderDetailTest extends BaseTest {

    private int[] lastOrderInfo;

    @BeforeMethod
    public void setupLogin() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.gotoUseHistoryPage();
    }

    @Test(testName = "OderDetailPage Refund Button Test", groups = "RefundTest")
    public void oderDetailPage_RefundButtonTest() {
        UseHistoryPage useHistoryPage = new UseHistoryPage(driver);
        useHistoryPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        String OderStatus_Before = "결제완료";
        lastOrderInfo = useHistoryPage.foundOrderStatus(OderStatus_Before);
        OderDetailPage oderDetailPage = new OderDetailPage(driver);

        if (lastOrderInfo[0] == -1) {
            softAssert.fail("모든 페이지를 확인했으나 [" + OderStatus_Before + "] 상태의 주문을 찾을 수 없습니다.");
            softAssert.assertAll();
            return;
        }

        int targetPage = lastOrderInfo[0];
        int targetIndex = lastOrderInfo[1];

        oderDetailPage.clickRefundButton();

        String refundAlertMsg_1 =  oderDetailPage.alertGetText();
        softAssert.assertEquals(refundAlertMsg_1, AppMessages.oderDetailPage_Refund_AlertMsg_1,"[FAIL]환불요청 확인 메세지의 텍스트가 올바르지 않습니다.");
        oderDetailPage.alertAccept();

        String refundAlertMsg_2 =  oderDetailPage.alertGetText();
        softAssert.assertEquals(refundAlertMsg_2, AppMessages.oderDetailPage_Refund_AlertMsg_2,"[FAIL]환불요청 완료 메세지의 텍스트가 올바르지 않습니다.");
        oderDetailPage.alertAccept();

        UseHistoryPage useHistoryPage_afterRefund = new UseHistoryPage(driver);
        useHistoryPage_afterRefund.waitForPageLoad();

        useHistoryPage_afterRefund.movePage(targetPage);
        String OderStatus_After = "환불완료";
        softAssert.assertTrue(useHistoryPage_afterRefund.checkTargetStatus(targetIndex,OderStatus_After),"[FAIL]해당 리스트의 처리상태가 '환불완료'로 갱신되지 않았습니다");

        softAssert.assertAll();
    }

    @Test(testName = "OderDetailPage DeliveryCheck/List Button Test", groups = "DefaultTest")
    public void oderDetailPage_DeliveryCheckList_ButtonTest(){
        UseHistoryPage useHistoryPage = new UseHistoryPage(driver);
        useHistoryPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        //배송조회버튼
        useHistoryPage.inToTargetOderDetail(config.getProperty("OderDetail_Test_OderNum_1"));
        OderDetailPage oderDetailPage = new OderDetailPage(driver);
        oderDetailPage.waitForPageLoad();
        Map<String,String> URL = oderDetailPage.getDeliveryTrackingInfo("Retrieve");
        String actualURL =URL.get("url");
        String actualTitle =URL.get("title");

        softAssert.assertTrue(actualURL.contains(config.getProperty("DeliveryTrackerURL")), "[FAIL]수거조회 URL을 정상적으로 불러오지 못했습니다.");
        softAssert.assertTrue(actualTitle.contains(PageLabels.DeliveryTrackerTitle), "[FAIL]수거조회 페이지 타이틀을 정상적으로 불러오지 못했습니다.");


        Map<String,String> URL_2 = oderDetailPage.getDeliveryTrackingInfo("Return");
        String actualURL_2 =URL_2.get("url");
        String actualTitle_2 =URL_2.get("title");

        softAssert.assertTrue(actualURL_2.contains(config.getProperty("DeliveryTrackerURL")), "[FAIL]반환조회 URL을 정상적으로 불러오지 못했습니다.");
        softAssert.assertTrue(actualTitle_2.contains(PageLabels.DeliveryTrackerTitle), "[FAIL]반환조회 페이지 타이틀을 정상적으로 불러오지 못했습니다.");

        //목록버튼
        oderDetailPage.clickListButton();
        UseHistoryPage useHistoryPage_2nd = new UseHistoryPage(driver);
        useHistoryPage_2nd.waitForPageLoad();
        String CurrentUrl = useHistoryPage_2nd.getCurrentUrl();
        String PageTitle = useHistoryPage_2nd.getPageTitle();

        softAssert.assertEquals(CurrentUrl, config.getProperty("UseHistoryPageURL"), "[FAIL]목록버튼을 클릭후의 URL이 잘못되었습니다.");
        softAssert.assertEquals(PageTitle, PageLabels.useHistoryPageTittle, "[FAIL]목록버튼을 클릭후의 페이지 타이틀이 잘못되었습니다."); //현재DF

        softAssert.assertAll();
    }

    @Test(testName = "OderDetailPage UserDataLink Test", groups = "DefaultTest")
    public void oderDetailPage_UserDataLinkTest(){
        UseHistoryPage useHistoryPage = new UseHistoryPage(driver);
        useHistoryPage.waitForPageLoad();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        useHistoryPage.clickOderDetailButton();

        OderDetailPage oderDetailPage = new OderDetailPage(driver);
        oderDetailPage.waitForPageLoad();

        String oderDetailPage_UserId = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.USERINFO_ID);
        String oderDetailPage_UserPhone = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.USERINFO_PHONE);
        String oderDetailPage_UserAddress = oderDetailPage.getLabel(OderDetailPage.OderDetailPageLabel.USERINFO_ADDRESS);

        oderDetailPage.navi.goToMyinfoPage();
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.waitForPageLoad();

        Map<String, String> myInfoData = myinfoPage.getAllUserInfo();
        String myInfoPage_UserId = myInfoData.get("userId");
        String myInfoPage_UserPhone = myInfoData.get("phone");
        String myInfoPage_UserAddress = myInfoData.get("address");

        softAssert.assertEquals(oderDetailPage_UserId,myInfoPage_UserId,"[FAIL]주문상세의 유저ID가 회원정보와 다릅니다.");
        softAssert.assertEquals(oderDetailPage_UserPhone,myInfoPage_UserPhone,"[FAIL]주문상세의 전화번호가 회원정보와 다릅니다.");
        softAssert.assertEquals(oderDetailPage_UserAddress,myInfoPage_UserAddress,"[FAIL]주문상세의 주소가 회원정보와 다릅니다.");

        softAssert.assertAll();
    }

    @AfterMethod(onlyForGroups = "RefundTest")
    private void Logout_RecoverData() {
        OderDetailPage oderDetailPage = new OderDetailPage(driver);
        oderDetailPage.navi.clickLogoutLink();

        HomePage homePage = new HomePage(driver);
        homePage.waitForPageLoad();

        loginAsAdminUser();

        HomePage afterLoginPage = new HomePage(driver);
        afterLoginPage.waitForPageLoad();
        afterLoginPage.navi.gotoUseHistoryPage();

        AdminUseHistoryPage adminUseHistoryPage = new AdminUseHistoryPage(driver);
        String targetOderNum = String.valueOf(lastOrderInfo[2]);
        adminUseHistoryPage.searchKeyword(targetOderNum);

        AdminUseHistoryPage adminUseHistoryPage_search = new AdminUseHistoryPage(driver);
        adminUseHistoryPage_search.waitForPageLoad();

        int targetOder = adminUseHistoryPage_search.checkSearchResult();
        if (targetOder==1) {
            adminUseHistoryPage_search.RecoverStatus();
            adminUseHistoryPage_search.alertAccept();
            adminUseHistoryPage_search.alertAccept();
            System.out.println("[INFO] 해당 주문번호의 상태를 '결제완료'로 원복완료하였습니다.");
        }
        if(targetOder==0||targetOder>1){
            System.out.println("[FAIL] 해당 주문번호의 리스트가 "+targetOder+"건 입니다.");
        }

        adminUseHistoryPage_search.navi.clickLogoutLink();
    }

    @AfterMethod(onlyForGroups = "DefaultTest")
    private void Logout() {
        OderDetailPage oderDetailPage = new OderDetailPage(driver);
        oderDetailPage.navi.clickLogoutLink();
    }
}
