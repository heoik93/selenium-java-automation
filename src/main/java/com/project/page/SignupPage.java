package com.project.page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SignupPage extends BasePage {

    public SignupPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        // scroll = new ScrollUtil(driver);
    }

    @FindBy(id = "chk_all")
    private WebElement agreeAllCheckbox;

    @FindBy(id = "agree11")
    private WebElement agreeTermsCheckbox;

    @FindBy(id = "agree21")
    private WebElement agreePersonalInfoCheckbox;

    @FindBy(xpath = "//a[contains(text(),'계속')]")
    private WebElement confirmButton;

    @FindBy(xpath = "//a[contains(text(),'취소')]")
    private WebElement cancelButton;

    @FindBy(id = "name")
    private WebElement nameInputBox;

    @FindBy(id = "email")
    private WebElement emailInputBox;

    @FindBy(id = "id")
    private WebElement useridInputBox;

    @FindBy(id = "pwd")
    private WebElement passwordInputBox;

    @FindBy(id = "pwd2")
    private WebElement confirmPasswordInputBox;

    @FindBy(css = "button[onclick='execDaumPostcode()']")
    private WebElement addressButton;

    @FindBy(id = "postcode")
    private WebElement postcodeInputBox;

    @FindBy(id = "addr")
    private WebElement addressInputBox;

    @FindBy(id = "detailAddr")
    private WebElement detailAddressInputBox;

    @FindBy(id = "extraAddr")
    private WebElement extraAddressInputBox;

    @FindBy(id = "countryCodeSelect")
    private WebElement countryCodeSelectBox;

    @FindBy(id = "phone")
    private WebElement phoneInputBox;

    @FindBy(css = "button[type='submit']")
    private WebElement signupSubmitButton;

    public void agreeSignup() {
        click(agreeAllCheckbox);
        scroll.scrollToBottom();
        click(confirmButton);
    }

    //테스트중
    public void setAgreeTermsCheckbox() {
        click(agreeTermsCheckbox);
        scroll.scrollToBottom();
        click(confirmButton);
    }

    public void inputName(String name) {
        sendKeys(nameInputBox, name);
    }

    public void inputEmail(String email) {
        sendKeys(emailInputBox, email);
    }

    public void inputUserId(String userId) {
        sendKeys(useridInputBox, userId);
    }

    public void inputPassword(String password) {
        sendKeys(passwordInputBox, password);
    }

    public void inputConfirmPassword(String password) {
        sendKeys(confirmPasswordInputBox, password);
    }

    public void inputPostcode(String postcode) {
        sendKeys(postcodeInputBox, postcode);
    }

    public void inputDetailAddress(String detailAddress) {
        sendKeys(detailAddressInputBox, detailAddress);
    }

    public void selectCountry(String countryKeyword) {
        Select dropdown = new Select(countryCodeSelectBox);
        boolean found = false;

        // 1차: countryKeyword 포함 여부 확인
        for (WebElement option : dropdown.getOptions()) {
            if (option.getText().contains(countryKeyword)) {
                option.click();
                found = true;
                break;
            }
        }

        // 2차: 없으면 "82" 포함된 값 선택
        if (!found) {
            for (WebElement option : dropdown.getOptions()) {
                if (option.getText().contains("82")) {
                    option.click();
                    break;
                }
            }
        }
    }

    public void inputPhone(String phone) {
        sendKeys(phoneInputBox, phone);
    }

    public void SignupInputData() {
        inputName(testUserData.randomUserName);
        inputEmail(testUserData.randomUserEmail);
        inputUserId(testUserData.randomUserId);
        inputPassword(testUserData.randomUserPassword);
        inputConfirmPassword(testUserData.randomUserPassword);
        selectCountry(testUserData.randomUserCountry);
        inputPhone(testUserData.randomUserPhone);
    }

    public void enterAddress(String zipcode, String address, String detail, String extra) {
        js.setValue(postcodeInputBox, zipcode);
        js.setValue(addressInputBox, address);
        // 자바스크립트로 직접입력하면 NG가 되는 버그
        // js.setValue(detailAddressInputBox, detail);
        inputDetailAddress("상세주소123");
        js.setValue(extraAddressInputBox, extra);
    }

    public void clickSignupSubmit() {
        click(signupSubmitButton);
    }

    public String getSignupAlertText() {
        Alert alert = wait.until(driver -> {
            try {
                return driver.switchTo().alert();
            } catch (NoAlertPresentException e) {
                return null;
            }
        });
        return alert.getText();
    }

    public void acceptSignupAlert() {
        driver.switchTo().alert().accept();
    }
}

