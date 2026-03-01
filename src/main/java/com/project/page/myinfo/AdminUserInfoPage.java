package com.project.page.myinfo;

import com.project.page.BasePage;
import com.project.page.NavigationBar;
import com.project.page.customerSupport.NoticeCreatePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdminUserInfoPage extends BasePage {

    public NavigationBar navi;

    public AdminUserInfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.navi = new NavigationBar(driver);
    }

    //탭
    @FindBy(css = ".active")
    private WebElement myInfoTab;

    @FindBy(css = "li[class='subNavItem'] a")
    private WebElement useHistoryTab;

    //검색
    @FindBy(id = "condition")
    private WebElement search_FilterList;

    @FindBy(id = "keyword")
    private WebElement search_Keyword;

    @FindBy(css = "button[type='submit']")
    private WebElement search_Button;

    @FindBy(id = "deleteBtn")
    private WebElement deleteButton;


    //라벨
    @FindBy(id = "allCheck")
    private WebElement allCheckButton;

    @FindBy(xpath = "//tr/th[2]")
    private WebElement userIdLabel;

    @FindBy(xpath = "//tr/th[3]")
    private WebElement userNameLabel;

    @FindBy(xpath = "//tr/th[4]")
    private WebElement emailLabel;

    @FindBy(xpath = "//tr/th[5]")
    private WebElement phoneLabel;

    @FindBy(xpath = "//tr/th[6]")
    private WebElement signUpDateLabel;

    @FindBy(id = "manager")
    private WebElement userTypeFilter;

    //리스트
    @FindBy(xpath = "//tr/td/input")
    private List<WebElement> CheckButton;

    @FindBy(xpath = "//tr/td[2]")
    private List<WebElement>  userIdList;

    @FindBy(xpath = "//tr/td[3]")
    private List<WebElement>  userNameList;

    @FindBy(xpath = "//tr/td[4]")
    private List<WebElement>  emailList;

    @FindBy(xpath = "//tr/td[5]")
    private List<WebElement>  phoneList;

    @FindBy(xpath = "//tr/td[6]")
    private List<WebElement>  signUpDateList;

    @FindBy(xpath = "//tr/td[7]")
    private List<WebElement>  userTypeList;

    @FindBy(xpath = "//tr/td/button")
    private List<WebElement>  userTypeChangeButton;


    //관리용 텍스트 그룹
    public enum AdminUserInfoPageLabel {
        MYINFOTAB,USEHISTORYTAB,
        USERIDLABEL,USERNAMELABEL,EMAILLABEL,PHONELABEL,SIGNUPDATELABEL,
        SEARCH_BUTTON,DELETEBUTTON
    }


    //통합 텍스트 추출 메서드
    public String getLabel(AdminUserInfoPage.AdminUserInfoPageLabel labelType) {
        switch (labelType) {
            case MYINFOTAB:           return getText(myInfoTab);
            case USEHISTORYTAB:       return getText(useHistoryTab);
            case USERIDLABEL:         return getText(userIdLabel);
            case USERNAMELABEL:       return getText(userNameLabel);
            case EMAILLABEL:          return getText(emailLabel);
            case PHONELABEL:          return getText(phoneLabel);
            case SIGNUPDATELABEL:     return getText(signUpDateLabel);
            case SEARCH_BUTTON:       return getText(search_Button);
            case DELETEBUTTON:        return getText(deleteButton);

            default:  throw new IllegalArgumentException("정의되지 않은 라벨 타입입니다: " + labelType);
        }
    }

    //검색
    public void searchKeyword(String keyword){
        search_Keyword.clear();
        search_Keyword.sendKeys(keyword);
        click(search_Button);
    }

    public int checkResult(){
        int ListCount = CheckButton.size();
        System.out.println("검색결과는 "+ListCount+"건 입니다.");
        return ListCount ;
    }

    //검색조건변경
    public void selectOption(int index) {
        Select select = new Select(search_FilterList);
        select.selectByIndex(index);
        System.out.println("[INFO] 검색조건을 "+index+" (으)로 변경했습니다.");
    }

    public String getChangeButtonText() {
        return getText(userTypeChangeButton.get(1));
    }

    public List<String> getAllOptions_searchFilter() {
        Select select = new Select(search_FilterList);
        List<WebElement> options = select.getOptions();

        List<String> optionTexts = new ArrayList<>();
        for (WebElement option : options) {
            optionTexts.add(option.getText().trim());
        }
        return optionTexts;
    }

    public List<String> getAllOptions_userTypeFilter() {
        Select select = new Select(userTypeFilter);
        List<WebElement> options = select.getOptions();

        List<String> optionTexts = new ArrayList<>();
        for (WebElement option : options) {
            optionTexts.add(option.getText().trim());
        }
        return optionTexts;
    }

    public boolean isMyInfoTabActive(){
        return isTabActive(myInfoTab);
    }

    public void clickUseHistoryTab(){ click(useHistoryTab);}

    public void clickMyInfoTab(){ click(myInfoTab); }

    public void clickAllCheckButton(){ click(allCheckButton); }

    public int getCheckBoxCount(){ return CheckButton.size();}

    public int getCheckedCheckBoxCount(){
        int checkedCount = 0;
        for (WebElement checkBox : CheckButton) {
            if (checkBox.isSelected()) {
                checkedCount++;
            }
        }
        return checkedCount;
    }

    public String selectUSerTypeFilter(int index) {
        Select select = new Select(userTypeFilter);
        select.selectByIndex(index);

        String indexName = select.getOptions().get(index).getText().trim();
        System.out.println("[INFO] 유저타입 필터를 "+indexName+"(으)로 변경했습니다.");
        return indexName;
    }

    public boolean checkUserTypeFilterResult(String userType) {
        for (WebElement type : userTypeList) {
            if (!type.getText().trim().equals(userType)) {
                System.out.println("[FAIL] 유저타입 필터링 결과에 '"+userType+"'이(가) 아닌 항목이 포함되어 있습니다: " + type.getText().trim());
                return false;
            }
        }
        return true;
    }

    public void clickRandomModifyButton(){
        int buttonCount = userTypeChangeButton.size();
        if (buttonCount > 0) {
            int randomIndex = (int) (Math.random() * buttonCount);
            click(userTypeChangeButton.get(randomIndex));
        } else {
            System.out.println("[WARN] 변경 가능한 유저가 없습니다.");
        }
    }

    public void clickTargetCheckBox(String userId){
        for (int i = 0; i < userIdList.size(); i++) {
            if (userIdList.get(i).getText().trim().equals(userId)) {
                click(CheckButton.get(i));
                return;
            }
        }
        System.out.println("[WARN] '"+userId+"' 유저를 찾을 수 없습니다.");
    }

    public void clickTargetModifyButton(String userId){
        for (int i = 0; i < userIdList.size(); i++) {
            if (userIdList.get(i).getText().trim().equals(userId)) {
                click(userTypeChangeButton.get(i));
                return;
            }
        }
        System.out.println("[WARN] '"+userId+"' 유저를 찾을 수 없습니다.");
    }

}
