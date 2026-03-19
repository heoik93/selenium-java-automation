package com.project.ui.customerSupport;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.customerSupport.FAQBoardPage;
import com.project.page.customerSupport.FAQCreatePage;
import com.project.utils.ScreenshotSoftAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class FAQCreatePageTest extends BaseTest {

    @BeforeMethod
    public void setupLogin() {
        loginAsAdminUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToFAQBoardPage();
        FAQBoardPage faqBoard = new FAQBoardPage(driver);
        faqBoard.waitForPageLoad();
        faqBoard.clickCreateButton();
    }

    @Test(testName = "FAQCreate/ModifyPage Text Test")
    public void FAQCreateModifyPage_TextTest(){
        FAQCreatePage faqCreatePage = new FAQCreatePage(driver);
        faqCreatePage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        //탭
        String reviewBoardTab = faqCreatePage.getLabel((FAQCreatePage.FAQCreatePageLabel.REVIEWBOARDTAB));
        softAssert.assertEquals(reviewBoardTab, PageLabels.customerSupportPage_reviewBoardTab);

        String FAQBoardTab = faqCreatePage.getLabel((FAQCreatePage.FAQCreatePageLabel.FAQBOARDTAB));
        softAssert.assertEquals(FAQBoardTab, PageLabels.customerSupportPage_FAQBoardTab);

        String QnABoardTab = faqCreatePage.getLabel((FAQCreatePage.FAQCreatePageLabel.QNABOARDTAB));
        softAssert.assertEquals(QnABoardTab, PageLabels.customerSupportPage_QnABoardTab);

        String NoticeTab = faqCreatePage.getLabel((FAQCreatePage.FAQCreatePageLabel.NOTICETAB));
        softAssert.assertEquals(NoticeTab, PageLabels.customerSupportPage_NoticeTab);

        //페이지
        String faqCreate_PageTitle = faqCreatePage.getLabel((FAQCreatePage.FAQCreatePageLabel.FAQCREATE_PAGETITLE));
        softAssert.assertEquals(faqCreate_PageTitle, PageLabels.faqCreatePage_PageTitle);

        String faqCreate_CategoryLabel = faqCreatePage.getLabel((FAQCreatePage.FAQCreatePageLabel.FAQCREATE_CATEGORYLABEL));
        softAssert.assertEquals(faqCreate_CategoryLabel, PageLabels.faqCreatePage_CategoryLabel);

        String faqCreate_TitleLabel = faqCreatePage.getLabel((FAQCreatePage.FAQCreatePageLabel.FAQCREATE_TITLELABEL));
        softAssert.assertEquals(faqCreate_TitleLabel, PageLabels.faqCreatePage_TitleLabel);

        String faqCreate_ContentLabel = faqCreatePage.getLabel((FAQCreatePage.FAQCreatePageLabel.FAQCREATE_CONTENTLABEL));
        softAssert.assertEquals(faqCreate_ContentLabel, PageLabels.faqCreatePage_ContentLabel);

        String submitButton = faqCreatePage.getLabel((FAQCreatePage.FAQCreatePageLabel.SUBMITBUTTON));
        softAssert.assertEquals(submitButton, PageLabels.faqCreatePage_submitButton);

        String cancelButton = faqCreatePage.getLabel((FAQCreatePage.FAQCreatePageLabel.CANCELBUTTON));
        softAssert.assertEquals(cancelButton, PageLabels.faqCreatePage_cancelButton);

        //수정페이지
        faqCreatePage.clickFAQBoardTab();
        FAQBoardPage faqBoardPage_2nd = new FAQBoardPage(driver);
        faqBoardPage_2nd.waitForPageLoad();
        faqBoardPage_2nd.clickRandomModifyButton();

        FAQCreatePage faqModifyPage = new FAQCreatePage(driver);
        faqModifyPage.waitForPageLoad();

        String faqModify_PageTitle = faqCreatePage.getLabel((FAQCreatePage.FAQCreatePageLabel.FAQCREATE_PAGETITLE));
        softAssert.assertEquals(faqModify_PageTitle, PageLabels.faqModifyPage_PageTitle);

        String faqModify_CategoryLabel = faqCreatePage.getLabel((FAQCreatePage.FAQCreatePageLabel.FAQCREATE_CATEGORYLABEL));
        softAssert.assertEquals(faqModify_CategoryLabel, PageLabels.faqModifyPage_CategoryLabel);

        String faqModify_TitleLabel = faqCreatePage.getLabel((FAQCreatePage.FAQCreatePageLabel.FAQCREATE_TITLELABEL));
        softAssert.assertEquals(faqModify_TitleLabel, PageLabels.faqModifyPage_TitleLabel);

        String faqModify_ContentLabel = faqCreatePage.getLabel((FAQCreatePage.FAQCreatePageLabel.FAQCREATE_CONTENTLABEL));
        softAssert.assertEquals(faqModify_ContentLabel, PageLabels.faqModifyPage_ContentLabel);

        String faqModify_modifyButton = faqCreatePage.getLabel((FAQCreatePage.FAQCreatePageLabel.MODIFYBUTTON));
        softAssert.assertEquals(faqModify_modifyButton, PageLabels.faqModifyPage_ModifyButton);

        String faqModify_cancelButton = faqCreatePage.getLabel((FAQCreatePage.FAQCreatePageLabel.MODIFYCANCELBUTTON));
        softAssert.assertEquals(faqModify_cancelButton, PageLabels.faqModifyPage_cancelButton);

        softAssert.assertAll();
    }

    @Test(testName = "FAQCreate/ModifyPage CategoryListBox Test")
    public void FAQCreateModifyPage_CategoryListBoxTest(){
        FAQCreatePage faqCreatePage = new FAQCreatePage(driver);
        faqCreatePage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        List<String> actualOptions = faqCreatePage.getAllSearchOptions();
        List<String> expectedOptions = PageLabels.faqBoard_CategoryOptions;

        softAssert.assertEquals(actualOptions.size(), expectedOptions.size());
        softAssert.assertEquals(actualOptions, expectedOptions, "[FAIL]평점리스트 구성이나 순서가 잘못되었습니다.(작성페이지)");

        faqCreatePage.clickFAQBoardTab();
        FAQBoardPage faqBoardPage_2nd = new FAQBoardPage(driver);
        faqBoardPage_2nd.waitForPageLoad();
        faqBoardPage_2nd.clickRandomModifyButton();

        FAQCreatePage faqModifyPage = new FAQCreatePage(driver);
        faqModifyPage.waitForPageLoad();

        List<String> actualOptions_modify = faqCreatePage.getAllSearchOptions();

        softAssert.assertEquals(actualOptions_modify.size(), expectedOptions.size());
        softAssert.assertEquals(actualOptions_modify, expectedOptions, "[FAIL]평점리스트 구성이나 순서가 잘못되었습니다.(수정페이지)"); //현재DF

        softAssert.assertAll();
    }

    @AfterMethod
    private void Logout() {
        FAQCreatePage faqCreatePage = new FAQCreatePage(driver);
        faqCreatePage.navi.clickLogoutLink();
    }

}
