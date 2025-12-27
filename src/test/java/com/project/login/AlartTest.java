package com.project.login;

import com.project.base.BaseTest;
import com.project.page.HomePage;
import com.project.page.SignupPage;
import config.ConfigReader;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.Test;

public class AlartTest extends BaseTest {


    @Test
    public void test123() {

        ConfigReader config = new ConfigReader();
        driver.get(config.getUrl());

        HomePage homePage = new HomePage(driver);
        homePage.navi.goToSignupPage();

        SignupPage SignupPage = new SignupPage(driver);
        SignupPage.setAgreeTermsCheckbox();

        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("ALERT TEXT = " + alert.getText());
        } catch (NoAlertPresentException e) {
            System.out.println("이건 브라우저 Alert 아님");
        }

    }
}