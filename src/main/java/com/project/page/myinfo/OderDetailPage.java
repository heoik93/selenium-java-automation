package com.project.page.myinfo;

import com.project.page.BasePage;
import com.project.page.NavigationBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OderDetailPage extends BasePage {

    public NavigationBar navi;

    public OderDetailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.navi = new NavigationBar(driver);
    }

    //탭
    @FindBy(css = "li[class='subNavItem'] a")
    private WebElement myInfoTab;

    @FindBy(css = ".active")
    private WebElement useHistoryTab;

    //고객정보
    @FindBy(xpath = "//div[3]/div[1]/h3[1]/span[1]")
    private WebElement userInfo_Title;

    @FindBy(xpath = "//div[3]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/th[1]")
    private WebElement userInfo_IdLabel;

    @FindBy(xpath = "//div[3]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]")
    private WebElement userInfo_Id;

    @FindBy(xpath = "//div[3]/div[1]/div[1]/table[1]/tbody[1]/tr[2]/th[1]")
    private WebElement userInfo_NameLabel;

    @FindBy(xpath = "//div[3]/div[1]/div[1]/table[1]/tbody[1]/tr[2]/td[1]")
    private WebElement userInfo_Name;

    @FindBy(xpath = "//div[3]/div[1]/div[1]/table[1]/tbody[1]/tr[3]/th[1]")
    private WebElement userInfo_PhoneLabel;

    @FindBy(xpath = "//div[3]/div[1]/div[1]/table[1]/tbody[1]/tr[3]/td[1]")
    private WebElement userInfo_Phone;

    @FindBy(xpath = "//div[3]/div[1]/div[1]/table[1]/tbody[1]/tr[4]/th[1]")
    private WebElement userInfo_AddressLabel;

    @FindBy(xpath = "//div[3]/div[1]/div[1]/table[1]/tbody[1]/tr[4]/td[1]")
    private WebElement userInfo_Address;

    //주문정보
    @FindBy(xpath = "//div[2]/div[1]/h3[1]/span[1]")
    private WebElement oderInfo_Title;

    @FindBy(xpath = "//div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]")
    private WebElement oderInfo_Number;

    @FindBy(xpath = "//div[2]/div[1]/table[1]/tbody[1]/tr[1]/th[1]")
    private WebElement oderInfo_NumberLabel;

    @FindBy(xpath = "//div[2]/div[1]/table[1]/tbody[1]/tr[2]/td[1]")
    private WebElement oderInfo_status;

    @FindBy(xpath = "//div[2]/div[1]/table[1]/tbody[1]/tr[2]/th[1]")
    private WebElement oderInfo_statusLabel;

    @FindBy(xpath = "//div[2]/div[1]/table[1]/tbody[1]/tr[3]/td[1]")
    private WebElement oderInfo_bookingDate;

    @FindBy(xpath = "//div[2]/div[1]/table[1]/tbody[1]/tr[3]/th[1]")
    private WebElement oderInfo_bookingDateLabel;

    @FindBy(xpath = "//div[2]/div[1]/table[1]/tbody[1]/tr[4]/td[1]")
    private WebElement oderInfo_retrieveDate;

    @FindBy(xpath = "//div[2]/div[1]/table[1]/tbody[1]/tr[4]/th[1]")
    private WebElement oderInfo_retrieveDateLabel;

    @FindBy(xpath = "//div[2]/div[1]/table[1]/tbody[1]/tr[5]/td[1]")
    private WebElement oderInfo_address;

    @FindBy(xpath = "//div[2]/div[1]/table[1]/tbody[1]/tr[5]/th[1]")
    private WebElement oderInfo_addressLabel;

    @FindBy(xpath = "//div[2]/div[1]/table[1]/tbody[1]/tr[6]/td[1]")
    private WebElement oderInfo_request;

    @FindBy(xpath = "//div[2]/div[1]/table[1]/tbody[1]/tr[6]/th[1]")
    private WebElement oderInfo_requestLabel;

    @FindBy(xpath = "//div[2]/div[1]/table[1]/tbody[1]/tr[7]/td[1]")
    private WebElement oderInfo_retrieveNumber;

    @FindBy(xpath = "//div[2]/div[1]/table[1]/tbody[1]/tr[7]/th[1]")
    private WebElement oderInfo_retrieveNumberLabel;

    @FindBy(xpath = "//div[2]/div[1]/table[1]/tbody[1]/tr[8]/td[1]")
    private WebElement oderInfo_returnNumber;

    @FindBy(xpath = "//div[2]/div[1]/table[1]/tbody[1]/tr[8]/th[1]")
    private WebElement oderInfo_returnNumberLabel;


    //주문품목
    @FindBy(xpath = "//div[2]/div[2]/h3[1]/span[1]")
    private WebElement oderProduct_Title;

    @FindBy(css = "thead th:nth-child(1)")
    private WebElement oderProduct_No;

    @FindBy(css = "thead th:nth-child(2)")
    private WebElement oderProduct_Item;

    @FindBy(css = "thead th:nth-child(3)")
    private WebElement oderProduct_Price;

    @FindBy(css = "thead th:nth-child(4)")
    private WebElement oderProduct_Number;

    @FindBy(tagName = "h4")
    private WebElement oderProduct_Amount;

    @FindBy(xpath = "//div[2]/table[1]/tbody[1]/tr[1]/td")
    private List<WebElement> oderProduct_List;


    //버튼
    @FindBy(css = "button[data-courier='kr.logen']")
    private WebElement oderInfo_retrieveCheckButton;

    @FindBy(css = "button[data-courier='kr.epost']")
    private WebElement oderInfo_returnCheckButton;

    @FindBy(css = ".btn.btn-lg.btn-dark")
    private WebElement listButton;

    //관리용 텍스트그룹
    public enum OderDetailPageLabel {
        MYINFOTAB,USEHISTORYTAB,
        USERINFO_TITLE,USERINFO_IDLABEL,USERINFO_ID,USERINFO_NAMELABEL,USERINFO_NAME,USERINFO_PHONELABEL,
        USERINFO_PHONE,USERINFO_ADDRESSLABEL,USERINFO_ADDRESS,
        ODERINFO_TITLE,ODERINFO_NUMBER,ODERINFO_NUMBERLABEL,ODERINFO_STATUS,ODERINFO_STATUSLABEL,
        ODERINFO_BOOKINGDATE,ODERINFO_BOOKINGDATELABEL,ODERINFO_RETRIEVEDATE,ODERINFO_RETRIEVEDATELABEL,
        ODERINFO_ADDRESS,ODERINFO_ADDRESSLABEL,ODERINFO_REQUEST,ODERINFO_REQUESTLABEL,
        ODERINFO_RETRIEVENUMBER,ODERINFO_RETRIEVENUMBERLABEL,ODERINFO_RETURNNUMBER,ODERINFO_RETURNNUMBERLABEL,
        ODERPRODUCT_TITLE,ODERPRODUCT_NO,ODERPRODUCT_ITEM,ODERPRODUCT_PRICE,ODERPRODUCT_NUMBER,ODERPRODUCT_AMOUNT,
        ODERINFO_RETRIEVECHECKBUTTON,ODERINFO_RETURNCHECKBUTTON,LISTBUTTON
    }

    //통합 텍스트 추출 메서드
    public String getLabel(OderDetailPageLabel labelType) {
        switch (labelType) {
            case MYINFOTAB: return getText(myInfoTab);
            case USEHISTORYTAB: return getText(useHistoryTab);

            case USERINFO_TITLE: return getText(userInfo_Title);
            case USERINFO_IDLABEL: return getText(userInfo_IdLabel);
            case USERINFO_ID: return getText(userInfo_Id);
            case USERINFO_NAMELABEL: return getText(userInfo_NameLabel);
            case USERINFO_NAME: return getText(userInfo_Name);
            case USERINFO_PHONELABEL: return getText(userInfo_PhoneLabel);
            case USERINFO_PHONE: return getText(userInfo_Phone);
            case USERINFO_ADDRESSLABEL: return getText(userInfo_AddressLabel);
            case USERINFO_ADDRESS: return getText(userInfo_Address);

            case ODERINFO_TITLE: return getText(oderInfo_Title);
            case ODERINFO_NUMBER: return getText(oderInfo_Number);
            case ODERINFO_NUMBERLABEL: return getText(oderInfo_NumberLabel);
            case ODERINFO_STATUS: return getText(oderInfo_status);
            case ODERINFO_STATUSLABEL: return getText(oderInfo_statusLabel);
            case ODERINFO_BOOKINGDATE: return getText(oderInfo_bookingDate);
            case ODERINFO_BOOKINGDATELABEL: return getText(oderInfo_bookingDateLabel);
            case ODERINFO_RETRIEVEDATE: return getText(oderInfo_retrieveDate);
            case ODERINFO_RETRIEVEDATELABEL: return getText(oderInfo_retrieveDateLabel);
            case ODERINFO_ADDRESS: return getText(oderInfo_address);
            case ODERINFO_ADDRESSLABEL: return getText(oderInfo_addressLabel);
            case ODERINFO_REQUEST: return getText(oderInfo_request);
            case ODERINFO_REQUESTLABEL: return getText(oderInfo_requestLabel);
            case ODERINFO_RETRIEVENUMBER: return getText(oderInfo_retrieveNumber);
            case ODERINFO_RETRIEVENUMBERLABEL: return getText(oderInfo_retrieveNumberLabel);
            case ODERINFO_RETURNNUMBER: return getText(oderInfo_returnNumber);
            case ODERINFO_RETURNNUMBERLABEL: return getText(oderInfo_returnNumberLabel);

            case ODERPRODUCT_TITLE: return getText(oderProduct_Title);
            case ODERPRODUCT_NO: return getText(oderProduct_No);
            case ODERPRODUCT_ITEM: return getText(oderProduct_Item);
            case ODERPRODUCT_PRICE: return getText(oderProduct_Price);
            case ODERPRODUCT_NUMBER: return getText(oderProduct_Number);
            case ODERPRODUCT_AMOUNT: return getText(oderProduct_Amount);

            case ODERINFO_RETRIEVECHECKBUTTON: return getText(oderInfo_retrieveCheckButton);
            case ODERINFO_RETURNCHECKBUTTON: return getText(oderInfo_returnCheckButton);
            case LISTBUTTON: return getText(listButton);

            default: throw new IllegalArgumentException("정의되지 않은 라벨 타입입니다: " + labelType);
        }
    }





}
