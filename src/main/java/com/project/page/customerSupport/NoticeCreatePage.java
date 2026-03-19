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
import java.util.List;
import java.util.NoSuchElementException;

public class NoticeCreatePage extends BasePage {
    public NavigationBar navi;

    public NoticeCreatePage(WebDriver driver) {
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

    //공지사항 작성하기 페이지
    @FindBy(css = "label[for='title']")
    private WebElement Create_NoticeTitleLabel;

    @FindBy(id = "title")
    private WebElement Create_NoticeTitle;

    @FindBy(css = ".form-text.text-muted")
    private WebElement Create_NoticeTitleRuleText;

    @FindBy(css = "label[for='content']")
    private WebElement Create_ContentLabel;

    @FindBy(css = "button[type='submit']")
    private WebElement Create_SubmitButton;

    //공지사항 수정하기 페이지
    @FindBy(css = "label[for='writer']")
    private WebElement Modify_writerLabel;

    @FindBy(css = "#writer")
    private WebElement Modify_writer;

    @FindBy(css = "label[for='title']")
    private WebElement Modify_TitleLabel;

    @FindBy(id = "title")
    private WebElement Modify_Title;

    @FindBy(css = "label[for='content']")
    private WebElement Modify_ContentLabel;

    @FindBy(xpath = "//button[@class='btn btn-dark']")
    private WebElement Modify_ModifyButton;

    //관리용 텍스트 그룹
    public enum NoticeCreatePageLabel {
        CREATE_NOTICETITLELABEL,CREATE_NOTICETITLERULETEXT,
        CREATE_CONTENTLABEL,CREATE_SUBMITBUTTON,
        MODIFY_WRITERLABEL,MODIFY_WRITER,MODIFY_TITLELABEL,MODIFY_TITLE,
        MODIFY_CONTENTLABEL,MODIFY_MODIFYBUTTON
    }


    //통합 텍스트 추출 메서드
    public String getLabel(NoticeCreatePage.NoticeCreatePageLabel labelType) {
        switch (labelType) {
            case CREATE_NOTICETITLELABEL:      return getText(Create_NoticeTitleLabel);
            case CREATE_NOTICETITLERULETEXT:   return getText(Create_NoticeTitleRuleText);
            case CREATE_CONTENTLABEL:          return getText(Create_ContentLabel);
            case CREATE_SUBMITBUTTON:          return getText(Create_SubmitButton);
            case MODIFY_WRITERLABEL:           return getText(Modify_writerLabel);
            case MODIFY_TITLELABEL:            return getText(Modify_TitleLabel);
            case MODIFY_CONTENTLABEL:          return getText(Modify_ContentLabel);
            case MODIFY_MODIFYBUTTON:          return getText(Modify_ModifyButton);

            default:  throw new IllegalArgumentException("정의되지 않은 라벨 타입입니다: " + labelType);
        }
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

        throw new NoSuchElementException("[FAIL]모든 프레임을 탐색하였지만 'se2_inputarea' 를 찾지 못했습니다.");
    }


    public void CreateNotice(String Title, String Content) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 1. 제목 입력
        try {
            WebElement titleInput = wait.until(ExpectedConditions.visibilityOf(Create_NoticeTitle));
            titleInput.clear();
            titleInput.sendKeys(Title);
            System.out.println("[INFO] 제목 입력 완료: " + Title);
        } catch (Exception e) {
            System.out.println("[ERROR] 제목 필드를 찾을 수 없습니다: " + e.getMessage());
        }

        // 2. 에디터 본문 작성
        try {
            enterEditor();

            WebElement editorBody = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("se2_inputarea")));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // HTML 형식으로 컨텐츠 삽입
            String script = "arguments[0].innerHTML = '<b>" + Content + "</b>';";
            js.executeScript(script, editorBody);
            System.out.println("[INFO] 에디터 본문 입력 완료.");

        } catch (Exception e) {
            System.out.println("[ERROR] 에디터 작성 중 오류 발생: " + e.getMessage());
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public String getContent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String NoticeContent = "";

        try {
            enterEditor();
            WebElement editorBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("se2_inputarea")));
            NoticeContent = editorBody.getText().trim();

        } catch (Exception e) {
            System.out.println("[ERROR] 본문 내용을 가져오는 중 오류 발생: " + e.getMessage());
        } finally {
            driver.switchTo().defaultContent();
        }

        return NoticeContent;
    }

    public void changTitle(String Title) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            WebElement titleInput = wait.until(ExpectedConditions.visibilityOf(Modify_Title));
            titleInput.clear();
            titleInput.sendKeys(Title);
            System.out.println("[INFO] 제목 입력 완료: " + Title);
        } catch (Exception e) {
            System.out.println("[ERROR] 제목 필드를 찾을 수 없습니다: " + e.getMessage());
        }

    }

    public void changeContent(String Content) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            enterEditor();

            WebElement editorBody = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("se2_inputarea")));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // HTML 형식으로 컨텐츠 삽입
            String script = "arguments[0].innerHTML = '<b>" + Content + "</b>';";
            js.executeScript(script, editorBody);
            System.out.println("[INFO] 에디터 본문 입력 완료.");

        } catch (Exception e) {
            System.out.println("[ERROR] 에디터 작성 중 오류 발생: " + e.getMessage());
        } finally {
            driver.switchTo().defaultContent();
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

    public void clickModifyButton() {
        hover(Modify_ModifyButton);
        click(Modify_ModifyButton);
    }

    public void clickCreateButton() {
        hover(Create_SubmitButton);
        click(Create_SubmitButton);
    }

    public String getWriter(){
        return Modify_writer.getAttribute("value");
    }

    public boolean checkWriterFiled_disable(){
        String isDisabled = Modify_writer.getAttribute("disabled");
        return isDisabled != null;
    }

    public String getTitle(){
        return Modify_Title.getAttribute("value");
    }

}
