package com.project.functional.customerSupport;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.customerSupport.FAQBoardPage;
import com.project.page.customerSupport.NoticePage;
import com.project.page.customerSupport.QnABoardPage;
import com.project.page.customerSupport.ReviewBoardPage;
import config.ConfigReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.project.utils.ScreenshotSoftAssert;

import java.lang.reflect.Method;

public class CustomerSupportPageTabTest extends BaseTest {

    private final ConfigReader config = new ConfigReader();
    private ScreenshotSoftAssert softAssert;

    private void verifyTabPage(String expectedUrlKey, String expectedTitle) {
        String currentUrl = driver.getCurrentUrl();
        String pageTitle = driver.getTitle();

        softAssert.assertEquals(currentUrl, config.getProperty(expectedUrlKey));
        softAssert.assertEquals(pageTitle, expectedTitle);
        System.out.println("[INFO] URL: " + currentUrl + " | Title: " + pageTitle);
        softAssert.assertAll();
    }

    @BeforeMethod
    public void setup(Method method) {
        softAssert = new ScreenshotSoftAssert(driver);
        String methodName = method.getName().toLowerCase();

        System.out.println("[INFO] ===== Setup Start: " + methodName + " =====");

        loginAsDefaultUser();

        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();

        if (methodName.contains("tabtest")) {
            try {
                if (methodName.startsWith("qnaboardpage")) {
                    afterLogin.navi.goToQnABoardPage();
                }
                else if (methodName.startsWith("faqboardpage")) {
                    afterLogin.navi.goToFAQBoardPage();
                }
                else if (methodName.startsWith("reviewboardpage")) {
                    afterLogin.navi.goToReviewBoardPage();
                }
                else if (methodName.startsWith("noticepage")) {
                    afterLogin.navi.goToNoticePage();
                } else {
                    System.out.println("[WARN] 라우팅 조건에 맞는 페이지가 없습니다: " + methodName);
                }
            } catch (Exception e) {
                System.out.println("[ERROR] 탭 진입 실패 : " + e.getMessage());
                throw e;
            }
        }

        System.out.println("[INFO] ===== Setup Completed: " + methodName + " =====");
    }

    //ReviewBoard
    @Test(testName = "ReviewBoardPage Tab Test1", groups = {"ReviewBoardPage"})
    public void reviewBoardPage_TabTest1() {
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();

        reviewBoardPage.clickReviewBoardTab();
        ReviewBoardPage tabPage = new ReviewBoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("ReviewBoardPageURL",PageLabels.reviewBoardPageTitle);
    }

    @Test(testName = "ReviewBoardPage Tab Test2", groups = {"FAQBoardPage"})
    public void reviewBoardPage_TabTest2() {
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();

        reviewBoardPage.clickFAQBoardTab();
        FAQBoardPage tabPage = new FAQBoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("FAQBoardPageURL",PageLabels.FAQBoardPageTitle);
    }

    @Test(testName = "ReviewBoardPage Tab Test3", groups = {"QnABoardPage"})
    public void reviewBoardPage_TabTest3() {
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();

        reviewBoardPage.clickQnABoardTab();
        QnABoardPage tabPage = new QnABoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("QnABoardPageURL",PageLabels.QnABoardPageTitle);
    }

    @Test(testName = "ReviewBoardPage Tab Test4", groups = {"NoticePage"})
    public void reviewBoardPage_TabTest4() {
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();

        reviewBoardPage.clickNoticeTab();
        NoticePage tabPage = new NoticePage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("NoticePageURL",PageLabels.noticePageTitle);
    }

    //FAQBoard
    @Test(testName = "FAQBoardPage Tab Test1", groups = {"ReviewBoardPage"})
    public void faqBoardPage_TabTest1() {
        FAQBoardPage faqBoardPage = new FAQBoardPage(driver);
        faqBoardPage.waitForPageLoad();

        faqBoardPage.clickReviewBoardTab();
        ReviewBoardPage tabPage = new ReviewBoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("ReviewBoardPageURL",PageLabels.reviewBoardPageTitle);
    }

    @Test(testName = "FAQBoardPage Tab Test2", groups = {"FAQBoardPage"})
    public void faqBoardPage_TabTest2() {
        FAQBoardPage faqBoardPage = new FAQBoardPage(driver);
        faqBoardPage.waitForPageLoad();

        faqBoardPage.clickFAQBoardTab();
        FAQBoardPage tabPage = new FAQBoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("FAQBoardPageURL",PageLabels.FAQBoardPageTitle);
    }

