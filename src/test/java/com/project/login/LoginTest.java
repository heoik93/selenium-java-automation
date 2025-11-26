package com.project.login;

import com.project.base.BaseTest;
import com.project.config.ConfigReader;
import com.project.page.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() {
        // 1. ConfigReader로 URL, 계정 읽기
        ConfigReader config = new ConfigReader();

        // 2. 브라우저 열고 URL 이동
        driver.get(config.getUrl());

        // 3. LoginPage 생성
        LoginPage loginPage = new LoginPage(driver);

        // 4. 로그인 수행
        loginPage.login(config.getUsername(), config.getPassword());

        // 5. 테스트 검증 (예: URL 변경, 특정 element 표시 등)
        // Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }
}
