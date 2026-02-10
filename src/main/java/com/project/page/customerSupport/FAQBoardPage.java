package com.project.page.customerSupport;

import com.project.page.BasePage;
import com.project.page.NavigationBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FAQBoardPage extends BasePage {

    public NavigationBar navi;

    public FAQBoardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.navi = new NavigationBar(driver);
    }

    //탭
    @FindBy(css = "nav[class='bg-light'] li:nth-child(1) a:nth-child(1)")
    private WebElement reviewBoardTab;

    @FindBy(css = "a[class=' active']")
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

    //페이지타이틀 및 필터
    @FindBy(css = "div[class='container'] h1")
    private WebElement pageTitleLabel;

    @FindBy(css = ".css-1y7lkh5")
    private WebElement pageTitleUnderTextLabel;

    @FindBy(css = "button[value='service']")
    private WebElement boardFilterServiceButton;

    @FindBy(css = "button[value='order']")
    private WebElement boardFilterOrderButton;

    @FindBy(css = "button[value='member']")
    private WebElement boardFilterMemberButton;

    @FindBy(css = "button[value='etc']")
    private WebElement boardFilterEtcButton;

    @FindBy(id = "qna")
    private WebElement qnaBoardButton;


    //게시판라벨
    @FindBy(css = "th:nth-child(1)")
    private WebElement FAQNumberLabel;

    @FindBy(css = "th:nth-child(2)")
    private WebElement FAQCategoryLabel;

    @FindBy(css = "th:nth-child(3)")
    private WebElement FAQTitleLabel;

    //게시판
    @FindBy(css = "tbody tr")
    private List<WebElement> FAQBoradList;

    @FindBy(css = "div[class='container'] li")
    private List<WebElement> FAQBoradNavi;


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

}
