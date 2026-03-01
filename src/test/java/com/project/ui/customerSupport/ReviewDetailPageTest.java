package com.project.ui.customerSupport;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.customerSupport.ReviewBoardPage;
import com.project.page.customerSupport.ReviewDetailPage;
import config.ConfigReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.project.utils.ScreenshotSoftAssert;

import java.util.List;

public class ReviewDetailPageTest extends BaseTest {

    @DataProvider(name = "loginUser")
    public Object[][] loginUser() {
        return new Object[][] {
                { "Default"},
                { "Admin"},
        };
    }

    @BeforeMethod
    public void setupLogin(Object[] data) {
        String loginUser = (String) data[0];
        if (loginUser.equals("Admin")) {
            loginAsAdminUser();
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
        }
        else if (loginUser.equals("Default")) { loginAsDefaultUser(); }
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToReviewBoardPage();
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();

        reviewBoardPage.clickRandomReview();
    }
    //src체크
    @Test(testName = "ReviewDetailPage SrcCheck Test",dataProvider = "loginUser")
    public void ReviewDetailPage_SrcCheckTest(String loginUser) {
        ReviewDetailPage reviewDetailPage = new ReviewDetailPage(driver);
        reviewDetailPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        String ReviewDetailPage_Img_src =  reviewDetailPage.getSrc_ReviewDetailPage();
        softAssert.assertEquals(ReviewDetailPage_Img_src, config.getProperty("ReviewDetailPage_Img_src"));

        softAssert.assertAll();
    }


    //권한별 버튼표시체크
    @Test(testName = "ReviewDetailPage DisplayButton Test",dataProvider = "loginUser")
    public void ReviewDetailPage_DisplayButtonTest(String loginUser) {
        ReviewDetailPage reviewDetailPage = new ReviewDetailPage(driver);
        reviewDetailPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        if (loginUser.equals("Admin")) {
            softAssert.assertTrue(reviewDetailPage.ModifyButton_displayCheck());
            softAssert.assertTrue(reviewDetailPage.DeleteButton_displayCheck());
        }
        softAssert.assertTrue(reviewDetailPage.ListButton_displayCheck());
        softAssert.assertAll();
    }

    //기본 라벨체크
    @Test(testName = "ReviewDetailPage TextTest",dataProvider = "loginUser")
    public void ReviewDetailPage_TextTest(String loginUser) {
        ReviewDetailPage reviewDetailPage = new ReviewDetailPage(driver);
        reviewDetailPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        String reviewBoardTab_TextLabel=reviewDetailPage.getLabel(ReviewDetailPage.ReviewDetailPageLabel.REVIEWBOARDTAB);
        softAssert.assertEquals(reviewBoardTab_TextLabel, PageLabels.customerSupportPage_reviewBoardTab);

        String FAQBoardTab_TextLabel=reviewDetailPage.getLabel(ReviewDetailPage.ReviewDetailPageLabel.FAQBOARDTAB);
        softAssert.assertEquals(FAQBoardTab_TextLabel,PageLabels.customerSupportPage_FAQBoardTab);

        String QnABoardTab_TextLabel=reviewDetailPage.getLabel(ReviewDetailPage.ReviewDetailPageLabel.QNABOARDTAB);
        softAssert.assertEquals(QnABoardTab_TextLabel,PageLabels.customerSupportPage_QnABoardTab);

        String NoticeTab_TextLabel=reviewDetailPage.getLabel(ReviewDetailPage.ReviewDetailPageLabel.NOTICETAB);
        softAssert.assertEquals(NoticeTab_TextLabel,PageLabels.customerSupportPage_NoticeTab);

        String ReviewDetail_TitleLabel = reviewDetailPage.getLabel(ReviewDetailPage.ReviewDetailPageLabel.TITLELABEL);
        softAssert.assertEquals(ReviewDetail_TitleLabel,PageLabels.reviewDetailPage_TitleLabel);

        String ReviewDetailPage_OderNumberLabel = reviewDetailPage.getLabel(ReviewDetailPage.ReviewDetailPageLabel.ODERNUMBERLABEL);
        softAssert.assertEquals(ReviewDetailPage_OderNumberLabel,PageLabels.reviewDetailPage_OderNumberLabel);

        String ReviewDetailPage_ItemLabel = reviewDetailPage.getLabel(ReviewDetailPage.ReviewDetailPageLabel.ITEMLABEL);
        softAssert.assertEquals(ReviewDetailPage_ItemLabel,PageLabels.reviewDetailPage_ItemLabel);

        String ReviewDetailPage_StarLabel = reviewDetailPage.getLabel(ReviewDetailPage.ReviewDetailPageLabel.STARLABEL);
        softAssert.assertEquals(ReviewDetailPage_StarLabel,PageLabels.reviewDetailPage_StarLabel);

        String ReviewDetailPage_ContentLabel = reviewDetailPage.getLabel(ReviewDetailPage.ReviewDetailPageLabel.CONTENTLABEL);
        softAssert.assertEquals(ReviewDetailPage_ContentLabel,PageLabels.reviewDetailPage_ContentLabel);

        if (loginUser.equals("Admin")) {
            String ReviewDetailPage_modifyButton = reviewDetailPage.getLabel(ReviewDetailPage.ReviewDetailPageLabel.MODIFYBUTTON);
            softAssert.assertEquals(ReviewDetailPage_modifyButton, PageLabels.reviewDetailPage_modifyButton);

            String ReviewDetailPage_delectButton = reviewDetailPage.getLabel(ReviewDetailPage.ReviewDetailPageLabel.DELETEBUTTON);
            softAssert.assertEquals(ReviewDetailPage_delectButton, PageLabels.reviewDetailPage_delectButton);
        }

        String ReviewDetailPage_listButton = reviewDetailPage.getLabel(ReviewDetailPage.ReviewDetailPageLabel.LISTBUTTON);
        softAssert.assertEquals(ReviewDetailPage_listButton,PageLabels.reviewDetailPage_listButton);

        softAssert.assertAll();
    }

    @Test(testName = "ReviewDetail StarListBox Test", dataProvider ="loginUser")
    public void ReviewDetailPage_StarListBoxTest(String loginUser) {
        ReviewDetailPage reviewDetailPage = new ReviewDetailPage(driver);
        reviewDetailPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        List<String> actualOptions = reviewDetailPage.getAllSearchOptions();
        List<String> expectedOptions = PageLabels.reviewBoard_starOptions;

        softAssert.assertEquals(actualOptions.size(), expectedOptions.size());
        softAssert.assertEquals(actualOptions, expectedOptions, "평점리스트 구성이나 순서가 잘못되었습니다.");

        softAssert.assertAll();
    }

    @AfterMethod
    private void Logout() {
        ReviewDetailPage reviewDetailPage = new ReviewDetailPage(driver);
        reviewDetailPage.navi.clickLogoutLink();
    }

}
