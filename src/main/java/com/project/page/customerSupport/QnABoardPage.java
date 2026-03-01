package com.project.page.customerSupport;

import com.project.page.BasePage;
import com.project.page.NavigationBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class QnABoardPage extends BasePage {

    public NavigationBar navi;

    public QnABoardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.navi = new NavigationBar(driver);
    }

    //탭
    @FindBy(css = "nav[class='bg-light'] li:nth-child(1) a:nth-child(1)")
    private WebElement reviewBoardTab;

    @FindBy(css = "nav[class='bg-light'] li:nth-child(2) a:nth-child(1)")
    private WebElement FAQBoardTab;

    @FindBy(css = "a[class=' active']")
    private WebElement QnABoardTab;

    @FindBy(css = "nav[class='bg-light'] li:nth-child(4) a:nth-child(1)")
    private WebElement NoticeTab;

    //라벨
    @FindBy(css = "div[class='content-wrapper container'] h1 strong")
    private WebElement boardTitle;

    @FindBy(xpath = "//div[3]/span")
    private WebElement boardSubText;

    //게시물 라벨
    @FindBy(css = "th:nth-child(1)")
    private WebElement listTitle_Label;

    @FindBy(css = "th:nth-child(2)")
    private WebElement listCreateDate_Label;

    @FindBy(css = "th:nth-child(3)")
    private WebElement listAnswerStatus_Label;

    @FindBy(xpath = "//body[1]/div[3]/button[1]")
    private WebElement createQnAButton;

    //게시물
    @FindBy(xpath = "//tr/td[1]")
    private List<WebElement> listTitle_List;

    @FindBy(xpath = "//tr/td[2]")
    private List<WebElement> listCreateDate_List;

    @FindBy(xpath = "//tr/td[3]")
    private List<WebElement> listAnswerStatus_List;


    //탭클릭 메서드
    public void clickReviewBoardTab() {
        hover(reviewBoardTab);
        click(reviewBoardTab);
    }

    public void clickFAQBoardTab() {
        hover(FAQBoardTab);
        click(FAQBoardTab);
    }

    public void clickQnABoardTab() {
        hover(QnABoardTab);
        click(QnABoardTab);
    }

    public void clickNoticeTab() {
        hover(NoticeTab);
        click(NoticeTab);
    }

    //관리용 텍스트 그룹
    public enum QnABoardPageLabel {
        REVIEWBOARDTAB,FAQBOARDTAB,QNABOARDTAB,NOTICETAB,
        BOARDTITLE,BOARDSUBTEXT,
        LISTTITLE_LABEL,LISTCREATEDATE_LABEL,LISTANSWERSTATUS_LABEL,
        CREATEQNABUTTON
    }


    //통합 텍스트 추출 메서드
    public String getLabel(QnABoardPage.QnABoardPageLabel labelType) {
        switch (labelType) {
            case REVIEWBOARDTAB :         return getText(reviewBoardTab);
            case FAQBOARDTAB :            return getText(FAQBoardTab);
            case QNABOARDTAB :            return getText(QnABoardTab);
            case NOTICETAB :              return getText(NoticeTab);
            case BOARDTITLE:              return getText(boardTitle);
            case BOARDSUBTEXT:            return getText(boardSubText);
            case LISTTITLE_LABEL:         return getText(listTitle_Label);
            case LISTCREATEDATE_LABEL:    return getText(listCreateDate_Label);
            case LISTANSWERSTATUS_LABEL:  return getText(listAnswerStatus_Label);
            case CREATEQNABUTTON :        return getText(createQnAButton);

            default:  throw new IllegalArgumentException("정의되지 않은 라벨 타입입니다: " + labelType);
        }
    }

    public void clickListTitle(int index) {
        if (index < 0 || index >= listTitle_List.size()) {
            throw new IllegalArgumentException("유효하지 않은 인덱스입니다: " + index);
        }
        click(listTitle_List.get(index));
    }

    public void clickCreateQnAButton(){
        click(createQnAButton);
    }

    public void clickRandomQnAList(){
        int listSize = listTitle_List.size();
        if (listSize > 0) {
            int randomIndex = ThreadLocalRandom.current().nextInt(listSize);
            System.out.println("[INFO] 총 " + listSize + "개 중 " + randomIndex + "번째 공지사항을 선택합니다.");
            click(listTitle_List.get(randomIndex));
        } else {
            System.out.println("[WARN] 클릭할 공지사항이 없습니다.");
        }
    }

    public int getRandomQnAListIndex() {
        int listSize = listTitle_List.size();
        if (listSize > 0) {
            int randomIndex = ThreadLocalRandom.current().nextInt(listSize);
            System.out.println("[INFO] 총 " + listSize + "개 중 " + randomIndex + "번째 문의사항을 선택합니다.");
            return randomIndex;
        } else {
            System.out.println("[WARN] 선택할 공지사항이 없습니다.");
            return -1;
        }
    }

    public String getListTitle(int index) {
        if (index < 0 || index >= listTitle_List.size()) {
            throw new IllegalArgumentException("유효하지 않은 인덱스입니다: " + index);
        }
        return getText(listTitle_List.get(index));
    }

    public String getListCreateDate(int index) {
        if (index < 0 || index >= listCreateDate_List.size()) {
            throw new IllegalArgumentException("유효하지 않은 인덱스입니다: " + index);
        }
        return getText(listCreateDate_List.get(index));
    }

    public String getListAnswerStatus(int index) {
        if (index < 0 || index >= listAnswerStatus_List.size()) {
            throw new IllegalArgumentException("유효하지 않은 인덱스입니다: " + index);
        }
        return getText(listAnswerStatus_List.get(index));
    }

    public boolean checkTargetQnA_Delete(String Title,String CreateDate){
        for(int i=0; i<listTitle_List.size(); i++){
            String currentTitle = getText(listTitle_List.get(i));
            String currentCreateDate = getText(listCreateDate_List.get(i));
            if(currentTitle.equals(Title) && currentCreateDate.equals(CreateDate)){
                System.out.println("[INFO] 삭제된 QnA 게시물이 여전히 목록에 존재합니다: " + Title + " - " + CreateDate);
                return false;
            }
        }
        System.out.println("[INFO] 삭제된 QnA 게시물이 여전히 목록에 존재하지 않습니다.");
        return true;

    }

    public String getTestQnA_status(String Title, String CreateDate){
        for(int i=0; i<listTitle_List.size(); i++){
            String currentTitle = getText(listTitle_List.get(i));
            String currentCreateDate = getText(listCreateDate_List.get(i));
            if(currentTitle.equals(Title) && currentCreateDate.equals(CreateDate)){
                String status = getText(listAnswerStatus_List.get(i));
                System.out.println("[INFO] 작성한 테스트 QnA 게시물의 답변상태 (인덱스 값 : "+status+")");
                return status;
            }
        }
        System.out.println("[WARN] 작성한 테스트 QnA 게시물을 찾을 수 없습니다.");
        return null;
    }

    public void clickTargetQnAList(String Title, String CreateDate){
        for(int i=0; i<listTitle_List.size(); i++){
            String currentTitle = getText(listTitle_List.get(i));
            String currentCreateDate = getText(listCreateDate_List.get(i));
            if(currentTitle.equals(Title) && currentCreateDate.equals(CreateDate)){
                click(listTitle_List.get(i));
                return;
            }
        }
        System.out.println("[WARN] 해당 테스트 QnA 게시물을 찾을 수 없습니다.");
    }



}
