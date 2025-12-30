package com.project.page.brandinfo;

import com.project.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HistoryPage extends BasePage {

    public HistoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[@class='subNavItem']//a[contains(text(),'회사소개')]")
    public WebElement comintroTab;

    @FindBy(css = ".active")
    public WebElement historyTab;

    @FindBy(xpath = "//li[@class='subNavItem']//a[contains(text(),'창업안내')]")
    public WebElement startupTab;

    public void clickTab(WebElement tab) {
        click(tab);
    }
}
