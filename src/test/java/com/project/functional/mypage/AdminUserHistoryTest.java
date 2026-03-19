package com.project.functional.mypage;

import com.project.base.BaseTest;
import com.project.constants.AppMessages;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.myinfo.AdminUseHistoryPage;
import com.project.page.myinfo.AdminUserInfoPage;
import com.project.page.myinfo.OderDetailPage;
import com.project.utils.ScreenshotSoftAssert;
import config.ConfigReader;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

public class AdminUserHistoryTest extends BaseTest {

    @BeforeMethod
    public void setupLogin() {
        loginAsAdminUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.gotoUseHistoryPage();
    }

    @Test(testName = "adminUseHistoryPage ActiveTab Test")
    public void adminUseHistoryPage_ActiveTabTest() {
        AdminUseHistoryPage adminUseHistoryPage = new AdminUseHistoryPage(driver);
        adminUseHistoryPage.waitForPageLoad();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        softAssert.assertTrue(adminUseHistoryPage.isUseHistoryTabActive(),"[FAIL]주문관리탭이 활성화 되어있지 않습니다.");

        softAssert.assertAll();
    }

    @Test(testName = "adminUseHistoryPage Tab Test")
    public void adminUseHistoryPage_TabTest() {
        AdminUseHistoryPage adminUseHistoryPage = new AdminUseHistoryPage(driver);
        adminUseHistoryPage.waitForPageLoad();


        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        adminUseHistoryPage.clickUseHistoryTab();
        AdminUseHistoryPage adminUseHistoryPage_2nd = new AdminUseHistoryPage(driver);
        adminUseHistoryPage_2nd.waitForPageLoad();

        softAssert.assertEquals(adminUseHistoryPage_2nd.getPageTitle(), PageLabels.adminUseHistoryPageTitle,
                "[FAIL]주문관리페이지에서 주문관리탭 클릭후의 페이지타이틀이 올바르지 않습니다.");
        softAssert.assertEquals(adminUseHistoryPage_2nd.getCurrentUrl(),config.getProperty("UseHistoryPageURL"),
                "[FAIL]주문관리페이지에서 주문관리탭 클릭후의 URL이 올바르지 않습니다.");

        adminUseHistoryPage_2nd.clickMyInfoTab();
        AdminUserInfoPage adminUserInfoPage = new AdminUserInfoPage(driver);
        adminUserInfoPage.waitForPageLoad();

        softAssert.assertEquals(adminUserInfoPage.getPageTitle(), PageLabels.adminUserInfoPageTitle,
                "[FAIL]주문관리페이지에서 고객관리탭 클릭후의 페이지타이틀이 올바르지 않습니다.");
        softAssert.assertEquals(adminUserInfoPage.getCurrentUrl(),config.getProperty("MyInfoPageURL"),
                "[FAIL]주문관리페이지에서 고객관리탭 클릭후의 URL이 올바르지 않습니다.");

        adminUseHistoryPage.clickUseHistoryTab();

        softAssert.assertAll();
    }

