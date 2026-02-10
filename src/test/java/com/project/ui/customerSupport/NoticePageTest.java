package com.project.ui.customerSupport;

import com.project.base.BaseTest;
import com.project.page.HomePage;
import org.testng.annotations.BeforeMethod;

public class NoticePageTest extends BaseTest {

    @BeforeMethod
    public void goToNoticePage() {
        connectToUrl();
        HomePage homepage = new HomePage(driver);
        homepage.navi.waitForPageLoad();
        homepage.navi.goToNoticePage();
    }
}
