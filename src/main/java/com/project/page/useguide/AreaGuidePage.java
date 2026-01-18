package com.project.page.useguide;

import com.project.page.BasePage;
import com.project.page.NavigationBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AreaGuidePage extends BasePage {

    public NavigationBar navi;

    public AreaGuidePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        this.navi = new NavigationBar(driver);
    }

    @FindBy(css = "li[class='subNavItem'] a")
    private WebElement priceGuideTab;

    @FindBy(css = ".active")
    private WebElement areaGuideTab;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[1]/img[1]")
    private WebElement serviceAreaImage;

    @FindBy(css = "body > div:nth-child(3) > div:nth-child(2) > p:nth-child(1)")
    private WebElement serviceAreaTitle;

    @FindBy(css = "div[id='areaContent1'] p[class='textBold']")
    private WebElement serviceAreaLabel_1;

    @FindBy(css = "div[id='areaContent1'] li:nth-child(1)")
    private WebElement serviceAreaLabel_2;

    @FindBy(css = "div[id='areaContent1'] li:nth-child(2)")
    private WebElement serviceAreaLabel_3;

    @FindBy(css = "div[id='areaContent1'] li:nth-child(3)")
    private WebElement serviceAreaLabel_4;

    @FindBy(css = "div[id='areaContent2'] p:nth-child(1)")
    private WebElement nonserviceAreaTitle;

    @FindBy(css = "div[id='areaContent2'] p:nth-child(2)")
    private WebElement nonserviceAreaText;

    @FindBy(css = "div[id='areaContent2'] li:nth-child(1)")
    private WebElement nonserviceAreaLabel_1;

    @FindBy(css = "div[id='areaContent2'] li:nth-child(2)")
    private WebElement nonserviceAreaLabel_2;

    @FindBy(css = "div[id='areaContent2'] li:nth-child(3)")
    private WebElement nonserviceAreaLabel_3;

    @FindBy(css = "div[id='areaContent2'] li:nth-child(4)")
    private WebElement nonserviceAreaLabel_4;

    @FindBy(css = "div[id='areaContent2'] li:nth-child(5)")
    private WebElement nonserviceAreaLabel_5;

    @FindBy(css = "div[id='areaContent2'] li:nth-child(6)")
    private WebElement nonserviceAreaLabel_6;

    @FindBy(css = "div[id='areaContent2'] li:nth-child(7)")
    private WebElement nonserviceAreaLabel_7;

    @FindBy(css = "div[id='areaContent2'] li:nth-child(8)")
    private WebElement nonserviceAreaLabel_8;

    @FindBy(css = "body > div:nth-child(3) > div:nth-child(2) > p:nth-child(5)")
    private WebElement operTimeTitle;

    @FindBy(css = "div[id='areaContent3'] p:nth-child(1)")
    private WebElement operTimeLabel_1;

    @FindBy(css = "div[id='areaContent3'] p:nth-child(2)")
    private WebElement operTimeLabel_2;

    @FindBy(css = "div[id='areaContent3'] p:nth-child(3)")
    private WebElement operTimeLabel_3;

    @FindBy(css = "div[id='areaContent3'] p:nth-child(4)")
    private WebElement operTimeLabel_4;

    //탭
    public void clickPriceGuideTab() {
        click(priceGuideTab);
    }

    public void clickAreaGuideTab() {
        click(areaGuideTab);
    }

    public boolean activeTabText(){
        return isTabActive(areaGuideTab);
    }

    public String getAreaGuideTabText() {
        return getText(areaGuideTab);
    }

    public  String getPriceGuideTabText() {
        return getText(priceGuideTab);
    }

    //이미지src획득
    public String getSrcAreaImage(){
        return serviceAreaImage.getAttribute("src");
    }

    //관리용 텍스트그룹
    public enum AreaLabel {
        SERVICE_TITLE, SERVICE_L1, SERVICE_L2, SERVICE_L3, SERVICE_L4,
        NON_SERVICE_TITLE, NON_SERVICE_TEXT, NON_SERVICE_L1, NON_SERVICE_L2, NON_SERVICE_L3,
        NON_SERVICE_L4, NON_SERVICE_L5, NON_SERVICE_L6, NON_SERVICE_L7, NON_SERVICE_L8,
        OPER_TIME_TITLE, OPER_L1, OPER_L2, OPER_L3, OPER_L4
    }

    //통합 텍스트 추출 메서드
    public String getLabel(AreaLabel labelType) {
        switch (labelType) {
            case SERVICE_TITLE: return getText(serviceAreaTitle);
            case SERVICE_L1: return getText(serviceAreaLabel_1);
            case SERVICE_L2: return getText(serviceAreaLabel_2);
            case SERVICE_L3: return getText(serviceAreaLabel_3);
            case SERVICE_L4: return getText(serviceAreaLabel_4);

            case NON_SERVICE_TITLE: return getText(nonserviceAreaTitle);
            case NON_SERVICE_TEXT: return getText(nonserviceAreaText);
            case NON_SERVICE_L1: return getText(nonserviceAreaLabel_1);
            case NON_SERVICE_L2: return getText(nonserviceAreaLabel_2);
            case NON_SERVICE_L3: return getText(nonserviceAreaLabel_3);
            case NON_SERVICE_L4: return getText(nonserviceAreaLabel_4);
            case NON_SERVICE_L5: return getText(nonserviceAreaLabel_5);
            case NON_SERVICE_L6: return getText(nonserviceAreaLabel_6);
            case NON_SERVICE_L7: return getText(nonserviceAreaLabel_7);
            case NON_SERVICE_L8: return getText(nonserviceAreaLabel_8);

            case OPER_TIME_TITLE: return getText(operTimeTitle);
            case OPER_L1: return getText(operTimeLabel_1);
            case OPER_L2: return getText(operTimeLabel_2);
            case OPER_L3: return getText(operTimeLabel_3);
            case OPER_L4: return getText(operTimeLabel_4);

            default: throw new IllegalArgumentException("정의되지 않은 라벨 타입입니다: " + labelType);
        }
    }

}