    @Test(testName = "AdminUseHistoryPage Search Test")
    public void adminUseHistoryPage_SearchTest() {
        AdminUseHistoryPage adminUseHistoryPage = new AdminUseHistoryPage(driver);
        adminUseHistoryPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        //결과있음
        adminUseHistoryPage.changeSearchFilter(0);
        adminUseHistoryPage.searchKeyword("1");

        AdminUseHistoryPage adminUseHistoryPage_search1 = new AdminUseHistoryPage(driver);
        adminUseHistoryPage_search1.waitForPageLoad();

        int searchResult1 = adminUseHistoryPage_search1.checkResult();
        softAssert.assertTrue(searchResult1>0, "[FAIL]첫번째 검색결과가 잘못되었습니다. (기대값 : 결과있음, 실제값 : 결과없음) ");

        adminUseHistoryPage_search1.changeSearchFilter(1);
        adminUseHistoryPage_search1.searchKeyword(config.getProperty("username"));

        AdminUseHistoryPage adminUseHistoryPage_search2 = new AdminUseHistoryPage(driver);
        adminUseHistoryPage_search2.waitForPageLoad();

        int searchResult2 = adminUseHistoryPage_search2.checkResult();
        softAssert.assertTrue(searchResult2>0, "[FAIL]두번째 검색결과가 잘못되었습니다. (기대값 : 결과있음, 실제값 : 결과없음) ");

        //결과없음
        adminUseHistoryPage_search2.changeSearchFilter(0);
        adminUseHistoryPage_search2.searchKeyword("!@#$%");

        AdminUseHistoryPage adminUseHistoryPage_search3 = new AdminUseHistoryPage(driver);
        adminUseHistoryPage_search3.waitForPageLoad();

        int searchResult3 = adminUseHistoryPage_search3.checkResult();
        softAssert.assertTrue(searchResult3==0, "[FAIL]세번째 검색결과가 잘못되었습니다. (기대값 : 결과없음, 실제값 : 결과있음) ");

        adminUseHistoryPage_search3.changeSearchFilter(1);
        adminUseHistoryPage_search3.searchKeyword("!@#$%");

        AdminUseHistoryPage adminUseHistoryPage_search4 = new AdminUseHistoryPage(driver);
        adminUseHistoryPage_search4.waitForPageLoad();

        int searchResult4 = adminUseHistoryPage_search4.checkResult();
        softAssert.assertTrue(searchResult4==0, "[FAIL]네번째 검색결과가 잘못되었습니다. (기대값 : 결과없음, 실제값 : 결과있음) ");

        softAssert.assertAll();
    }

    @Test(testName = "AdminUseHistoryPage AllCheckButton Test")
    public void adminUseHistoryPage_AllCheckButtonTest() {
        AdminUseHistoryPage adminUseHistoryPage = new AdminUseHistoryPage(driver);
        adminUseHistoryPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        adminUseHistoryPage.clickAllCheckButton();

        int checkBoxCount = adminUseHistoryPage.getCheckBoxCount();
        int checkedCount = adminUseHistoryPage.getCheckedCheckBoxCount();

        softAssert.assertEquals(checkedCount, checkBoxCount, "[FAIL]전체 체크박스가 선택되지 않았습니다. (기대값 : " + checkBoxCount + "개 선택, 실제값 : " + checkedCount + "개 선택)");

        softAssert.assertAll();
    }

    @Test(testName = "AdminUseHistoryPage ChangeStatus Test")
    public void adminUseHistoryPage_ChangeStatusTest() {
        AdminUseHistoryPage adminUseHistoryPage = new AdminUseHistoryPage(driver);
        adminUseHistoryPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        int maxIndex = adminUseHistoryPage.getStatusOptionCount();
        for(int i = 1; i < maxIndex; i++) {
            adminUseHistoryPage.clickTarget_FirstCheckBox();
            adminUseHistoryPage.clickModifyButton();

            String status = adminUseHistoryPage.changeStatus(i);

            String expectedAlertMsg1 = AppMessages.adminUseHistoryPage_StatusChange_AlertMsg1 + status + AppMessages.adminUseHistoryPage_StatusChange_AlertMsg2;
            softAssert.assertEquals(expectedAlertMsg1, adminUseHistoryPage.alertGetText(),
                    "[FAIL]상태변경 alert 메시지1가 잘못되었습니다. (기대값 : "+expectedAlertMsg1+", 실제값 : "+adminUseHistoryPage.alertGetText()+")");
            adminUseHistoryPage.alertAccept();

            String expectedAlertMsg2 = "1" + AppMessages.adminUseHistoryPage_StatusChange_AlertMsg3;
            softAssert.assertEquals(expectedAlertMsg2, adminUseHistoryPage.alertGetText(),
                    "[FAIL]상태변경 alert 메시지3가 잘못되었습니다. (기대값 : "+expectedAlertMsg2+", 실제값 : "+adminUseHistoryPage.alertGetText()+")");
            adminUseHistoryPage.alertAccept();

            AdminUseHistoryPage adminUseHistoryPage_for = new AdminUseHistoryPage(driver);
            adminUseHistoryPage_for.waitForPageLoad();

            softAssert.assertTrue(adminUseHistoryPage.checkTargetStatus(0,status),"[FAIL]상태변경이 제대로 적용되지 않았습니다. ");
        }

        softAssert.assertAll();
    }

