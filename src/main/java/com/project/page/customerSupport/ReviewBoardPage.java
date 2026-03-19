package com.project.page.customerSupport;

import com.project.page.BasePage;
import com.project.page.NavigationBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class ReviewBoardPage extends BasePage {

    public NavigationBar navi;

    public ReviewBoardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.navi = new NavigationBar(driver);
    }

    //탭
    @FindBy(css = "a[class=' active']")
    private WebElement reviewBoardTab;

    @FindBy(css = "nav[class='bg-light'] li:nth-child(2) a:nth-child(1)")
    private WebElement FAQBoardTab;

    @FindBy(css = "nav[class='bg-light'] li:nth-child(3) a:nth-child(1)")
    private WebElement QnABoardTab;

    @FindBy(css = "nav[class='bg-light'] li:nth-child(4) a:nth-child(1)")
    private WebElement NoticeTab;

    //검색창
    @FindBy(css = "select[name='condition']")
    private WebElement searchConditionSelect;

    @FindBy(name = "keyword")
    private WebElement searchKeywordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement searchButton;

    //라벨
    @FindBy(css = "th:nth-child(1)")
    private WebElement reviewNumberLabel;

    @FindBy(css = "th:nth-child(2)")
    private WebElement reviewUserLabel;

    @FindBy(css = "th:nth-child(3)")
    private WebElement reviewTitleLabel;

    @FindBy(css = "th:nth-child(4)")
    private WebElement reviewHitsLabel;

    @FindBy(css = "th:nth-child(5)")
    private WebElement reviewDateLabel;

    @FindBy(css = "th:nth-child(6)")
    private WebElement reviewPointLabel;

    //게시판
    @FindBy(css = "tbody tr")
    private List<WebElement> reviewBoardList;

    //게시물
    @FindBy(xpath = "//td[4]")
    private List<WebElement> reviewBoardList_Count;

    @FindBy(xpath = "//td[3]")
    private List<WebElement> reviewBoardList_Title;

    @FindBy(xpath = "//td[6]")
    private List<WebElement> reviewBoardList_Star;


    @FindBy(xpath = "//li[contains(@class,'page-')]")
    private List<WebElement> pageNaviList;


    //관리용 텍스트 그룹
    public enum ReviewBoardPageLabel {
        REVIEWBOARDTAB,FAQBOARDTAB,QNABOARDTAB,NOTICETAB,
        REVIEWNUMBERLABEL,REVIEWUSERLABEL,REVIEWTITLELABEL,REVIEWHITSLABEL,REVIEWDATELABEL,REVIEWPOINTLABEL,
        SEARCHCONDITIONSELECT,SEARCHBUTTON
    }

    //통합 텍스트 추출 메서드
    public String getLabel(ReviewBoardPageLabel labelType) {
        switch (labelType) {
            case REVIEWBOARDTAB:        return getText(reviewBoardTab);
            case FAQBOARDTAB:           return getText(FAQBoardTab);
            case QNABOARDTAB:           return getText(QnABoardTab);
            case NOTICETAB:             return getText(NoticeTab);
            case REVIEWNUMBERLABEL:     return getText(reviewNumberLabel);
            case REVIEWUSERLABEL:       return getText(reviewUserLabel);
            case REVIEWTITLELABEL:      return getText(reviewTitleLabel);
            case REVIEWHITSLABEL:       return getText(reviewHitsLabel);
            case REVIEWDATELABEL:       return getText(reviewDateLabel);
            case REVIEWPOINTLABEL:      return getText(reviewPointLabel);
            case SEARCHCONDITIONSELECT: return getText(searchConditionSelect);
            case SEARCHBUTTON:          return getText(searchButton);

            default:  throw new IllegalArgumentException("정의되지 않은 라벨 타입입니다: " + labelType);
        }
    }

    //검색조건변경
    public void selectOption(int index) {
        Select select = new Select(searchConditionSelect);
        select.selectByIndex(index);
        System.out.println("[INFO] 검색조건을"+index+"(으)로 변경했습니다.");
    }
    public String getSelectedSearchConditionText() {
        Select select = new Select(searchConditionSelect);
        return select.getFirstSelectedOption().getText().trim();
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

    public void clickRandomReview(){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("tbody tr")));

        Random random = new Random();
        int index = random.nextInt(reviewBoardList.size());
        click(reviewBoardList.get(index));
    }

    public int selectRandomReview(){
        wait.until(ExpectedConditions.visibilityOfAllElements(reviewBoardList));

        if (reviewBoardList.isEmpty()) {
            throw new RuntimeException("[FAIL]게시글 리스트를 찾을 수 없습니다. (Size 0)");
        }

        Random random = new Random();
        int index = random.nextInt(reviewBoardList.size());
        System.out.println("[INFO] 선택된 인덱스: " + index + " / 전체 개수: " + reviewBoardList.size());

        return index;
    }

    public int getReviewCount(int index){
        String countText = reviewBoardList_Count.get(index).getText();
        int reviewCount = Integer.parseInt(countText);
        System.out.println("[INFO] 현재 조회수: " + reviewCount);

        return reviewCount;
    }

    public String getReviewTitle(int index){
        return reviewBoardList_Title.get(index).getText();
    }
    public String getReviewStar(int index){
        return reviewBoardList_Star.get(index).getText();
    }
    public void clickReview(int index) {
        click(reviewBoardList.get(index));
    }

    //검색
    public void searchKeyword(String keyword){
        searchKeywordInput.sendKeys(keyword);
        click(searchButton);
    }

    public int checkResult(){
        int ReviewListCount = reviewBoardList.size();
        System.out.println("검색결과는 "+ReviewListCount+"건 입니다.");
        return ReviewListCount ;
    }


    public int ListNumber(){
        return reviewBoardList.size();
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

}
