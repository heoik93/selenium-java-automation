package com.project.ui.customerSupport;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.customerSupport.NoticeCreatePage;
import com.project.page.customerSupport.NoticeDetailPage;
import com.project.page.customerSupport.NoticePage;
import com.project.utils.ScreenshotSoftAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class NoticePageTest extends BaseTest {
    @DataProvider(name = "loginUser")
    public Object[][] loginUser() {
        return new Object[][] {
                { "Default"},
                { "Admin"},
        };
    }

    @BeforeMethod(onlyForGroups = "Default")
    public void setupLogin(Object[] data) {
        if (data != null && data.length > 0) {
            String loginUser = (String) data[0];
            switch (loginUser) {
                case "Admin":    loginAsAdminUser_2();    break;
                case "Default":  connectToUrl();  break;
            }
        } else {
            connectToUrl();
        }

        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToNoticePage();
    }

    @BeforeMethod(onlyForGroups = "Admin")
    public void setupLogin_Admin() {
        loginAsAdminUser_2();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToNoticePage();
    }

    @Test(testName = "NoticePage Text Test",dataProvider = "loginUser",groups = "Default")
    public void NoticePage_TextTest(String loginUser) {
        NoticePage noticePage = new NoticePage(driver);
        noticePage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        String searchButton = noticePage.getLabel(NoticePage.NoticePageLabel.SEARCHBUTTON);
        softAssert.assertEquals(searchButton, PageLabels.noticePage_searchButton);

        if(loginUser.equals("Admin")) {
            String createButton = noticePage.getLabel(NoticePage.NoticePageLabel.CREATEBUTTON);
            softAssert.assertEquals(createButton, PageLabels.noticePage_createButton);
        }

        String ListNumber_Label = noticePage.getLabel(NoticePage.NoticePageLabel.LISTNUMBER_LABEL);
        softAssert.assertEquals(ListNumber_Label, PageLabels.noticePage_ListNumberLabel);

        String ListTitle_Label = noticePage.getLabel(NoticePage.NoticePageLabel.LISTTITLE_LABEL);
        softAssert.assertEquals(ListTitle_Label, PageLabels.noticePage_ListTitleLabel);

        String ListRegiDate_Label = noticePage.getLabel(NoticePage.NoticePageLabel.LISTREGIDATE_LABEL);
        softAssert.assertEquals(ListRegiDate_Label, PageLabels.noticePage_ListRegiDateLabel);

        softAssert.assertAll();
    }

    @Test(testName = "NoticeCreatePage Text Test", groups = "Admin")
    public void NoticeCreatePage_TextTest() {
        NoticePage noticePage = new NoticePage(driver);
        noticePage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        noticePage.clickCreateButton();
        NoticeCreatePage noticeCreatePage = new NoticeCreatePage(driver);
        noticeCreatePage.waitForPageLoad();

        String NoticeTitleLabel = noticeCreatePage.getLabel(NoticeCreatePage.NoticeCreatePageLabel.CREATE_NOTICETITLELABEL);
        softAssert.assertEquals(NoticeTitleLabel, PageLabels.noticeCreatePage_NoticeTitleLabel);

        String NoticeTitleRuleText = noticeCreatePage.getLabel(NoticeCreatePage.NoticeCreatePageLabel.CREATE_NOTICETITLERULETEXT);
        softAssert.assertEquals(NoticeTitleRuleText, PageLabels.noticeCreatePage_NoticeTitleRuleText);

        String ContentLabel = noticeCreatePage.getLabel(NoticeCreatePage.NoticeCreatePageLabel.CREATE_CONTENTLABEL);
        softAssert.assertEquals(ContentLabel, PageLabels.noticeCreatePage_ContentLabel);

        String SubmitButton = noticeCreatePage.getLabel(NoticeCreatePage.NoticeCreatePageLabel.CREATE_SUBMITBUTTON);
        softAssert.assertEquals(SubmitButton, PageLabels.noticeCreatePage_SubmitButton);

        noticeCreatePage.clickNoticeTab();

        softAssert.assertAll();
    }

    @Test(testName = "NoticeModifyPage Text Test", groups = "Admin")
    public void NoticeModifyPage_TextTest() {
        NoticePage noticePage = new NoticePage(driver);
        noticePage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        noticePage.selectTestContent_Detail();

        NoticeDetailPage noticeDetailPage = new NoticeDetailPage(driver);
        noticeDetailPage.waitForPageLoad();

        noticeDetailPage.clickModifyButton();

        NoticeCreatePage noticeModifyPage = new NoticeCreatePage(driver);
        noticeModifyPage.waitForPageLoad();

        String writerLabel = noticeModifyPage.getLabel(NoticeCreatePage.NoticeCreatePageLabel.MODIFY_WRITERLABEL);
        softAssert.assertEquals(writerLabel, PageLabels.noticeModifyPage_writerLabel);

        String TitleLabel = noticeModifyPage.getLabel(NoticeCreatePage.NoticeCreatePageLabel.MODIFY_TITLELABEL);
        softAssert.assertEquals(TitleLabel, PageLabels.noticeModifyPage_TitleLabel);

        String ContentLabel = noticeModifyPage.getLabel(NoticeCreatePage.NoticeCreatePageLabel.MODIFY_CONTENTLABEL);
        softAssert.assertEquals(ContentLabel, PageLabels.noticeModifyPage_ContentLabel);

        String ModifyButton = noticeModifyPage.getLabel(NoticeCreatePage.NoticeCreatePageLabel.MODIFY_MODIFYBUTTON);
        softAssert.assertEquals(ModifyButton, PageLabels.noticeModifyPage_ModifyButton);

        noticeModifyPage.clickNoticeTab();

        softAssert.assertAll();
    }

    @Test(testName = "NoticeDetailPage Text Test",dataProvider = "loginUser",groups = "Default")
    public void NoticeDetailPage_TextTest(String loginUser) {
        NoticePage noticePage = new NoticePage(driver);
        noticePage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        noticePage.selectTestContent_Detail();

        NoticeDetailPage noticeDetailPage = new NoticeDetailPage(driver);
        noticeDetailPage.waitForPageLoad();

        String ListButton = noticeDetailPage.getLabel(NoticeDetailPage.NoticeDetailPageLabel.DETAIL_LISTBUTTON);
        softAssert.assertEquals(ListButton, PageLabels.noticeDetailPage_ListButton);

        if(loginUser.equals("Admin")) {
            String ModifyButton = noticeDetailPage.getLabel(NoticeDetailPage.NoticeDetailPageLabel.DETAIL_MODIFYBUTTON);
            softAssert.assertEquals(ModifyButton, PageLabels.noticeDetailPage_ModifyButton);

            String DeleteButton = noticeDetailPage.getLabel(NoticeDetailPage.NoticeDetailPageLabel.DETAIL_DELETEBUTTON);
            softAssert.assertEquals(DeleteButton, PageLabels.noticeDetailPage_DeleteButton);
        }

        noticeDetailPage.clickNoticeTab();

        softAssert.assertAll();

    }

    @AfterMethod(onlyForGroups = "Admin")
    private void Logout() {
        NoticePage noticePage = new NoticePage(driver);
        noticePage.waitForPageLoad();
        noticePage.navi.clickLogoutLink();
    }

}
