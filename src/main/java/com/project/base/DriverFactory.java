package com.project.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import config.ConfigReader;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    public static WebDriver createDriver(String browser) {
        ConfigReader config = new ConfigReader();
        boolean isHeadless = Boolean.parseBoolean(config.getProperty("headless"));

        switch (browser.toLowerCase()) {

            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();

                if (isHeadless) {
                    options.addArguments("--headless=new"); // 최신 헤드리스 모드
                    options.addArguments("--window-size=1920,1080"); // 해상도 강제 지정
                    options.addArguments("--start-maximized");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--no-sandbox"); // 리소스 제한 해제
                    options.addArguments("--disable-dev-shm-usage"); // 메모리 공유 문제 해결
                    options.addArguments("--force-device-scale-factor=1"); // 배율 100% 고정
                    options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"); // 봇 탐지 회피
                    System.out.println("Chrome 헤드리스 모드로 실행합니다.");
                } else {
                    options.addArguments("--start-maximized");
                }

                return new ChromeDriver(options);

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();

            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();

            default:
                throw new IllegalArgumentException("지원하지 않는 브라우저입니다: " + browser);
        }
    }
}
