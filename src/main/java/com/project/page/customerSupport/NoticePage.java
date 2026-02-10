package com.project.page.customerSupport;

import com.project.page.BasePage;
import com.project.page.NavigationBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    //라벨
    @FindBy(css = "th:nth-child(1)")
    private WebElement ListNumber;

    @FindBy(css = "th:nth-child(1)")
    private WebElement ListTitle;

    @FindBy(css = "th:nth-child(1)")
    private WebElement ListRegiDate;





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
