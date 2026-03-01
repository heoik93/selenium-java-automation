package com.project.page.customerSupport;

import com.project.page.BasePage;
import com.project.page.NavigationBar;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NoticeDetailPage extends BasePage {

    public NavigationBar navi;

    public NoticeDetailPage(WebDriver driver) {
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


    //공지사할 디테일 페이지
    @FindBy(xpath = "/html[1]/body[1]/div[3]/h2[1]")
    private WebElement Detail_NoticeTitle;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/span[1]/span[1]")
    private WebElement Detail_UserId;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/span[1]/span[2]")
    private WebElement Detail_Count;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/span[1]/span[3]")
    private WebElement Detail_CreateDate;

    @FindBy(css = ".content")
    private WebElement Detail_Content;

    @FindBy(xpath = "//div[2]/a[1]")
    private WebElement Detail_ListButton;

    @FindBy(xpath = "//div[2]/span[1]/a[1]")
    private WebElement Detail_ModifyButton;

    @FindBy(css = "button[type='submit']")
    private WebElement Detail_DeleteButton;


    //관리용 텍스트 그룹
    public enum NoticeDetailPageLabel {
        DETAIL_NOTICETITLE,DETAIL_USERID,DETAIL_COUNT,DETAIL_CREATEDATE,DETAIL_CONTENT,
        DETAIL_LISTBUTTON, DETAIL_MODIFYBUTTON,DETAIL_DELETEBUTTON
    }


    //통합 텍스트 추출 메서드
    public String getLabel(NoticeDetailPage.NoticeDetailPageLabel labelType) {
        switch (labelType) {
            case DETAIL_NOTICETITLE:           return getText(Detail_NoticeTitle);
            case DETAIL_USERID:                return getText(Detail_UserId);
            case DETAIL_COUNT:                 return getText(Detail_Count);
            case DETAIL_CREATEDATE:            return getText(Detail_CreateDate);
            case DETAIL_CONTENT:               return getText(Detail_Content);
            case DETAIL_LISTBUTTON:            return getText(Detail_ListButton);
            case DETAIL_MODIFYBUTTON:          return getText(Detail_ModifyButton);
            case DETAIL_DELETEBUTTON:          return getText(Detail_DeleteButton);

            default:  throw new IllegalArgumentException("정의되지 않은 라벨 타입입니다: " + labelType);
        }
    }


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

    public void clickModifyButton(){
        hover(Detail_ModifyButton);
        click(Detail_ModifyButton);
    }

    public int getNoticeCount(){
        String countText = Detail_Count.getText();
        int noticeCount = Integer.parseInt(countText);
        System.out.println("[INFO] 현재 조회수: " + noticeCount);

        return noticeCount;
    }

    public void clickListButton(){
        click(Detail_ListButton);
    }

    public void clickDeleteButton(){
        click(Detail_DeleteButton);
    }


}
