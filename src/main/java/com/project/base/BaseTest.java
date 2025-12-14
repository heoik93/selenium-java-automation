package com.project.base;

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

        // 3) 공통 설정
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
           // 테스트기간동안 확인을 위해 비활성화
            // driver.quit();
        }
    }
}
