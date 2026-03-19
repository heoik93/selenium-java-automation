package com.project.page.customerSupport;

import com.project.constants.PageLabels;
import com.project.page.BasePage;
import com.project.page.NavigationBar;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class FAQCreatePage extends BasePage {

    public NavigationBar navi;

    public FAQCreatePage(WebDriver driver) {
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

    //페이지
    @FindBy(css = ".mb-4")
    private WebElement faqCreate_PageTitle;

    @FindBy(css = "label[for='category']")
    private WebElement faqCreate_CategoryLabel;

    @FindBy(id = "category")
    private List<WebElement> faqCreate_CategoryList;

    @FindBy(id = "category")
    private WebElement faqCreate_Category;

    @FindBy(css = "label[for='title']")
    private WebElement faqCreate_TitleLabel;

    @FindBy(id = "title")
    private WebElement faqCreate_Title;

    @FindBy(css = "label[for='content']")
    private WebElement faqCreate_ContentLabel;

    @FindBy(xpath = "//iframe[@id='se2_iframe']")
    private WebElement faqCreate_Content;

    //버튼
    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    @FindBy(css = "button[type='reset']")
    private WebElement cancelButton;

    @FindBy(css = "button[class='btn btn-dark']")
    private WebElement modifyButton;

    @FindBy(css = ".btn.btn-dark.float-end")
    private WebElement modify_CancelButton;

    //관리용 텍스트 그룹
    public enum FAQCreatePageLabel {
        REVIEWBOARDTAB,FAQBOARDTAB,QNABOARDTAB,NOTICETAB,
        FAQCREATE_PAGETITLE,FAQCREATE_CATEGORYLABEL,
        FAQCREATE_TITLELABEL,FAQCREATE_CONTENTLABEL,
        SUBMITBUTTON,CANCELBUTTON,MODIFYBUTTON,MODIFYCANCELBUTTON
    }

    //통합 텍스트 추출 메서드
    public String getLabel(FAQCreatePageLabel labelType) {
        switch (labelType) {
            case REVIEWBOARDTAB:           return getText(reviewBoardTab);
            case FAQBOARDTAB:              return getText(FAQBoardTab);
            case QNABOARDTAB:              return getText(QnABoardTab);
            case NOTICETAB:                return getText(NoticeTab);
            case FAQCREATE_PAGETITLE:      return getText(faqCreate_PageTitle);
            case FAQCREATE_CATEGORYLABEL:  return getText(faqCreate_CategoryLabel);
            case FAQCREATE_TITLELABEL:     return getText(faqCreate_TitleLabel);
            case FAQCREATE_CONTENTLABEL:   return getText(faqCreate_ContentLabel);
            case SUBMITBUTTON:             return getText(submitButton);
            case CANCELBUTTON:             return getText(cancelButton);
            case MODIFYBUTTON:             return getText(modifyButton);
            case MODIFYCANCELBUTTON:       return getText(modify_CancelButton);


            default:  throw new IllegalArgumentException("정의되지 않은 라벨 타입입니다: " + labelType);
        }
    }

    public String getTitle(){
        return faqCreate_Title.getAttribute("value");
    }

    public String getCategory(){
        Select select = new Select(faqCreate_Category);
        return select.getFirstSelectedOption().getText();
    }

    public String getContent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String FAQContent = "";

        try {
            enterEditor();
            WebElement editorBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("se2_inputarea")));
            FAQContent = editorBody.getText().trim();

        } catch (Exception e) {
            System.out.println("[ERROR] 본문 내용을 가져오는 중 오류 발생: " + e.getMessage());
        } finally {
            driver.switchTo().defaultContent();
        }

        return FAQContent;
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



    public void CreateFAQ(String Title, String Category, String Content) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 1. 제목 입력
        try {
            WebElement titleInput = wait.until(ExpectedConditions.visibilityOf(faqCreate_Title));
            titleInput.clear();
            titleInput.sendKeys(Title);
            System.out.println("[INFO] 제목 입력 완료: " + Title);
        } catch (Exception e) {
            System.out.println("[ERROR] 제목 필드를 찾을 수 없습니다: " + e.getMessage());
        }

        //2. 카테고리선택
        try {
            wait.until(ExpectedConditions.visibilityOf(faqCreate_Category));
            Select select = new Select(faqCreate_Category);
            select.selectByVisibleText(Category);
            System.out.println("[INFO] 카테고리 선택 완료: " + Category);
        } catch (Exception e) {
            System.out.println("[ERROR] 카테고리 선택 실패: " + e.getMessage());
        }

        // 3. 에디터 본문 작성
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

    public void inputTitle(String Title) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            WebElement titleInput = wait.until(ExpectedConditions.visibilityOf(faqCreate_Title));
            titleInput.clear();
            titleInput.sendKeys(Title);
            System.out.println("[INFO] 제목 입력 완료: " + Title);
        } catch (Exception e) {
            System.out.println("[ERROR] 제목 필드를 찾을 수 없습니다: " + e.getMessage());
        }

    }

    public void inputCategory(String Category) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            wait.until(ExpectedConditions.visibilityOf(faqCreate_Category));
            Select select = new Select(faqCreate_Category);
            select.selectByVisibleText(Category);
            System.out.println("[INFO] 카테고리 선택 완료: " + Category);
        } catch (Exception e) {
            System.out.println("[ERROR] 카테고리 선택 실패: " + e.getMessage());
        }

    }

    public void inputContent(String Content) {
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

    public void clickCancelButton(){
        click(cancelButton);
    }
    public void clickSubmitButton(){
        click(submitButton);
    }
    public void clickModifyButton(){
        click(modifyButton);
    }

    public void clickFAQBoardTab() {
        hover(FAQBoardTab);
        click(FAQBoardTab);
    }

    public void clickModifyCancelButton(){
        click(modify_CancelButton);
    }

    public List<String> getAllSearchOptions() {
        Select select = new Select(faqCreate_Category);
        List<WebElement> options = select.getOptions();

        List<String> optionTexts = new ArrayList<>();
        for (WebElement option : options) {
            optionTexts.add(option.getText().trim());
        }
        return optionTexts;
    }

    public String selectRandomCategory() {
        List<String> categories = PageLabels.faqBoard_CategoryOptions;
        Random random = new Random();
        int randomIndex = random.nextInt(categories.size() - 1) + 1;
        return categories.get(randomIndex);
    }

}
