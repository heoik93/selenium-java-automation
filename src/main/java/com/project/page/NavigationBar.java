package com.project.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationBar extends BasePage {

    public NavigationBar(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//strong[contains(text(),'로그인')]")
    private WebElement loginLink;

    @FindBy(xpath = "//strong[contains(text(),'회원가입')]")
    private WebElement signupLink;

    @FindBy(css = "ul[class='menu1'] a")
    private WebElement logoutLink;

    @FindBy(xpath = "//a[contains(text(),'마이페이지')]")
    private WebElement myinfoMenuLink;

    @FindBy(css = "a[href='/LoginInfo/Mypage/MyInfo']")
    private WebElement myinfoLink;

    public void goToLoginPage() {
        click(loginLink);
    }

    public boolean isLoginButtonVisible() {
        return isDisplayed(loginLink);
    }

    public void logout() {
        click(logoutLink);
    }

    public boolean isLogoutButtonVisible() {
        return isDisplayed(logoutLink);
    }


    public void goToSignupPage() {
        click(signupLink);
    }

   public boolean isSignupButtonVisible() {
        return isDisplayed(signupLink);
    }

    public void goToMyinfoPage() {
        hover(myinfoMenuLink);
        click(myinfoMenuLink);
        click(myinfoLink);
    }

    public void clickLogoutLink() {
        hover(logoutLink);
        click(logoutLink);
    }

}


