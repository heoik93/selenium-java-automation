package com.project.functional.bookingpage;

import com.project.base.BaseTest;
import com.project.constants.AppMessages;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.booking.BookingCategoryPage;
import com.project.page.booking.BookingInfoPage;
import com.project.page.booking.BookingPaymentPage;
import com.project.utils.ExcelUtil;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class BookingCategoryTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void goToBooking_InfoPage() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToBookingInfoPage();
    }

    @DataProvider(name = "categoryProvider")
    public Object[][] categoryProvider() {
        return new Object[][] {
                { "의류" , "Category_clothes_Img_src"},
                { "침구" , "Category_bedding_Img_src"},
                { "신발" , "Category_shoes_Img_src"},
                { "리빙" , "Category_living_Img_src"}
        };
    }

    //옵션선택후 에리어활성화 확인
    @Test(testName = "Booking Category Page AreaDisplay Test", dataProvider = "categoryProvider")
    public void bookingCategoryPage_AreaDisplayTest(String categoryName, String configKey) {
        BookingInfoPage bookingInfoPage = new BookingInfoPage(driver);
        bookingInfoPage.waitForPageLoad();
        bookingInfoPage.clickCategoryBox(categoryName);

        BookingCategoryPage bookingCategoryPage = new BookingCategoryPage(driver);
        bookingCategoryPage.waitForPageLoad();
        ConfigReader config = new ConfigReader();

        //에리어 비표시 확인
        Assert.assertFalse(bookingCategoryPage.areaDisplayCheck_Name());
        Assert.assertFalse(bookingCategoryPage.areaDisplayCheck_Number());
        Assert.assertFalse(bookingCategoryPage.areaDisplayCheck_Amount());

        String PriceGuideData_path = config.getProperty("PriceGuideDataPath");
        List<Map<String, String>> testData = ExcelUtil.getTestData(PriceGuideData_path, "Item");

        List<String> expectedOptions = testData.stream()
                .filter(d -> categoryName.equals(d.get("Category")))
                .map(d -> d.get("Item_Name"))                        // 아이템 이름만 추출
                .collect(Collectors.toList());

        //옵션에서 랜덤값
        String pickedItem = expectedOptions.get(new Random().nextInt(expectedOptions.size()));
        bookingCategoryPage.selectProductByName(pickedItem);

        //에리어 표시확인
        Assert.assertTrue(bookingCategoryPage.areaDisplayCheck_Name());
        Assert.assertTrue(bookingCategoryPage.areaDisplayCheck_Number());
        Assert.assertTrue(bookingCategoryPage.areaDisplayCheck_Amount());
    }


    //옵션 선택후 에리어에 올바른 상품 및 가격
    @Test(testName = "Booking Category Page AllProduct Test", dataProvider = "categoryProvider")
    public void bookingCategoryPage_AllProductTest(String categoryName, String configKey) {
        BookingInfoPage bookingInfoPage = new BookingInfoPage(driver);
        bookingInfoPage.waitForPageLoad();
        bookingInfoPage.clickCategoryBox(categoryName);

        BookingCategoryPage bookingCategoryPage = new BookingCategoryPage(driver);
        bookingCategoryPage.waitForPageLoad();

        SoftAssert softAssert = new SoftAssert();
        ConfigReader config = new ConfigReader();

        List<Map<String, String>> testData = ExcelUtil.getTestData(config.getProperty("PriceGuideDataPath"), "Item");
        List<Map<String, String>> categoryItems = testData.stream()
                .filter(d -> categoryName.equals(d.get("Category")))
                .collect(Collectors.toList());

        for (Map<String, String> item : categoryItems) {
            String expectedName = item.get("Item_Name").trim();
            String expectedPrice = item.get("Item_Price").trim().replaceAll("[^0-9]", "").replaceAll(",","");

            // 1. 상품 선택
            bookingCategoryPage.clickSelectBox();
            bookingCategoryPage.selectProductByName(expectedName);

            // 2. 실제 화면 값 추출
            String actualName = bookingCategoryPage.getActualProductName().trim();
            String actualNumber = bookingCategoryPage.getActualNumber();
            String actualPriceRaw = bookingCategoryPage.getActualAmount();
            String actualPrice = actualPriceRaw.replaceAll("[^0-9]", "");

            // 3. 검증
            softAssert.assertEquals(actualName, expectedName, "화면에 노출된 상품명이 기대값과 다릅니다.");
            softAssert.assertEquals(actualNumber, "1", expectedName + " 의 기본 수량이 1이 아닙니다.");
            softAssert.assertEquals(actualPrice, expectedPrice, expectedName + " 상품의 가격이 일치하지 않습니다.");
        }

        softAssert.assertAll();
    }

    // 총상품금액 확인 (랜덤 상품 2~5개, 수량 1~9개)
    @Test(testName = "Booking Category Page Random Amount Test", dataProvider = "categoryProvider")
    public void bookingCategoryPage_RandomAmountTest(String categoryName, String configKey) {
        BookingInfoPage bookingInfoPage = new BookingInfoPage(driver);
        bookingInfoPage.waitForPageLoad();
        bookingInfoPage.clickCategoryBox(categoryName);

        BookingCategoryPage bookingCategoryPage = new BookingCategoryPage(driver);
        bookingCategoryPage.waitForPageLoad();

        SoftAssert softAssert = new SoftAssert();
        ConfigReader config = new ConfigReader();

        List<Map<String, String>> testData = ExcelUtil.getTestData(config.getProperty("PriceGuideDataPath"), "Item");
        List<Map<String, String>> categoryItems = testData.stream()
                .filter(d -> categoryName.equals(d.get("Category")))
                .collect(Collectors.toList());

        // 랜덤 로직
        Random random = new Random();
        long expectedTotalAmount = 0; // 기대하는 총 금액 합계 변수
        Collections.shuffle(categoryItems);

        // 랜덤 상품 개수 설정 (2 ~ 5개, 전체 상품 개수를 넘지 않도록 조정)
        int maxCount = Math.min(categoryItems.size(), 5);
        int countToSelect = random.nextInt(maxCount - 1) + 2; // 최소 2개 보장

        List<Map<String, String>> selectedItems = categoryItems.subList(0, countToSelect);
        System.out.println(">>> [" + categoryName + "] 테스트 시작: 총 " + countToSelect + "개 상품 랜덤 선택");

        //상품 선택 및 수량 변경 루프
        for (Map<String, String> item : selectedItems) {
            String itemName = item.get("Item_Name").trim();
            // 엑셀가격 변환
            long itemPrice = Long.parseLong(item.get("Item_Price").trim().replaceAll("[^0-9]", ""));

            //랜덤 수량 생성
            int quantity = random.nextInt(9) + 1;

            // 상품 선택
            bookingCategoryPage.clickSelectBox();
            bookingCategoryPage.selectProductByName(itemName);
            bookingCategoryPage.setLastProductQuantity(quantity);

            // 기대 금액 누적 계산 (단가 * 수량)
            long currentItemTotal = itemPrice * quantity;
            expectedTotalAmount += currentItemTotal;

            System.out.println(String.format(" - 추가됨: %s | 단가: %d | 수량: %d | 합계: %d",
                    itemName, itemPrice, quantity, currentItemTotal));
        }

        //화면의 총 금액 가져오기
        String actualTotalRaw = bookingCategoryPage.getTotalActualAmount();
        long actualTotalAmount = Long.parseLong(actualTotalRaw.replaceAll("[^0-9]", ""));

        //검증
        softAssert.assertEquals(actualTotalAmount, expectedTotalAmount,
                "계산된 총 금액과 화면의 총 금액이 일치하지 않습니다.");

        System.out.println(">>> 결과 검증 | 기대값: " + expectedTotalAmount + " | 실제값: " + actualTotalAmount);

        softAssert.assertAll();
    }

    //예약하기버튼 (1~3 : Alert)
    @Test(testName = "Booking Category Page BookingButton Test", dataProvider = "categoryProvider")
    public void bookingCategoryPage_BookingButtonTest(String categoryName, String configKey){
        BookingInfoPage bookingInfoPage = new BookingInfoPage(driver);
        bookingInfoPage.waitForPageLoad();
        bookingInfoPage.clickCategoryBox(categoryName);

        BookingCategoryPage bookingCategoryPage = new BookingCategoryPage(driver);
        bookingCategoryPage.waitForPageLoad();

        SoftAssert softAssert = new SoftAssert();
        ConfigReader config = new ConfigReader();

        List<Map<String, String>> testData = ExcelUtil.getTestData(config.getProperty("PriceGuideDataPath"), "Item");
        List<Map<String, String>> categoryItems = testData.stream()
                .filter(d -> categoryName.equals(d.get("Category")))
                .collect(Collectors.toList());

        //1. 옵션과 날짜 미선택 클릭 검증
        bookingCategoryPage.clickBookingButton();

        softAssert.assertEquals(bookingCategoryPage.alertGetText(), AppMessages.bookingCategoryPage_NoDate_AlertMsg);
        bookingCategoryPage.alertAccept();

        //2. 예약날짜만 넣고 클릭 검증
        driver.navigate().refresh();
        bookingCategoryPage.waitForPageLoad();

        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String strToday = today.format(formatter);
        bookingCategoryPage.inputBookingDate(strToday);
        bookingCategoryPage.clickBookingButton();

        softAssert.assertEquals(bookingCategoryPage.alertGetText(), AppMessages.bookingCategoryPage_NoOption_AlertMsg);
        bookingCategoryPage.alertAccept();

        //3. 옵션만 넣고 클릭 검증
        driver.navigate().refresh();
        bookingCategoryPage.waitForPageLoad();

        String pickedItem = categoryItems.get(1).get("Item_Name");
        bookingCategoryPage.clickSelectBox();
        bookingCategoryPage.selectProductByName(pickedItem);
        bookingCategoryPage.clickBookingButton();

        softAssert.assertEquals(bookingCategoryPage.alertGetText(), AppMessages.bookingCategoryPage_NoDate_AlertMsg);
        bookingCategoryPage.alertAccept();

        //4. 모두넣고 클릭 검증
        driver.navigate().refresh();
        bookingCategoryPage.waitForPageLoad();

        bookingCategoryPage.inputBookingDate(strToday);
        bookingCategoryPage.selectProductByName(pickedItem);
        bookingCategoryPage.clickBookingButton();

        BookingPaymentPage bookingPaymentPage = new BookingPaymentPage(driver);
        bookingPaymentPage.waitForPageLoad();

        String currentUrl = bookingPaymentPage.getCurrentUrl();
        String expectedUrlBase = config.getProperty("BookingPaymentURL");
        String PageTittle = bookingPaymentPage.getPageTitle();

        softAssert.assertTrue(currentUrl.contains(expectedUrlBase),expectedUrlBase);
        softAssert.assertEquals(PageTittle, PageLabels.bookingPaymentPageTitle);

        softAssert.assertAll();
    }

    //예약날짜 테스트(과거불가/현재/미래) ==> 현재 과거여도 넘어가지는 DF로 인한 코드작성 불가
