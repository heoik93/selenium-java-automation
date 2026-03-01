package com.project.ui.customerSupport;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.customerSupport.QnABoardPage;
import com.project.utils.ScreenshotSoftAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class QnABoardPageTest extends BaseTest {

    @BeforeMethod
    public void setupLogin() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToQnABoardPage();
    }

    @Test(testName = "QnABoardPage Text Test")
    public void QnABoardPage_TextTest(){
        QnABoardPage qnaBoardPage = new QnABoardPage(driver);
        qnaBoardPage.waitForPageLoad();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        //탭
        String reviewBoardTab = qnaBoardPage.getLabel((QnABoardPage.QnABoardPageLabel.REVIEWBOARDTAB));
        softAssert.assertEquals(reviewBoardTab, PageLabels.customerSupportPage_reviewBoardTab);

        String FAQBoardTab = qnaBoardPage.getLabel((QnABoardPage.QnABoardPageLabel.FAQBOARDTAB));
        softAssert.assertEquals(FAQBoardTab, PageLabels.customerSupportPage_FAQBoardTab);

        String QnABoardTab = qnaBoardPage.getLabel((QnABoardPage.QnABoardPageLabel.QNABOARDTAB));
        softAssert.assertEquals(QnABoardTab, PageLabels.customerSupportPage_QnABoardTab);

        String NoticeTab = qnaBoardPage.getLabel((QnABoardPage.QnABoardPageLabel.NOTICETAB));
        softAssert.assertEquals(NoticeTab, PageLabels.customerSupportPage_NoticeTab);

        //페이지
        String boardTitle = qnaBoardPage.getLabel((QnABoardPage.QnABoardPageLabel.BOARDTITLE));
        softAssert.assertEquals(boardTitle, PageLabels.qnaBoardPage_BoardTitle);

        String boardSubText = qnaBoardPage.getLabel((QnABoardPage.QnABoardPageLabel.BOARDSUBTEXT));
        softAssert.assertEquals(boardSubText, PageLabels.qnaBoardPage_BoardSubText);

        //게시물 라벨
        String listTitle_Label = qnaBoardPage.getLabel((QnABoardPage.QnABoardPageLabel.LISTTITLE_LABEL));
        softAssert.assertEquals(listTitle_Label, PageLabels.qnaBoardList_ListTitle_Label);

        String listCreateDate_Label = qnaBoardPage.getLabel((QnABoardPage.QnABoardPageLabel.LISTCREATEDATE_LABEL));
        softAssert.assertEquals(listCreateDate_Label, PageLabels.qnaBoardList_ListCreateDate_Label);

        String listAnswerStatus_Label = qnaBoardPage.getLabel((QnABoardPage.QnABoardPageLabel.LISTANSWERSTATUS_LABEL));
        softAssert.assertEquals(listAnswerStatus_Label, PageLabels.qnaBoardList_ListAnswerStatus_Label);

        String createButton = qnaBoardPage.getLabel((QnABoardPage.QnABoardPageLabel.CREATEQNABUTTON));
        softAssert.assertEquals(createButton, PageLabels.qnaBoardList_CreateButton);

        softAssert.assertAll();
    }

    @AfterMethod
    private void Logout() {
        QnABoardPage qnaBoardPage = new QnABoardPage(driver);
        qnaBoardPage.navi.clickLogoutLink();
    }
}