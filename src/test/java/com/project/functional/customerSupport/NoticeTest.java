package com.project.functional.customerSupport;

import com.project.base.BaseTest;
import com.project.constants.AppMessages;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.customerSupport.NoticeCreatePage;
import com.project.page.customerSupport.NoticeDetailPage;
import com.project.page.customerSupport.NoticePage;
import com.project.utils.ScreenshotSoftAssert;
import config.ConfigReader;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NoticeTest extends BaseTest {

    @BeforeMethod(onlyForGroups = "Admin")
    public void setupLogin_Admin() {
        loginAsAdminUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToNoticePage();
    }

    @BeforeMethod(onlyForGroups = "Default")
    public void setup() {
        connectToUrl();
        HomePage homepage = new HomePage(driver);
        homepage.navi.waitForPageLoad();
        homepage.navi.goToNoticePage();
    }

    @Test(testName = "Notice Search Test",groups = "Default")
    public void NoticePage_SearchTest(){
        NoticePage noticePage = new NoticePage(driver);
        noticePage.waitForPageLoad();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        //검색결과 없음
        noticePage.searchKeyword("1234!@#");
        NoticePage afterSearchPage = new NoticePage(driver);
        afterSearchPage.waitForPageLoad();

        int NoticeList_noCountList = afterSearchPage.checkResult();
        softAssert.assertTrue(NoticeList_noCountList == 0,"[FAIL]공지사항의 검색결과가 올바르지 않습니다.(잘못된 검색어)");

        afterSearchPage.clickNoticeTab();
        NoticePage noticePage_2rd = new NoticePage(driver);
        noticePage_2rd.waitForPageLoad();

        //검색결과 있음
        noticePage_2rd.searchKeyword("서비스");
        NoticePage afterSearchPage_2nd = new NoticePage(driver);
        afterSearchPage_2nd.waitForPageLoad();

        int ReviewList = afterSearchPage_2nd.checkResult();
        softAssert.assertTrue(ReviewList > 0,"[FAIL]공지사항의 검색결과가 올바르지 않습니다.(올바른 검색어)");

        softAssert.assertAll();
    }

    @Test(testName = "Notice SearchFilter Test",groups = "Default")
    public void noticePage_SearchFilterTest(){
        NoticePage noticePage = new NoticePage(driver);
        noticePage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        //제목검색
        noticePage.selectOption(1);
        noticePage.searchKeyword("서비스");
        NoticePage afterSearchPage = new NoticePage(driver);
        afterSearchPage.waitForPageLoad();

        int NoticeList_noCountList = afterSearchPage.checkResult();
        softAssert.assertTrue(NoticeList_noCountList > 0,"[FAIL]공지사항의 검색결과가 올바르지 않습니다.(올바른 검색어/제목검색)");

        afterSearchPage.clickNoticeTab();
        NoticePage noticePage_2rd = new NoticePage(driver);
        noticePage_2rd.waitForPageLoad();

        //작성자검색
        noticePage_2rd.selectOption(2);
        noticePage_2rd.searchKeyword(config.getProperty("username"));
        NoticePage afterSearchPage_2nd = new NoticePage(driver);
        afterSearchPage_2nd.waitForPageLoad();

        int NoticeList = afterSearchPage_2nd.checkResult();
        softAssert.assertTrue(NoticeList > 0,"[FAIL]공지사항의 검색결과가 올바르지 않습니다.(올바른 검색어/작성자검색)");

        softAssert.assertAll();
    }

    //공지사항 조회수 테스트
    @Test(testName = "Notice ListCount Test",groups = "Default")
    public void noticePage_ListCountTest(){
        NoticePage noticePage = new NoticePage(driver);
        noticePage.waitForPageLoad();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        int index = noticePage.selectRandomNotice();
        NoticeDetailPage noticeDetailPage = new NoticeDetailPage(driver);
        noticeDetailPage.waitForPageLoad();

        int beforeCount = noticeDetailPage.getNoticeCount();
        noticeDetailPage.clickListButton();

        NoticePage noticePage_2nd = new NoticePage(driver);
        noticePage_2nd.waitForPageLoad();

        noticePage_2nd.gotoTargetNotice(index);

        NoticeDetailPage noticeDetailPage_2nd = new NoticeDetailPage(driver);
        noticeDetailPage_2nd.waitForPageLoad();

        int afterCount = noticeDetailPage_2nd.getNoticeCount();

        softAssert.assertTrue(beforeCount+1 == afterCount,"[FAIL]공지사항의 조회수가 증가하지 않았습니다.");

        softAssert.assertAll();
    }

    @Test(testName = "Notice ListMax Test",groups = "Default")
    public void NoticePage_ListMaxTest(){
        NoticePage noticePage = new NoticePage(driver);
        noticePage.waitForPageLoad();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        int currentListNumber = noticePage.ListNumber();
        if (currentListNumber == 0) {
            System.out.println("[INFO]테스트할 데이터가 없습니다.");
            throw new SkipException("[INFO]테스트할 데이터가 없습니다.");
        }
        else {
            if(currentListNumber<5) {
                System.out.println("[INFO]테스트할 게시물 수가 부족합니다.(5개 미만)");
                throw new SkipException("[INFO]테스트할 게시물 수가 부족합니다.(5개 미만)");
            }
        }

        softAssert.assertFalse(currentListNumber > 5, "[FAIL]한 페이지에 표시되는 게시물 수가 5개를 초과합니다.");
        softAssert.assertTrue(noticePage.pageNaviDisplayCheck(), "[FAIL]게시물 수가 5개를 넘어도 페이지 네비게이션이 표시되지 않습니다.");

        softAssert.assertAll();
    }

    @Test(testName = "Notice Flow Test",groups = "Admin")
    public void NoticePage_FlowTest(){
        NoticePage noticePage = new NoticePage(driver);
        noticePage.waitForPageLoad();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        //작성하기
        noticePage.clickCreateNotice();
        NoticeCreatePage noticeCreatePage = new NoticeCreatePage(driver);
        noticeCreatePage.waitForPageLoad();

        //공지사항 "작성" 버튼테스트
        softAssert.assertEquals(noticeCreatePage.getPageTitle(), PageLabels.noticeCreatePageTitle,
                "[FAIL]공지사항 작성페이지의 타이틀이 기대치와 다릅니다.");
        softAssert.assertEquals(noticeCreatePage.getCurrentUrl(),config.getProperty("NoticeCreatePageURL"),
                "[FAIL]공지사항 작성페이지의 URL이 기대치와 다릅니다.");

        String TestNoticeTitle = config.getProperty("Notice_Title");
        String TestNoticeContent = config.getProperty("Notice_Content");

        noticeCreatePage.CreateNotice(TestNoticeTitle,TestNoticeContent);
        noticeCreatePage.clickCreateButton();

        //공지사항 "저장" 버튼테스트(alert)
        softAssert.assertEquals(noticeCreatePage.alertGetText(), AppMessages.noticeCreatePage_Create_AlertMsg_1,
                "[FAIL]공지사항 저장버튼 클릭 후의 메세지1 이 기대치와 다릅니다.");
        noticeCreatePage.alertAccept();

        softAssert.assertEquals(noticeCreatePage.alertGetText().replace("\"",""), AppMessages.noticeCreatePage_Create_AlertMsg_2,
                "[FAIL]공지사항 저장버튼 클릭 후의 메세지2 이 기대치와 다릅니다.");
        noticeCreatePage.alertAccept();

        NoticePage noticePage_afterCreate = new NoticePage(driver);
        noticePage_afterCreate.waitForPageLoad();

        //공지사항 "저장" 버튼테스트
        softAssert.assertEquals(noticePage_afterCreate.getPageTitle(),PageLabels.noticePageTitle,
                "[FAIL]공지사항 페이지의 타이틀이 기대치와 다릅니다.(저장버튼 클릭후)");
        softAssert.assertEquals(noticePage_afterCreate.getCurrentUrl(), config.getProperty("NoticePageURL"),
                "[FAIL]공지사항 페이지의 URL이 기대치와 다릅니다.(저장버튼 클릭후)");

        //공지사항 페이지에서 등록일,글번호 획득
        String targetNoticeNum = noticePage_afterCreate.getNoticeNum(TestNoticeTitle);
        String targetNoticeCreateDate = noticePage_afterCreate.getNoticeCreateDate(TestNoticeTitle);
        noticePage_afterCreate.clickTargetNotice(TestNoticeTitle);

        //공지사항상세 페이지테스트
        NoticeDetailPage noticeDetailPage = new NoticeDetailPage(driver);
        noticeDetailPage.waitForPageLoad();

        softAssert.assertEquals(noticeDetailPage.getPageTitle(),PageLabels.noticeDetailPageTitle,
                "[FAIL]공지사항 상세페이지의 타이틀이 기대치와 다릅니다.");
        softAssert.assertTrue(noticeDetailPage.getCurrentUrl().contains(config.getProperty("NoticeDetailPageURL")+targetNoticeNum),
                "[FAIL]공지사항 상세페이지의 URL이 기대치와 다릅니다.");

        String Detail_NoticeTitle = noticeDetailPage.getLabel(NoticeDetailPage.NoticeDetailPageLabel.DETAIL_NOTICETITLE);
        String Detail_UserId = noticeDetailPage.getLabel(NoticeDetailPage.NoticeDetailPageLabel.DETAIL_USERID);
        String Detail_CreateDate = noticeDetailPage.getLabel(NoticeDetailPage.NoticeDetailPageLabel.DETAIL_CREATEDATE);
        String Detail_Content = noticeDetailPage.getLabel(NoticeDetailPage.NoticeDetailPageLabel.DETAIL_CONTENT);

        softAssert.assertEquals(Detail_NoticeTitle,config.getProperty("Notice_Title"),"[FAIL]공지사항 상세페이지의 제목이 기대치와 다릅니다.");
        softAssert.assertEquals(Detail_UserId, config.getProperty("adminusername"),"[FAIL]공지사항 상세페이지의 작성자가 기대치와 다릅니다.");
        softAssert.assertEquals(Detail_CreateDate,targetNoticeCreateDate,"[FAIL]공지사항 상세페이지의 등록일이 기대치와 다릅니다.");
        softAssert.assertEquals(Detail_Content,TestNoticeContent,"[FAIL]공지사항 상세페이지의 내용이 기대치와 다릅니다.");

        //상세페이지 "목록보기" 버튼테스트
        noticeDetailPage.clickListButton();
        NoticePage noticePage_afterListBtn = new NoticePage(driver);
        noticePage_afterListBtn.waitForPageLoad();

        softAssert.assertEquals(noticePage_afterListBtn.getPageTitle(),PageLabels.noticePageTitle,
                "[FAIL]공지사항 페이지의 타이틀이 기대치와 다릅니다.(목록보기 버튼 클릭후)");
        softAssert.assertTrue(noticePage_afterListBtn.getCurrentUrl().contains(config.getProperty("NoticePageURL")),
                "[FAIL]공지사항 페이지의 URL이 기대치와 다릅니다.(목록보기 버튼 클릭후)");


        //수정하기페이지 테스트
        noticePage_afterCreate.clickTargetNotice(TestNoticeTitle);
        NoticeDetailPage noticeDetailPage_2nd = new NoticeDetailPage(driver);
        noticeDetailPage_2nd.waitForPageLoad();

        noticeDetailPage.clickModifyButton();
        NoticeCreatePage noticeModifyPage = new NoticeCreatePage(driver);
        noticeModifyPage.waitForPageLoad();

        //수정 테스트(페이지타이틀,url,작성자,작성자비활성화,제목,내용)
        softAssert.assertEquals(noticeModifyPage.getPageTitle(),PageLabels.noticeModifyPageTitle,
                "[FAIL]공지사항 수정페이지의 타이틀이 기대치와 다릅니다.");
        softAssert.assertTrue(noticeModifyPage.getCurrentUrl().contains(config.getProperty("NoticeModifyPageURL")+targetNoticeNum),
                "[FAIL]공지사항 수정페이지의 URL이 기대치와 다릅니다.");
        softAssert.assertEquals(noticeModifyPage.getWriter(),config.getProperty("adminusername"),
                "[FAIL]공지사항 수정페이지의 작성자가 기대치와 다릅니다.");
        softAssert.assertTrue(noticeModifyPage.checkWriterFiled_disable(),"[FAIL]공지사항 수정페이지의 작성자 라벨이 활성화 되어있습니다.");
        softAssert.assertEquals(noticeModifyPage.getTitle(),TestNoticeTitle,"[FAIL]공지사항 수정페이지의 타이틀이 기대치와 다릅니다.");
        softAssert.assertEquals(noticeModifyPage.getContent(),TestNoticeContent,
                "[FAIL]공지사항 수정페이지의 내용이 기대치와 다릅니다.");

        //수정하기
        String Modify_TestNoticeTitle = config.getProperty("Notice_Title_Modify");
        String Modify_TestNoticeContent = config.getProperty("Notice_Content_Modify");


        noticeModifyPage.changTitle(Modify_TestNoticeTitle);
        noticeModifyPage.changeContent(Modify_TestNoticeContent);

        noticeModifyPage.clickModifyButton();
        softAssert.assertEquals(noticeModifyPage.alertGetText(),AppMessages.noticeCreatePage_Modify_AlertMsg_1,
                "[FAIL]공지사항 수정페이지의 수정버튼 클릭 후의 메세지1 이 기대치와 다릅니다.");
        noticeModifyPage.alertAccept();
        softAssert.assertEquals(noticeModifyPage.alertGetText().replace("\"",""),AppMessages.noticeCreatePage_Modify_AlertMsg_2,
                "[FAIL]공지사항 수정페이지의 수정버튼 클릭 후의 메세지2 이 기대치와 다릅니다.");
        noticeModifyPage.alertAccept();

        NoticeDetailPage noticeDetailPage_afterModifyBtn = new NoticeDetailPage(driver);
        noticeDetailPage_afterModifyBtn.waitForPageLoad();
        softAssert.assertEquals(noticeDetailPage_afterModifyBtn.getPageTitle(),PageLabels.noticeDetailPageTitle,
                "[FAIL]공지사항 페이지의 타이틀이 기대치와 다릅니다.(수정하기 버튼 클릭후)");
        softAssert.assertTrue(noticeDetailPage_afterModifyBtn.getCurrentUrl().contains(config.getProperty("NoticeDetailPageURL")),
                "[FAIL]공지사항 페이지의 URL이 기대치와 다릅니다.(수정하기 버튼 클릭후)");

        //수정확인하기
        String Modify_NoticeTitle = noticeDetailPage_afterModifyBtn.getLabel(NoticeDetailPage.NoticeDetailPageLabel.DETAIL_NOTICETITLE);
        String Modify_Content = noticeDetailPage_afterModifyBtn.getLabel(NoticeDetailPage.NoticeDetailPageLabel.DETAIL_CONTENT);
        softAssert.assertEquals(Modify_NoticeTitle,config.getProperty("Notice_Title_Modify"),
                "[FAIL]공지사항 상세페이지의 제목이 기대치와 다릅니다.(수정하기 버튼 클릭 이후)");
        softAssert.assertEquals(Modify_Content,config.getProperty("Notice_Content_Modify"),
                "[FAIL]공지사항 상세페이지의 내용이 기대치와 다릅니다.(수정하기 버튼 클릭 이후)");

        //삭제하기
        noticeDetailPage_afterModifyBtn.clickDeleteButton();
        softAssert.assertEquals(noticeDetailPage_afterModifyBtn.alertGetText(),AppMessages.noticeCreatePage_Delete_AlertMsg_1,
                "[FAIL]공지사항 상세 페이지의 삭제버튼 클릭 후의 메세지1 이 기대치와 다릅니다.");
        noticeModifyPage.alertAccept();
        softAssert.assertEquals(noticeDetailPage_afterModifyBtn.alertGetText().replace("\"",""),AppMessages.noticeCreatePage_Delete_AlertMsg_2,
                "[FAIL]공지사항 상세 페이지의 삭제버튼 클릭 후의 메세지2 이 기대치와 다릅니다.");
        noticeModifyPage.alertAccept();

        NoticePage noticePage_afterDeleteBtn = new NoticePage(driver);
        noticePage_afterDeleteBtn.waitForPageLoad();

        softAssert.assertEquals(noticePage_afterDeleteBtn.getPageTitle(),PageLabels.noticePageTitle,
                "[FAIL]공지사항 페이지의 타이틀이 기대치와 다릅니다.(삭제 버튼 클릭후)");
        softAssert.assertTrue(noticePage_afterDeleteBtn.getCurrentUrl().contains(config.getProperty("NoticePageURL")),
                "[FAIL]공지사항 페이지의 URL이 기대치와 다릅니다.(삭제 버튼 클릭후)");

        softAssert.assertTrue(noticePage_afterDeleteBtn.checkTargetNotice_DeleteCheck(TestNoticeTitle));

        softAssert.assertAll();
    }


    @AfterMethod()
    private void Logout() {
        NoticePage noticePage = new NoticePage(driver);
        noticePage.navi.clickLogoutLink();
    }

}
