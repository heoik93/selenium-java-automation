package com.project.functional.customerSupport;

import com.project.base.BaseTest;
import com.project.constants.AppMessages;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.customerSupport.FAQBoardPage;
import com.project.page.customerSupport.FAQCreatePage;
import com.project.page.customerSupport.QnABoardPage;
import config.ConfigReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.project.utils.ScreenshotSoftAssert;

public class FAQBoardTest extends BaseTest {

    @BeforeMethod(onlyForGroups = "Default")
    public void setupLogin_default() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToFAQBoardPage();
    }

    @BeforeMethod(onlyForGroups = "Admin")
    public void setupLogin_admin() {
        loginAsAdminUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToFAQBoardPage();
    }


    @Test(testName = "FAQBoardPage Button Test", groups = "Default")
    public void FAQBoardPage_ButtonTest() throws InterruptedException {
        FAQBoardPage faqBoardPage = new FAQBoardPage(driver);
        faqBoardPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        //펼치기버튼 테스트
        int FoldListCount = faqBoardPage.checkFoldButtonCount();
        for(int i=0; i==FoldListCount; i++){
            faqBoardPage.clickFoldButton(i);
            Thread.sleep(500);
            String dataNum = faqBoardPage.getDataNum(i);
            softAssert.assertTrue(faqBoardPage.checkFold(dataNum),"[FAIL] 펼치기 버튼 클릭시 내용이 펼쳐지지 않았습니다. : "+i+"번쨰 게시물");
        }
        driver.navigate().refresh();
        faqBoardPage.waitForPageLoad();

        //카테고리 버튼
        //서비스이용
        faqBoardPage.clickFilterServiceButton();
        FAQBoardPage faqBoardPage_Service = new FAQBoardPage(driver);
        faqBoardPage_Service.waitForPageLoad();
        softAssert.assertTrue(faqBoardPage_Service.checkCategory(PageLabels.faqBoardPage_boardFilterServiceButton));

        //주문·결제·배송
        faqBoardPage_Service.clickFilterOrderButton();
        FAQBoardPage faqBoardPage_Oder = new FAQBoardPage(driver);
        faqBoardPage_Oder.waitForPageLoad();
        softAssert.assertTrue(faqBoardPage_Oder.checkCategory(PageLabels.faqBoardPage_boardFilterOrderButton));

        //회원정보
        faqBoardPage_Oder.clickFilterMemberButton();
        FAQBoardPage faqBoardPage_Member = new FAQBoardPage(driver);
        faqBoardPage_Member.waitForPageLoad();
        softAssert.assertTrue(faqBoardPage_Member.checkCategory(PageLabels.faqBoardPage_boardFilterMemberButton));

        //기타
        faqBoardPage_Member.clickFilterEtcButton();
        FAQBoardPage faqBoardPage_Etc = new FAQBoardPage(driver);
        faqBoardPage_Etc.waitForPageLoad();
        softAssert.assertTrue(faqBoardPage_Etc.checkCategory(PageLabels.faqBoardPage_boardFilterEtcButton));

        //1:1문의하기버튼
        driver.navigate().refresh();
        FAQBoardPage faqBoardPage_2nd = new FAQBoardPage(driver);
        faqBoardPage_2nd.waitForPageLoad();
        faqBoardPage_2nd.clickQnAButton();

        QnABoardPage qnaBoardPage = new QnABoardPage(driver);
        qnaBoardPage.waitForPageLoad();

        String currentUrl = qnaBoardPage.getCurrentUrl();
        String PageTitle = qnaBoardPage.getPageTitle();

        softAssert.assertEquals(currentUrl, config.getProperty("QnABoardPageURL"));
        softAssert.assertEquals(PageTitle, PageLabels.QnABoardPageTitle);

        softAssert.assertAll();
    }

    @Test(testName = "FAQBoardPage Admin Button Test", groups = "Admin")
    public void FAQBoardPage_AdminButtonTest() {
        FAQBoardPage faqBoardPage = new FAQBoardPage(driver);
        faqBoardPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        //작성하기버튼
        faqBoardPage.clickCreateButton();
        FAQCreatePage faqCreatePage = new FAQCreatePage(driver);
        faqCreatePage.waitForPageLoad();

        String currentUrl_Create = faqCreatePage.getCurrentUrl();
        String pageTitle_Create = faqCreatePage.getPageTitle();

        softAssert.assertEquals(currentUrl_Create,config.getProperty("FAQCreatePageURL"),"[FAIL]FAQ작성하기 URL이 올바르지 않습니다.");
        softAssert.assertEquals(pageTitle_Create,PageLabels.FAQCreatePageTitle,"[FAIL]FAQ작성하기 페이지 타이틀이 올바르지 않습니다.");


        //수정하기버튼
        faqCreatePage.clickFAQBoardTab();
        FAQBoardPage faqBoardPage_2nd = new FAQBoardPage(driver);
        faqBoardPage_2nd.waitForPageLoad();

        faqBoardPage_2nd.clickRandomModifyButton();
        FAQCreatePage faqModifyPage = new FAQCreatePage(driver);
        faqModifyPage.waitForPageLoad();

        String currentUrl_Modify = faqModifyPage.getCurrentUrl();
        String pageTitle_Modify = faqModifyPage.getPageTitle();

        softAssert.assertTrue(currentUrl_Modify.contains(config.getProperty("FQAModifyPageURL")),"[FAIL]FAQ수정하기 URL이 올바르지 않습니다.");
        softAssert.assertEquals(pageTitle_Modify,PageLabels.FAQModifyPageTitle,"[FAIL]FAQ수정하기 페이지 타이틀이 올바르지 않습니다.");

        softAssert.assertAll();
    }

    @Test(testName = "FAQBoardPage AdminFlow(Create/Modify/Delete) Test", groups = "Admin")
    public void FAQBoardPage_AdminFlowTest(){
        FAQBoardPage faqBoardPage = new FAQBoardPage(driver);
        faqBoardPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        faqBoardPage.clickCreateButton();

        FAQCreatePage faqCreatePage = new FAQCreatePage(driver);
        faqCreatePage.waitForPageLoad();

        //관리자
        //작성,수정,삭제하기버튼
        String Title = config.getProperty("FQA_Title");
        String Category = faqCreatePage.selectRandomCategory();
        String Content = config.getProperty("FQA_Content");

        faqCreatePage.CreateFAQ(Title,Category,Content);
        faqCreatePage.clickSubmitButton();

        softAssert.assertEquals(faqCreatePage.alertGetText(), AppMessages.faqCreatePage_Create_AlertMsg_1);
        faqCreatePage.alertAccept();
        softAssert.assertEquals(faqCreatePage.alertGetText(), AppMessages.faqCreatePage_Create_AlertMsg_2);
        faqCreatePage.alertAccept();

        FAQBoardPage faqBoardPage_2nd = new FAQBoardPage(driver);
        faqBoardPage_2nd.waitForPageLoad();
        softAssert.assertTrue(faqBoardPage_2nd.checkTestFAQ(Title, Category, Content,"modify"), "[FAIL] 작성한 FAQ를 찾을 수 없습니다.");

        FAQCreatePage faqModifyPage = new FAQCreatePage(driver);
        faqModifyPage.waitForPageLoad();

        String ModifyPage_Title = faqModifyPage.getTitle();
        String ModifyPage_Category = faqModifyPage.getCategory();
        String ModifyPage_Content = faqModifyPage.getContent();

        softAssert.assertEquals(Title,ModifyPage_Title,"[FAIL]FAQ 수정페이지의 질문이 작성값과 다릅니다.");
        softAssert.assertEquals(Category,ModifyPage_Category,"[FAIL]FAQ 수정페이지의 카테고리가 작성값과 다릅니다.");
        softAssert.assertEquals(Content,ModifyPage_Content,"[FAIL]FAQ 수정페이지의 답변 내용이 작성값과 다릅니다.");

        String Title_Modify = config.getProperty("FQA_Title_Modify");
        String Category_Modify = faqModifyPage.selectRandomCategory();
        String Content_Modify = config.getProperty("FQA_Content_Modify");

        faqModifyPage.CreateFAQ(Title_Modify,Category_Modify,Content_Modify);
        faqModifyPage.clickModifyButton();

        softAssert.assertEquals(faqModifyPage.alertGetText(),AppMessages.faqCreatePage_Modify_AlertMsg_1,"[FAIL]수정확인 메세지의 문구가 일치하지 않습니다.");
        faqModifyPage.alertAccept();

        softAssert.assertEquals(faqModifyPage.alertGetText().replace("\"", ""),
                AppMessages.faqCreatePage_Modify_AlertMsg_2,"[FAIL]수정완료 메세지의 문구가 일치하지 않습니다.");
        faqModifyPage.alertAccept();

        FAQBoardPage faqBoardPage_3rd = new FAQBoardPage(driver);
        faqBoardPage_3rd.waitForPageLoad();

        softAssert.assertTrue(faqBoardPage_2nd.checkTestFAQ(Title_Modify, Category_Modify, Content_Modify,"delete"), "[FAIL]수정한 FAQ를 찾을 수 없습니다.");

        softAssert.assertEquals(faqBoardPage_3rd.alertGetText(),AppMessages.faqCreatePage_Delete_AlertMsg_1,"[FAIL]삭제확인 메세지의 문구가 일치하지 않습니다.");
        faqBoardPage_3rd.alertAccept();

        softAssert.assertEquals(faqBoardPage_3rd.alertGetText().replace("\"", ""),
                AppMessages.faqCreatePage_Delete_AlertMsg_2,"[FAIL]삭제완료 메세지의 문구가 일치하지 않습니다.");
        faqBoardPage_3rd.alertAccept();
        

        softAssert.assertAll();
    }

    @Test(testName = "FAQBoardPage Search Test", groups = "Default")
    public void FAQBoardPage_SearchTest(){
        FAQBoardPage faqBoardPage = new FAQBoardPage(driver);
        faqBoardPage.waitForPageLoad();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        //제목+내용검색
        //검색결과 없음
        faqBoardPage.searchKeyword("1234!@#");
        FAQBoardPage afterSearchPage = new FAQBoardPage(driver);
        afterSearchPage.waitForPageLoad();

        int FaqList_noCountList = afterSearchPage.checkResult();
        softAssert.assertTrue(FaqList_noCountList == 0);
        softAssert.assertFalse(afterSearchPage.checkPageNavi(),"[FAIL]페이지 네비게이션이 기대치보다 많이 존재합니다.(검색결과 없음)"); //현재DF

        afterSearchPage.clickFAQBoardTab();
        FAQBoardPage reviewBoardPage_2rd = new FAQBoardPage(driver);
        reviewBoardPage_2rd.waitForPageLoad();

        //검색결과 있음
        reviewBoardPage_2rd.searchKeyword("수원");
        FAQBoardPage afterSearchPage_2nd = new FAQBoardPage(driver);
        afterSearchPage_2nd.waitForPageLoad();

        int FaqList = afterSearchPage_2nd.checkResult();
        softAssert.assertTrue(FaqList > 0);
        if(FaqList <5){  softAssert.assertFalse(afterSearchPage_2nd.checkPageNavi(),"[FAIL]페이지 네비게이션이 기대치보다 많이 존재합니다.(검색결과 있음)");  }

        softAssert.assertAll();
    }

    @Test(testName = "FAQBoardPage SearchFilter Test", groups = "Default")
    public void FAQBoardPage_SearchFilterTest(){
        FAQBoardPage faqBoardPage = new FAQBoardPage(driver);
        faqBoardPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        //제목검색
        faqBoardPage.selectOption(1);
        //검색결과 없음
        faqBoardPage.searchKeyword("1234!@#");
        FAQBoardPage afterSearchPage = new FAQBoardPage(driver);
        afterSearchPage.waitForPageLoad();

        int ReviewList_noCountList = afterSearchPage.checkResult();
        softAssert.assertTrue(ReviewList_noCountList == 0);
        softAssert.assertFalse(afterSearchPage.checkPageNavi(),"[FAIL]페이지 네비게이션이 기대치보다 많이 존재합니다.(검색결과 없음)"); //현재DF

        afterSearchPage.clickFAQBoardTab();
        FAQBoardPage reviewBoardPage_2rd = new FAQBoardPage(driver);
        reviewBoardPage_2rd.waitForPageLoad();

        //제목검색
        faqBoardPage.selectOption(1);
        //검색결과 있음
        reviewBoardPage_2rd.searchKeyword("Laundry365");
        FAQBoardPage afterSearchPage_2nd = new FAQBoardPage(driver);
        afterSearchPage_2nd.waitForPageLoad();

        int ReviewList = afterSearchPage_2nd.checkResult();
        softAssert.assertTrue(ReviewList > 0);
        if(ReviewList<5){  softAssert.assertFalse(afterSearchPage_2nd.checkPageNavi(),"[FAIL]페이지 네비게이션이 기대치보다 많이 존재합니다.(검색결과 있음)");  }

        softAssert.assertAll();
    }


    @AfterMethod
    private void Logout() {
        FAQBoardPage faqBoardPage = new FAQBoardPage(driver);
        faqBoardPage.navi.clickLogoutLink();
    }
}

