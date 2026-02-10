package com.project.page.customerSupport;

import com.project.page.BasePage;
import com.project.page.NavigationBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(css = "body div[class='content-wrapper container'] span:nth-child(1)")
    private WebElement boardSubText;

    //게시물
    @FindBy(css = "th:nth-child(1)")
    private WebElement listTitle;

    @FindBy(css = "th:nth-child(2)")
    private WebElement listCreateDate;

    @FindBy(css = "th:nth-child(3)")
    private WebElement listAnswerStatus;



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
