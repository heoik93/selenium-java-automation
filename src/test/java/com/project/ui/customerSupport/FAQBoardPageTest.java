package com.project.ui.customerSupport;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.customerSupport.FAQBoardPage;
import com.project.utils.ScreenshotSoftAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FAQBoardPageTest extends BaseTest {

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
        afterLogin.navi.goToFAQBoardPage();
    }

    @Test(testName = "FAQBoardPage Text Test",dataProvider = "loginUser")
    public void FAQBoardPage_TextTest(String loginUser) {
        FAQBoardPage faqBoardPage = new FAQBoardPage(driver);
        faqBoardPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        String reviewBoardTab = faqBoardPage.getLabel(FAQBoardPage.FAQBoardPageLabel.REVIEWBOARDTAB);
        softAssert.assertEquals(reviewBoardTab,PageLabels.customerSupportPage_reviewBoardTab);

        String FAQBoardTab = faqBoardPage.getLabel(FAQBoardPage.FAQBoardPageLabel.FAQBOARDTAB);
        softAssert.assertEquals(FAQBoardTab,PageLabels.customerSupportPage_FAQBoardTab);

        String QnABoardTab = faqBoardPage.getLabel(FAQBoardPage.FAQBoardPageLabel.QNABOARDTAB);
        softAssert.assertEquals(QnABoardTab,PageLabels.customerSupportPage_QnABoardTab);

        String NoticeTab = faqBoardPage.getLabel(FAQBoardPage.FAQBoardPageLabel.NOTICETAB);
        softAssert.assertEquals(NoticeTab,PageLabels.customerSupportPage_NoticeTab);

        String pageTitleLabel = faqBoardPage.getLabel(FAQBoardPage.FAQBoardPageLabel.PAGETITLELABEL);
        softAssert.assertEquals(pageTitleLabel,PageLabels.faqBoardPage_pageTitleLabel);

        String pageTitleUnderTextLabel = faqBoardPage.getLabel(FAQBoardPage.FAQBoardPageLabel.PAGETITLEUNDERTEXTLABEL);
        softAssert.assertEquals(pageTitleUnderTextLabel,PageLabels.faqBoardPage_pageTitleUnderTextLabel);

        String boardFilterServiceButton = faqBoardPage.getLabel(FAQBoardPage.FAQBoardPageLabel.BOARDFILTERSERVICEBUTTON);
        softAssert.assertEquals(boardFilterServiceButton,PageLabels.faqBoardPage_boardFilterServiceButton);

        String boardFilterOrderButton = faqBoardPage.getLabel(FAQBoardPage.FAQBoardPageLabel.BOARDFILTERORDERBUTTON);
        softAssert.assertEquals(boardFilterOrderButton,PageLabels.faqBoardPage_boardFilterOrderButton);

        String boardFilterMemberButton = faqBoardPage.getLabel(FAQBoardPage.FAQBoardPageLabel.BOARDFILTERMEMBERBUTTON);
        softAssert.assertEquals(boardFilterMemberButton,PageLabels.faqBoardPage_boardFilterMemberButton);

        String boardFilterEtcButton = faqBoardPage.getLabel(FAQBoardPage.FAQBoardPageLabel.BOARDFILTERETCBUTTON);
        softAssert.assertEquals(boardFilterEtcButton,PageLabels.faqBoardPage_boardFilterEtcButton);

        String qnaBoardButton = faqBoardPage.getLabel(FAQBoardPage.FAQBoardPageLabel.QNABOARDBUTTON);
        softAssert.assertEquals(qnaBoardButton,PageLabels.faqBoardPage_qnaBoardButton);

        String FAQNumberLabel = faqBoardPage.getLabel(FAQBoardPage.FAQBoardPageLabel.FAQNUMBERLABEL);
        softAssert.assertEquals(FAQNumberLabel,PageLabels.faqBoardPage_FAQNumberLabel);

        String FAQCategoryLabel = faqBoardPage.getLabel(FAQBoardPage.FAQBoardPageLabel.FAQCATEGORYLABEL);
        softAssert.assertEquals(FAQCategoryLabel,PageLabels.faqBoardPage_FAQCategoryLabel);

        String FAQTitleLabel = faqBoardPage.getLabel(FAQBoardPage.FAQBoardPageLabel.FAQTITLELABEL);
        softAssert.assertEquals(FAQTitleLabel, PageLabels.faqBoardPage_FAQTitleLabel);

        if(loginUser.equals("Admin")) {
            String FAQModifyLabel = faqBoardPage.getLabel(FAQBoardPage.FAQBoardPageLabel.FAQMODIFYLABEL);
            softAssert.assertEquals(FAQModifyLabel, PageLabels.faqBoardPage_FAQModifyLabel);

            String FAQDeleteLabel = faqBoardPage.getLabel(FAQBoardPage.FAQBoardPageLabel.FAQDELETELABEL);
            softAssert.assertEquals(FAQDeleteLabel, PageLabels.faqBoardPage_FAQDeleteLabel);

            String FQACreateButton = faqBoardPage.getLabel(FAQBoardPage.FAQBoardPageLabel.FQACREATEBUTTON);
            softAssert.assertEquals(FQACreateButton, PageLabels.faqBoardPage_FQACreateButton);
        }

        softAssert.assertAll();
    }

    @Test(testName = "FAQBoardPage UI Display Test",dataProvider = "loginUser")
    public void FAQBoardPage_UiDisplayTest(String loginUser) {
        FAQBoardPage faqBoardPage = new FAQBoardPage(driver);
        faqBoardPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        int listCount = faqBoardPage.checkListCount();
        int openBtnCount = faqBoardPage.checkFoldButtonCount();
        int modifyBtnCount = faqBoardPage.checkModifyButtonCount();
        int delectBtnCount = faqBoardPage.checkDeleteButtonCount();



        softAssert.assertEquals(listCount, openBtnCount);

        if(loginUser.equals("Default")) {
            softAssert.assertNotEquals(listCount, modifyBtnCount);
            softAssert.assertNotEquals(listCount, delectBtnCount);
            softAssert.assertTrue(faqBoardPage.checkHiddenCreateButton());
        }

        if(loginUser.equals("Admin")) {
            softAssert.assertEquals(listCount, modifyBtnCount);
            softAssert.assertEquals(listCount, delectBtnCount);
            softAssert.assertTrue(faqBoardPage.checkCreateButton());
        }


        if (listCount == 5) {
            softAssert.assertTrue(faqBoardPage.checkPageNavi());
            System.out.println("[INFO] 페이지네비게이션이 존재합니다.");
        }
        else if (listCount < 5) {
            softAssert.assertTrue(faqBoardPage.checkHiddenPageNavi());
            System.out.println("[INFO] 페이지네비게이션이 존재 하지 않습니다.");
        }

        softAssert.assertAll();
    }


    @AfterMethod
    private void Logout() {
        FAQBoardPage faqBoardPage = new FAQBoardPage(driver);
        faqBoardPage.navi.clickLogoutLink();
    }


}