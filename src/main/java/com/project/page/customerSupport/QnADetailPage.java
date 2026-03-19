package com.project.page.customerSupport;

import com.project.page.BasePage;
import com.project.page.NavigationBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class QnADetailPage extends BasePage {

    public NavigationBar navi;

    public QnADetailPage(WebDriver driver) {
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


    @FindBy(xpath = "//h1")
    private WebElement QnADetail_PageTitle;

    @FindBy(xpath = "//tr[1]/th")
    private WebElement QnADetail_NumberLabel;

    @FindBy(xpath = "//tr[1]/td")
    private WebElement QnADetail_Number;

    @FindBy(xpath = "//tr[2]/th")
    private WebElement QnADetail_writerLabel;

    @FindBy(xpath = "//tr[2]/td")
    private WebElement QnADetail_writer;

    @FindBy(xpath = "//tr[3]/th")
    private WebElement QnADetail_TitleLabel;

    @FindBy(xpath = "//tr[3]/td")
    private WebElement QnADetail_Title;

    @FindBy(xpath = "//tr[4]/th")
    private WebElement QnADetail_CreateDateLabel;

    @FindBy(xpath = "//tr[4]/td")
    private WebElement QnADetail_CreateDate;

    @FindBy(xpath = "//td/div/p")
    private WebElement QnADetail_Content;

    @FindBy(css = ".btn.btn-outline-dark.mt-3.float-end")
    private WebElement QnADetail_DeleteButton;

    @FindBy(xpath = "//button[2]")
    private WebElement QnADetail_ListButton;

    //관리자권한
    @FindBy(css = "textarea[name='content']")
    private WebElement QnADetail_AdminAnswer;

    @FindBy(css = "button[type='submit']")
    private WebElement QnADetail_AdminAnswerSubmitButton;

    //관리자 답변후
    @FindBy(css = ".rcontent")
    private WebElement QnADetail_AnswerArea;

    @FindBy(xpath = "//dt[i[contains(@class, 'bi-chat-square-quote')]]")
    private WebElement QnADetail_AnswerArea_TextLabel;

    @FindBy(xpath = "//dd/pre")
    private WebElement QnADetail_AnswerArea_Content;

    //관리가 권한
    @FindBy(xpath = "//div[1]/div[1]/div[1]/a[2]")
    private WebElement QnADetail_AnswerArea_ModifyButton;

    @FindBy(xpath = "//div[1]/div[1]/div[1]/a[1]")
    private WebElement QnADetail_AnswerArea_DeleteButton;



    //관리용 텍스트 그룹
    public enum QnADetailPageLabel {
        QNADETAIL_PAGETITLE,QNADETAIL_NUMBERLABEL,QNADETAIL_WRITERLABEL,
        QNADETAIL_TITLELABEL,QNADETAIL_CREATEDATELABEL,QNADETAIL_NUMBER,
        QNADETAIL_WRITER,QNADETAIL_TITLE,QNADETAIL_CREATEDATE,QNADETAIL_CONTENT,
        QNADETAIL_DELETEBUTTON,QNADETAIL_LISTBUTTON,
        QNADETAIL_ANWSERAREACONTENT,QNADETAIL_ANWSERAREASUBMITBUTTON
    }


    //통합 텍스트 추출 메서드
    public String getLabel(QnADetailPage.QnADetailPageLabel labelType) {
        switch (labelType) {
            case QNADETAIL_PAGETITLE:              return getText(QnADetail_PageTitle);
            case QNADETAIL_NUMBERLABEL:            return getText(QnADetail_NumberLabel);
            case QNADETAIL_WRITERLABEL:            return getText(QnADetail_writerLabel);
            case QNADETAIL_TITLELABEL:             return getText(QnADetail_TitleLabel);
            case QNADETAIL_CREATEDATELABEL:        return getText(QnADetail_CreateDateLabel);
            case QNADETAIL_NUMBER:                 return getText(QnADetail_Number);
            case QNADETAIL_WRITER:                 return getText(QnADetail_writer);
            case QNADETAIL_TITLE:                  return getText(QnADetail_Title);
            case QNADETAIL_CREATEDATE:             return getText(QnADetail_CreateDate);
            case QNADETAIL_CONTENT:                return getText(QnADetail_Content);
            case QNADETAIL_DELETEBUTTON:           return getText(QnADetail_DeleteButton);
            case QNADETAIL_LISTBUTTON:             return getText(QnADetail_ListButton);
            case QNADETAIL_ANWSERAREACONTENT:      return getText(QnADetail_AnswerArea_Content);
            case QNADETAIL_ANWSERAREASUBMITBUTTON: return getText(QnADetail_AdminAnswerSubmitButton);

            default:  throw new IllegalArgumentException("정의되지 않은 라벨 타입입니다: " + labelType);
        }
    }

    public void clickDeleteButton(){
        click(QnADetail_DeleteButton);
    }
    public void clickListButton(){
        click(QnADetail_ListButton);
    }
    public void clickAnswerSubmitButton(){
        click(QnADetail_AdminAnswerSubmitButton);
    }
    public void clickAnswerModifyButton(){
        click(QnADetail_AnswerArea_ModifyButton);
    }
    public void clickAnswerDeleteButton(){
        click(QnADetail_AnswerArea_DeleteButton);
    }

    public void inputAdminAnswer(String answer){
        waitForVisible(QnADetail_AdminAnswerSubmitButton);
        QnADetail_AdminAnswer.clear();
        sendKeys(QnADetail_AdminAnswer,answer);
    }

    public boolean checkAnswerAreaVisible(){
        return QnADetail_AnswerArea.isDisplayed();
    }

    public String getContent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String QnAContent = "";

        try {
            enterEditor();
            WebElement editorBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("se2_inputarea")));
            QnAContent = editorBody.getText().trim();

        } catch (Exception e) {
            System.out.println("[ERROR] 본문 내용을 가져오는 중 오류 발생: " + e.getMessage());
        } finally {
            driver.switchTo().defaultContent();
        }

        return QnAContent;
    }

    public void enterEditor() {

        driver.switchTo().defaultContent();

        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        for (WebElement frame : frames) {
            try {
                driver.switchTo().frame(frame);

                if (driver.findElements(By.className("se2_inputarea")).size() > 0) {
                    return;
                }


                List<WebElement> childFrames = driver.findElements(By.tagName("iframe"));
                if (childFrames.size() > 0) {
                    for (WebElement childFrame : childFrames) {
                        driver.switchTo().frame(childFrame);
                        if (driver.findElements(By.className("se2_inputarea")).size() > 0) {
                            return;
                        }
                        driver.switchTo().parentFrame();
                    }
                }
                driver.switchTo().defaultContent();

            } catch (Exception e) {
                System.out.println("[WARN] 프레임 접근 불가: " + e.getMessage());
                driver.switchTo().defaultContent();
            }
        }
    }


}
