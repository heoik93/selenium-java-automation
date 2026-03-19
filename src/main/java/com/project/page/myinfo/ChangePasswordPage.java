package com.project.page.myinfo;

import com.project.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChangePasswordPage extends BasePage {

    public ChangePasswordPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "pwd")
    public WebElement pwdField;

    @FindBy(id = "newPwd")
    public WebElement newPwdField;

    @FindBy(id = "newPwd2")
    public WebElement newPwd2Field;

    @FindBy(css = "button[type='submit']")
    public WebElement saveButton;

    @FindBy(css = "button[type='reset']")
    public WebElement clearButton;

    public void changePassword(String currentPwd, String newPwd, String newPwd2) {
        sendKeys(pwdField, currentPwd);
        sendKeys(newPwdField, newPwd);
        sendKeys(newPwd2Field, newPwd2);
    }

    public void clickSaveButton() {
        click(saveButton);
    }

    public void clickClearButton() {
        click(clearButton);
    }


}
