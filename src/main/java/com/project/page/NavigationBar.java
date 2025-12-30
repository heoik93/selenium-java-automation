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

    @FindBy(xpath = "//a[contains(text(),'브랜드소개')]")
    private WebElement brandintroMenuLink;

    @FindBy(css = "a[href='/Corporation/Brand']")
    private WebElement comintroLink;

    @FindBy(css = "a[href='/Corporation/History']")
    private WebElement historyLink;

    @FindBy(css = "a[href='/Corporation/Startup']")
    private WebElement startupLink;

    @FindBy(xpath = "//button[contains(text(),'회사소개')]")
    private WebElement comintroButton;

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

    public void goToMyinfoPage() {
        hover(myinfoMenuLink);
        click(myinfoMenuLink);
        click(myinfoLink);
    }

    public void clickLogoutLink() {
        hover(logoutLink);
        click(logoutLink);
    }

    public void goToComintroPage() {
        hover(brandintroMenuLink);
        click(brandintroMenuLink);
        click(comintroLink);
    }

    public void goToHistoryPage() {
        hover(brandintroMenuLink);
        click(brandintroMenuLink);
        click(historyLink);
    }

    public void goToStartupPage() {
        hover(brandintroMenuLink);
        click(brandintroMenuLink);
        click(startupLink);
    }

}