//    @Test(testName = "Booking Category Page DateInputBox Test", dataProvider = "categoryProvider")
//    public void bookingCategoryPage_DateInputBoxTest(String categoryName, String configKey){
//        BookingInfoPage bookingInfoPage = new BookingInfoPage(driver);
//        bookingInfoPage.waitForPageLoad();
//        bookingInfoPage.clickCategoryBox(categoryName);
//
//        BookingCategoryPage bookingCategoryPage = new BookingCategoryPage(driver);
//        bookingCategoryPage.waitForPageLoad();
//
//        SoftAssert softAssert = new SoftAssert();
//        ConfigReader config = new ConfigReader();
//
//        List<Map<String, String>> testData = ExcelUtil.getTestData(config.getProperty("PriceGuideDataPath"), "Item");
//        List<Map<String, String>> categoryItems = testData.stream()
//                .filter(d -> categoryName.equals(d.get("Category")))
//                .collect(Collectors.toList());
//
//        LocalDate today = LocalDate.now();
//        LocalDate yesterday = today.minusDays(1);
//        LocalDate tomorrow = today.plusDays(1);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
//        String strYesterday = yesterday.format(formatter);
//        String strToday = today.format(formatter);
//        String strTomorrow = tomorrow.format(formatter);
//        String pickedItem = categoryItems.get(1).get("Item_Name");
//
//        bookingCategoryPage.clickSelectBox();
//        bookingCategoryPage.selectProductByName(pickedItem);
//
//        //과거날짜
//        bookingCategoryPage.inputBookingDate(strYesterday);
//
//        BookingPaymentPage bookingPaymentPage = new BookingPaymentPage(driver);
//        bookingPaymentPage.waitForPageLoad();
//        String currentUrl = bookingPaymentPage.getCurrentUrl();
//        String PageTittle = bookingPaymentPage.getPageTitle();
//
//
//        softAssert.assertEquals(currentUrl,config.getProperty("BoolingPaymentURL"));
//        softAssert.assertEquals(PageTittle, PageLabels.bookingPaymentPageTitle);
//
//        driver.navigate().back();
//
//
//        //현재날짜
//        bookingCategoryPage.inputBookingDate(strToday);
//
//        //미래날짜
//        bookingCategoryPage.inputBookingDate(strTomorrow);
//    }


    @AfterMethod
    private void Logout() {
        BookingCategoryPage bookingCategoryPage = new BookingCategoryPage(driver);
        bookingCategoryPage.navi.clickLogoutLink();
    }
}
