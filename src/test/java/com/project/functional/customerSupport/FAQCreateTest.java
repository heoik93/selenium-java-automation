package com.project.functional.customerSupport;

import com.project.base.BaseTest;
import com.project.constants.AppMessages;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.customerSupport.FAQBoardPage;
import com.project.page.customerSupport.FAQCreatePage;
import com.project.utils.ScreenshotSoftAssert;
import config.ConfigReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FAQCreateTest extends BaseTest {

    @BeforeMethod
    public void setupLogin() {
        loginAsAdminUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToFAQBoardPage();
    }


    @Test(testName = "FAQ Create Submit Rule Test")
    public void FAQCreatePage_SubmitRuleTest(){
        FAQBoardPage faqBoardPage = new FAQBoardPage(driver);
        faqBoardPage.waitForPageLoad();
        faqBoardPage.clickCreateButton();

        FAQCreatePage faqCreatePage = new FAQCreatePage(driver);
        faqCreatePage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        String Title = config.getProperty("FQA_Title");
        String Category = faqCreatePage.selectRandomCategory();
        String Content = config.getProperty("FQA_Content");

        //타이틀 미작성
        faqCreatePage.inputCategory(Category);
        faqCreatePage.inputContent(Content);
        faqCreatePage.clickSubmitButton();

        softAssert.assertEquals(faqCreatePage.alertGetText(), AppMessages.faqCreatePage_Create_NoTitle_AlertMsg,
                "[FAIL]FAQ등록시 타이틀이 없을때의 Alert 메세지가 올바르지 않습니다.");
        faqCreatePage.alertAccept();
        softAssert.assertEquals(faqCreatePage.getPageTitle(), PageLabels.FAQCreatePageTitle, "[FAIL]현재 URL이 FAQ등록 페이지 타이틀이 아닙니다.");
        softAssert.assertEquals(faqCreatePage.getCurrentUrl(),config.getProperty("FAQCreatePageURL"), "[FAIL]현재 URL이 FAQ등록 URL이 아닙니다.");

        //컨텐츠 미작성
        driver.navigate().refresh();
        FAQCreatePage faqCreatePage_2nd = new FAQCreatePage(driver);
        faqCreatePage_2nd.waitForPageLoad();

        faqCreatePage_2nd.inputTitle(Title);
        faqCreatePage_2nd.inputCategory(Category);
        faqCreatePage_2nd.clickSubmitButton();

        softAssert.assertEquals(faqCreatePage_2nd.alertGetText(), AppMessages.faqCreatePage_Create_NoContent_AlertMsg,
                "[FAIL]FAQ등록시 내용이 없을때의 Alert 메세지가 올바르지 않습니다.");
        faqCreatePage_2nd.alertAccept();
        softAssert.assertEquals(faqCreatePage_2nd.getPageTitle(), PageLabels.FAQCreatePageTitle, "[FAIL]현재 URL이 FAQ등록 페이지 타이틀이 아닙니다.");
        softAssert.assertEquals(faqCreatePage_2nd.getCurrentUrl(),config.getProperty("FAQCreatePageURL"), "[FAIL]현재 URL이 FAQ등록 URL이 아닙니다.");

        //카테고리 미작성
        driver.navigate().refresh();
        FAQCreatePage faqCreatePage_3rd = new FAQCreatePage(driver);
        faqCreatePage_3rd.waitForPageLoad();

        faqCreatePage_3rd.inputTitle(Title);
        faqCreatePage_3rd.inputContent(Content);
        faqCreatePage_3rd.clickSubmitButton();

        softAssert.assertEquals(faqCreatePage_3rd.alertGetText(), AppMessages.faqCreatePage_Create_NoCategory_AlertMsg,
                "[FAIL]FAQ등록시 카테고리 미선택시의 Alert 메세지가 올바르지 않습니다.");
        faqCreatePage_3rd.alertAccept();
        softAssert.assertEquals(faqCreatePage_3rd.getPageTitle(), PageLabels.FAQCreatePageTitle, "[FAIL]현재 URL이 FAQ등록 페이지 타이틀이 아닙니다.");
        softAssert.assertEquals(faqCreatePage_3rd.getCurrentUrl(),config.getProperty("FAQCreatePageURL"), "[FAIL]현재 URL이 FAQ등록 URL이 아닙니다.");

        softAssert.assertAll();
    }


    @Test(testName = "FAQ Create/ModifyPage CancelButtonTest")
    public void FAQCreateModifyPage_CancelButtonTest(){
        FAQBoardPage faqBoardPage = new FAQBoardPage(driver);
        faqBoardPage.waitForPageLoad();
        faqBoardPage.clickCreateButton();

        FAQCreatePage faqCreatePage = new FAQCreatePage(driver);
        faqCreatePage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        faqCreatePage.clickCancelButton();
        FAQBoardPage faqBoardPage_2nd = new FAQBoardPage(driver);
        faqBoardPage_2nd.waitForPageLoad();

        softAssert.assertEquals(faqBoardPage_2nd.getPageTitle(), PageLabels.FAQBoardPageTitle, "[FAIL]현재 URL이 FAQ 페이지 타이틀이 아닙니다.");
        softAssert.assertEquals(faqBoardPage_2nd.getCurrentUrl(),config.getProperty("FAQBoardPageURL"), "[FAIL]현재 URL이 FAQ URL이 아닙니다.");

        faqBoardPage_2nd.clickRandomModifyButton();
        FAQCreatePage faqModifyPage = new FAQCreatePage(driver);
        faqModifyPage.waitForPageLoad();

        faqModifyPage.clickModifyCancelButton();
        FAQBoardPage faqBoardPage_3rd = new FAQBoardPage(driver);
        faqBoardPage_3rd.waitForPageLoad();


        softAssert.assertEquals(faqBoardPage_3rd.getPageTitle(), PageLabels.FAQBoardPageTitle, "[FAIL]현재 URL이 FAQ 페이지 타이틀이 아닙니다.");
        softAssert.assertEquals(faqBoardPage_3rd.getCurrentUrl(),config.getProperty("FAQBoardPageURL"), "[FAIL]현재 URL이 FAQ URL이 아닙니다.");

        softAssert.assertAll();
    }


    @AfterMethod
    private void Logout() {
        FAQCreatePage faqCreatePage = new FAQCreatePage(driver);
        faqCreatePage.navi.clickLogoutLink();
    }


}
