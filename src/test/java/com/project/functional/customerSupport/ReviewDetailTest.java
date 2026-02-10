package com.project.functional.customerSupport;

import com.project.base.BaseTest;
import com.project.constants.AppMessages;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.customerSupport.ReviewBoardPage;
import com.project.page.customerSupport.ReviewDetailPage;
import com.project.page.myinfo.UseHistoryPage;
import config.ConfigReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Objects;

public class ReviewDetailTest extends BaseTest {

    @DataProvider(name = "allUsers")
    public Object[][] loginAllUser() {
        return new Object[][] {
                { "Default"},
                { "Another"},
                { "Admin"},
        };
    }

    @DataProvider(name = "DefaultUsers")
    public Object[][] loginDefaultUser() {
        return new Object[][] {
                { "Default"},
                { "Admin"},
        };
    }

    @BeforeMethod
    public void setupLogin(Object[] data) {
        String loginUser = (String) data[0];
        switch (loginUser) {
            case "Admin":    loginAsAdminUser();    break;
            case "Another":  loginAsAnotherUser();  break;
            case "Default":  loginAsDefaultUser();  break;
        }
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToReviewBoardPage();
    }

    @Test(testName = "ReviewDetail NoChangeFiled Test", dataProvider ="DefaultUsers")
    public void ReviewDetailPage_NoChangeFiledTest(String loginUser){
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();

        reviewBoardPage.clickRandomReview();

        ReviewDetailPage reviewDetailPage = new ReviewDetailPage(driver);
        reviewDetailPage.waitForPageLoad();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertFalse(reviewDetailPage.check_OderNumberFiledEnable());
        softAssert.assertFalse(reviewDetailPage.check_ItemFiledEnable());

        softAssert.assertAll();
    }

    @Test(testName = "ReviewDetail StarListBox Test", dataProvider ="DefaultUsers")
    public void ReviewDetailPage_StarListBoxTest(String loginUser) {
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();

        reviewBoardPage.clickRandomReview();

        ReviewDetailPage reviewDetailPage = new ReviewDetailPage(driver);
        reviewDetailPage.waitForPageLoad();

        SoftAssert softAssert = new SoftAssert();

        List<String> actualOptions = reviewDetailPage.getAllSearchOptions();
        List<String> expectedOptions = PageLabels.reviewBoard_starOptions;

        softAssert.assertEquals(actualOptions.size(), expectedOptions.size());
        softAssert.assertEquals(actualOptions, expectedOptions, "평점리스트 구성이나 순서가 잘못되었습니다.");

        softAssert.assertAll();
    }

    @Test(testName = "ReviewDetail Review ModifyButton Test", dataProvider ="allUsers")
    public void ReviewDetailPage_ModifyListButtonTest(String loginUser) {
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();

        SoftAssert softAssert = new SoftAssert();
        ConfigReader config  = new ConfigReader();

        reviewBoardPage.selectOption(2);
        reviewBoardPage.searchKeyword(config.getProperty("username"));

        ReviewBoardPage reviewBoardPage_afterSearch = new ReviewBoardPage(driver);
        reviewBoardPage_afterSearch.waitForPageLoad();

        reviewBoardPage_afterSearch.clickRandomReview();

        ReviewDetailPage reviewDetailPage = new ReviewDetailPage(driver);
        reviewDetailPage.waitForPageLoad();

        //권한별 수정/비표시 체크
        if(Objects.equals(loginUser, "Default")||Objects.equals(loginUser, "Admin")){
            reviewDetailPage.clickModifyButton();
            softAssert.assertEquals(reviewDetailPage.alertGetText(), AppMessages.reviewDetailPage_Modify_AlertMsg);
            reviewDetailPage.alertAccept();
        }
        if(Objects.equals(loginUser, "Another")){
            softAssert.assertTrue(reviewDetailPage.ModifyButton_hiddenCheck());
        }

        reviewDetailPage.waitForPageLoad();
        reviewDetailPage.clickListButton();

        ReviewBoardPage reviewBoardPage_2nd = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();

        String currentUrl = reviewBoardPage_2nd.getCurrentUrl();
        String currentTitle = reviewBoardPage_2nd.getPageTitle();

        //목록버튼클릭후 정상이동확인
        softAssert.assertEquals(currentUrl,config.getProperty("ReviewBoardPageURL"));
        softAssert.assertEquals(currentTitle,PageLabels.reviewBoardPageTitle);

        softAssert.assertAll();
    }

