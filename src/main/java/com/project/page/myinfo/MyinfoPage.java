package com.project.page.myinfo;

import com.project.page.BasePage;
import com.project.page.NavigationBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

public class MyinfoPage  extends BasePage {

    public NavigationBar navi;

    public MyinfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.navi = new NavigationBar(driver);
    }

    @FindBy(css = ".active")
    public WebElement myinfoTab;

    @FindBy(xpath = "//tbody/tr[2]/td")
    private WebElement useridField;

    @FindBy(xpath = "//tbody/tr[3]/td")
    private WebElement addressField;

    @FindBy(xpath = "//tbody/tr[4]/td")
    private WebElement emailField;

    @FindBy(xpath = "//tbody/tr[5]/td")
    private WebElement phoneField;

    @FindBy(xpath = "//a[contains(text(),'개인정보 수정')]")
    private WebElement modifyButton;

    @FindBy(css = ".lshbtn.lshbtncenter")
    private WebElement changePasswordButton;

    @FindBy(xpath = "//a[contains(text(),'탈퇴')]")
    private WebElement withdrawButton;

    public String getUserId() {
            return getText(useridField);
    }

    public String getAddress() {
            return getText(addressField);
    }

    public String getEmail() {
            return getText(emailField);
    }

    public String getPhone() {
            return getText(phoneField);
    }

    public void clickModifyButton() {
            click(modifyButton);
    }

    public void clickChangePasswordButton() {
            click(changePasswordButton);
    }

    public void clickWithdrawButton() {
            click(withdrawButton);
    }

    public Map<String, String> getAllUserInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("userId", getUserId());
        info.put("address", getAddress());
        info.put("email", getEmail());
        info.put("phone", getPhone());
        return info;
    }


}
