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

    @FindBy(css = "div[class='main-content'] h2:nth-child(1)")
    public WebElement history_main_Label;

    @FindBy(css = ".timeline-year")
    public WebElement timelineYear_Label;

    @FindBy(css = "div[class='main-content'] li:nth-child(1) div:first-child")
    public WebElement timeline_1st_Label;

    @FindBy(css = "div[class='main-content'] li:nth-child(1) div:last-child")
    public WebElement timeline_1st_textLabel;

    @FindBy(css = "div[class='main-content'] li:nth-child(2) div:first-child")
    public WebElement timeline_2nd_Label;

    @FindBy(css = "div[class='main-content'] li:nth-child(2) div:last-child")
    public WebElement timeline_2nd_textLabel;

    @FindBy(css = "div[class='main-content'] li:nth-child(3) div:first-child")
    public WebElement timeline_3rd_Label;

    @FindBy(css = "div[class='main-content'] li:nth-child(3) div:last-child")
    public WebElement timeline_3rd_textLabel;

    @FindBy(css = "div[class='main-content'] li:nth-child(4) div:first-child")
    public WebElement timeline_4th_Label;

    @FindBy(css = "div[class='main-content'] li:nth-child(4) div:last-child")
    public WebElement timeline_4th_textLabel;

    @FindBy(css = "div[class='main-content'] li:nth-child(5) div:first-child")
    public WebElement timeline_5th_Label;

    @FindBy(css = "div[class='main-content'] li:nth-child(5) div:last-child")
    public WebElement timeline_5th_textLabel;

    @FindBy(css = "div[class='main-content'] li:nth-child(6) div:first-child")
    public WebElement timeline_6th_Label;

    @FindBy(css = "div[class='main-content'] li:nth-child(6) div:last-child")
    public WebElement timeline_6th_textLabel;

    @FindBy(css = "div[class='main-content'] li:nth-child(7) div:first-child")
    public WebElement timeline_7th_Label;

    @FindBy(css = "div[class='main-content'] li:nth-child(7) div:last-child")
    public WebElement timeline_7th_textLabel;

    @FindBy(css = "div[class='main-content'] li:nth-child(8) div:first-child")
    public WebElement timeline_8th_Label;

    @FindBy(css = "div[class='main-content'] li:nth-child(8) div:last-child")
    public WebElement timeline_8th_textLabel;

    @FindBy(css = "div[class='main-content'] li:nth-child(9) div:first-child")
    public WebElement timeline_9th_Label;

    @FindBy(css = "div[class='main-content'] li:nth-child(9) div:last-child")
    public WebElement timeline_9th_textLabel;

    @FindBy(css = "div[class='main-content'] li:nth-child(10) div:first-child")
    public WebElement timeline_10th_Label;

    @FindBy(css = "div[class='main-content'] li:nth-child(10) div:last-child")
    public WebElement timeline_10th_textLabel;

    @FindBy(css = "div[class='main-content'] li:nth-child(11) div:first-child")
    public WebElement timeline_11th_Label;

    @FindBy(css = "div[class='main-content'] li:nth-child(11) div:last-child")
    public WebElement timeline_11th_textLabel;

    @FindBy(css = "div[class='main-content'] li:nth-child(12) div:first-child")
    public WebElement timeline_12th_Label;

    @FindBy(css = "div[class='main-content'] li:nth-child(12) div:last-child")
    public WebElement timeline_12th_textLabel;


    public void clickTab(WebElement tab) {
        click(tab);
    }

    public boolean activeTabText() {
        return isTabActive(historyTab);
    }
    public String getComintroTabText() {
        return getText(comintroTab);
    }
    public String getHistoryTabText() {
        return getText(historyTab);
    }
    public String getStartupTabText() {
        return getText(startupTab);
    }
    public String getLabelText(WebElement labelName) {
        return getText(labelName);
    }
}



