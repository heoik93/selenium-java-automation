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

public class QnACreatePage extends BasePage {

    public NavigationBar navi;

    public QnACreatePage(WebDriver driver) {
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
    private WebElement Create_PageTitle;

    @FindBy(className = "form-label")
    private WebElement Create_TitleLabel;

    @FindBy(id = "title")
    private WebElement Create_Title;

    @FindBy(css = "label[for='content']")
    private WebElement Create_ContentLabel;

    @FindBy(css = "button[type='submit']")
    private WebElement Create_CreateButton;

    @FindBy(css = "button[onclick='goBack()']")
    private WebElement Create_CancelButton;


    //관리용 텍스트 그룹
    public enum QnACreatePageLabel {
        CREATE_PAGETITLE,CREATE_TITLELABEL,CREATE_TITLE,
        CREATE_CONTENTLABEL,CREATE_CREATEBUTTON,CREATE_CANCELBUTTON
    }


    //통합 텍스트 추출 메서드
    public String getLabel(QnACreatePage.QnACreatePageLabel labelType) {
        switch (labelType) {
            case CREATE_PAGETITLE:                 return getText(Create_PageTitle);
            case CREATE_TITLELABEL:                return getText(Create_TitleLabel);
            case CREATE_TITLE:                     return getText(Create_Title);
            case CREATE_CONTENTLABEL:              return getText(Create_ContentLabel);
            case CREATE_CREATEBUTTON:              return getText(Create_CreateButton);
            case CREATE_CANCELBUTTON:              return getText(Create_CancelButton);

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


    public void CreateQnA(String Title, String Content) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 1. 제목 입력
        try {
            WebElement titleInput = wait.until(ExpectedConditions.visibilityOf(Create_Title));
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

    public void clickCreateButton() {
        click(Create_CreateButton);
    }

    public void clickCancelButton() {
        click(Create_CancelButton);
    }



}
