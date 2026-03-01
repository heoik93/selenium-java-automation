package com.project.ui.customerSupport;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.customerSupport.QnABoardPage;
import com.project.page.customerSupport.QnACreatePage;
import com.project.page.customerSupport.QnADetailPage;
import com.project.utils.ScreenshotSoftAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class QnACreatePageTest extends BaseTest {

    @BeforeMethod(onlyForGroups = "QnACreateTest")
    public void setupLogin_Create() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToQnABoardPage();
        QnABoardPage qnaBoardPage = new QnABoardPage(driver);
        qnaBoardPage.waitForPageLoad();
        qnaBoardPage.clickCreateQnAButton();
    }

    @BeforeMethod(onlyForGroups = "QnADetailTest")
    public void setupLogin_Detail() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToQnABoardPage();
        QnABoardPage qnaBoardPage = new QnABoardPage(driver);
        qnaBoardPage.waitForPageLoad();
        qnaBoardPage.clickRandomQnAList();
    }

    @Test(testName = "QnACreatePage Text Test", groups = "QnACreateTest")
    public void QnACreatePage_TextTest(){
        QnACreatePage qnaCreatePage = new QnACreatePage(driver);
        qnaCreatePage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        String PageTitle = qnaCreatePage.getLabel((QnACreatePage.QnACreatePageLabel.CREATE_PAGETITLE));
        softAssert.assertEquals(PageTitle, PageLabels.qnaCreatePage_PageTitle);

        String TitleLabel = qnaCreatePage.getLabel((QnACreatePage.QnACreatePageLabel.CREATE_TITLELABEL));
        softAssert.assertEquals(TitleLabel, PageLabels.qnaCreatePage_TitleLabel);

        String ContentLabel = qnaCreatePage.getLabel((QnACreatePage.QnACreatePageLabel.CREATE_CONTENTLABEL));
        softAssert.assertEquals(ContentLabel, PageLabels.qnaCreatePage_ContentLabel);

        String CreateButton = qnaCreatePage.getLabel((QnACreatePage.QnACreatePageLabel.CREATE_CREATEBUTTON));
        softAssert.assertEquals(CreateButton, PageLabels.qnaCreatePage_CreateButton);

        String CancelButton = qnaCreatePage.getLabel((QnACreatePage.QnACreatePageLabel.CREATE_CANCELBUTTON));
        softAssert.assertEquals(CancelButton, PageLabels.qnaCreatePage_CancelButton);

        softAssert.assertAll();
    }

    @Test(testName = "QnADetailPage Text Test", groups = "QnADetailTest")
    public void QnADetailPage_TextTest(){
        QnADetailPage qnaDetailPage = new QnADetailPage(driver);
        qnaDetailPage.waitForPageLoad();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        String PageTitle = qnaDetailPage.getLabel((QnADetailPage.QnADetailPageLabel.QNADETAIL_PAGETITLE));
        softAssert.assertEquals(PageTitle, PageLabels.qnaDetailPage_PageTitle);

        String NumberLabel = qnaDetailPage.getLabel((QnADetailPage.QnADetailPageLabel.QNADETAIL_NUMBERLABEL));
        softAssert.assertEquals(NumberLabel, PageLabels.qnaDetailPage_NumberLabel);

        String WriterLabel = qnaDetailPage.getLabel((QnADetailPage.QnADetailPageLabel.QNADETAIL_WRITERLABEL));
        softAssert.assertEquals(WriterLabel, PageLabels.qnaDetailPage_WriterLabel);

        String TitleLabel = qnaDetailPage.getLabel((QnADetailPage.QnADetailPageLabel.QNADETAIL_TITLELABEL));
        softAssert.assertEquals(TitleLabel, PageLabels.qnaDetailPage_TitleLabel);

        String CreateDateLabel = qnaDetailPage.getLabel((QnADetailPage.QnADetailPageLabel.QNADETAIL_CREATEDATELABEL));
        softAssert.assertEquals(CreateDateLabel, PageLabels.qnaDetailPage_CreateDateLabel);

        String DeleteButton = qnaDetailPage.getLabel((QnADetailPage.QnADetailPageLabel.QNADETAIL_DELETEBUTTON));
        softAssert.assertEquals(DeleteButton, PageLabels.qnaDetailPage_DeleteButton);

        String ListButton = qnaDetailPage.getLabel((QnADetailPage.QnADetailPageLabel.QNADETAIL_LISTBUTTON));
        softAssert.assertEquals(ListButton, PageLabels.qnaDetailPage_ListButton);

        softAssert.assertAll();
    }

    @AfterMethod(onlyForGroups = "QnACreateTest")
    private void Logout_Create() {
        QnACreatePage qnaCreatePage = new QnACreatePage(driver);
        qnaCreatePage.navi.clickLogoutLink();
    }

    @AfterMethod(onlyForGroups = "QnADetailTest")
    private void Logout_Detail() {
        QnADetailPage qnaDetailPage = new QnADetailPage(driver);
        qnaDetailPage.navi.clickLogoutLink();
    }








}
