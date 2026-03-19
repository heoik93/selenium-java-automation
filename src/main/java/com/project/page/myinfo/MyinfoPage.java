package com.project.page.myinfo;

import com.project.page.BasePage;
import com.project.page.NavigationBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

public class MyinfoPage  extends BasePage {

    public NavigationBar navi;

    public MyinfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.navi = new NavigationBar(driver);
    }
    //탭
    @FindBy(css = ".active")
    private WebElement myInfoTab;

    @FindBy(css = "li[class='subNavItem'] a")
    private WebElement useHistoryTab;

    //라벨
    @FindBy(css ="tbody tr:nth-child(1) th:nth-child(1)")
    private WebElement profileLabel;

    @FindBy(css ="tbody tr:nth-child(2) th:nth-child(1)")
    private WebElement userIdLabel;

    @FindBy(css ="tbody tr:nth-child(3) th:nth-child(1)")
    private WebElement addressLabel;

    @FindBy(css ="tbody tr:nth-child(4) th:nth-child(1)")
    private WebElement emailLabel;

    @FindBy(css ="tbody tr:nth-child(5) th:nth-child(1)")
    private WebElement phoneLabel;

    @FindBy(css ="tbody tr:nth-child(6) th:nth-child(1)")
    private WebElement signupDateLabel;

    @FindBy(css =".imgtxt")
    private WebElement profileTextLabel;

    //정보
    @FindBy(xpath = "//tbody/tr[2]/td")
    private WebElement useridField;

    @FindBy(xpath = "//tbody/tr[3]/td")
    private WebElement addressField;

    @FindBy(xpath = "//tbody/tr[4]/td")
    private WebElement emailField;

    @FindBy(xpath = "//tbody/tr[5]/td")
    private WebElement phoneField;

    @FindBy(xpath = "//a[contains(text(),'개인정보 수정')]")
    private WebElement modifyButton;

    @FindBy(css = ".lshbtn.lshbtncenter")
    private WebElement changePasswordButton;

    @FindBy(xpath = "//a[contains(text(),'탈퇴')]")
    private WebElement withdrawButton;

    public String getUserId() {
            return getText(useridField);
    }

    public String getAddress() {
            return getText(addressField);
    }

    public String getEmail() {
            return getText(emailField);
    }

    public String getPhone() {
            return getText(phoneField);
    }

    public void clickModifyButton() {
        hover(modifyButton);
        click(modifyButton);
    }

    public void clickChangePasswordButton() {
        hover(changePasswordButton);
        click(changePasswordButton);
    }

    public void clickWithdrawButton() {
        hover(withdrawButton);
        click(withdrawButton);
    }

    public void clickMyInfoTab(){
        hover(myInfoTab);
        click(myInfoTab);
    }

    public void clickUseHistoryTab(){
        hover(useHistoryTab);
        click(useHistoryTab);
    }

    public boolean isMyInfoTabActive(){
        return isTabActive(myInfoTab);
    }

    public Map<String, String> getAllUserInfo() {
        waitForVisible(useridField);
        Map<String, String> info = new HashMap<>();
        info.put("userId", getUserId());
        info.put("address", getAddress());
        info.put("email", getEmail());
        info.put("phone", getPhone());
        return info;
    }

    //관리용 텍스트 그룹
    public enum MyinfoPageLabel {
        MYINFOTAB, USEHISTORYTAB,
        PROFILELABEL, USERIDLABEL, ADDRESSLABEL, EMAILLABEL,
        PHONELABEL, SIGNUDATEPLABEL, PROFILETEXTLABEL,
        MODIFYBUTTON, CHANGEPASSWORDBUTTON, WITHDRAWBUTTON
    }

    //통합 텍스트 추출 메서드
    public String getLabel(MyinfoPage.MyinfoPageLabel labelType) {
        switch (labelType) {
            case MYINFOTAB:            return getText(myInfoTab);
            case USEHISTORYTAB:        return getText(useHistoryTab);
            case PROFILELABEL:         return getText(profileLabel);
            case USERIDLABEL:          return getText(userIdLabel);
            case ADDRESSLABEL:         return getText(addressLabel);
            case EMAILLABEL:           return getText(emailLabel);
            case PHONELABEL:           return getText(phoneLabel);
            case SIGNUDATEPLABEL:      return getText(signupDateLabel);
            case PROFILETEXTLABEL:     return getText(profileTextLabel);
            case MODIFYBUTTON:         return getText(modifyButton);
            case CHANGEPASSWORDBUTTON: return getText(changePasswordButton);
            case WITHDRAWBUTTON:       return getText(withdrawButton);

            default:  throw new IllegalArgumentException("정의되지 않은 라벨 타입입니다: " + labelType);
        }
    }


}
