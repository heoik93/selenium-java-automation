package com.project.ui.customerSupport;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.customerSupport.ReviewBoardPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.project.utils.ScreenshotSoftAssert;

public class ReviewBoardPageTest extends BaseTest {

    @BeforeMethod
    public void setupLogin() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToReviewBoardPage();
    }

    @Test(testName = "ReviewBoardPage TextTest")
    public void ReviewBoardPage_TextTest(){
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        String reviewBoardTab_TextLabel=reviewBoardPage.getLabel(ReviewBoardPage.ReviewBoardPageLabel.REVIEWBOARDTAB);
        softAssert.assertEquals(reviewBoardTab_TextLabel, PageLabels.customerSupportPage_reviewBoardTab);

        String FAQBoardTab_TextLabel=reviewBoardPage.getLabel(ReviewBoardPage.ReviewBoardPageLabel.FAQBOARDTAB);
        softAssert.assertEquals(FAQBoardTab_TextLabel,PageLabels.customerSupportPage_FAQBoardTab);

        String QnABoardTab_TextLabel=reviewBoardPage.getLabel(ReviewBoardPage.ReviewBoardPageLabel.QNABOARDTAB);
        softAssert.assertEquals(QnABoardTab_TextLabel,PageLabels.customerSupportPage_QnABoardTab);

        String NoticeTab_TextLabel=reviewBoardPage.getLabel(ReviewBoardPage.ReviewBoardPageLabel.NOTICETAB);
        softAssert.assertEquals(NoticeTab_TextLabel,PageLabels.customerSupportPage_NoticeTab);

        String reviewNumberLabel_TextLabel=reviewBoardPage.getLabel(ReviewBoardPage.ReviewBoardPageLabel.REVIEWNUMBERLABEL);
        softAssert.assertEquals(reviewNumberLabel_TextLabel,PageLabels.reviewBoardPage_reviewNumberLabel);

        String reviewUserLabel_TextLabel=reviewBoardPage.getLabel(ReviewBoardPage.ReviewBoardPageLabel.REVIEWUSERLABEL);
        softAssert.assertEquals(reviewUserLabel_TextLabel,PageLabels.reviewBoardPage_reviewUserLabel);

        String reviewTitleLabel_TextLabel=reviewBoardPage.getLabel(ReviewBoardPage.ReviewBoardPageLabel.REVIEWTITLELABEL);
        softAssert.assertEquals(reviewTitleLabel_TextLabel,PageLabels.reviewBoardPage_reviewTitleLabel);

        String reviewHitsLabel_TextLabel=reviewBoardPage.getLabel(ReviewBoardPage.ReviewBoardPageLabel.REVIEWHITSLABEL);
        softAssert.assertEquals(reviewHitsLabel_TextLabel,PageLabels.reviewBoardPage_reviewHitsLabel);

        String reviewDateLabel_TextLabel=reviewBoardPage.getLabel(ReviewBoardPage.ReviewBoardPageLabel.REVIEWDATELABEL);
        softAssert.assertEquals(reviewDateLabel_TextLabel,PageLabels.reviewBoardPage_reviewDateLabel);

        String reviewPointLabel_TextLabel=reviewBoardPage.getLabel(ReviewBoardPage.ReviewBoardPageLabel.REVIEWPOINTLABEL);
        softAssert.assertEquals(reviewPointLabel_TextLabel,PageLabels.reviewBoardPage_reviewPointLabel);

        String searchButton_TextLabel_TextLabel=reviewBoardPage.getLabel(ReviewBoardPage.ReviewBoardPageLabel.SEARCHBUTTON);
        softAssert.assertEquals(searchButton_TextLabel_TextLabel,PageLabels.reviewBoardPage_searchButton);

        //기본검색조건
        String searchConditionSelect_TextLabel1=reviewBoardPage.getSelectedSearchConditionText();
        softAssert.assertEquals(searchConditionSelect_TextLabel1,PageLabels.reviewBoardPage_searchConditionSelect1);

        //검색조건변경1
        reviewBoardPage.selectOption(1);
        String searchConditionSelect_TextLabel2=reviewBoardPage.getSelectedSearchConditionText();
        softAssert.assertEquals(searchConditionSelect_TextLabel2,PageLabels.reviewBoardPage_searchConditionSelect2);

        //검색조건변경2
        reviewBoardPage.selectOption(2);
        String searchConditionSelect_TextLabel3=reviewBoardPage.getSelectedSearchConditionText();
        softAssert.assertEquals(searchConditionSelect_TextLabel3,PageLabels.reviewBoardPage_searchConditionSelect3);

        softAssert.assertAll();
    }

    @AfterMethod
    private void Logout() {
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.navi.clickLogoutLink();
    }
}
