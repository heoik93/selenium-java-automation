package com.project.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JsUtil {

    private WebDriver driver;

    public JsUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void setValueById(String id, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "document.getElementById(arguments[0]).value=arguments[1];",
                id, value
        );
    }

    public void setValue(WebElement element, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].value=arguments[1];",
                element, value
        );
    }
}