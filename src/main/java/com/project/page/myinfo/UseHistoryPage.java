package com.project.page.myinfo;

import com.project.page.BasePage;
import com.project.page.NavigationBar;
import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class UseHistoryPage extends BasePage {

    public NavigationBar navi;
    private Random random = new Random();
    private ConfigReader config = new ConfigReader();

    public UseHistoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.navi = new NavigationBar(driver);
    }

    //탭
    @FindBy(css = "li[class='subNavItem'] a")
    private WebElement myInfoTab;

    @FindBy(css = ".active")
    private WebElement useHistoryTab;

    //탑라벨
    @FindBy(css = "th:nth-child(1)")
    private WebElement oderNumber;

    @FindBy(css = "th:nth-child(2)")
    private WebElement oderDetail;

    @FindBy(css = "th:nth-child(3)")
    private WebElement oderPrice;

    @FindBy(css = "th:nth-child(4)")
    private WebElement address;

    @FindBy(css = "th:nth-child(5)")
    private WebElement bookingDate;

    @FindBy(css = "th:nth-child(6)")
    private WebElement retrieveDate;

    @FindBy(css = "th:nth-child(7)")
    private WebElement status;

    @FindBy(css = "th:nth-child(8)")
    private WebElement review;

    //게시물리스트
    @FindBy(xpath = "//td[1]")
    private List<WebElement> oderNumberList;

    @FindBy(xpath = "//td[2]")
    private List<WebElement> oderDetailList;

    @FindBy(xpath = "//td[3]")
    private List<WebElement> oderPriceList;

    @FindBy(xpath = "//td[4]")
    private List<WebElement> addressList;

    @FindBy(xpath = "//td[5]")
    private List<WebElement> bookingDateList;

    @FindBy(xpath = "//td[6]")
    private List<WebElement> retrieveDateList;

    @FindBy(xpath = "//td[7]")
    private List<WebElement> statusList;

    @FindBy(xpath = "//td[8]")
    private List<WebElement> reviewList_Link;

    @FindBy(xpath = "//div[@class='page-ui my-4']//li")
    private List<WebElement> pageNaviList;

    //관리용 텍스트 그룹
    public enum UseHistoryPageLabel {
        MYINFOTAB, USEHISTORYTAB,
        ODERNUMBERLABEL, ODERDETAILLABEL, ODERPRICELABEL, ADDRESSLABEL, BOKKINGDATELABEL,
        RETRIEVEDATELABEL, STATUSLABEL, REVIEWLABEL
    }

    //통합 텍스트 추출 메서드
    public String getLabel(UseHistoryPageLabel labelType) {
        switch (labelType) {
            case MYINFOTAB:           return getText(myInfoTab);
            case USEHISTORYTAB:       return getText(useHistoryTab);
            case ODERNUMBERLABEL:     return getText(oderNumber);
            case ODERDETAILLABEL:     return getText(oderDetail);
            case ODERPRICELABEL:      return getText(oderPrice);
            case ADDRESSLABEL:        return getText(address);
            case BOKKINGDATELABEL:    return getText(bookingDate);
            case RETRIEVEDATELABEL:   return getText(retrieveDate);
            case STATUSLABEL:         return getText(status);
            case REVIEWLABEL:         return getText(review);

            default:  throw new IllegalArgumentException("정의되지 않은 라벨 타입입니다: " + labelType);
        }
    }

    //탭 메서드
    public void clickMyInfoTab(){
        hover(myInfoTab);
        click(myInfoTab);
    }

    public void clickUseHistoryTab(){
        hover(useHistoryTab);
        click(useHistoryTab);
    }

    //액티브탭확인
    public boolean isUseHistoryTabActive(){
        return isTabActive(useHistoryTab);
    }

    public String clickReviewCreateButton() {
        if (reviewList_Link == null || reviewList_Link.isEmpty()) {
            System.out.println("[SKIP] 리뷰 작성 가능 버튼이 없습니다.");
            return null;
        }

        int index = random.nextInt(reviewList_Link.size());
        WebElement reviewCreateButton = reviewList_Link.get(index);

        String selectOrderNum = "";
        try {
            selectOrderNum = reviewCreateButton.findElement(By.xpath("./ancestor::tr/td[1]")).getText();
        } catch (Exception e) {
            System.out.println("[ERROR] 주문번호를 찾을 수 없습니다: " + e.getMessage());
            selectOrderNum = "Unknown";
        }

        hover(reviewCreateButton);
        click(reviewCreateButton);

        System.out.println((index + 1) + "번 게시물 클릭 완료 (주문번호: " + selectOrderNum + ")");
        return selectOrderNum;
    }

    public String clickOderDetailButton() {
        if (oderDetailList == null || oderDetailList.isEmpty()) {
            System.out.println("[SKIP] 상세정보보기 클릭할 리스트가 없습니다.");
            return null;
        }
        int index = random.nextInt(oderDetailList.size());
        WebElement detailButton = oderDetailList.get(index);
        String selectOrderNum = oderNumberList.get(index).getText();

        hover(detailButton);
        click(detailButton);

        System.out.println((index + 1) + "번 게시물의 상세정보보기 클릭");
        return selectOrderNum;
    }

    public int clickPageNavi() {

        if (pageNaviList == null || pageNaviList.isEmpty()) {
            System.out.println("[SKIP] 페이지 네비게이션이 존재하지 않습니다.");
            return 0;
        }

        int pageNumber = random.nextInt(pageNaviList.size()) + 1;

        if (pageNumber > pageNaviList.size()) {
            throw new IllegalArgumentException("유효하지 않은 페이지 번호: " + pageNumber);
        }

        WebElement pageButton = pageNaviList.get(pageNumber - 1);
        hover(pageButton);
        click(pageButton);
        System.out.println(pageNumber + "번째 페이지 네비게이션 클릭 완료");

        return pageNumber;
    }

    public boolean ReviewButtonRuleCheck() {
        boolean isAllMatch = true;

        List<WebElement> rows = driver.findElements(By.xpath("//div[3]/table[1]/tbody[1]/tr"));

        for (int i = 0; i < rows.size(); i++) {
            WebElement row = rows.get(i);

            //처리상태 텍스트 추출
            String status = row.findElement(By.xpath("./td[7]")).getText().trim();

            //작성하기 버튼확인
            List<WebElement> buttons = row.findElements(By.xpath(".//a[contains(text(),'작성하기')]"));
            boolean hasButton = !buttons.isEmpty() && buttons.get(0).isDisplayed();

            //룰체크
            if (status.equals("반환완료")) {
                if (!hasButton) {
                    System.out.println("[FAIL] " + (i + 1) + "번 게시물: 상태는 '반환완료'인데 버튼이 안 보임");
                    isAllMatch = false;
                }
            } else {
                if (hasButton) {
                    System.out.println("[FAIL] " + (i + 1) + "번 게시물: 상태가 '" + status + "'인데 버튼이 노출됨");
                    isAllMatch = false;
                }
            }
        }

        return isAllMatch;
    }

    public String urlForcedMove_ReviewLink() {
        String invalidOrderNum = null;

        List<WebElement> rows = driver.findElements(By.xpath("//div[3]/table[1]/tbody[1]/tr"));
        for (WebElement row : rows) {
            String status = row.findElement(By.xpath("./td[7]")).getText().trim();
            if (!"반환완료".equals(status)) {
                invalidOrderNum = row.findElement(By.xpath("./td[1]")).getText().trim();
                System.out.println("작성권한 없는 주문번호 발견 (상태: " + status + "): " + invalidOrderNum);
                break;
            }
        }

        if (invalidOrderNum == null) {
            System.out.println("[SKIP] 모든 게시물이 '반환완료' 상태라 테스트가 불가능합니다.");
            return null;
        }

        //추출한 주문번호로 강제 URL 조합 및 이동
        String baseUrl = config.getProperty("ReviewDetailPageURL");
        String forcedUrl = baseUrl + invalidOrderNum;

        System.out.println("강제 접속 시도 URL: " + forcedUrl);
        driver.get(forcedUrl);
        waitForPageLoad();

        return driver.getCurrentUrl();
    }

    public int ListNumber(){
        return oderNumberList.size();
    }

    public boolean pageNaviDisplayCheck(){
        return pageNaviList.get(1).isDisplayed();
    }

    public void gotoDeleteContent_Create(String oderNumber) {
        for (int p = 0; p < pageNaviList.size(); p++) {
            for (int i = 0; i < oderNumberList.size(); i++) {
                String currentOrderNum = oderNumberList.get(i).getText().trim();
                if (Objects.equals(currentOrderNum, oderNumber)) {
                    click(reviewList_Link.get(i));
                    return;
                }
            }

            if (p < pageNaviList.size() - 1) {
                click(pageNaviList.get(p + 1));
                waitForPageLoad();
            }
        }

        System.out.println("[INFO] 해당 주문번호를 찾을 수 없습니다: " + oderNumber);
    }

    public WebElement findOrderDetailByOrderNumber(String targetOrderNum) {
        int listSize = oderNumberList.size();

            //리스트 순회하며 주문번호 매칭
            for (int i = 0; i < listSize; i++) {
                String currentOrderNum = oderNumberList.get(i).getText().trim();
                if (currentOrderNum.equals(targetOrderNum)) {
                        System.out.println("[INFO] 주문번호 '" + targetOrderNum + "'를 찾았습니다. (Index: " + i + ")");
                        return oderDetailList.get(i);
                }
            //현재 페이지에 없으면 다음 페이지로 이동
            if (!clickNextPage()) {
                break;
            }
        }
        System.out.println("[FAIL]주문번호 " + targetOrderNum + "인 항목이 존재하지 않습니다. (송장번호 발급한 주문이 없습니다.)");
        return null;
    }


    private boolean clickNextPage() {
        try {
            for (int i = 0; i < pageNaviList.size(); i++) {
                WebElement pageItem = pageNaviList.get(i);
                String classAttribute = pageItem.getAttribute("class");

                if (classAttribute != null && classAttribute.contains("active")) {
                    if (i < pageNaviList.size() - 1) {
                        WebElement nextPage = pageNaviList.get(i + 1);
                        System.out.println("[INFO] 다음 페이지(" + nextPage.getText() + ")로 이동합니다.");
                        nextPage.click();

                        Thread.sleep(1000);
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("[WARN] 페이지 이동 중 오류 발생: " + e.getMessage());
        }
        return false;
    }

    public void clickTestOderList(WebElement TestOder){
        hover(TestOder);
        click(TestOder);
    }

}
