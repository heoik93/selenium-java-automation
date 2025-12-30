package com.project.functional;

import com.project.base.BaseTest;
import com.project.page.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(testName = "default login test")
    public void testLogin() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        Assert.assertTrue(afterLogin.navi.isLogoutButtonVisible(), "로그인 실패: 로그아웃 버튼이 표시되지 않음");
    }
}