    @Test(testName = "AdminUseHistoryPage StatusButton Test")
    public void adminUseHistoryPage_StatusButtonTest() {
        AdminUseHistoryPage adminUseHistoryPage = new AdminUseHistoryPage(driver);
        adminUseHistoryPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        //수정버튼 클릭이전 Disable
        softAssert.assertTrue(adminUseHistoryPage.checkStatusDisabled(),"[FAIL]상태변경 버튼이 비활성화 되어있지 않습니다. ");

        //수정버튼 클릭후 버튼표시확인
        adminUseHistoryPage.clickModifyButton();
        softAssert.assertTrue(adminUseHistoryPage.checkDisplayButton_Cancel(),"[FAIL]상태변경 취소버튼이 표시되지 않습니다. ");
        softAssert.assertTrue(adminUseHistoryPage.checkDisplayButton_Change(),"[FAIL]상태변경 확인버튼이 표시되지 않습니다. ");

        //취소버튼 확인테스트
        adminUseHistoryPage.clickCancelButton();
        softAssert.assertTrue(adminUseHistoryPage.checkDisplayButton_Modify(),"[FAIL]상태변경 취소버튼 클릭후에 수정버튼이 표시되지 않습니다. ");
        softAssert.assertTrue(adminUseHistoryPage.checkStatusDisabled(),"[FAIL]상태변경 버튼이 비활성화 되어있지 않습니다. (취소버튼 클릭 이후)");

        //확인버튼 테스트
        adminUseHistoryPage.clickModifyButton();
        adminUseHistoryPage.clickChangeStatusButton();

        softAssert.assertEquals(adminUseHistoryPage.alertGetText(),AppMessages.adminUseHistoryPage_StatusChange_NoCheck_AlertMsg,
                "[FAIL]상태변경시 항목 미선택시의 Alert 메세지가 잘못되었습니다.");
        adminUseHistoryPage.alertAccept();

        adminUseHistoryPage.clickTarget_FirstCheckBox();
        adminUseHistoryPage.clickChangeStatusButton();
        softAssert.assertEquals(adminUseHistoryPage.alertGetText(),AppMessages.adminUseHistoryPage_StatusChange_NoStatus_AlertMsg,
                "[FAIL]상태변경시 상태 미선택시의 Alert 메세지가 잘못되었습니다.");
        adminUseHistoryPage.alertAccept();

        adminUseHistoryPage.changeStatusFilter(1);
        AdminUseHistoryPage adminUseHistoryPage_Filter = new AdminUseHistoryPage(driver);
        adminUseHistoryPage_Filter.waitForPageLoad();

        softAssert.assertTrue(adminUseHistoryPage_Filter.checkStatusDisabled(),"[FAIL]상태변경 버튼이 비활성화 되어있지 않습니다. (상태 필터링 이후)");
        softAssert.assertTrue(adminUseHistoryPage_Filter.checkDisplayButton_Modify(),"[FAIL]상태 필터링 이후에 수정버튼이 표시되지 않습니다. ");

        //복수인원변경 확인텐스트
        adminUseHistoryPage_Filter.clickAllCheckButton();
        int Checked = adminUseHistoryPage_Filter.getCheckedCheckBoxCount();
        adminUseHistoryPage_Filter.clickModifyButton();
        adminUseHistoryPage_Filter.changeStatus(2);
        adminUseHistoryPage_Filter.clickChangeStatusButton();
        adminUseHistoryPage_Filter.alertAccept();

        softAssert.assertEquals(adminUseHistoryPage_Filter.alertGetText(),Checked+AppMessages.adminUseHistoryPage_StatusChange_AlertMsg3,
                "[FAIL]복수인원 상태변경시의 Alert 메세지가 잘못되었습니다.");
        adminUseHistoryPage_Filter.alertAccept();


        AdminUseHistoryPage adminUseHistoryPage_AfterChange = new AdminUseHistoryPage(driver);
        adminUseHistoryPage_AfterChange.waitForPageLoad();

        //상태원복
        adminUseHistoryPage_AfterChange.changeStatusFilter(3);
        AdminUseHistoryPage adminUseHistoryPage_Recover = new AdminUseHistoryPage(driver);
        adminUseHistoryPage_Recover.waitForPageLoad();
        adminUseHistoryPage_Recover.clickAllCheckButton();
        adminUseHistoryPage_Recover.clickModifyButton();
        adminUseHistoryPage_Recover.changeStatus(1);
        adminUseHistoryPage_Recover.clickChangeStatusButton();
        adminUseHistoryPage_Recover.alertAccept();
        adminUseHistoryPage_Recover.alertAccept();
        System.out.println("[INFO] 테스트 데이터를 원복하였습니다.");

        softAssert.assertAll();
    }

