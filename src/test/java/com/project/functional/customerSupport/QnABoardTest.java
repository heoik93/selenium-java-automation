package com.project.functional.customerSupport;

import com.project.base.BaseTest;
import com.project.constants.AppMessages;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.customerSupport.QnABoardPage;
import com.project.page.customerSupport.QnACreatePage;
import com.project.page.customerSupport.QnADetailPage;
import com.project.utils.ScreenshotSoftAssert;
import config.ConfigReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;

public class QnABoardTest extends BaseTest {

    @BeforeMethod(onlyForGroups = "DefaultUser")
    public void setupLogin_Default() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToQnABoardPage();
    }

    @BeforeMethod(onlyForGroups = "AdminUser")
    public void setupLogin_Admin() {
        loginAsAdminUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToQnABoardPage();
    }

    @Test(testName = "QnABoardPage QnACreate Button Test", groups = "DefaultUser")
    public void QnABoardPage_QnACreateButtonTest(){
        QnABoardPage qnaBoardPage = new QnABoardPage(driver);
        qnaBoardPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        qnaBoardPage.clickCreateQnAButton();
        QnACreatePage qnaCreatePage = new QnACreatePage(driver);
        qnaCreatePage.waitForPageLoad();

        String actualPageTitle = qnaCreatePage.getPageTitle();
        String actualUrl = qnaCreatePage.getCurrentUrl();

        softAssert.assertEquals(actualPageTitle,PageLabels.QnACreatePageTitle,"[FAIL] 1:1문의 작성페이지의 페이지 타이틀이 올바르지 않습니다.");
        softAssert.assertEquals(actualUrl,config.getProperty("QnACreatePageURL"),"[FAIL] 1:1문의 작성페이지의 URL이 올바르지 않습니다.");

        softAssert.assertAll();

        qnaCreatePage.clickCancelButton();
        QnABoardPage qnaBoardPage_2nd = new QnABoardPage(driver);
        qnaBoardPage_2nd.waitForPageLoad();
    }

    @Test(testName = "QnADetailPage List Button Test", groups = "DefaultUser")
    public void QnADetailPage_ListButtonTest(){
        QnABoardPage qnaBoardPage = new QnABoardPage(driver);
        qnaBoardPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        int RandomQnAIndex = qnaBoardPage.getRandomQnAListIndex();
        qnaBoardPage.clickListTitle(RandomQnAIndex);

        QnADetailPage qnaDetailPage = new QnADetailPage(driver);
        qnaDetailPage.waitForPageLoad();

        qnaDetailPage.clickListButton();
        QnABoardPage qnaBoardPage_afterListBtn = new QnABoardPage(driver);
        qnaBoardPage_afterListBtn.waitForPageLoad();

        String actualPageTitle = qnaBoardPage_afterListBtn.getPageTitle();
        String actualUrl = qnaBoardPage_afterListBtn.getCurrentUrl();

        softAssert.assertEquals(actualPageTitle,PageLabels.QnABoardPageTitle,
                "[FAIL] 1:1문의 페이지의 페이지 타이틀이 올바르지 않습니다.(상세페이지에서 목록보기 버튼 클릭 후)");
        softAssert.assertEquals(actualUrl,config.getProperty("QnABoardPageURL"),
                "[FAIL] 1:1문의 페이지의 URL이 올바르지 않습니다.(상세페이지에서 목록보기 버튼 클릭 후)");

        softAssert.assertAll();
    }

    @Test(testName = "QnADetailPage DataLink Test", groups = "DefaultUser")
    public void QnADetailPage_DataLinkTest(){
        QnABoardPage qnaBoardPage = new QnABoardPage(driver);
        qnaBoardPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        int RandomQnAIndex = qnaBoardPage.getRandomQnAListIndex();
        String targetTitle = qnaBoardPage.getListTitle(RandomQnAIndex);
        String targetCreateDate = qnaBoardPage.getListCreateDate(RandomQnAIndex);
        String targetStatus = qnaBoardPage.getListAnswerStatus(RandomQnAIndex);
        String targetWriter = config.getProperty("username");

        qnaBoardPage.clickListTitle(RandomQnAIndex);

        QnADetailPage qnaDetailPage = new QnADetailPage(driver);
        qnaDetailPage.waitForPageLoad();

        String actualPageTitle = qnaDetailPage.getPageTitle();
        String actualUrl = qnaDetailPage.getCurrentUrl();
        String actualNumber = qnaDetailPage.getLabel(QnADetailPage.QnADetailPageLabel.QNADETAIL_NUMBER);

        softAssert.assertEquals(actualPageTitle,PageLabels.QnADetailPageTitle,"[FAIL] 1:1문의 상세 페이지의 페이지 타이틀이 올바르지 않습니다.");
        softAssert.assertEquals(actualUrl,config.getProperty("QnADetailPageURL")+actualNumber,
                "[FAIL] 1:1문의 상세 페이지의 URL이 올바르지 않습니다.");

        softAssert.assertEquals(targetTitle,qnaDetailPage.getLabel(QnADetailPage.QnADetailPageLabel.QNADETAIL_TITLE),
                "[FAIL] 1:1문의 상세 페이지의 제목이 일치하지 않습니다.");
        softAssert.assertEquals(targetCreateDate,qnaDetailPage.getLabel(QnADetailPage.QnADetailPageLabel.QNADETAIL_CREATEDATE),
                "[FAIL] 1:1문의 상세 페이지의 작성일이 일치하지 않습니다.");
        softAssert.assertEquals(targetWriter,qnaDetailPage.getLabel(QnADetailPage.QnADetailPageLabel.QNADETAIL_WRITER),
                "[FAIL] 1:1문의 상세 페이지의 작성자가 일치하지 않습니다.");

        if(Objects.equals(targetStatus, "답변완료")) {
            String actualAnswerContent = qnaDetailPage.getLabel(QnADetailPage.QnADetailPageLabel.QNADETAIL_ANWSERAREACONTENT);
            softAssert.assertNotNull(actualAnswerContent, "[FAIL] 1:1문의 상세 페이지의 답변 내용이 존재하지 않습니다.(답변완료 상태)");
        }else {
            softAssert.assertFalse(qnaDetailPage.checkAnswerAreaVisible(), "[FAIL] 1:1문의 상세 페이지의 답변내용이 존재합니다.");
        }

        softAssert.assertFalse(qnaDetailPage.getLabel(QnADetailPage.QnADetailPageLabel.QNADETAIL_CONTENT).isEmpty(),
                "[FAIL] 1:1문의 상세 페이지의 내용이 존재하지 않습니다.");

        qnaDetailPage.clickListButton();
        QnABoardPage qnaBoardPage_afterListBtn = new QnABoardPage(driver);
        qnaBoardPage_afterListBtn.waitForPageLoad();

        softAssert.assertAll();
    }

    @Test(testName = "QnACreatePage Cancel Button Test", groups = "DefaultUser")
    public void QnACreatePage_CancelButtonTest(){
        QnABoardPage qnaBoardPage = new QnABoardPage(driver);
        qnaBoardPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        qnaBoardPage.clickCreateQnAButton();

        QnACreatePage qnaCreatePage = new QnACreatePage(driver);
        qnaCreatePage.waitForPageLoad();

        qnaCreatePage.clickCancelButton();

        QnABoardPage qnaBoardPage_afterCancel = new QnABoardPage(driver);
        qnaBoardPage_afterCancel.waitForPageLoad();

        String actualPageTitle = qnaBoardPage_afterCancel.getPageTitle();
        String actualUrl = qnaBoardPage_afterCancel.getCurrentUrl();

        softAssert.assertEquals(actualPageTitle,PageLabels.QnABoardPageTitle,
                "[FAIL] 1:1문의 페이지의 페이지 타이틀이 올바르지 않습니다.(작성 페이지에서 취소 버튼 클릭 후)");
        softAssert.assertEquals(actualUrl,config.getProperty("QnABoardPageURL"),
                "[FAIL] 1:1문의 페이지의 URL이 올바르지 않습니다.(작성 페이지에서 취소 버튼 클릭 후)");

        softAssert.assertAll();
    }

    @Test(testName = "QnABoardPage CreateDelete Test", groups = "DefaultUser")
    public void QnABoardPage_CreateDeleteTest(){
        QnABoardPage qnaBoardPage = new QnABoardPage(driver);
        qnaBoardPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        qnaBoardPage.clickCreateQnAButton();

        QnACreatePage qnaCreatePage = new QnACreatePage(driver);
        qnaCreatePage.waitForPageLoad();

        String TestQnATitle = config.getProperty("QnA_Title");
        String TestQnAContent = config.getProperty("QnA_Content");

        //문의등록 버튼테스트
        qnaCreatePage.CreateQnA(TestQnATitle,TestQnAContent);
        qnaCreatePage.clickCreateButton();

        softAssert.assertEquals(qnaCreatePage.alertGetText(), AppMessages.qnaCreatePage_Create_AlertMsg_1,
                "[FAIL] 1:1문의 작성시 나타나는 알림창1 의 메시지가 올바르지 않습니다.");
        qnaCreatePage.alertAccept();
        softAssert.assertEquals(qnaCreatePage.alertGetText().replace("\"",""), AppMessages.qnaCreatePage_Create_AlertMsg_2,
                "[FAIL] 1:1문의 작성시 나타나는 알림창2 의 메시지가 올바르지 않습니다.");
        qnaCreatePage.alertAccept();

        QnADetailPage qnaDetailPage_afterCreate = new QnADetailPage(driver);
        qnaDetailPage_afterCreate.waitForPageLoad();

        String targetNumber = qnaDetailPage_afterCreate.getLabel(QnADetailPage.QnADetailPageLabel.QNADETAIL_NUMBER);
        String targetTitle = qnaDetailPage_afterCreate.getLabel(QnADetailPage.QnADetailPageLabel.QNADETAIL_TITLE);
        String targetCreateDate = qnaDetailPage_afterCreate.getLabel(QnADetailPage.QnADetailPageLabel.QNADETAIL_CREATEDATE);

        softAssert.assertEquals(qnaDetailPage_afterCreate.getPageTitle(),PageLabels.QnADetailPageTitle,
                "[FAIL] 1:1문의 작성페이지의 등록버튼 클릭후의 상세 페이지의 페이지 타이틀이 올바르지 않습니다.");
        softAssert.assertEquals(qnaDetailPage_afterCreate.getCurrentUrl(),config.getProperty("QnADetailPageURL")+targetNumber,
                "[FAIL] 1:1문의 작성페이지의 등록버튼 클릭후의 상세 페이지의 URL이 올바르지 않습니다.");
        softAssert.assertEquals(targetTitle,TestQnATitle,"[FAIL] 1:1문의 작성시의 제목과 상세 페이지의 제목이 일치하지 않습니다.");

        //글삭제 버튼테스트
        qnaDetailPage_afterCreate.clickDeleteButton();

        softAssert.assertEquals(qnaDetailPage_afterCreate.alertGetText(), AppMessages.qnaDetailPage_Delete_AlertMsg_1,
                "[FAIL] 1:1문의 삭제시 나타나는 알림창1 의 메시지가 올바르지 않습니다.");
        qnaCreatePage.alertAccept();
        softAssert.assertEquals(qnaDetailPage_afterCreate.alertGetText().replace("\"",""), AppMessages.qnaDetailPage_Delete_AlertMsg_2,
                "[FAIL] 1:1문의 삭제시 나타나는 알림창2 의 메시지가 올바르지 않습니다.");
        qnaCreatePage.alertAccept();

        QnABoardPage qnaBoardPage_afterDelete = new QnABoardPage(driver);
        qnaBoardPage_afterDelete.waitForPageLoad();

        softAssert.assertEquals(qnaBoardPage_afterDelete.getPageTitle(),PageLabels.QnABoardPageTitle,
                "[FAIL] 1:1문의 상세페이지의 삭제버튼 클릭후의 1:1문의의 페이지 타이틀이 올바르지 않습니다.");
        softAssert.assertEquals(qnaBoardPage_afterDelete.getCurrentUrl(),config.getProperty("QnABoardPageURL"),
                "[FAIL] 1:1문의 상세페이지의 삭제버튼 클릭후의 1:1문의의 URL이 올바르지 않습니다.");
        softAssert.assertTrue(qnaBoardPage_afterDelete.checkTargetQnA_Delete(targetTitle,targetCreateDate),
                "[FAIL] 1:1문의 상세페이지의 삭제버튼 클릭후 해당 글이 삭제되지 않았습니다.");

        softAssert.assertAll();
    }

    @Test(testName = "QnABoardPage StatusChange Test", groups = "DefaultUser")
    public void QnABoardPage_StatusChangeTest(){
        //일반유저 작성하기 -> 답변대기상태인지 확인 -> 상세이동 후 답변에리어 비표시 확인
        QnABoardPage qnaBoardPage = new QnABoardPage(driver);
        qnaBoardPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        qnaBoardPage.clickCreateQnAButton();
        QnACreatePage qnaCreatePage = new QnACreatePage(driver);
        qnaCreatePage.waitForPageLoad();

        String TestQnATitle = config.getProperty("QnA_Title");
        String TestQnAContent = config.getProperty("QnA_Content");

        qnaCreatePage.CreateQnA(TestQnATitle,TestQnAContent);
        qnaCreatePage.clickCreateButton();

        qnaCreatePage.alertAccept();
        qnaCreatePage.alertAccept();
        System.out.println("[INFO] 테스트용 1:1문의 작성완료");

        QnADetailPage qnaDetailPage = new QnADetailPage(driver);
        qnaDetailPage.waitForPageLoad();

        String TestQnACreateDate = qnaDetailPage.getLabel(QnADetailPage.QnADetailPageLabel.QNADETAIL_CREATEDATE);
        qnaDetailPage.clickListButton();

        QnABoardPage qnaBoardPage_afterCreate = new QnABoardPage(driver);
        qnaBoardPage_afterCreate.waitForPageLoad();
        String TestQnAStatus = qnaBoardPage_afterCreate.getTestQnA_status(TestQnATitle,TestQnACreateDate);

        //작성후 답변대상태 확인
        softAssert.assertEquals(TestQnAStatus,PageLabels.qnaBoardList_ListStatus_wait,
                "[FAIL] 1:1문의 작성 후, 게시판에서 해당 글의 상태가 '답변대기'로 표시되지 않습니다.");
        System.out.println("[INFO] 테스트용 1:1문의 작성완료");

        qnaBoardPage_afterCreate.navi.clickLogoutLink();

        //관리자 로그인
        setupLogin_Admin();

        QnABoardPage qnaBoardPage_admin = new QnABoardPage(driver);
        qnaBoardPage_admin.waitForPageLoad();

        //관리자 페이지에서 일반유저가 작성한 문의의 상태확인
        String TestQnAStatus_Admin = qnaBoardPage_afterCreate.getTestQnA_status(TestQnATitle,TestQnACreateDate);
        softAssert.assertEquals(TestQnAStatus_Admin,TestQnAStatus,
                "[FAIL] 일반유저 작성한 1:1문의의 상태가 관리자 페이지에서 다르게 표시됩니다. 일반유저 페이지 상태: " + TestQnAStatus + " / 관리자 페이지 상태: " + TestQnAStatus_Admin);

        qnaBoardPage_admin.clickTargetQnAList(TestQnATitle,TestQnACreateDate);
        QnADetailPage qnaDetailPage_admin = new QnADetailPage(driver);
        qnaDetailPage_admin.waitForPageLoad();

        //답변등록
        qnaDetailPage_admin.inputAdminAnswer(config.getProperty("QnA_AdminAnswer"));
        qnaDetailPage_admin.clickAnswerSubmitButton();
        qnaDetailPage_admin.alertAccept();

        System.out.println("[INFO] 테스트용 1:1문의 관리자 답변완료");

        QnADetailPage qnaDetailPage_admin_afterAnswer = new QnADetailPage(driver);
        qnaDetailPage_admin_afterAnswer.waitForPageLoad();
        qnaDetailPage_admin_afterAnswer.clickListButton();

        QnABoardPage qnaBoardPage_admin_afterAnswer = new QnABoardPage(driver);
        qnaBoardPage_admin_afterAnswer.waitForPageLoad();

        //관리자 답변 등록 후 상태확인
        String TestQnAStatus_Admin_afterAnswer = qnaBoardPage_admin_afterAnswer.getTestQnA_status(TestQnATitle,TestQnACreateDate);
        softAssert.assertEquals(TestQnAStatus_Admin_afterAnswer,PageLabels.qnaBoardList_ListStatus_complete,
                "[FAIL] 1:1문의에 관리자 답변 등록 후, 게시판에서 해당 글의 상태가 '답변완료'로 표시되지 않습니다.");

        qnaBoardPage_admin_afterAnswer.navi.clickLogoutLink();

        setupLogin_Default();
        QnABoardPage qnaBoardPage_user_afterAnswer = new QnABoardPage(driver);
        qnaBoardPage_user_afterAnswer.waitForPageLoad();

        //일반유저 답변대기상태 변화 확인
        String TestQnAStatus_User_afterAnswer = qnaBoardPage_user_afterAnswer.getTestQnA_status(TestQnATitle,TestQnACreateDate);
        softAssert.assertEquals(TestQnAStatus_User_afterAnswer,TestQnAStatus_Admin_afterAnswer,
                "[FAIL] 관리자 답변 등록 후, 일반유저 페이지에서 해당 글의 상태가 다르게 표시됩니다. 관리자 페이지 상태: " + TestQnAStatus_Admin_afterAnswer + " / 일반유저 페이지 상태: " + TestQnAStatus_User_afterAnswer);

        //삭제하기
        qnaBoardPage_user_afterAnswer.clickTargetQnAList(TestQnATitle,TestQnACreateDate);
        QnADetailPage qnaDetailPage_user_afterAnswer = new QnADetailPage(driver);
        qnaDetailPage_user_afterAnswer.waitForPageLoad();
        qnaDetailPage_user_afterAnswer.clickDeleteButton();
        qnaDetailPage_user_afterAnswer.alertAccept();
        qnaDetailPage_user_afterAnswer.alertAccept();

        System.out.println("[INFO] 테스트용 1:1문의 삭제완료");

        QnABoardPage qnaBoardPage_afterDelete = new QnABoardPage(driver);
        qnaBoardPage_afterDelete.waitForPageLoad();

        softAssert.assertAll();
    }

    @Test(testName = "QnABoardPage Admin Test", groups = "AdminUser")
    public void QnABoardPage_AdminTest(){
        QnABoardPage qnaBoardPage = new QnABoardPage(driver);
        qnaBoardPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);
        ConfigReader config = new ConfigReader();

        //관리자 답변 등록&수정&삭제확인
        qnaBoardPage.clickCreateQnAButton();
        QnACreatePage qnaCreatePage = new QnACreatePage(driver);
        qnaCreatePage.waitForPageLoad();

        String TestQnATitle = config.getProperty("QnA_Title");
        String TestQnAContent = config.getProperty("QnA_Content");

        qnaCreatePage.CreateQnA(TestQnATitle,TestQnAContent);
        qnaCreatePage.clickCreateButton();

        qnaCreatePage.alertAccept();
        qnaCreatePage.alertAccept();
        System.out.println("[INFO] 테스트용 1:1문의 작성완료");

        QnADetailPage qnaDetailPage = new QnADetailPage(driver);
        qnaDetailPage.waitForPageLoad();

        qnaDetailPage.inputAdminAnswer(config.getProperty("QnA_AdminAnswer"));
        String answerSubmitButton_AnswerBefore = qnaDetailPage.getLabel(QnADetailPage.QnADetailPageLabel.QNADETAIL_ANWSERAREASUBMITBUTTON);
        softAssert.assertEquals(answerSubmitButton_AnswerBefore,PageLabels.qnaDetailPage_Admin_AnswerBefore_SubmitButton,
                "[FAIL] 1:1문의에서 답변후 수정버튼 클릭시, Submit버튼의 텍스트가 올바르지 않습니다.");

        //관리자 답변 등록
        qnaDetailPage.clickAnswerSubmitButton();
        softAssert.assertEquals(qnaDetailPage.alertGetText(),AppMessages.qnaDetailPage_AdminAnswer_Create_AlertMsg,
                "[FAIL] 1:1문의 상세페이지에서 관리자가 답변 등록시 나타나는 알림창의 메시지가 올바르지 않습니다.");
        qnaDetailPage.alertAccept();

        QnADetailPage qnaDetailPage_afterAnswer = new QnADetailPage(driver);
        qnaDetailPage_afterAnswer.waitForPageLoad();

        String answerContent = qnaDetailPage_afterAnswer.getLabel(QnADetailPage.QnADetailPageLabel.QNADETAIL_ANWSERAREACONTENT);

        softAssert.assertEquals(answerContent,config.getProperty("QnA_AdminAnswer"),
                "[FAIL] 1:1문의에서 관리자가 등록한 답변내용이 상세페이지 답변내용과 일치하지 않습니다.");
        softAssert.assertTrue(qnaDetailPage_afterAnswer.checkAnswerAreaVisible(),
                "[FAIL] 1:1문의에서 관리자가 답변을 등록해도 해당에리어가 표시되지 않았습니다.");

        System.out.println("[INFO] 관리자 답변 등록 확인 완료.");

        //관리자 답변 수정
        qnaDetailPage_afterAnswer.clickAnswerModifyButton();
        qnaDetailPage_afterAnswer.inputAdminAnswer(config.getProperty("QnA_AdminAnswer_Modify"));
        String answerSubmitButton_AnswerAfter = qnaDetailPage_afterAnswer.getLabel(QnADetailPage.QnADetailPageLabel.QNADETAIL_ANWSERAREASUBMITBUTTON);
        softAssert.assertEquals(answerSubmitButton_AnswerAfter,PageLabels.qnaDetailPage_Admin_AnswerAfter_SubmitButton,
                "[FAIL] 1:1문의에서 답변후 수정버튼 클릭시, Submit버튼의 텍스트가 올바르지 않습니다.");

        qnaDetailPage_afterAnswer.clickAnswerSubmitButton();
        softAssert.assertEquals(qnaDetailPage_afterAnswer.alertGetText(),AppMessages.qnaDetailPage_AdminAnswer_Modify_AlertMsg,
                "[FAIL] 1:1문의 상세페이지에서 관리자가 답변 수정시 나타나는 알림창의 메시지가 올바르지 않습니다.");
        qnaDetailPage_afterAnswer.alertAccept();

        QnADetailPage qnaDetailPage_afterAnswerModify = new QnADetailPage(driver);
        qnaDetailPage_afterAnswerModify.waitForPageLoad();

        String answerContent_afterModify = qnaDetailPage_afterAnswerModify.getLabel(QnADetailPage.QnADetailPageLabel.QNADETAIL_ANWSERAREACONTENT);

        softAssert.assertEquals(answerContent_afterModify,config.getProperty("QnA_AdminAnswer_Modify"),
                "[FAIL] 1:1문의에서 관리자가 수정한 답변내용이 상세페이지 답변내용과 일치하지 않습니다.");

        System.out.println("[INFO] 관리자 답변 수정 확인 완료.");

        //관리자 답변 삭제
        qnaDetailPage_afterAnswerModify.clickAnswerDeleteButton();

        softAssert.assertEquals(qnaDetailPage_afterAnswerModify.alertGetText(),AppMessages.qnaDetailPage_AdminAnswer_Delete_AlertMsg_1,
                "[FAIL] 1:1문의 상세페이지에서 관리자가 삭제시 나타나는 알림창의 메시지1이 올바르지 않습니다.");
        qnaDetailPage_afterAnswerModify.alertAccept();
        softAssert.assertEquals(qnaDetailPage_afterAnswerModify.alertGetText(),AppMessages.qnaDetailPage_AdminAnswer_Delete_AlertMsg_2,
                "[FAIL] 1:1문의 상세페이지에서 관리자가 삭제시 나타나는 알림창의 메시지2가 올바르지 않습니다.");
        qnaDetailPage_afterAnswerModify.alertAccept();

        QnADetailPage qnaDetailPage_afterAnswerDelete = new QnADetailPage(driver);
        qnaDetailPage_afterAnswerDelete.waitForPageLoad();

        String deleteAfterLabel = qnaDetailPage_afterAnswerDelete.getLabel(QnADetailPage.QnADetailPageLabel.QNADETAIL_ANWSERAREASUBMITBUTTON);

        softAssert.assertEquals(deleteAfterLabel,PageLabels.qnaDetailPage_Admin_AnswerBefore_SubmitButton,
                "[FAIL] 1:1문의에서 답변후 수정버튼 클릭시, Submit버튼의 텍스트가 올바르지 않습니다.");

        System.out.println("[INFO] 관리자 답변 삭제 확인 완료.");

        qnaDetailPage_afterAnswerDelete.clickDeleteButton();
        qnaDetailPage_afterAnswerDelete.alertAccept();
        qnaDetailPage_afterAnswerDelete.alertAccept();

        System.out.println("[INFO] 테스트용 1:1문의 삭제완료");

        QnABoardPage qnaBoardPage_end = new QnABoardPage(driver);
        qnaBoardPage_end.waitForPageLoad();

        softAssert.assertAll();
    }

    @AfterMethod()
    private void Logout() {
        QnABoardPage qnaBoardPage = new QnABoardPage(driver);
        qnaBoardPage.navi.clickLogoutLink();
    }
}
