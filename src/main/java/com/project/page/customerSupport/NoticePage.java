package com.project.page.customerSupport;

import com.project.page.BasePage;
import com.project.page.NavigationBar;
import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class NoticePage extends BasePage {

    public NavigationBar navi;

    public NoticePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.navi = new NavigationBar(driver);
    }

    //탭
    @FindBy(css = "nav[class='bg-light'] li:nth-child(1) a:nth-child(1)")
    private WebElement reviewBoardTab;

    @FindBy(css = "nav[class='bg-light'] li:nth-child(2) a:nth-child(1)")
    private WebElement FAQBoardTab;

    @FindBy(css = "nav[class='bg-light'] li:nth-child(3) a:nth-child(1)")
    private WebElement QnABoardTab;

    @FindBy(css = "a[class=' active']")
    private WebElement NoticeTab;

    //검색창
    @FindBy(css = "select[name='condition']")
    private WebElement searchConditionSelect;

    @FindBy(name = "keyword")
    private WebElement searchKeywordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement searchButton;

    @FindBy(css = "#btnInsertForm")
    private WebElement createButton;

    //라벨
    @FindBy(css = "th:nth-child(1)")
    private WebElement ListNumber_Label;

    @FindBy(css = "th:nth-child(2)")
    private WebElement ListTitle_Label;

    @FindBy(css = "th:nth-child(3)")
    private WebElement ListRegiDate_Label;

    @FindBy(xpath = "//tbody/tr")
    private List<WebElement> Notice_List;

    @FindBy(xpath = "//td[1]")
    private List<WebElement> Notice_ListNumberList;

    @FindBy(xpath = "//td[2]")
    private List<WebElement> Notice_TitleList;

    @FindBy(xpath = "//td[3]")
    private List<WebElement> Notice_CreateDateList;

    @FindBy(xpath = "//li[contains(@class,'page-')]")
    private List<WebElement> pageNaviList;

    //관리용 텍스트 그룹
    public enum NoticePageLabel {
        SEARCHBUTTON,CREATEBUTTON,
        LISTNUMBER_LABEL,LISTTITLE_LABEL,LISTREGIDATE_LABEL
    }


    //통합 텍스트 추출 메서드
    public String getLabel(NoticePage.NoticePageLabel labelType) {
        switch (labelType) {
            case SEARCHBUTTON:        return getText(searchButton);
            case CREATEBUTTON:        return getText(createButton);
            case LISTNUMBER_LABEL:    return getText(ListNumber_Label);
            case LISTTITLE_LABEL:     return getText(ListTitle_Label);
            case LISTREGIDATE_LABEL:  return getText(ListRegiDate_Label);

            default:  throw new IllegalArgumentException("정의되지 않은 라벨 타입입니다: " + labelType);
        }
    }


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

    public void clickCreateButton() {
        click(createButton);
    }

    public void selectTestContent_Detail() {
        ConfigReader config = new ConfigReader();
        Select select = new Select(searchConditionSelect);
        select.selectByIndex(2);
        System.out.println("[INFO] 검색대상을 '작성자'로 선택했습니다.");
        searchKeywordInput.sendKeys(config.getProperty("adminusername2"));
        click(searchButton);

        NoticePage noticePage_search = new NoticePage(driver);
        noticePage_search.waitForPageLoad();
        click(Notice_List.get(0));
    }

    //검색
    public void searchKeyword(String keyword){
        searchKeywordInput.sendKeys(keyword);
        click(searchButton);
    }

    public int checkResult(){
        int NoticeListCount = Notice_List.size();
        System.out.println("검색결과는 "+NoticeListCount+"건 입니다.");
        return NoticeListCount ;
    }

    //검색조건변경
    public void selectOption(int index) {
        waitForVisible(searchConditionSelect);
        Select select = new Select(searchConditionSelect);
        select.selectByIndex(index);
        System.out.println("[INFO] 검색조건을"+index+"(으)로 변경했습니다.");
    }

    public int selectRandomNotice(){
        wait.until(ExpectedConditions.visibilityOfAllElements(Notice_List));

        if (Notice_List.isEmpty()) {
            throw new RuntimeException("[FAIL] 게시글 리스트를 찾을 수 없습니다.");
        }

        Random random = new Random();
        int index = random.nextInt(Notice_List.size());
        System.out.println("[INFO] 선택된 인덱스: " + index + " / 전체 개수: " + Notice_List.size());

        click(Notice_List.get(index));

        return index;
    }

    public int ListNumber(){
        return Notice_List.size();
    }

    public boolean pageNaviDisplayCheck() {
        if (pageNaviList.isEmpty()) {
            return false;
        }

        if (pageNaviList.size() > 1) {
            return pageNaviList.get(1).isDisplayed();
        }

        return pageNaviList.get(0).isDisplayed();
    }

    public void gotoTargetNotice(int index){
        click(Notice_List.get(index));

    }

    public void clickCreateNotice(){
        click(createButton);
    }

    public String getNoticeNum(String Title){
        for(int i=0;i<Notice_List.size();i++){
            if(Objects.equals(Notice_TitleList.get(i).getText(), Title)){
                return Notice_ListNumberList.get(i).getText();
            }
        }
        System.out.println("[WARN]작성한 공지사항과 제목일 일치한 공지사항이 없어서 글번호를 획득하지 못했습니다.");
        return null;
    }
    public String getNoticeCreateDate(String Title){
        for(int i=0;i<Notice_List.size();i++){
            if(Objects.equals(Notice_TitleList.get(i).getText(), Title)){
                return Notice_CreateDateList.get(i).getText();
            }
        }
        System.out.println("[WARN]작성한 공지사항과 제목일 일치한 공지사항이 없어서 등록일을 획득하지 못했습니다.");
        return null;
    }

    public void clickTargetNotice(String Title){
        System.out.println("[INFO]타켓 공지사항의 타이틀은 "+Title+" 입니다.");
        boolean isFound = false;
        for(int i=0;i<Notice_List.size();i++){
            if(Objects.equals(Notice_TitleList.get(i).getText(), Title)){
                click( Notice_ListNumberList.get(i));
                System.out.println("[INFO]타켓 공지사항으로 이동하였습니다.");
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            System.out.println("[ERROR] 타켓 공지사항을 찾지 못했습니다.");
        }
    }

    public boolean checkTargetNotice_DeleteCheck(String Title){
        for(int i=0;i<Notice_List.size();i++){
            if(Objects.equals(Notice_TitleList.get(i).getText(), Title)){
                System.out.println("[INFO]타켓 공지사항이 존재합니다.");
                return false;
            }
        }
        System.out.println("[INFO]타켓 공지사항이 없는것이 확인하였습니다.");
        return true;
    }


}
