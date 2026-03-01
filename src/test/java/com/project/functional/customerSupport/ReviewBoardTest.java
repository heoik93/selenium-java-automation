package com.project.functional.customerSupport;

import com.project.base.BaseTest;
import com.project.page.HomePage;
import com.project.page.customerSupport.ReviewBoardPage;
import com.project.page.customerSupport.ReviewDetailPage;
import config.ConfigReader;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.project.utils.ScreenshotSoftAssert;

public class ReviewBoardTest extends BaseTest {

    @BeforeMethod
    public void setup(){

        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToReviewBoardPage();
    }

    @Test(testName = "ReviewBoard Search Test")
    public void reviewBoardPage_SearchTest(){
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        //검색결과 없음
        reviewBoardPage.searchKeyword("1234!@#");
        ReviewBoardPage afterSearchPage = new ReviewBoardPage(driver);
        afterSearchPage.waitForPageLoad();

        int ReviewList_noCountList = afterSearchPage.checkResult();
        softAssert.assertTrue(ReviewList_noCountList == 0);

        afterSearchPage.clickReviewBoardTab();
        ReviewBoardPage reviewBoardPage_2rd = new ReviewBoardPage(driver);
        reviewBoardPage_2rd.waitForPageLoad();

        //검색결과 있음
        reviewBoardPage_2rd.searchKeyword("후기");
        ReviewBoardPage afterSearchPage_2nd = new ReviewBoardPage(driver);
        afterSearchPage_2nd.waitForPageLoad();

        int ReviewList = afterSearchPage_2nd.checkResult();
        softAssert.assertTrue(ReviewList > 0);

        softAssert.assertAll();
    }

    @Test(testName = "ReviewBoard SearchFilter Test")
    public void reviewBoardPage_SearchFilterTest(){
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        //제목검색
        reviewBoardPage.selectOption(1);
        reviewBoardPage.searchKeyword("후기");
        ReviewBoardPage afterSearchPage = new ReviewBoardPage(driver);
        afterSearchPage.waitForPageLoad();

        int ReviewList_noCountList = afterSearchPage.checkResult();
        softAssert.assertTrue(ReviewList_noCountList > 0);

        afterSearchPage.clickReviewBoardTab();
        ReviewBoardPage reviewBoardPage_2rd = new ReviewBoardPage(driver);
        reviewBoardPage_2rd.waitForPageLoad();

        //작성자검색
        reviewBoardPage_2rd.selectOption(2);
        reviewBoardPage_2rd.searchKeyword(config.getProperty("username"));
        ReviewBoardPage afterSearchPage_2nd = new ReviewBoardPage(driver);
        afterSearchPage_2nd.waitForPageLoad();

        int ReviewList = afterSearchPage_2nd.checkResult();
        softAssert.assertTrue(ReviewList > 0);

        softAssert.assertAll();
    }

    @Test(testName = "ReviewBoard ListCount Test")
    public void reviewBoardPage_ListCountTest(){
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        int index = reviewBoardPage.selectRandomReview();
        int beforeCount = reviewBoardPage.getReviewCount(index);
        reviewBoardPage.clickReview(index);

        ReviewDetailPage reviewDetailPage = new ReviewDetailPage(driver);
        reviewDetailPage.waitForPageLoad();

        driver.navigate().back();
        driver.navigate().refresh();
        ReviewBoardPage reviewBoardPage_after = new ReviewBoardPage(driver);
        reviewBoardPage_after.waitForPageLoad();

        int afterCount = reviewBoardPage_after.getReviewCount(index);

        softAssert.assertTrue(beforeCount+1 == afterCount);
        softAssert.assertAll();
    }

    @Test(testName = "ReviewBoard DataLink Test")
    public void reviewBoardPage_DataLinkTest(){
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        int index = reviewBoardPage.selectRandomReview();
        String expectTitle =reviewBoardPage.getReviewTitle(index);
        String expectStar =reviewBoardPage.getReviewStar(index);

        reviewBoardPage.clickReview(index);
        ReviewDetailPage reviewDetailPage = new ReviewDetailPage(driver);
        reviewDetailPage.waitForPageLoad();

        String actualTitle = reviewDetailPage.getTitle();
        String actualStar = reviewDetailPage.getStar();

        softAssert.assertEquals(actualTitle,expectTitle);
        softAssert.assertEquals(expectStar,actualStar);

        softAssert.assertAll();
    }

    @Test(testName = "ReviewBoard ListMax Test")
    public void reviewBoardPage_ListMaxTest(){
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        int currentListNumber = reviewBoardPage.ListNumber();
        if (currentListNumber == 0) {
            System.out.println("[INFO]테스트할 데이터가 없습니다.");
            throw new SkipException("테스트할 데이터가 없습니다.");
        }
        else {
            if(currentListNumber<5) {
                System.out.println("[INFO]테스트할 게시물 수가 부족합니다.(5개 미만)");
                throw new SkipException("테스트할 게시물 수가 부족합니다.(5개 미만)");
            }
        }

        softAssert.assertFalse(currentListNumber > 5, "한 페이지에 표시되는 게시물 수가 5개를 초과합니다.");
        softAssert.assertTrue(reviewBoardPage.pageNaviDisplayCheck(), "게시물 수가 5개를 넘어도 페이지 네비게이션이 표시되지 않습니다.");

        softAssert.assertAll();
    }

    @AfterMethod
    private void Logout() {
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.navi.clickLogoutLink();
    }


}