    @Test(testName = "ReviewDetail Review Delete/Create Button Test", dataProvider ="allUsers")
    public void ReviewDetailPage_ReviewDeleteCreateButtonTest(String loginUser) {
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();

        SoftAssert softAssert = new SoftAssert();
        ConfigReader config = new ConfigReader();

        reviewBoardPage.selectOption(2);
        reviewBoardPage.searchKeyword(config.getProperty("username"));

        ReviewBoardPage reviewBoardPage_afterSearch = new ReviewBoardPage(driver);
        reviewBoardPage_afterSearch.waitForPageLoad();

        reviewBoardPage_afterSearch.clickRandomReview();

        ReviewDetailPage reviewDetailPage = new ReviewDetailPage(driver);
        reviewDetailPage.waitForPageLoad();

        //삭제버튼테스트(작성자,관리자)
        if (Objects.equals(loginUser, "Default")) {
            String reviewTitle = reviewDetailPage.getTitle();
            String reviewContent = reviewDetailPage.getContent();
            String reviewStar = reviewDetailPage.getStar();
            String reviewOderNumber = reviewDetailPage.getOderNumber();
            System.out.println("[INFO] 삭제할 후기 정보 : 제목-"+reviewTitle+", 내용-"+reviewContent+", 평점-"+reviewStar);

            if (reviewOderNumber == null || reviewOderNumber.isEmpty()) {
                softAssert.fail("[CRITICAL] 주문번호를 가져오지 못했습니다. 테스트를 중단합니다.");
                softAssert.assertAll();
                return;}

            reviewDetailPage.clickDeleteButton();
            softAssert.assertEquals(reviewDetailPage.alertGetText(), AppMessages.reviewDetailPage_delect_AlertMsg);
            reviewDetailPage.alertAccept();

            ReviewBoardPage reviewBoardPage_2nd = new ReviewBoardPage(driver);
            softAssert.assertEquals(reviewBoardPage_2nd.alertGetText(), AppMessages.reviewDetailPage_delect_Success_AlertMsg);
            reviewBoardPage_2nd.alertAccept();
            reviewBoardPage_2nd.waitForPageLoad();

            //삭제확인
            reviewBoardPage_2nd.selectOption(1);
            reviewBoardPage_2nd.searchKeyword(reviewTitle);
            if (reviewBoardPage_2nd.ListNumber() == 0) {
                System.out.println("[INFO]해당 후기가 정상적으로 삭제되었습니다.(주문번호 : "+reviewOderNumber+" )");
            }
            if (reviewBoardPage_2nd.ListNumber() > 0) {
                System.out.println("[INFO]동일한 제목의 후기가 존재하기에 해당 주문번호를 확인합니다.");
                reviewBoardPage_2nd.clickRandomReview();
                ReviewDetailPage reviewDetailPage_checkSame = new ReviewDetailPage(driver);
                reviewDetailPage_checkSame.waitForPageLoad();

                softAssert.assertFalse(
                        Objects.equals(reviewContent, reviewDetailPage_checkSame.getLabel(ReviewDetailPage.ReviewDetailPageLabel.CONTENT))
                                & Objects.equals(reviewStar, reviewDetailPage_checkSame.getStar()), "[INFO]동일한 후기가 존재합니다.");
                reviewDetailPage_checkSame.clickListButton();
            }

                //작성하기 페이지로 이동
                ReviewBoardPage reviewBoardPage_afterDelete = new ReviewBoardPage(driver);
                reviewBoardPage_afterDelete.waitForPageLoad();
                reviewBoardPage_afterDelete.navi.gotoUseHistoryPage();

                //삭제한 후기의 주문번호 찾기
                UseHistoryPage useHistoryPage = new UseHistoryPage(driver);
                useHistoryPage.waitForPageLoad();
                useHistoryPage.gotoDeleteContent_Create(reviewOderNumber);

                //리뷰작성
                ReviewDetailPage reviewDetailPage_Create = new ReviewDetailPage(driver);
                reviewDetailPage_Create.waitForPageLoad();

                reviewDetailPage_Create.CreateReview(reviewTitle, reviewStar, reviewContent);
                softAssert.assertEquals(reviewDetailPage_Create.alertGetText(), AppMessages.reviewDetailPage_crate_AlertMsg);
                reviewDetailPage_Create.alertAccept();

        }

        if (Objects.equals(loginUser, "Another")) {
            softAssert.assertTrue(reviewDetailPage.DeleteButton_hiddenCheck());
            reviewDetailPage.clickListButton();
        }

        if (Objects.equals(loginUser, "Admin")) {
            softAssert.assertTrue(reviewDetailPage.DeleteButton_displayCheck());
            reviewDetailPage.clickListButton();
        }

        softAssert.assertAll();
    }

    @AfterMethod
    private void Logout() {
        ReviewDetailPage reviewDetailPage = new ReviewDetailPage(driver);
        reviewDetailPage.navi.clickLogoutLink();
    }



}