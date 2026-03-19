package com.project.functional.customerSupport;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.customerSupport.FAQBoardPage;
import com.project.page.customerSupport.NoticePage;
import com.project.page.customerSupport.QnABoardPage;
import com.project.page.customerSupport.ReviewBoardPage;
import com.project.utils.ScreenshotSoftAssert;
import config.ConfigReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class CustomerSupportPageTabTest extends BaseTest {

    private final ConfigReader config = new ConfigReader();
    private ScreenshotSoftAssert softAssert;

    private void verifyTabPage(String expectedUrlKey, String expectedTitle) {
        String currentUrl = driver.getCurrentUrl();
        String pageTitle = driver.getTitle();

        softAssert.assertEquals(currentUrl, config.getProperty(expectedUrlKey),"[FAIL]탭클릭 후의 URL이 올바르지 않습니다.");
        softAssert.assertEquals(pageTitle, expectedTitle,"[FAIL]탭클릭 후의 페이지타이틀이 올바르지 않습니다.");
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

    //탭테스트 (후기게시판페이지 : 후기게시판탭)
    @Test(testName = "ReviewBoardPage ReviewBoardTabTest", groups = {"ReviewBoardPage"})
    public void reviewBoardPage_ReviewBoardTabTest() {
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();

        reviewBoardPage.clickReviewBoardTab();
        ReviewBoardPage tabPage = new ReviewBoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("ReviewBoardPageURL",PageLabels.reviewBoardPageTitle);
    }

    //탭테스트 (후기게시판페이지 : 질문게시판탭)
    @Test(testName = "ReviewBoardPage FAQBoardTabTest", groups = {"FAQBoardPage"})
    public void reviewBoardPage_FAQBoardTabTest() {
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();

        reviewBoardPage.clickFAQBoardTab();
        FAQBoardPage tabPage = new FAQBoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("FAQBoardPageURL",PageLabels.FAQBoardPageTitle);
    }

    //탭테스트 (후기게시판페이지 : 1:1 문의탭)
    @Test(testName = "ReviewBoardPage QnABoardTabTest", groups = {"QnABoardPage"})
    public void reviewBoardPage_QnABoardTabTest() {
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();

        reviewBoardPage.clickQnABoardTab();
        QnABoardPage tabPage = new QnABoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("QnABoardPageURL",PageLabels.QnABoardPageTitle);
    }

    //탭테스트 (후기게시판페이지 : 공지사항탭)
    @Test(testName = "ReviewBoardPage NoticeTabTest", groups = {"NoticePage"})
    public void reviewBoardPage_NoticeTabTest() {
        ReviewBoardPage reviewBoardPage = new ReviewBoardPage(driver);
        reviewBoardPage.waitForPageLoad();

        reviewBoardPage.clickNoticeTab();
        NoticePage tabPage = new NoticePage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("NoticePageURL",PageLabels.noticePageTitle);
    }

    //탭테스트 (질문게시판페이지 : 후기게시판탭)
    @Test(testName = "FAQBoardPage ReviewBoardTabTest", groups = {"ReviewBoardPage"})
    public void faqBoardPage_ReviewBoardTabTest() {
        FAQBoardPage faqBoardPage = new FAQBoardPage(driver);
        faqBoardPage.waitForPageLoad();

        faqBoardPage.clickReviewBoardTab();
        ReviewBoardPage tabPage = new ReviewBoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("ReviewBoardPageURL",PageLabels.reviewBoardPageTitle);
    }

    //탭테스트 (질문게시판페이지 : 질문게시판탭)
    @Test(testName = "FAQBoardPage FAQBoardTabTest", groups = {"FAQBoardPage"})
    public void faqBoardPage_FAQBoardTabTest() {
        FAQBoardPage faqBoardPage = new FAQBoardPage(driver);
        faqBoardPage.waitForPageLoad();

        faqBoardPage.clickFAQBoardTab();
        FAQBoardPage tabPage = new FAQBoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("FAQBoardPageURL",PageLabels.FAQBoardPageTitle);
    }

    //탭테스트 (질문게시판페이지 : 1:1 문의탭)
    @Test(testName = "FAQBoardPage QnABoardTabTest", groups = {"QnABoardPage"})
    public void faqBoardPage_QnABoardTabTest() {
        FAQBoardPage faqBoardPage = new FAQBoardPage(driver);
        faqBoardPage.waitForPageLoad();

        faqBoardPage.clickQnABoardTab();
        QnABoardPage tabPage = new QnABoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("QnABoardPageURL",PageLabels.QnABoardPageTitle);
    }

    //탭테스트 (질문게시판페이지 : 공지사항탭)
    @Test(testName = "FAQBoardPage NoticeTabTest", groups = {"NoticePage"})
    public void faqBoardPage_NoticeTabTest() {
        FAQBoardPage faqBoardPage = new FAQBoardPage(driver);
        faqBoardPage.waitForPageLoad();

        faqBoardPage.clickNoticeTab();
        NoticePage tabPage = new NoticePage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("NoticePageURL",PageLabels.noticePageTitle);
    }

    //탭테스트 (1:1 문의페이지 : 후기게시판탭)
    @Test(testName = "QnABoardPage ReviewBoardTabTest", groups = {"ReviewBoardPage"})
    public void qnaBoardPage_ReviewBoardTabTest() {
        QnABoardPage qnaBoardPage = new QnABoardPage(driver);
        qnaBoardPage.waitForPageLoad();

        qnaBoardPage.clickReviewBoardTab();
        ReviewBoardPage tabPage = new ReviewBoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("ReviewBoardPageURL",PageLabels.reviewBoardPageTitle);
    }

    //탭테스트 (1:1 문의페이지 : 질문게시판탭)
    @Test(testName = "QnABoardPage FAQBoardTabTest", groups = {"FAQBoardPage"})
    public void qnaBoardPage_FAQBoardTabTest() {
        QnABoardPage qnaBoardPage = new QnABoardPage(driver);
        qnaBoardPage.waitForPageLoad();

        qnaBoardPage.clickFAQBoardTab();
        FAQBoardPage tabPage = new FAQBoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("FAQBoardPageURL",PageLabels.FAQBoardPageTitle);
    }

    //탭테스트 (1:1 문의페이지 : 1:1 문의탭)
    @Test(testName = "QnABoardPage QnABoardTabTest", groups = {"QnABoardPage"})
    public void qnaBoardPage_QnABoardTabTest() {
        QnABoardPage qnaBoardPage = new QnABoardPage(driver);
        qnaBoardPage.waitForPageLoad();

        qnaBoardPage.clickQnABoardTab();
        QnABoardPage tabPage = new QnABoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("QnABoardPageURL",PageLabels.QnABoardPageTitle);
    }

    //탭테스트 (1:1 문의페이지 : 공지사항탭)
    @Test(testName = "QnABoardPage NoticeTabTest", groups = {"NoticePage"})
    public void qnaBoardPage_NoticeTabTest() {
        QnABoardPage qnaBoardPage = new QnABoardPage(driver);
        qnaBoardPage.waitForPageLoad();

        qnaBoardPage.clickNoticeTab();
        NoticePage tabPage = new NoticePage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("NoticePageURL",PageLabels.noticePageTitle);
    }

    //탭테스트 (공지사항페이지 : 후기게시판탭)
    @Test(testName = "NoticePage ReviewBoardTabTest", groups = {"ReviewBoardPage"})
    public void noticePage_ReviewBoardTabTest() {
        NoticePage noticePage = new NoticePage(driver);
        noticePage.waitForPageLoad();

        noticePage.clickReviewBoardTab();
        ReviewBoardPage tabPage = new ReviewBoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("ReviewBoardPageURL",PageLabels.reviewBoardPageTitle);
    }

    //탭테스트 (공지사항페이지 : 질문게시판탭)
    @Test(testName = "NoticePage FAQBoardTabTest", groups = {"FAQBoardPage"})
    public void noticePage_FAQBoardTabTest() {
        NoticePage noticePage = new NoticePage(driver);
        noticePage.waitForPageLoad();

        noticePage.clickFAQBoardTab();
        FAQBoardPage tabPage = new FAQBoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("FAQBoardPageURL",PageLabels.FAQBoardPageTitle);
    }

    //탭테스트 (공지사항페이지 : 1:1 문의탭)
    @Test(testName = "NoticePage QnABoardTabTest", groups = {"QnABoardPage"})
    public void noticePage_QnABoardTabTest() {
        NoticePage noticePage = new NoticePage(driver);
        noticePage.waitForPageLoad();

        noticePage.clickQnABoardTab();
        QnABoardPage tabPage = new QnABoardPage(driver);
        tabPage.waitForPageLoad();

        verifyTabPage("QnABoardPageURL",PageLabels.QnABoardPageTitle);
    }

    //탭테스트 (공지사항페이지 : 공지사항탭)
    @Test(testName = "NoticePage NoticeTabTest", groups = {"NoticePage"})
    public void noticePage_NoticeTabTest() {
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
