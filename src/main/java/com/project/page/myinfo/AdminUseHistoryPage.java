package com.project.page.myinfo;

import com.project.page.BasePage;
import com.project.page.NavigationBar;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class AdminUseHistoryPage extends BasePage {

    public NavigationBar navi;

    public AdminUseHistoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.navi = new NavigationBar(driver);
    }

    //탭
    @FindBy(css = "li[class='subNavItem'] a")
    private WebElement myInfoTab;

    @FindBy(css = ".active")
    private WebElement useHistoryTab;

    //검색
    @FindBy(id = "condition")
    private List<WebElement> search_FilterList;

    @FindBy(id = "condition")
    private WebElement search_Filter;

    @FindBy(id = "keyword")
    private WebElement search_Keyword;

    @FindBy(css = "button[type='submit']")
    private WebElement search_Button;

    //송장번호 발급
    @FindBy(id = "courier")
    private List<WebElement>  delivery_CompanyList;

    @FindBy(id = "courier")
    private WebElement  delivery_Company;

    @FindBy(id = "getBtn")
    private WebElement delivery_GetButton;

    @FindBy(id = "sendBtn")
    private WebElement delivery_SendButton;

    //상태수정
    @FindBy(id = "updateBtn")
    private WebElement status_modifyButton;

    @FindBy(id = "updateState")
    private List<WebElement>  status_OptionList;

    @FindBy(id = "updateState")
    private WebElement  status_Option;

    @FindBy(id = "changeBtn")
    private WebElement status_ChangeButton;

    @FindBy(id = "cancleBtn")
    private WebElement status_CancelButton;

    //리스트
    @FindBy(id = "allCheck")
    private WebElement table_AllCheckButton;

    @FindBy(xpath = "//th[2]")
    private WebElement table_OderNumberLabel;

    @FindBy(xpath = "//th[3]")
    private WebElement table_UserLabel;

    @FindBy(xpath = "//th[4]")
    private WebElement table_PriceLabel;

    @FindBy(id = "state")
    private List<WebElement> table_StatusSelectBox_List;

    @FindBy(id = "state")
    private WebElement table_StatusSelectBox;

    @FindBy(xpath = "//td[2]")
    private List<WebElement> table_OderNumberList;

    @FindBy(xpath = "//td[3]")
    private List<WebElement> table_UserIdList;

    @FindBy(xpath = "//td[4]")
    private List<WebElement> table_PriceList;

    @FindBy(xpath = "//td[5]")
    private List<WebElement> table_StatusList;

    @FindBy(xpath = "//td[6]")
    private List<WebElement> table_BookingDateList;

    @FindBy(xpath = "//td[7]")
    private List<WebElement> table_RequestDateList;

    @FindBy(xpath = "//td[8]")
    private List<WebElement> table_GetDeliveryNumList;

    @FindBy(xpath = "//td[9]")
    private List<WebElement> table_SendDeliveryNumList;

    @FindBy(xpath = "//th[6]")
    private WebElement table_BookingDateLabel;

    @FindBy(xpath = "//th[7]")
    private WebElement table_RequestDateLabel;

    @FindBy(xpath = "//th[8]")
    private WebElement table_GetDeliveryNumLabel;

    @FindBy(xpath = "//th[9]")
    private WebElement table_SendDeliveryNumLabel;

    @FindBy(xpath = "//th[10]")
    private WebElement table_DetailLabel;

    @FindBy(css = "tbody tr")
    private List<WebElement> table_List;

    @FindBy(xpath = "//td//input")
    private List<WebElement> table_CheckBox;

    @FindBy(xpath = "//td//a")
    private List<WebElement> table_DetailLink;

    //페이지네비
    @FindBy(xpath = "//div[@class='page-ui my-4']//li")
    private List<WebElement> pageNaviList;

    //관리용 텍스트 그룹
    public enum AdminUseHistoryPageLabel {
        MYINFOTAB, USEHISTORYTAB,
        SEARCH_BUTTON,DELIVERY_GETBUTTON,DELIVERY_SENDBUTTON,STATUS_MODIFYBUTTON,STATUS_CHANGEBUTTON,STATUS_CANCELBUTTON,
        TABLE_ODERNUMBERLABEL,TABLE_USERLABEL,TABLE_PRICELABEL,TABLE_BOOKINGDATELABEL,
        TABLE_REQUESTDATELABEL,TABLE_GETDELIVERYNUMLABEL,TABLE_SENDDELIVERYNUMLABEL,TABLE_DETAILLABEL
    }

    //통합 텍스트 추출 메서드
    public String getLabel(AdminUseHistoryPage.AdminUseHistoryPageLabel labelType) {
        switch (labelType) {
            case MYINFOTAB:                   return getText(myInfoTab);
            case USEHISTORYTAB:               return getText(useHistoryTab);
            case SEARCH_BUTTON:               return getText(search_Button);
            case DELIVERY_GETBUTTON:          return getText(delivery_GetButton);
            case DELIVERY_SENDBUTTON:         return getText(delivery_SendButton);
            case STATUS_MODIFYBUTTON:         return getText(status_modifyButton);
            case STATUS_CHANGEBUTTON:         return getText(status_ChangeButton);
            case STATUS_CANCELBUTTON:         return getText(status_CancelButton);
            case TABLE_ODERNUMBERLABEL:       return getText(table_OderNumberLabel);
            case TABLE_USERLABEL:             return getText(table_UserLabel);
            case TABLE_PRICELABEL:            return getText(table_PriceLabel);
            case TABLE_BOOKINGDATELABEL:      return getText(table_BookingDateLabel);
            case TABLE_REQUESTDATELABEL:      return getText(table_RequestDateLabel);
            case TABLE_GETDELIVERYNUMLABEL:   return getText(table_GetDeliveryNumLabel);
            case TABLE_SENDDELIVERYNUMLABEL:  return getText(table_SendDeliveryNumLabel);
            case TABLE_DETAILLABEL:           return getText(table_DetailLabel);

            default:
                throw new IllegalArgumentException("정의되지 않은 라벨 타입입니다: " + labelType);
        }
    }

    public List<String> getSelectBoxOptions(String selectBox) {
        WebElement targetElement = null;

        switch (selectBox) {
            case "SearchFilter":  targetElement = search_FilterList.get(0); break;
            case "Company":       targetElement = delivery_CompanyList.get(0); break;
            case "ModifyStatus":  targetElement = status_OptionList.get(0); break;
            case "StatusFilter":  targetElement = table_StatusSelectBox_List.get(0); break;
            default:
                System.out.println("[WARN] 잘못된 타겟: " + selectBox);
                return Collections.emptyList();
        }

        Select select = new Select(targetElement);
        List<WebElement> options = select.getOptions();

        List<String> optionTexts = new ArrayList<>();
        for (WebElement option : options) {
            String text = option.getText().trim();
            if (!text.isEmpty()) {
                optionTexts.add(text);
            }
        }

        return optionTexts;
    }

    public void searchKeyword(String keyword){
        search_Keyword.clear();
        search_Keyword.sendKeys(keyword);
        click(search_Button);
    }

    public void changeSearchFilter(int index) {
        Select select = new Select(search_Filter);
        select.selectByIndex(index);
        System.out.println("[INFO] 검색조건을"+index+"(으)로 변경했습니다.");
    }

    public String changeStatus(int index) {
        Select select = new Select(status_Option);
        select.selectByIndex(index);
        String selectedText = select.getFirstSelectedOption().getText().trim();
        System.out.println("[INFO] 검색조건을 "+selectedText+" (으)로 변경했습니다.");
        click(status_ChangeButton);
        return selectedText;
    }

    public int getStatusOptionCount() {
        Select select = new Select(status_Option);
        return select.getOptions().size();
    }

    public int checkResult(){
        int ListCount = table_CheckBox.size();
        System.out.println("검색결과는 "+ListCount+"건 입니다.");
        return ListCount ;
    }

    public void RecoverStatus() {
        click(status_modifyButton);
        waitForVisible(status_CancelButton);
        click(status_ChangeButton);
    }

    public void clickModifyButton() {
        click(status_modifyButton);
        waitForVisible(status_CancelButton);
    }

    public int checkSearchResult(){
        System.out.println("[INFO] 검색결과 : "+table_List.size()+"건 의 리스트가 확인됩니다.");
        return table_List.size();
    }

    public void clickTarget_FirstCheckBox(){
        WebElement firstCheckBox = table_CheckBox.get(0);
        click(firstCheckBox);
    }

    public boolean isUseHistoryTabActive(){
        return isTabActive(useHistoryTab);
    }

    public void clickUseHistoryTab(){ click(useHistoryTab);}

    public void clickMyInfoTab(){ click(myInfoTab); }

    public void clickAllCheckButton(){ click(table_AllCheckButton); }

    public void clickChangeStatusButton(){ click(status_ChangeButton); }

    public int getCheckBoxCount(){ return table_CheckBox.size();}

    public int getCheckedCheckBoxCount(){
        int checkedCount = 0;
        for (WebElement checkBox : table_CheckBox) {
            if (checkBox.isSelected()) {
                checkedCount++;
            }
        }
        return checkedCount;
    }

    public boolean checkTargetStatus(int index, String status) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            boolean isUpdated = wait.until(ExpectedConditions.textToBePresentInElement(
                    table_StatusList.get(index), status.trim()));

            System.out.println("[INFO] 상태 변경 확인 완료: " + status);
            return isUpdated;

        } catch (TimeoutException e) {
            String actualStatus = table_StatusList.get(index).getText().trim();
            System.out.println("[FAIL] 상태 변경 타임아웃! 기대값: [" + status + "], 실제값: [" + actualStatus + "]");
            return false;
        }
    }

    public boolean checkStatusDisabled(){
        String ListDisable = status_Option.getAttribute("disabled");
        return Objects.equals(ListDisable, "true");
    }

    public boolean checkDisplayButton_Cancel(){
        return status_CancelButton.isDisplayed();
    }

    public boolean checkDisplayButton_Change(){
        return status_CancelButton.isDisplayed();
    }

    public boolean checkDisplayButton_Modify(){
        return status_modifyButton.isDisplayed();
    }

    public void clickCancelButton(){
        click(status_CancelButton);
    }

    public String changeStatusFilter(int index) {
        Select select = new Select(table_StatusSelectBox);
        select.selectByIndex(index);
        String selectedText = select.getFirstSelectedOption().getText().trim();
        System.out.println("[INFO] 상태필터를 "+selectedText+" (으)로 변경했습니다.");
        return selectedText;
    }

    public int getStatusFilterOptions(){
        if (table_StatusSelectBox_List.isEmpty()) return 0;
        Select select = new Select(table_StatusSelectBox_List.get(0));
        return select.getOptions().size();
    }

    public List<String> getTargetInfo(int index){
        String OderNumber = table_OderNumberList.get(index).getText().trim();
        String UserId = table_UserIdList.get(index).getText().trim();
        String Price = table_PriceList.get(index).getText().trim();
        String Status = table_StatusList.get(index).getText().trim();
        String BookingDate = table_BookingDateList.get(index).getText().trim();
        String RequestDate = table_RequestDateList.get(index).getText().trim();
        String GetDeliveryNum = table_GetDeliveryNumList.get(index).getText().trim();
        String SendDeliveryNum = table_SendDeliveryNumList.get(index).getText().trim();

        return Arrays.asList(OderNumber,UserId,Price,Status,BookingDate,RequestDate,GetDeliveryNum,SendDeliveryNum);
    }

    public void clickDetailButton(int index){
        click(table_DetailLink.get(index));
    }

    public String searchTestData() {
        int currentPageIndex = 0;

        while (true) {
            int maxList = table_List.size();
            for (int i = 0; i < maxList; i++) {
                String getDeliveryNum = table_GetDeliveryNumList.get(i).getText().trim();
                String sendDeliveryNum = table_SendDeliveryNumList.get(i).getText().trim();

                if (getDeliveryNum.equals(sendDeliveryNum)) {
                    String targetOrderNumber = table_OderNumberList.get(i).getText().trim();
                    System.out.println("[INFO] 데이터 발견! 주문번호: " + targetOrderNumber);
                    return targetOrderNumber;
                }
            }

            if (table_List.size() == 5 && currentPageIndex + 1 < pageNaviList.size()) {
                currentPageIndex++;

                System.out.println("[INFO] " + (currentPageIndex + 1) + "페이지 버튼을 클릭합니다.");
                pageNaviList.get(currentPageIndex).click();

                waitForPageLoad();
            } else {
                System.out.println("[WARN] 더 이상 검색할 페이지가 없습니다. 탐색 종료.");
                return null;
            }
        }
    }


    public void clickDeliveryGetButton(){  click(delivery_GetButton);  }
    public void clickDeliverySendButton(){  click(delivery_SendButton);  }

    public int getTargetIndex(String OderNum){
        int maxList = table_List.size();
        for(int i=0;i<maxList;i++){
            String searchOderNum = table_OderNumberList.get(i).getText().trim();
            if(searchOderNum.equals(OderNum)){return i;}
        }
        System.out.println("[WARN] 해당 주문번호를 가진 리스트가 확인되지 않습니다 (타겟 주문번호 : "+OderNum+" )");
        return -1;
    }

    public void clickCheckButton(int index){
        click(table_CheckBox.get(index));
    }

    public int getDeliveryCompanyOptionCount() {
        Select select = new Select(delivery_Company);
        return select.getOptions().size();
    }

    public String selectDeliveryCompany(int index) {
        Select select = new Select(delivery_Company);
        select.selectByIndex(index);
        return select.getFirstSelectedOption().getText().trim();
    }











}

