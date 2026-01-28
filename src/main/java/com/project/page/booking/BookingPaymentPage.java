package com.project.page.booking;

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
import java.util.Set;

import static org.openqa.selenium.devtools.v117.domstorage.DOMStorage.clear;

public class BookingPaymentPage extends BasePage {

    public NavigationBar navi;

    public BookingPaymentPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        this.navi = new NavigationBar(driver);
    }

    @FindBy(css = "li[class='subNavItem'] a")
    private WebElement pageTitle;

    @FindBy(css = "div[class='step'] span")
    private WebElement pageSubTitle;

    @FindBy(css = "div[class='product'] p")
    private WebElement productInfoLabel;

    @FindBy(xpath = "/html[1]/body[1]/form[1]/div[1]/div[1]/div[2]/div[1]/label[1]")
    private WebElement userIdLabel;

    @FindBy(xpath = "//input[@id='orderer']")
    private WebElement userIdInputBox;

    @FindBy(xpath = "/html[1]/body[1]/form[1]/div[1]/div[1]/div[2]/div[2]/label[1]")
    private WebElement productCountLabel;

    @FindBy(xpath = "//input[@id='productcount']")
    private WebElement productCountInputBox;

    @FindBy(xpath = "/html[1]/body[1]/form[1]/div[1]/div[1]/div[2]/div[3]/label[1]")
    private WebElement amountLabel;

    @FindBy(xpath = "//input[@id='order_price']")
    private WebElement amountInputBox;

    @FindBy(xpath = "/html[1]/body[1]/form[1]/div[1]/div[1]/div[2]/div[4]/label[1]")
    private WebElement bookingDateLabel;

    @FindBy(xpath = "//input[@id='reservation_date']")
    private WebElement bookingDateInputBox;

    @FindBy(css = "div[class='addr'] p")
    private WebElement bookingAddressLabel;

    @FindBy(id = "order_addr_old")
    private WebElement oldAddressCheckbox;

    @FindBy(css = "label[for='order_addr_old']")
    private WebElement oldAddressLabel;

    @FindBy(id = "order_addr_new")
    private WebElement newAddressCheckbox;

    @FindBy(css = "label[for='order_addr_new']")
    private WebElement newAddressLabel;

    @FindBy(css = "label[for='order_addr']")
    private WebElement addressLabel;

    @FindBy(id = "order_addr")
    private WebElement addressInputBox;

    @FindBy(css = "label[for='request']")
    private WebElement requestLabel;

    @FindBy(id = "request")
    private WebElement requestInputBox;

    @FindBy(css = "label[for='creditEmail']")
    private WebElement emailLabel;

    @FindBy(id = "creditEmail")
    private WebElement emailInputBox;

    @FindBy(css = "div[class='creditForm'] p")
    private WebElement paymentMethodLabel;

    @FindBy(css = ".btn.btn-dark")
    private WebElement paymentButton;

    //기존 및 신규배송지 관련 표시/비표시 요소
    //1. 기존주소
    @FindBy(id = "selectAddr_curAddr")
    private WebElement oldAddressArea;

    //2. 신규주소
    @FindBy(id = "selectAddr_newAddr")
    private WebElement newAddressArea;

    @FindBy(css = ".btn.btn-outline-primary.my-2")
    private WebElement newAddressSearchButton;



    //관리용 텍스트 그룹
    public enum BookingPaymentLabel {
        PAGETITLE, PAGESUBTITLE,
        PRODUCTINFOLABEL, USERIDLABEL, PRODUCTCOUNTLABEL, AMOUNTLABEL,
        BOOKINGDATELABEL, BOOKINGADDRESSLABEL, ADDRESSLABEL, REQUESTLABEL,
        EMAILLABEL, PAYMENTMETHODLABEL, PAYMENTBUTTON, OLDADDRESSLABEL, NEWADDRESSLABEL
    }

    //통합 텍스트 추출 메서드
    public String getLabel(BookingPaymentLabel labelType) {
        switch (labelType) {
            case PAGETITLE:           return getText(pageTitle);
            case PAGESUBTITLE:        return getText(pageSubTitle);
            case PRODUCTINFOLABEL:    return getText(productInfoLabel);
            case USERIDLABEL:         return getText(userIdLabel);
            case PRODUCTCOUNTLABEL:   return getText(productCountLabel);
            case AMOUNTLABEL:         return getText(amountLabel);
            case BOOKINGDATELABEL:    return getText(bookingDateLabel);
            case BOOKINGADDRESSLABEL: return getText(bookingAddressLabel);
            case ADDRESSLABEL:        return getText(addressLabel);
            case REQUESTLABEL:        return getText(requestLabel);
            case EMAILLABEL:          return getText(emailLabel);
            case PAYMENTMETHODLABEL:  return getText(paymentMethodLabel);
            case PAYMENTBUTTON:       return getText(paymentButton);
            case OLDADDRESSLABEL:     return getText(oldAddressLabel);
            case NEWADDRESSLABEL:     return getText(newAddressLabel);

            default:  throw new IllegalArgumentException("정의되지 않은 라벨 타입입니다: " + labelType);
        }
    }

    //placeholder 및 inputbox 텍스트 추출메서드
    public String getPlaceholder_requestInputBox() {  return requestInputBox.getAttribute("placeholder");  }
    public String getPlaceholder_emailInputBox(){  return emailInputBox.getAttribute("placeholder");   }

    public String getInputbox_userIdInputBox(){  return userIdInputBox.getAttribute("value");  }
    public String getInputbox_productCountInputBox(){  return productCountInputBox.getAttribute("value");  }
    public String getInputbox_amountInputBox(){  return amountInputBox.getAttribute("value");  }
    public String getInputbox_bookingDateInputBox(){  return bookingDateInputBox.getAttribute("value");  }
    public String getInputbox_addressInputBox(){  return addressInputBox.getAttribute("value");  }

    //클릭 및 입력 메서드
    public void clickOldAddressCheckbox(){
        hover(oldAddressCheckbox);
        click(oldAddressCheckbox);
    }

    public void clickNewAddressCheckbox(){
        hover(newAddressCheckbox);
        click(newAddressCheckbox);
    }

    public void clickPaymentButton(){
        hover(paymentButton);
        click(paymentButton);
    }

    public void clickNewAddressSearchButton(){
        hover(newAddressSearchButton);
        click(newAddressSearchButton);
    }

    public void inputRequestInputBox(String request){  sendKeys(requestInputBox, request);  }
    public void inputEmailInputBox(String email){  sendKeys(emailInputBox, email);  }

    //비표시확인 메서드
    public boolean isDisplayCheck_OldAddressArea(){
        String style = oldAddressArea.getAttribute("style");
        if (style.contains("display: none")) {
            return false; // 비표시 상태
        } else {
            return true; // 표시 상태
        }
    }

    public boolean isDisplayCheck_NewAddressArea(){
        String style = newAddressArea.getAttribute("style");
        if (style.contains("display: none")) {
            return false; // 비표시 상태
        } else {
            return true; // 표시 상태
        }
    }

    public boolean isDaumPostcodePopupDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        String mainWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String handle : allWindows) {
            if (!mainWindow.equals(handle)) {
                driver.switchTo().window(handle);

                try {
                    wait.until(d -> d.getPageSource().length() > 100); // 내용이 어느 정도 채워질 때까지 대기

                    String pageSource = driver.getPageSource();
                    String title = driver.getTitle();

                    // 타이틀이나 페이지 소스 중 하나라도 주소창임을 나타내면 성공
                    if (title.contains("주소") || pageSource.contains("postcode") || pageSource.contains("우편번호")) {
                        System.out.println(">>> 주소 팝업 확인 성공 (Title: " + title + ")");
                        driver.close();
                        driver.switchTo().window(mainWindow);
                        return true;
                    }
                } catch (Exception e) {
                    System.out.println(">>> 팝업 내부 컨텐츠 분석 실패: " + e.getMessage());
                }
            }
        }

        driver.switchTo().window(mainWindow);
        return false;
    }

    public boolean isInicisPaymentPopupDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        String mainWindow = driver.getWindowHandle();

        try {
            // 결제창 표시대기 (창 개수 2개 확인)
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            Set<String> allWindows = driver.getWindowHandles();
            for (String handle : allWindows) {
                if (!mainWindow.equals(handle)) {
                    driver.switchTo().window(handle);

                    // KG이니시스 고유의 키워드 로드확인(Title과 PageSource를 같이 확인)
                    wait.until(d -> d.getTitle().length() > 0 || d.getPageSource().contains("INIpay"));

                    String title = driver.getTitle();
                    String source = driver.getPageSource();

                    if (title.contains("INIpay") || title.contains("결제") || source.contains("inicis")) {
                        System.out.println(">>> KG이니시스 결제 팝업 감지 성공: " + title);
                        driver.close();
                        driver.switchTo().window(mainWindow);
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(">>> 결제 팝업 감지 실패: " + e.getMessage());
            driver.switchTo().window(mainWindow);
        }
        return false;
    }

}
