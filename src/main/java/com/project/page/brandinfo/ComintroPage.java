package com.project.page.brandinfo;

import com.project.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ComintroPage extends BasePage {

    public ComintroPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".active")
    public WebElement comintroTab;

    @FindBy(xpath = "//li[@class='subNavItem']//a[contains(text(),'연혁')]")
    public WebElement historyTab;

    @FindBy(xpath = "//li[@class='subNavItem']//a[contains(text(),'창업안내')]")
    public WebElement startupTab;

    @FindBy(css = "section[class='intro'] h1")
    public WebElement comintro_h1_Label;

    @FindBy(css = "section[class='intro'] h2")
    private WebElement comintro_h2_Label;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/section[1]/p[1]")
    private WebElement comintro_h2p1_Label;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/section[1]/p[2]")
    private WebElement comintro_h2p2_Label;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/section[2]/div[1]/h3[1]")
    private WebElement comintro_1h3_Label;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/section[2]/div[1]/p[1]")
    private WebElement comintro_1h3p_Label;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/section[2]/div[2]/h3[1]")
    private WebElement comintro_2h3_Label;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/section[2]/div[2]/p[1]")
    private WebElement comintro_2h3p_Label;

    //텍스트 가져오기 메서드
    public String getComintro_h1_LabelText() {
        return getText(comintro_h1_Label);
    }
    public String getComintro_h2_LabelText() {
        return getText(comintro_h2_Label);
    }
    public String getComintro_h2p1_LabelText() {
        return getText(comintro_h2p1_Label);
    }
    public String getComintro_h2p2_LabelText() {
        return getText(comintro_h2p2_Label);
    }
    public String getComintro_1h3_LabelText() {
        return getText(comintro_1h3_Label);
    }
    public String getComintro_1h3p_LabelText() {
        return getText(comintro_1h3p_Label);
    }
    public String getComintro_2h3_LabelText() {
        return getText(comintro_2h3_Label);
    }
    public String getComintro_2h3p_LabelText() {
        return getText(comintro_2h3p_Label);
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

    //클릭 메서드
    public void clickTab(WebElement tab) {
        click(tab);
    }

    //기타
    public boolean activeTabText() {
       return isTabActive(comintroTab);
    }

}
