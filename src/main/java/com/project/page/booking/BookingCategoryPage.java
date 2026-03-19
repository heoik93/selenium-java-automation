package com.project.page.booking;

import com.project.page.BasePage;
import com.project.page.NavigationBar;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class BookingCategoryPage extends BasePage {

    public NavigationBar navi;

    public BookingCategoryPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        this.navi = new NavigationBar(driver);
    }

    @FindBy(css = "li[class='subNavItem'] a")
    private WebElement pageTitle;

    @FindBy(css = ".prod")
    private WebElement pageSubTitle;

    @FindBy(css = "img[alt='상품 이미지']")
    private  WebElement productImg;

    @FindBy(css = "div[class='top'] h1")
    private WebElement productBuyLabel;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/main[1]/div[2]/div[1]/dl[1]/dt[1]")
    private WebElement productNameLabel;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/main[1]/div[2]/div[1]/dl[1]/dt[2]")
    private WebElement productOptionLabel;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/main[1]/div[2]/div[1]/dl[1]/dt[3]")
    private WebElement productBookingdateLabel;

    @FindBy(css = "main[id='order_wrap'] p:nth-child(1)")
    private WebElement productAmountLabel;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/main[1]/div[2]/div[1]/dl[1]/dd[1]")
    private WebElement productName;

    @FindBy(id = "product")
    private WebElement productOptionSelectBox;

    @FindBy(id = "one")
    private WebElement productBookingdateInputbox;

    @FindBy(id = "order_btn")
    private WebElement  productBookingButton;

    @FindBy(className = "result_price")
    private WebElement productAmount;

    @FindBy(css = "div[class='explain'] h1")
    private WebElement explainTitle;

    @FindBy(css = "img[alt='수거/배송 안내']")
    private WebElement explainImg_1;

    @FindBy(css = "div[class='explain'] p:nth-child(1)")
    private WebElement explainText_1;

    @FindBy(css = "div[class='explain'] p:nth-child(2)")
    private WebElement explainText_2;

    @FindBy(css = "div[class='explain'] p:nth-child(3)")
    private WebElement explainText_3;

    @FindBy(css = "img[alt='절차1']")
    private WebElement explainImg_2;

    @FindBy(css = "img[alt='절차2']")
    private WebElement explainImg_3;

    @FindBy(css = "img[alt='절차3']")
    private WebElement explainImg_4;

    @FindBy(xpath = "//div[@class='container']//h2[1]")
    private WebElement explainFooterText;


    @FindBy(className = "number")
    private WebElement productBuydetailArea;

    //옵션 선택전에는 비활성화
    @FindBy(css = ".name")
    private List<WebElement> productBuydetailArea_Name;

    @FindBy(xpath = "//input[contains(@id,'num')]")
    private List<WebElement> productBuydetailArea_Number;

    @FindBy(xpath = "//div[contains(@class,'price in num')]")
    private List<WebElement> productBuydetailArea_Amount;

    @FindBy(css = "button[aria-label='삭제']")
    private List<WebElement> itemDeleteButton;

    //관리용 텍스트그룹
    public enum BookingCategoryLabel {
        PAGE_TITLE,PAGE_SUBTITLE,
        PRODUCT_BUY,PRODUCT_NAME,PRODUCT_OPTION,PRODUCT_BOOkINGDATE,PRODUCT_AMOUNT,
        PRODUCTNAME,PRODUCTBOOKINGBTOON,PRODUCTAMOUNT,
        EXPLAIN_TITLE,EXPLAIN_TEXT1,EXPLAIN_TEXT2,EXPLAIN_TEXT3,EXPLAIN_FOOTERTEXT
    }

    //통합 텍스트 추출 메서드
    public String getLabel(BookingCategoryLabel labelType) {
        switch (labelType) {
            case PAGE_TITLE: return getText(pageTitle);
            case PAGE_SUBTITLE: return getText(pageSubTitle);

            case PRODUCT_BUY: return getText(productBuyLabel);
            case PRODUCT_NAME: return getText(productNameLabel);
            case PRODUCT_OPTION: return getText(productOptionLabel);
            case PRODUCT_BOOkINGDATE: return getText(productBookingdateLabel);
            case PRODUCT_AMOUNT: return getText(productAmountLabel);

            case PRODUCTNAME: return getText(productName);
            case PRODUCTBOOKINGBTOON: return getText(productBookingButton);
            case PRODUCTAMOUNT: return getText(productAmount);

            case EXPLAIN_TITLE: return getText(explainTitle);
            case EXPLAIN_TEXT1: return getText(explainText_1);
            case EXPLAIN_TEXT2: return getText(explainText_2);
            case EXPLAIN_TEXT3: return getText(explainText_3);
            case EXPLAIN_FOOTERTEXT: return getText(explainFooterText);

            default: throw new IllegalArgumentException("정의되지 않은 라벨 타입입니다: " + labelType);
        }
    }

    public String getImgSrc_productImg(){ return productImg.getAttribute("src"); }
    public String getImgSrc_explainImg_1(){ return explainImg_1.getAttribute("src"); }
    public String getImgSrc_explainImg_2(){ return explainImg_2.getAttribute("src"); }
    public String getImgSrc_explainImg_3(){ return explainImg_3.getAttribute("src"); }
    public String getImgSrc_explainImg_4(){ return explainImg_4.getAttribute("src"); }


    public void clickBookingButton(){  click(productBookingButton); }
    public void clickSelectBox(){  click(productOptionSelectBox); }
    public void clickDeleteButton(int index){
        if(index >= 0 && index < itemDeleteButton.size()){
            click(itemDeleteButton.get(index));
        }
    }

    public void inputBookingDate(String date){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("$(arguments[0]).val('" + date + "').change();", productBookingdateInputbox);
        System.out.println("[INFO] JS 주입을 통해 날짜 입력 완료: " + date);
    }


    //에리어 표시 확인
    public boolean areaDisplayCheck_Name(){
        return  !productBuydetailArea_Name.isEmpty() && productBuydetailArea_Name.get(0).isDisplayed();
    }

    public boolean areaDisplayCheck_Number(){
        return !productBuydetailArea_Number.isEmpty() && productBuydetailArea_Number.get(0).isDisplayed();
    }

    public boolean areaDisplayCheck_Amount(){
        return !productBuydetailArea_Amount.isEmpty() && productBuydetailArea_Amount.get(0).isDisplayed();
    }


    // SelectBox 옵션 리스트화
    public List<String> getAllProductOptions() {
        Select select = new Select(productOptionSelectBox);
        return select.getOptions().stream()
                .map(WebElement::getText)
                .filter(text -> !text.equals("선택해주세요"))
                .collect(Collectors.toList());
    }

    // 특정 상품을 이름으로 선택하는 메서드
    public void selectProductByName(String itemName) {
        Select select = new Select(productOptionSelectBox);
        select.selectByVisibleText(itemName);

    }

    // 상품명 텍스트 가져오기
    public String getActualProductName() {
        if (!productBuydetailArea_Name.isEmpty()) {
            int lastIndex = productBuydetailArea_Name.size() - 1;
            return productBuydetailArea_Name.get(lastIndex).getText().trim();
        }
        return "";
    }

    // 가격(Amount) 텍스트 가져오기
    public String getActualAmount() {
        if (!productBuydetailArea_Amount.isEmpty()) {
            int lastIndex = productBuydetailArea_Amount.size() - 1;
            return productBuydetailArea_Amount.get(lastIndex).getText().trim();
        }
        return "";
    }

    // 수량(Number) 입력값 가져오기
    public String getActualNumber() {
        if (!productBuydetailArea_Number.isEmpty()) {
            int lastIndex = productBuydetailArea_Number.size() - 1;
            return productBuydetailArea_Number.get(lastIndex).getAttribute("value");
        }
        return "";
    }

    public void setLastProductQuantity(int count) {
        // 상품 리스트 중 가장 마지막 요소의 수량 Input 혹은 SelectBox를 찾음
        List<WebElement> quantityInputs = driver.findElements(By.xpath("//input[contains(@id,'num')]"));
        if (!quantityInputs.isEmpty()) {
            WebElement lastInput = quantityInputs.get(quantityInputs.size() - 1);
            lastInput.clear();
            lastInput.sendKeys(String.valueOf(count));
            lastInput.sendKeys(Keys.ENTER);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getTotalActualAmount() {
    new WebDriverWait(driver, Duration.ofSeconds(2)).until(d -> !productAmount.getText().isEmpty());
        return productAmount.getText().trim();
    }

}