    @Test(testName = "AdminUseHistoryPage StatusFilter Test")
    public void adminUseHistoryPage_StatusFilterTest() {
        AdminUseHistoryPage adminUseHistoryPage = new AdminUseHistoryPage(driver);
        adminUseHistoryPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        int maxFilterIndex = adminUseHistoryPage.getStatusFilterOptions();

        for (int i = 1; i < maxFilterIndex; i++) {
            String status = adminUseHistoryPage.changeStatusFilter(i);
            adminUseHistoryPage.waitForPageLoad();

            int filterResultCount = adminUseHistoryPage.checkSearchResult();
            System.out.println("[INFO] '" + status + "' 필터링 결과: " + filterResultCount + "건");

            if (filterResultCount > 0) {
                for (int d = 0; d < filterResultCount; d++) {
                    boolean isMatched = adminUseHistoryPage.checkTargetStatus(d, status);
                    softAssert.assertTrue(isMatched,
                            "[FAIL]필터링 오류: " + d + "번째 행의 상태가 '" + status + "'와 다릅니다.");
                }
            } else {
                System.out.println("[INFO] 해당 상태의 검색 결과가 없습니다.");
            }
        }
        softAssert.assertAll();
    }

    @Test(testName = "AdminUseHistoryPage DetailButton(DataLink) Test")
    public void adminUseHistoryPage_DetailButtonTest() {
        AdminUseHistoryPage adminUseHistoryPage = new AdminUseHistoryPage(driver);
        adminUseHistoryPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        int testData = 2;
        List<String> targetInfo = adminUseHistoryPage.getTargetInfo(testData);
        adminUseHistoryPage.clickDetailButton(testData);

        OderDetailPage oderDetailPage = new OderDetailPage(driver);
        oderDetailPage.waitForPageLoad();

        String testOderNumber = targetInfo.get(0);
        String userId = targetInfo.get(1);

        softAssert.assertEquals(oderDetailPage.getPageTitle(),PageLabels.orderDetailPageTitle,
                "[FAIL]주문관리 페이지에서 상세버튼 클릭시의 페이지 타이틀이 잘못되었습니다.");
        softAssert.assertEquals(oderDetailPage.getCurrentUrl(),config.getProperty("OrderDetailPageURL")+testOderNumber+"&orderer="+userId,
                "[FAIL]주문관리 페이지에서 상세버튼 클릭시의 URL이 잘못되었습니다.");


        List<String> detailInfo = oderDetailPage.getTargetDetail();
        softAssert.assertEquals(targetInfo,detailInfo,"[FAIL]주문관리 페이지의 정보와 주문상세 페이지의 정보가 일치하지 않습니다.");

        oderDetailPage.clickListButton();

        softAssert.assertAll();
    }

