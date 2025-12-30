package com.project.page;

import com.project.utils.JsUtil;
import com.project.utils.ScrollUtil;
import com.project.utils.TestUserRandomData;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    public TestUserRandomData testUserData = new TestUserRandomData();
    protected ScrollUtil scroll;
    protected JsUtil js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.scroll = new ScrollUtil(driver);
        this.js = new JsUtil(driver);
    }

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected void sendKeys(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }

    protected String getText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public void waitForVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }


    protected void selectContainsText(WebElement selectElement, String text) {
        Select dropdown = new Select(selectElement);

        for (WebElement option : dropdown.getOptions()) {
            if (option.getText().contains(text)) {
                option.click();
                break;
            }
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
    }

    protected void hover(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public Alert waitForAlert() {
        WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return alertWait.until(ExpectedConditions.alertIsPresent());
    }

    public String alertGetText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            return alert.getText();
        } catch (TimeoutException e) {
            System.out.println("설정한 시간 내에 알림창이 표시되지 않았습니다.");
            return "";
        }
    }

    public void alertAccept() {
        try {
            Alert alert = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception e) {
            System.out.println("처리할 알림창이 없습니다.");
        }
    }

    public boolean isTabActive(WebElement tabElement) {
        String classValue = tabElement.getAttribute("class");
        return classValue.contains("active") || classValue.contains("on");
    }


}



