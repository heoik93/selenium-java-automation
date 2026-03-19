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

    @FindBy(css = ".section-title")
    public WebElement startup_main_Label;

    @FindBy(css = "th:nth-child(1)")
    public WebElement startup_table_Title1_Label;

    @FindBy(css = "th:nth-child(2)")
    public WebElement startup_table_Title2_Label;

    @FindBy(css = "th:nth-child(3)")
    public WebElement startup_table_Title3_Label;

    @FindBy(xpath = "//tbody/tr[1]/td[1]")
    public WebElement startup_table_row1col1_Label;
    @FindBy(xpath = "//tbody/tr[1]/td[2]")
    public WebElement startup_table_row1col2_Label;
    @FindBy(xpath = "//tbody/tr[1]/td[3]")
    public WebElement startup_table_row1col3_Label;
    @FindBy(xpath = "//tbody/tr[2]/td[1]")
    public WebElement startup_table_row2col1_Label;
    @FindBy(xpath = "//tbody/tr[2]/td[2]")
    public WebElement startup_table_row2col2_Label;
    @FindBy(xpath = "//tbody/tr[3]/td[1]")
    public WebElement startup_table_row3col1_Label;
    @FindBy(xpath = "//tbody/tr[3]/td[2]")
    public WebElement startup_table_row3col2_Label;
    @FindBy(xpath = "//tbody/tr[3]/td[3]")
    public WebElement startup_table_row3col3_Label;
    @FindBy(xpath = "//tbody/tr[4]/td[1]")
    public WebElement startup_table_row4col1_Label;
    @FindBy(xpath = "//tbody/tr[4]/td[2]")
    public WebElement startup_table_row4col2_Label;

    @FindBy(css = ".note")
    public WebElement startup_NoteLabel;

    @FindBy(xpath = "(//div[@class='step-label'])[1]")
    public WebElement startup_step1_Label;
    @FindBy(xpath = "(//div[@class='step-label'])[2]")
    public WebElement startup_step2_Label;
    @FindBy(xpath = "(//div[@class='step-label'])[3]")
    public WebElement startup_step3_Label;
    @FindBy(xpath = "(//div[@class='step-label'])[4]")
    public WebElement startup_step4_Label;
    @FindBy(xpath = "(//div[@class='step-label'])[5]")
    public WebElement startup_step5_Label;
    @FindBy(xpath = "(//div[@class='step-label'])[6]")
    public WebElement startup_step6_Label;


    public void clickTab(WebElement tab) {
        click(tab);
    }

    public boolean activeTabText() {
        return isTabActive(startupTab);
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
        scroll.scrollToElement(labelName);
        return getText(labelName);
    }

}
