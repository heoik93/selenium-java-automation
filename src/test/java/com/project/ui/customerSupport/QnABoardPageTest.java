package com.project.ui.customerSupport;

import com.project.base.BaseTest;
import com.project.page.HomePage;
import org.testng.annotations.BeforeMethod;

public class QnABoardPageTest extends BaseTest {

    @BeforeMethod
    public void setupLogin() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToQnABoardPage();
    }
}