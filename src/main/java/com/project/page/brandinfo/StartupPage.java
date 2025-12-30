package com.project.page.brandinfo;

import com.project.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StartupPage extends BasePage {

    public StartupPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//li[@class='subNavItem']//a[contains(text(),'회사소개')]")
    public WebElement comintroTab;

    @FindBy(xpath = "//li[@class='subNavItem']//a[contains(text(),'연혁')]")
    public WebElement historyTab;

    @FindBy(css = ".active")
    public WebElement startupTab;

    public void clickTab(WebElement tab) {
        click(tab);
    }
}