    @Test(testName = "AdminUseHistoryPage DeliveryNumButton Test")
    public void adminUseHistoryPage_DeliveryNumButtonTest() {
        AdminUseHistoryPage adminUseHistoryPage = new AdminUseHistoryPage(driver);
        adminUseHistoryPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        String TestOderNum = adminUseHistoryPage.searchTestData();
        if(TestOderNum==null){
            throw new SkipException("[SKIP] 테스트에 사용할 데이터가 없어서 테스트를 건너뜁니다.");
        }

        adminUseHistoryPage.clickUseHistoryTab();
        AdminUseHistoryPage adminUseHistoryPage_2nd = new AdminUseHistoryPage(driver);
        adminUseHistoryPage_2nd.waitForPageLoad();

        adminUseHistoryPage_2nd.searchKeyword(TestOderNum);
        AdminUseHistoryPage adminUseHistoryPage_search = new AdminUseHistoryPage(driver);
        adminUseHistoryPage_search.waitForPageLoad();

        //미선택시 버튼클릭후 alert확인
        adminUseHistoryPage_search.clickDeliveryGetButton();
        softAssert.assertEquals(adminUseHistoryPage_search.alertGetText(),AppMessages.adminUseHistoryPage_Delivery_NoCheck_AlertMsg,
                "[FAIL]수거용 송장번호 발급버튼의 항목 미선택시 Alert 메세지가 잘못되었습니다.");
        adminUseHistoryPage_search.alertAccept();
        adminUseHistoryPage_search.clickDeliverySendButton();
        softAssert.assertEquals(adminUseHistoryPage_search.alertGetText(),AppMessages.adminUseHistoryPage_Delivery_NoCheck_AlertMsg,
                "[FAIL]반환용 송장번호 발급버튼의 항목 미선택시 Alert 메세지가 잘못되었습니다.");
        adminUseHistoryPage_search.alertAccept();

        int targetIndex = adminUseHistoryPage_search.getTargetIndex(TestOderNum);
        adminUseHistoryPage_search.clickCheckButton(targetIndex);

        //반환완료로 상태변경
        adminUseHistoryPage_search.clickModifyButton();
        adminUseHistoryPage_search.changeStatus(7);
        adminUseHistoryPage_search.clickChangeStatusButton();
        adminUseHistoryPage_search.alertAccept();
        adminUseHistoryPage_search.alertAccept();

        adminUseHistoryPage_search.clickCheckButton(targetIndex);

        //잘못된 상태 버튼테스트
        adminUseHistoryPage_search.clickDeliveryGetButton();
        softAssert.assertEquals(adminUseHistoryPage_search.alertGetText(),AppMessages.adminUseHistoryPage_Delivery_NoStatusGet_AlertMsg,
                "[FAIL]반환완료시의 수거용 송장번호 발급버튼 클릭 Alert 메세지가 잘못되었습니다.");
        adminUseHistoryPage_search.alertAccept();

        adminUseHistoryPage_search.clickDeliverySendButton();
        softAssert.assertEquals(adminUseHistoryPage_search.alertGetText(),AppMessages.adminUseHistoryPage_Delivery_NoStatusSend_AlertMsg,
                "[FAIL]반환완료시의 반환용 송장번호 발급버튼 클릭 Alert 메세지가 잘못되었습니다.");
        adminUseHistoryPage_search.alertAccept();

        //결재완료로 상태변경
        adminUseHistoryPage_search.clickModifyButton();
        adminUseHistoryPage_search.changeStatus(1);
        adminUseHistoryPage_search.clickChangeStatusButton();
        adminUseHistoryPage_search.alertAccept();
        adminUseHistoryPage_search.alertAccept();

        adminUseHistoryPage_search.clickCheckButton(targetIndex);

        //수거용 버튼 확인
        int courierOptionCount = adminUseHistoryPage_search.getDeliveryCompanyOptionCount();
        for (int i = 0; i < courierOptionCount; i++) {
            String selectedCourier = adminUseHistoryPage_search.selectDeliveryCompany(i);
            System.out.println("[INFO] " + i + "번째 옵션 검증 중: " + selectedCourier);

            adminUseHistoryPage_search.clickDeliveryGetButton();
            String expectedSendAlertMsg = AppMessages.adminUseHistoryPage_Delivery_OkCompany_AlertMsg1
                    + selectedCourier
                    + AppMessages.adminUseHistoryPage_Delivery_OkCompany_AlertMsg2;

            softAssert.assertEquals(adminUseHistoryPage_search.alertGetText(), expectedSendAlertMsg,
                    "[FAIL]수거용 버튼 클릭 Alert 검증 실패 (선택된 택배사: " + selectedCourier + ")");

            if (i == courierOptionCount - 1) {
                adminUseHistoryPage_search.alertAccept();
                adminUseHistoryPage_search.alertAccept();
            } else {
                adminUseHistoryPage_search.alertDismiss();
            }
        }

        adminUseHistoryPage_search.clickCheckButton(targetIndex);

        //재차 발급시 NG확인
        adminUseHistoryPage_search.clickDeliveryGetButton();
        softAssert.assertEquals(adminUseHistoryPage_search.alertGetText(),AppMessages.adminUseHistoryPage_Delivery_NoMoreGet_AlertMsg,
                "[FAIL]수거용 송장번호 재발급시의 NG Alert메세지가 잘못되었습니다.");
        adminUseHistoryPage_search.alertAccept();

        //세탁완료로 상태변경
        adminUseHistoryPage_search.clickModifyButton();
        adminUseHistoryPage_search.changeStatus(5);
        adminUseHistoryPage_search.clickChangeStatusButton();
        adminUseHistoryPage_search.alertAccept();
        adminUseHistoryPage_search.alertAccept();

        adminUseHistoryPage_search.clickCheckButton(targetIndex);

        //반환용 버튼 확인
        for (int i = 0; i < courierOptionCount; i++) {
            String selectedCourier = adminUseHistoryPage_search.selectDeliveryCompany(i);
            System.out.println("[INFO] " + i + "번째 옵션 검증 중: " + selectedCourier);

            adminUseHistoryPage_search.clickDeliverySendButton();
            String expectedSendAlertMsg = AppMessages.adminUseHistoryPage_Delivery_OkCompany_AlertMsg1
                    + selectedCourier
                    + AppMessages.adminUseHistoryPage_Delivery_OkCompany_AlertMsg2;

            softAssert.assertEquals(adminUseHistoryPage_search.alertGetText(), expectedSendAlertMsg,
                    "[FAIL]반환용 버튼 클릭 Alert 검증 실패 (선택된 택배사: " + selectedCourier + ")");

            if (i == courierOptionCount - 1) {
                adminUseHistoryPage_search.alertAccept();
                adminUseHistoryPage_search.alertAccept();
            } else {
                adminUseHistoryPage_search.alertDismiss();
            }
        }

        adminUseHistoryPage_search.waitForPageLoad();

        List<String> targetInfo = adminUseHistoryPage_search.getTargetInfo(targetIndex);
        String GetDeliveryNum = targetInfo.get(6);
        String SendDeliveryNum = targetInfo.get(7);

        softAssert.assertNotEquals(Objects.equals(GetDeliveryNum, "-"),"[FAIL]수거용 송장번호가 발급되어있지 않습니다.");
        softAssert.assertNotEquals(Objects.equals(SendDeliveryNum, "-"),"[FAIL]반환용 송장번호가 발급되어있지 않습니다.");
        System.out.println("[INFO] 테스트 데이터의 송장번호를 (수거용 : "+GetDeliveryNum+" ) / (반환용 : "+SendDeliveryNum+" )");

        softAssert.assertAll();
    }

    @AfterMethod
    private void Logout() {
        AdminUseHistoryPage adminUseHistoryPage = new AdminUseHistoryPage(driver);
        adminUseHistoryPage.navi.clickLogoutLink();
    }
}
