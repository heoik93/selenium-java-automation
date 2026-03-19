package com.project.functional.customerSupport;

import com.project.base.BaseTest;
import com.project.constants.AppMessages;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.customerSupport.ReviewBoardPage;
import com.project.page.customerSupport.ReviewDetailPage;
import com.project.page.myinfo.UseHistoryPage;
import com.project.utils.ScreenshotSoftAssert;
import config.ConfigReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        softAssert.assertFalse(reviewDetailPage.check_OderNumberFiledEnable(),"[FAIL]후기상세 페이지의 주문번호 필드가 활성화 되어있습니다.");
        softAssert.assertFalse(reviewDetailPage.check_ItemFiledEnable(),"[FAIL]후기상세 페이지의 품목 필드가 활성화 되어있습니다.");

        softAssert.assertAll();
    }

    @Test(testName = "ReviewDetail Review ModifyButton Test", dataProvider ="allUsers")
    public void ReviewDetailPage_ModifyListButtonTest(String loginUser) {
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
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
            softAssert.assertEquals(reviewDetailPage.alertGetText(), AppMessages.reviewDetailPage_Modify_AlertMsg,
                    "[FAIL]후기상세페이지의 수정버튼 클릭시 표시되는 Alert메세지가 올바르지 않습니다.");
            reviewDetailPage.alertAccept();
        }
        if(Objects.equals(loginUser, "Another")){
            softAssert.assertTrue(reviewDetailPage.ModifyButton_hiddenCheck(),
                    "[FAIL]후기상세페이지의 수정버튼이 일반유저임에도 표시되어있습니다.");
        }

        reviewDetailPage.waitForPageLoad();
        reviewDetailPage.clickListButton();

        ReviewBoardPage reviewBoardPage_2nd = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();

        String currentUrl = reviewBoardPage_2nd.getCurrentUrl();
        String currentTitle = reviewBoardPage_2nd.getPageTitle();

        //목록버튼클릭후 정상이동확인
        softAssert.assertEquals(currentUrl,config.getProperty("ReviewBoardPageURL"),"[FAIL]후기상세페이지에서 목록버튼 클릭후의 URL이 올바르지 않습니다.");
        softAssert.assertEquals(currentTitle,PageLabels.reviewBoardPageTitle,"[FAIL]후기상세페이지에서 목록버튼 클릭후의 페이지타이틀이 올바르지 않습니다.");

        softAssert.assertAll();
    }

    @Test(testName = "ReviewDetail Review Delete/Create Button Test", dataProvider ="allUsers")
    public void ReviewDetailPage_ReviewDeleteCreateButtonTest(String loginUser) {
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
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
                softAssert.fail("[FAIL] 주문번호를 가져오지 못했습니다. 테스트를 중단합니다.");
                softAssert.assertAll();
                return;}

            reviewDetailPage.clickDeleteButton();
            softAssert.assertEquals(reviewDetailPage.alertGetText(), AppMessages.reviewDetailPage_Delect_AlertMsg,
                    "[FAIL] 후기상세페이지에서 삭제버튼 클릭시의 삭제확인 Alert메세지가 올바르지 않습니다.");
            reviewDetailPage.alertAccept();

            ReviewBoardPage reviewBoardPage_2nd = new ReviewBoardPage(driver);
            softAssert.assertEquals(reviewBoardPage_2nd.alertGetText(), AppMessages.reviewDetailPage_Delect_Success_AlertMsg,
                    "[FAIL] 후기상세페이지에서 삭제버튼 클릭시의 삭제완료 Alert메세지가 올바르지 않습니다.");
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
                softAssert.assertEquals(reviewDetailPage_Create.alertGetText(), AppMessages.reviewDetailPage_Create_AlertMsg,
                        "[FAIL]후기작성페이지에서 저장시의 Alert메세지가 올바르지 않습니다.");
                reviewDetailPage_Create.alertAccept();

        }

        if (Objects.equals(loginUser, "Another")) {
            softAssert.assertTrue(reviewDetailPage.DeleteButton_hiddenCheck(),"[FAIL]일반유저임에도 후기삭제버튼이 표시되어있습니다.");
            reviewDetailPage.clickListButton();
        }

        if (Objects.equals(loginUser, "Admin")) {
            softAssert.assertTrue(reviewDetailPage.DeleteButton_displayCheck(),"[FAIL]관리자유저임에도 후기삭제버튼이 비표시되어있습니다.");
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