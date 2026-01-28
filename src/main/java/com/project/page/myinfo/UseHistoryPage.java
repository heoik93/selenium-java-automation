package com.project.page.myinfo;

import com.project.page.BasePage;
import com.project.page.NavigationBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UseHistoryPage extends BasePage {

    public NavigationBar navi;

    public UseHistoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.navi = new NavigationBar(driver);
    }

    //탭
    @FindBy(css = "li[class='subNavItem'] a")
    private WebElement myInfoTab;

    @FindBy(css = ".active")
    private WebElement useHistoryTab;

    //탑라벨
    @FindBy(css = "th:nth-child(1)")
    private WebElement oderNumber;

    @FindBy(css = "th:nth-child(2)")
    private WebElement oderDetail;

    @FindBy(css = "th:nth-child(3)")
    private WebElement oderPrice;

    @FindBy(css = "th:nth-child(4)")
    private WebElement address;

    @FindBy(css = "th:nth-child(5)")
    private WebElement bookingDate;

    @FindBy(css = "th:nth-child(6)")
    private WebElement retrieveDate;

    @FindBy(css = "th:nth-child(7)")
    private WebElement status;

    @FindBy(css = "th:nth-child(8)")
    private WebElement review;


}
