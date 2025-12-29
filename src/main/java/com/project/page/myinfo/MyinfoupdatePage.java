package com.project.page.myinfo;

import com.project.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

public class MyinfoupdatePage extends BasePage {

    public MyinfoupdatePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "profileImage")
    private WebElement profileImage;

    @FindBy(id = "id")
    private WebElement userIdField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "phone")
    private WebElement phoneField;

    @FindBy(id = "postcode")
    private WebElement postcodeField;

    @FindBy(id = "addr")
    private WebElement addressField;

    @FindBy(id = "detailAddr")
    private WebElement detailAddressField;

    @FindBy(id = "extraAddr")
    private WebElement extraAddressField;

    @FindBy(css = ".btn.btn-dark")
    private WebElement searchPostcodeButton;

    @FindBy(css = "button[type='submit']")
    private WebElement saveButton;

    public void clickProfileImage() {
        profileImage.click();
    }

    public String getUserId() {
        return userIdField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public  String getPostcode() {
        return postcodeField.getText();
    }

    public String getPhone() {
        return phoneField.getText();
    }

    public String getAddress() {
        return addressField.getText();
    }

    public String getDetailAddress() {
        return detailAddressField.getText();
    }

    public String getExtraAddress() {
        return extraAddressField.getText();
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public Boolean isEnableuserIdField() {
        return userIdField.isEnabled();
    }

    public void chageUserinfo01(String email, String phone) {
        emailField.clear();
        emailField.sendKeys(email);
        phoneField.clear();
        phoneField.sendKeys(phone);
    }

    //현재 주소필드 직접입력이 안되는 버그있음
    public void chageUserinfo02(String postcode, String address, String detailAddress, String extraAddress) {
        postcodeField.clear();
        postcodeField.sendKeys(postcode);
        addressField.clear();
        addressField.sendKeys(address);
        detailAddressField.clear();
        detailAddressField.sendKeys(detailAddress);
        extraAddressField.clear();
        extraAddressField.sendKeys(extraAddress);
    }

    public String infoUpdateAlertgetText() {
        return driver.switchTo().alert().getText();
    }

    public void infoUpdateAlertAccept() {
        driver.switchTo().alert().accept();
    }

    public Map<String, String> getAllUserInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("userId", userIdField.getAttribute("value"));
        info.put("email", emailField.getAttribute("value"));
        info.put("phone", phoneField.getAttribute("value"));

        String fullAddress = postcodeField.getAttribute("value") + "_" +
                addressField.getAttribute("value") + "_" +
                detailAddressField.getAttribute("value") + "_" +
                extraAddressField.getAttribute("value");
        info.put("address", fullAddress);

        info.put("backupEmail", emailField.getAttribute("value"));
        info.put("backupPhone", phoneField.getAttribute("value"));
        info.put("backupPostcode", postcodeField.getAttribute("value"));
        info.put("backupAddress", addressField.getAttribute("value"));
        info.put("backupDetailAddress", detailAddressField.getAttribute("value"));
        info.put("backupExtraAddress", extraAddressField.getAttribute("value"));

        return info;
    }

}