    @Test(testName = "FAQBoardPage Tab Test3", groups = {"QnABoardPage"})
    public void faqBoardPage_TabTest3() {
        FAQBoardPage faqBoardPage = new FAQBoardPage(driver);
        faqBoardPage.waitForPageLoad();

        faqBoardPage.clickQnABoardTab();
        QnABoardPage tabPage = new QnABoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("QnABoardPageURL",PageLabels.QnABoardPageTitle);
    }

    @Test(testName = "FAQBoardPage Tab Test4", groups = {"NoticePage"})
    public void faqBoardPage_TabTest4() {
        FAQBoardPage faqBoardPage = new FAQBoardPage(driver);
        faqBoardPage.waitForPageLoad();

        faqBoardPage.clickNoticeTab();
        NoticePage tabPage = new NoticePage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("NoticePageURL",PageLabels.noticePageTitle);
    }

    //QnABoard
    @Test(testName = "QnABoardPage Tab Test1", groups = {"ReviewBoardPage"})
    public void qnaBoardPage_TabTest1() {
        QnABoardPage qnaBoardPage = new QnABoardPage(driver);
        qnaBoardPage.waitForPageLoad();

        qnaBoardPage.clickReviewBoardTab();
        ReviewBoardPage tabPage = new ReviewBoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("ReviewBoardPageURL",PageLabels.reviewBoardPageTitle);
    }

    @Test(testName = "QnABoardPage Tab Test2", groups = {"FAQBoardPage"})
    public void qnaBoardPage_TabTest2() {
        QnABoardPage qnaBoardPage = new QnABoardPage(driver);
        qnaBoardPage.waitForPageLoad();

        qnaBoardPage.clickFAQBoardTab();
        FAQBoardPage tabPage = new FAQBoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("FAQBoardPageURL",PageLabels.FAQBoardPageTitle);
    }

    @Test(testName = "QnABoardPage Tab Test3", groups = {"QnABoardPage"})
    public void qnaBoardPage_TabTest3() {
        QnABoardPage qnaBoardPage = new QnABoardPage(driver);
        qnaBoardPage.waitForPageLoad();

        qnaBoardPage.clickQnABoardTab();
        QnABoardPage tabPage = new QnABoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("QnABoardPageURL",PageLabels.QnABoardPageTitle);
    }

    @Test(testName = "QnABoardPage Tab Test4", groups = {"NoticePage"})
    public void qnaBoardPage_TabTest4() {
        QnABoardPage qnaBoardPage = new QnABoardPage(driver);
        qnaBoardPage.waitForPageLoad();

        qnaBoardPage.clickNoticeTab();
        NoticePage tabPage = new NoticePage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("NoticePageURL",PageLabels.noticePageTitle);
    }

    //Notice
    @Test(testName = "NoticePage Tab Test1", groups = {"ReviewBoardPage"})
    public void noticePage_TabTest1() {
        NoticePage noticePage = new NoticePage(driver);
        noticePage.waitForPageLoad();

        noticePage.clickReviewBoardTab();
        ReviewBoardPage tabPage = new ReviewBoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("ReviewBoardPageURL",PageLabels.reviewBoardPageTitle);
    }

    @Test(testName = "NoticePage Tab Test2", groups = {"FAQBoardPage"})
    public void noticePage_TabTest2() {
        NoticePage noticePage = new NoticePage(driver);
        noticePage.waitForPageLoad();

        noticePage.clickFAQBoardTab();
        FAQBoardPage tabPage = new FAQBoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("FAQBoardPageURL",PageLabels.FAQBoardPageTitle);
    }

    @Test(testName = "NoticePage Tab Test3", groups = {"QnABoardPage"})
    public void noticePage_TabTest3() {
        NoticePage noticePage = new NoticePage(driver);
        noticePage.waitForPageLoad();

        noticePage.clickQnABoardTab();
        QnABoardPage tabPage = new QnABoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("QnABoardPageURL",PageLabels.QnABoardPageTitle);
    }

    @Test(testName = "NoticePage Tab Test4", groups = {"NoticePage"})
    public void noticePage_TabTest4() {
        NoticePage noticePage = new NoticePage(driver);
        noticePage.waitForPageLoad();

        noticePage.clickNoticeTab();
        NoticePage tabPage = new NoticePage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("NoticePageURL",PageLabels.noticePageTitle);
    }

    @AfterMethod
    public void tearDown(Method method) {
            new HomePage(driver).navi.clickLogoutLink();
    }

}
