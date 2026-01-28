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

    @FindBy(xpath = "//a[contains(text(),'이용안내')]")
    private  WebElement useguideMenuLink;

    @FindBy(css = "a[href='/Guide/PriceGuide']")
    private  WebElement priceGuideLink;

    @FindBy(css = "a[href='/Guide/AreaGuide']")
    private WebElement areaGuideLink;

    @FindBy(xpath = "//a[@class='menu-link1'][contains(text(),'예약안내')]")
    private WebElement bookinginfoMenuLink;

    @FindBy(xpath = "//a[@href='/Reserve/ReserveSelect']")
    private WebElement bookinginfoLink;

    //네비게이션바 버튼
    @FindBy(xpath = "//button[contains(text(),'회사소개')]")
    private WebElement comintroButton;

    @FindBy(xpath = "//button[contains(text(),'가격안내')]")
    private  WebElement priceGuideButton;

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

    public void goToPriceGuidePage(){
        hover(useguideMenuLink);
        click(useguideMenuLink);
        click(priceGuideLink);
    }

    public void goToAreaGuidePage(){
        hover(useguideMenuLink);
        click(useguideMenuLink);
        click(areaGuideLink);
    }

    public void goToBookingInfoPage(){
        hover(bookinginfoMenuLink);
        click(bookinginfoMenuLink);
        click(bookinginfoLink);
    }

    //네이게이션 바로 각 페이지로 이동시 url/타이틀이 유효한지 테스트를 추가할 것

}


