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
        try {
            // 1. 요소가 나타날 때까지 대기
            wait.until(ExpectedConditions.visibilityOf(element));

            // 2. [핵심] 요소를 화면 중앙으로 부드럽게 이동 (가려짐 방지)
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
            Thread.sleep(500); // 스크롤 안정화 대기

            // 3. 클릭 가능 상태 확인 후 클릭
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();

        } catch (Exception e) {
            // 4. 실패 시 JS로 강제 클릭 (최후의 수단)
            System.out.println("[⚠️ 경고] 물리적 클릭 실패. JS 클릭으로 전환합니다.");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
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
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();

        } catch (Exception e) {
            //System.out.println("[알림] 일반 호버 실패. 헤드리스 대응을 위해 JS 클릭을 시도합니다: " + e.getMessage());

            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", element);
            } catch (Exception jsException) {
                System.err.println("[오류] JS 클릭마저 실패했습니다: " + jsException.getMessage());
            }
        }
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



