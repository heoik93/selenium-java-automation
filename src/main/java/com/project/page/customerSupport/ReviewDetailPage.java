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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ReviewDetailPage extends BasePage {

    public NavigationBar navi;
    public ReviewDetailPage(WebDriver driver) {
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

    //이미지
    @FindBy(css = "img[alt='laundry image']")
    private WebElement ReviewDetailImg;

    //라벨
    @FindBy(className = "form-label")
    private WebElement ReviewDetail_TitleLabel;

    @FindBy(id = "title")
    private WebElement ReviewDetail_Title;

    @FindBy(css = "label[for='code']")
    private WebElement ReviewDetail_OderNumberLabel;

    @FindBy(id = "code")
    private WebElement ReviewDetail_OderNumber;

    @FindBy(css = "label[for='items']")
    private WebElement ReviewDetail_ItemLabel;

    @FindBy(id = "items")
    private WebElement ReviewDetail_Item;

    @FindBy(css = "label[for='star']")
    private WebElement ReviewDetail_StarLabel;

    @FindBy(id = "star")
    private WebElement ReviewDetail_Star;

    @FindBy(css = "label[for='content']")
    private WebElement ReviewDetail_ContentLabel;

    @FindBy(xpath = "//iframe[@id='se2_iframe']")
    private WebElement ReviewDetail_Content;

    //버튼
    @FindBy(css = "button[class='btn btn-outline-primary mt-3 mb-5']")
    private WebElement modifyButton;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[4]/form[1]/div[6]/button[2]")
    private WebElement deleteButton;

    @FindBy(css = ".btn.btn-outline-secondary.mt-3.mb-5")
    private WebElement listButton;

    @FindBy(css = "button[class='btn btn-outline-primary mt-3 mb-5']")
    private List<WebElement> modifyButtons;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    //버튼체크
    public boolean ModifyButton_displayCheck() {
            return modifyButton.isDisplayed();
    }

    public boolean DeleteButton_displayCheck() {
        return deleteButton.isDisplayed();
    }

    public boolean ListButton_displayCheck() {
        return listButton.isDisplayed();
    }

    public boolean ModifyButton_hiddenCheck() {
        return modifyButtons.isEmpty();
    }

    public boolean DeleteButton_hiddenCheck() {
        return modifyButtons.isEmpty();
    }


    //버튼클릭
    public void clickModifyButton() {
        hover(modifyButton);
        click(modifyButton);
    }

    public void clickDeleteButton() {
        hover(deleteButton);
        click(deleteButton);
    }

    public void clickListButton() {
        hover(listButton);
        click(listButton);
    }

    //src취득
    public String getSrc_ReviewDetailPage(){ return ReviewDetailImg.getAttribute("src");   }


    //관리용 텍스트 그룹
    public enum ReviewDetailPageLabel {
        REVIEWBOARDTAB,FAQBOARDTAB,QNABOARDTAB,NOTICETAB,
        TITLELABEL,TITLE,ODERNUMBERLABEL,ODERNUMBER,ITEMLABEL,ITEM,STARLABEL,STAR,
        CONTENTLABEL,CONTENT,MODIFYBUTTON,DELETEBUTTON,LISTBUTTON
    }

    //통합 텍스트 추출 메서드
    public String getLabel(ReviewDetailPage.ReviewDetailPageLabel labelType) {
        switch (labelType) {
            case REVIEWBOARDTAB:        return getText(reviewBoardTab);
            case FAQBOARDTAB:           return getText(FAQBoardTab);
            case QNABOARDTAB:           return getText(QnABoardTab);
            case NOTICETAB:             return getText(NoticeTab);
            case TITLELABEL:            return getText(ReviewDetail_TitleLabel);
            case TITLE:                 return getText(ReviewDetail_Title);
            case ODERNUMBERLABEL:       return getText(ReviewDetail_OderNumberLabel);
            case ODERNUMBER:            return getText(ReviewDetail_OderNumber);
            case ITEMLABEL:             return getText(ReviewDetail_ItemLabel);
            case ITEM:                  return getText(ReviewDetail_Item);
            case STARLABEL:             return getText(ReviewDetail_StarLabel);
            case STAR:                  return getText(ReviewDetail_Star);
            case CONTENTLABEL:          return getText(ReviewDetail_ContentLabel);
            case CONTENT:               return getText(ReviewDetail_Content);
            case MODIFYBUTTON:          return getText(modifyButton);
            case DELETEBUTTON:          return getText(deleteButton);
            case LISTBUTTON:            return getText(listButton);


            default:  throw new IllegalArgumentException("정의되지 않은 라벨 타입입니다: " + labelType);
        }
    }

    public String getTitle(){
       return ReviewDetail_Title.getAttribute("value");
    }

    public String getStar(){
        Select select = new Select(ReviewDetail_Star);
        return select.getFirstSelectedOption().getText();
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

    public String getContent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String reviewContent = "";

        try {
            enterEditor();
            WebElement editorBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("se2_inputarea")));
            reviewContent = editorBody.getText().trim();

        } catch (Exception e) {
            System.out.println("[ERROR] 본문 내용을 가져오는 중 오류 발생: " + e.getMessage());
        } finally {
            driver.switchTo().defaultContent();
        }

        return reviewContent;
    }

    public boolean check_OderNumberFiledEnable(){
        return ReviewDetail_OderNumber.isEnabled();
    }

    public boolean check_ItemFiledEnable(){
        return ReviewDetail_Item.isEnabled();
    }
    public List<String> getAllSearchOptions() {
        Select select = new Select(ReviewDetail_Star);
        List<WebElement> options = select.getOptions();

        List<String> optionTexts = new ArrayList<>();
        for (WebElement option : options) {
            optionTexts.add(option.getText().trim());
        }
        return optionTexts;
    }

    public void CreateReview(String Title, String Star, String Content) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 1. 제목 입력
        try {
            WebElement titleInput = wait.until(ExpectedConditions.visibilityOf(ReviewDetail_Title));
            titleInput.clear();
            titleInput.sendKeys(Title);
            System.out.println("[INFO] 제목 입력 완료: " + Title);
        } catch (Exception e) {
            System.out.println("[ERROR] 제목 필드를 찾을 수 없습니다: " + e.getMessage());
        }

        // 2. 별점 선택
        try {
            wait.until(ExpectedConditions.visibilityOf(ReviewDetail_Star));
            Select select = new Select(ReviewDetail_Star);
            select.selectByVisibleText(Star);
            System.out.println("[INFO] 별점 선택 완료: " + Star);
        } catch (Exception e) {
            System.out.println("[ERROR] 별점 선택 실패: " + e.getMessage());
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

        // 4. 저장 버튼 클릭
        try {
            click(submitButton);
            System.out.println("[INFO] 저장 버튼 클릭 완료.");
        } catch (Exception e) {
            System.out.println("[ERROR] 저장 버튼 클릭 실패: " + e.getMessage());
        }
    }

    public String getOderNumber (){
        return ReviewDetail_OderNumber.getAttribute("value");
    }



}
