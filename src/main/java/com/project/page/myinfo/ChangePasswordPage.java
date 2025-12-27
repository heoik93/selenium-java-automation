package com.project.page.myinfo;

import com.project.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ChangePasswordPage extends BasePage {

    public ChangePasswordPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
