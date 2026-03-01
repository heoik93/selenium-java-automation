package com.project.functional.mypage;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.myinfo.UseHistoryPage;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.project.utils.ScreenshotSoftAssert;

public class UseHistoryTest extends BaseTest {

    ConfigReader config = new ConfigReader();

    @BeforeMethod(alwaysRun = true)
    public void goToBooking_UseHistoryPage() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.gotoUseHistoryPage();
    }

    @Test(testName = "UseHistoryPage Tab Active Test")
    public void useHistoryPage_TabActiveTest() {
        UseHistoryPage useHistoryPage = new UseHistoryPage(driver);
        useHistoryPage.waitForPageLoad();

        Assert.assertTrue(useHistoryPage.isUseHistoryTabActive(), "신청내역확인 탭이 활성화 되어 있지 않습니다.");
    }

    @Test(testName = "UseHistoryPage tab test1")
    public void useHistoryPage_TabTest1() {
        UseHistoryPage useHistoryPage = new UseHistoryPage(driver);
        useHistoryPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        useHistoryPage.clickMyInfoTab();

        String currentUrl = useHistoryPage.getCurrentUrl();
        String pageTitle = useHistoryPage.getPageTitle();

        softAssert.assertEquals(currentUrl, config.getProperty("MyInfoPageURL"), "MyinfoPage URL이 일치하지 않습니다.");
        softAssert.assertEquals(pageTitle, PageLabels.myinfoPageTitle, "MyinfoPage 타이틀이 일치하지 않습니다.");

        softAssert.assertAll();
    }

    @Test(testName = "UseHistoryPage tab test2")
    public void useHistoryPage_TabTest2() {
        UseHistoryPage useHistoryPage = new UseHistoryPage(driver);
        useHistoryPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        useHistoryPage.clickUseHistoryTab();

        String currentUrl = useHistoryPage.getCurrentUrl();
        String pageTitle = useHistoryPage.getPageTitle();

        softAssert.assertEquals(currentUrl, config.getProperty("UseHistoryPageURL"), "UseHistoryPag URL이 일치하지 않습니다.");
        softAssert.assertEquals(pageTitle, PageLabels.useHistoryPageTittle, "UseHistoryPag 타이틀이 일치하지 않습니다."); //현재DF

        softAssert.assertAll();
    }

    @Test(testName = "UseHistoryPage DetailButton test")
    public void useHistoryPage_DetailButtonTest() {
        UseHistoryPage useHistoryPage = new UseHistoryPage(driver);
        useHistoryPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        String selectOrderNum = useHistoryPage.clickOderDetailButton();

        if (selectOrderNum == null) {
            System.out.println("테스트할 데이터가 없습니다.");
            return;
        }

        String currentUrl = useHistoryPage.getCurrentUrl();
        String pageTitle = useHistoryPage.getPageTitle();

        softAssert.assertTrue(currentUrl.contains(config.getProperty("OrderDetailPageURL")+selectOrderNum));
        softAssert.assertEquals(pageTitle, PageLabels.orderDetailPageTitle); //현재DF

        softAssert.assertAll();
    }

    @Test(testName = "UseHistoryPage ReviewButton test")
    public void useHistoryPage_ReviewButtonTest() {
        UseHistoryPage useHistoryPage = new UseHistoryPage(driver);
        useHistoryPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        String selectOrderNum = useHistoryPage.clickReviewCreateButton();

        if (selectOrderNum == null) {
            System.out.println("테스트할 데이터가 없습니다.");
            return;
        }

        String currentUrl = useHistoryPage.getCurrentUrl();
        String pageTitle = useHistoryPage.getPageTitle();

        softAssert.assertTrue(currentUrl.contains(config.getProperty("ReviewCreatePageURL")+selectOrderNum));
        softAssert.assertEquals(pageTitle, PageLabels.reviewBoardPageTitle); //현재DF

        softAssert.assertAll();
    }

    @Test(testName = "UseHistoryPage MaxList test")
    public void useHistoryPage_MaxListTest() {
        UseHistoryPage useHistoryPage = new UseHistoryPage(driver);
        useHistoryPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        int currentListNumber = useHistoryPage.ListNumber();
        if (currentListNumber == 0) {
            throw new SkipException("테스트할 데이터가 없습니다.");
        }
        else {
            if(currentListNumber<5) {
                throw new SkipException("테스트할 게시물 수가 부족합니다.(5개 미만)");
            }
           }

        softAssert.assertFalse(currentListNumber > 5, "한 페이지에 표시되는 게시물 수가 5개를 초과합니다.");
        softAssert.assertTrue(useHistoryPage.pageNaviDisplayCheck(), "게시물 수가 5개를 넘어도 페이지 네비게이션이 표시되지 않습니다.");

        softAssert.assertAll();
    }




    @Test(testName = "UseHistoryPage NaviButton test")
    public void useHistoryPage_NaviButtonTest() {
        UseHistoryPage useHistoryPage = new UseHistoryPage(driver);
        useHistoryPage.waitForPageLoad();

        int clickedPage = useHistoryPage.clickPageNavi();
        if (clickedPage == 0) { System.out.println("페이지 네비게이션이 없어 검증을 스킵합니다.");
            return; }

        String currentUrl = useHistoryPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(config.getProperty("UseHistoryNaviPageURl")+clickedPage));
    }

    @Test(testName = "UseHistoryPage ReviewRule test")
    public void useHistoryPage_ReviewRuleTest() {
        UseHistoryPage useHistoryPage = new UseHistoryPage(driver);
        useHistoryPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        //리뷰 작성하기버튼 룰체크
        softAssert.assertTrue(useHistoryPage.ReviewButtonRuleCheck(), "후기작성 버튼 노출 규칙이 일치하지 않습니다.");

        //url강제이동 여부확인
        String finalUrl = useHistoryPage.urlForcedMove_ReviewLink();
        if (finalUrl != null) {
             //최종 URL에 리뷰 작성 주소가 포함되지 않아야 함 (차단 성공)
            Assert.assertFalse(finalUrl.contains(config.getProperty("ReviewCreatePageURL")),
                    "보안 결함: 반환완료가 아닌 주문번호로 리뷰 작성 페이지에 접근 성공함!"); //현재DF

            System.out.println("[SUCCESS] 정상적으로 차단되어 현재 페이지는 " + finalUrl + " 입니다.");
        }

    }


    @AfterMethod
    private void Logout() {
        UseHistoryPage useHistoryPage = new UseHistoryPage(driver);
        useHistoryPage.navi.clickLogoutLink();
    }

}
