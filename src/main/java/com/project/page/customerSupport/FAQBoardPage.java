package com.project.page.customerSupport;

import com.project.page.BasePage;
import com.project.page.NavigationBar;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class FAQBoardPage extends BasePage {

    public NavigationBar navi;

    public FAQBoardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.navi = new NavigationBar(driver);
    }

    //탭
    @FindBy(css = "nav[class='bg-light'] li:nth-child(1) a:nth-child(1)")
    private WebElement reviewBoardTab;

    @FindBy(css = "a[class=' active']")
    private WebElement FAQBoardTab;

    @FindBy(css = "nav[class='bg-light'] li:nth-child(3) a:nth-child(1)")
    private WebElement QnABoardTab;

    @FindBy(css = "nav[class='bg-light'] li:nth-child(4) a:nth-child(1)")
    private WebElement NoticeTab;

    //검색창
    @FindBy(css = "select[name='condition']")
    private WebElement searchConditionSelect;

    @FindBy(name = "keyword")
    private WebElement searchKeywordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement searchButton;

    //페이지타이틀 및 필터
    @FindBy(css = "div[class='container'] h1")
    private WebElement pageTitleLabel;

    @FindBy(css = ".css-1y7lkh5")
    private WebElement pageTitleUnderTextLabel;

    @FindBy(css = "button[value='service']")
    private WebElement boardFilterServiceButton;

    @FindBy(css = "button[value='order']")
    private WebElement boardFilterOrderButton;

    @FindBy(css = "button[value='member']")
    private WebElement boardFilterMemberButton;

    @FindBy(css = "button[value='etc']")
    private WebElement boardFilterEtcButton;

    @FindBy(id = "qna")
    private WebElement qnaBoardButton;


    //게시판라벨
    @FindBy(css = "th:nth-child(1)")
    private WebElement FAQNumberLabel;

    @FindBy(css = "th:nth-child(2)")
    private WebElement FAQCategoryLabel;

    @FindBy(css = "th:nth-child(3)")
    private WebElement FAQTitleLabel;

    //게시판
    @FindBy(xpath = "//tr[@class='question']")
    private List<WebElement> FAQBoradList;

    @FindBy(xpath = "//tr[@class='question']")
    private WebElement FAQBorad;

    @FindBy(xpath = "//i[@class='bi bi-chevron-down']")
    private List<WebElement> FAQFoldButton;

    @FindBy(xpath = "//li[@class='page-item']")
    private List<WebElement> pageNaviList;

    @FindBy(css = "button[class='btn btn-outline-dark']")
    private List<WebElement> FQACreateButton_forCheck;

    @FindBy(xpath = "//tr//td[2]")
    private List<WebElement> CategoryFiledList;

    @FindBy(xpath = "//tr//td[3]")
    private List<WebElement> TitleFiledList;

    //관리자
    //게시판라벨
    @FindBy(css = "th:nth-child(4)")
    private WebElement FAQModifyLabel;

    @FindBy(css = "th:nth-child(5)")
    private WebElement FAQDeleteLabel;

    @FindBy(xpath = "//i[@class='bi bi-pencil-fill']")
    private List<WebElement> FAQModifyButtonList;

    @FindBy(xpath = "//i[@class='bi bi-x-circle-fill']")
    private List<WebElement> FAQDeleteButtonList;

    @FindBy(css = "button[class='btn btn-outline-dark']")
    private WebElement FQACreateButton;


    //탭클릭 메서드
    public void clickReviewBoardTab() {
        hover(reviewBoardTab);
        click(reviewBoardTab);
    }

    public void clickFAQBoardTab() {
        hover(FAQBoardTab);
        click(FAQBoardTab);
    }

    public void clickQnABoardTab() {
        hover(QnABoardTab);
        click(QnABoardTab);
    }

    public void clickNoticeTab() {
        hover(NoticeTab);
        click(NoticeTab);
    }

    //관리용 텍스트 그룹
    public enum FAQBoardPageLabel {
        REVIEWBOARDTAB,FAQBOARDTAB,QNABOARDTAB,NOTICETAB,
        PAGETITLELABEL,PAGETITLEUNDERTEXTLABEL,
        BOARDFILTERSERVICEBUTTON,BOARDFILTERORDERBUTTON,BOARDFILTERMEMBERBUTTON,BOARDFILTERETCBUTTON,QNABOARDBUTTON,
        FAQNUMBERLABEL,FAQCATEGORYLABEL,FAQTITLELABEL,
        FAQMODIFYLABEL,FAQDELETELABEL,FQACREATEBUTTON
    }

    //통합 텍스트 추출 메서드
    public String getLabel(FAQBoardPage.FAQBoardPageLabel labelType){
        switch (labelType) {
            case REVIEWBOARDTAB:            return getText(reviewBoardTab);
            case FAQBOARDTAB:               return getText(FAQBoardTab);
            case QNABOARDTAB:               return getText(QnABoardTab);
            case NOTICETAB:                 return getText(NoticeTab);
            case PAGETITLELABEL:            return getText(pageTitleLabel);
            case PAGETITLEUNDERTEXTLABEL:   return getText(pageTitleUnderTextLabel);
            case BOARDFILTERSERVICEBUTTON:  return getText(boardFilterServiceButton);
            case BOARDFILTERORDERBUTTON:    return getText(boardFilterOrderButton);
            case BOARDFILTERMEMBERBUTTON:   return getText(boardFilterMemberButton);
            case BOARDFILTERETCBUTTON:      return getText(boardFilterEtcButton);
            case QNABOARDBUTTON:            return getText(qnaBoardButton);
            case FAQNUMBERLABEL:            return getText(FAQNumberLabel);
            case FAQCATEGORYLABEL:          return getText(FAQCategoryLabel);
            case FAQTITLELABEL:             return getText(FAQTitleLabel);

            case FAQMODIFYLABEL:             return getText(FAQModifyLabel);
            case FAQDELETELABEL:             return getText(FAQDeleteLabel);
            case FQACREATEBUTTON:             return getText(FQACreateButton);

            default:  throw new IllegalArgumentException("정의되지 않은 라벨 타입입니다: " + labelType);
        }

    }

    //갯수취득
    public int checkListCount(){
        return FAQBoradList.size();
    }

    public int checkFoldButtonCount(){
        return FAQFoldButton.size();
    }

    public int checkModifyButtonCount(){
        return FAQModifyButtonList.size();
    }

    public int checkDeleteButtonCount(){
        return FAQDeleteButtonList.size();
    }

    //표시확인
    public boolean checkCreateButton(){
        return FQACreateButton.isDisplayed();
    }

    public boolean checkPageNavi(){
        System.out.println("페이지 네비게이션 개수 : "+pageNaviList.size()+"개");
        return pageNaviList.size()>1;
    }

    public boolean checkHiddenCreateButton(){
        return FQACreateButton_forCheck.isEmpty();
    }

    public boolean checkHiddenPageNavi(){
        return pageNaviList.isEmpty();
    }

    //검색
    public void searchKeyword(String keyword){
        searchKeywordInput.sendKeys(keyword);
        click(searchButton);
    }

    public int checkResult(){
        int FAQListCount = FAQBoradList.size();
        System.out.println("검색결과는 "+FAQListCount+"건 입니다.");
        return FAQListCount ;
    }

    public void selectOption(int index) {
        Select select = new Select(searchConditionSelect);
        select.selectByIndex(index);
        System.out.println("[INFO] 검색조건을"+index+"(으)로 변경했습니다.");
    }

    //클릭
    public void clickFoldButton(int index) {
        click(FAQFoldButton.get(index));
    }

    public void clickFilterServiceButton() {
        click(boardFilterServiceButton);
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        waitForVisible(FAQBorad);
    }

    public void clickFilterOrderButton() {
        click(boardFilterOrderButton);
        waitForVisible(FAQBorad);
    }

    public void clickFilterMemberButton() {
        click(boardFilterMemberButton);
        waitForVisible(FAQBorad);
    }

    public void clickFilterEtcButton() {
        click(boardFilterEtcButton);
        waitForVisible(FAQBorad);
    }

    public void clickQnAButton(){
        click(qnaBoardButton);
    }

    public void clickModifyButton() {
        click(FAQModifyButtonList.get(0));
    }

    public void clickDeleteButton() {
        click(FAQDeleteButtonList.get(0));
    }

    public void clickCreateButton() {
        click(FQACreateButton);
    }


    //기타
    public String getDataNum(int index){
        return FAQBoradList.get(index).getAttribute("data-num");
    }

    public boolean checkFold(String dataNum){
        WebElement targetElement = driver.findElement(By.id("answer"+dataNum));
        String targetClassName = targetElement.getAttribute("class");
        return targetClassName.equals("answer");
    }


    public boolean checkCategory(String category) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String expectedTag = "[" + category + "]";

        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(CategoryFiledList));

            try { Thread.sleep(500); } catch (InterruptedException ie) {}

            for (int i = 0; i < CategoryFiledList.size(); i++) {
                WebElement element = CategoryFiledList.get(i);
                String actualText = element.getText().trim();

                if (!actualText.equals(expectedTag)) {
                    System.out.println("[FAIL]일치하지 않음: " + actualText);
                    return false;
                }
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("[WARN] StaleElement 발생 - 리스트 재조회 시도 중...");
            return checkCategory(category);
        }

        System.out.println("[SUCCESS] 모든 리스트가 " + expectedTag + "와 일치합니다.");
        return true;
    }

    public void clickRandomModifyButton() {
        Random random = new Random();
        int index = random.nextInt(FAQModifyButtonList.size());
        click(FAQModifyButtonList.get(index));
    }

    public boolean checkTestFAQ(String title, String category, String content, String status) {
        String expectedCategory = "[" + category + "]";

        for (int i = 0; i < TitleFiledList.size(); i++) {
            String actualTitle = TitleFiledList.get(i).getText().trim();
            String actualCategory = CategoryFiledList.get(i).getText().trim();

            if (actualTitle.equals(title) && actualCategory.equals(expectedCategory)) {
                System.out.println("[INFO] 일치하는 게시글 발견 ");

                clickFoldButton(i);
                try { Thread.sleep(500); } catch (InterruptedException e) {}

                WebElement foldContent = TitleFiledList.get(i).findElement(By.xpath("./ancestor::tr/following-sibling::tr[@class='answer']"));

                String actualContent = foldContent.getText().trim();
                boolean isMatch = actualContent.equals(content);

                if (isMatch) {
                    System.out.println("[SUCCESS] 작성한 FAQ 내용과 일치합니다.");
                    if(Objects.equals(status, "modify")){
                        click(FAQModifyButtonList.get(i));
                        System.out.println("[INFO] 작성한 FAQ 수정합니다.");
                    }
                    if(Objects.equals(status, "delete")){
                        click(FAQDeleteButtonList.get(i));
                        System.out.println("[INFO] 작성한 FAQ 삭제합니다.");
                    }
                    
                } else {
                    System.out.println("[FAIL]내용 불일치! 기대값: " + content + " / 실제값: " + actualContent);
                }
                return isMatch;
            }
        }
        System.out.println("[FAIL]리스트에서 작성한 FAQ(제목: " + title + ")를 찾을 수 없습니다.");
        return false;
    }

}
