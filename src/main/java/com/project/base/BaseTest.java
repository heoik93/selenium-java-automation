package com.project.base;

import com.project.page.HomePage;
import com.project.page.LoginPage;
import config.ConfigReader;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // 1) 브라우저 선택 (나중에는 config 파일에서 읽도록 확장 가능)
        String browser = "chrome";

        driver = DriverFactory.createDriver(browser);

        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().deleteAllCookies();



    }

    public void loginAsDefaultUser() {
        ConfigReader config = new ConfigReader();
        driver.get(config.getUrl());

        HomePage homePage = new HomePage(driver);
        homePage.navi.goToLoginPage();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());
    }

    public void loginAsAdminUser() {
        ConfigReader config = new ConfigReader();
        driver.get(config.getUrl());

        HomePage homePage = new HomePage(driver);
        homePage.navi.goToLoginPage();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getProperty("adminusername"), config.getProperty("adminpassword"));
    }

    public void loginAsAnotherUser() {
        ConfigReader config = new ConfigReader();
        driver.get(config.getUrl());

        HomePage homePage = new HomePage(driver);
        homePage.navi.goToLoginPage();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getProperty("anotherusername"), config.getProperty("anotheruserpassword"));
    }

    public void connectToUrl() {
        ConfigReader config = new ConfigReader();
        driver.get(config.getUrl());
    }

    public void debugResult(String expected, String result) {
        System.out.println("기대값: " + expected+"   실제값: " + result);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {

            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

}
