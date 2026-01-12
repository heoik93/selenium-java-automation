package com.project.page.myinfo;

import com.project.page.BasePage;
import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
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

    @FindBy(id = "profileImage")
    private WebElement profileImageUploadInput;

    @FindBy(css = "input[type='file']")
    private WebElement realFileInput;

    public void clickProfileImage() {
        profileImage.click();
    }

    public String getUserId() {
        return userIdField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPostcode() {
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

    public void clickProfileImageUploadInput() {
        profileImageUploadInput.click();
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

    public void uploadProfileImage() {
        ConfigReader config = new ConfigReader();
        File uploadFile = new File(System.getProperty("user.dir"), config.getProperty("profileImagePath"));
        String absolutePath = uploadFile.getAbsolutePath();

        if (!uploadFile.exists()) {
            throw new RuntimeException("파일을 찾을 수 없습니다. 경로를 확인하세요: " + absolutePath);
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].style.display='block';" +
                        "arguments[0].style.visibility='visible';" +
                        "arguments[0].style.opacity='1';" +
                        "arguments[0].style.position='fixed';" + // 화면 상단 고정
                        "arguments[0].style.top='0';" +
                        "arguments[0].style.left='0';" +
                        "arguments[0].style.width='100px';" +
                        "arguments[0].style.height='100px';" +
                        "arguments[0].style.zIndex='10000';", realFileInput);

        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        realFileInput.sendKeys(absolutePath);
    }

    public String getProfileImageSrc() {
        return profileImage.getAttribute("src");
    }

    public void backupProfileImage() {
        ConfigReader config = new ConfigReader();

        File backupFile = new File(System.getProperty("user.dir"), config.getProperty("profileImageBackupPath"));
        String absolutePath = backupFile.getAbsolutePath();

        if (!backupFile.exists()) {
            throw new RuntimeException("파일을 찾을 수 없습니다. 경로를 확인하세요: " + absolutePath);
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.display='block'; arguments[0].style.visibility='visible';", realFileInput);

        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        realFileInput.sendKeys(absolutePath);
    }
}
