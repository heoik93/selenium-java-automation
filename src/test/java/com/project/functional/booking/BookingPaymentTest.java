package com.project.functional.booking;

import com.project.base.BaseTest;
import com.project.constants.AppMessages;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.booking.BookingCategoryPage;
import com.project.page.booking.BookingInfoPage;
import com.project.page.booking.BookingPaymentPage;
import com.project.page.myinfo.MyinfoPage;
import com.project.page.myinfo.UseHistoryPage;
import com.project.utils.ExcelUtil;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class BookingPaymentTest extends BaseTest {private List<Map<String, Object>> chosenProducts;
    private long expectedTotalAmount;
    private String expectedBookingDate;
    private String expectedUserId;
    private String expectedAddress;

    @BeforeMethod(onlyForGroups = {"ExistProductData"})
    public void goToBooking_PaymentPage_ProductData() {
        chosenProducts = new ArrayList<>();
        expectedTotalAmount = 0;

        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToBookingInfoPage();

        BookingInfoPage bookingInfoPage = new BookingInfoPage(driver);
        bookingInfoPage.waitForPageLoad();

        String[] categories = {"의류", "침구", "신발", "리빙"};
        Random random = new Random();
        String testCategory = categories[random.nextInt(categories.length)];

        bookingInfoPage.clickCategoryBox(testCategory);

        BookingCategoryPage bookingCategoryPage = new BookingCategoryPage(driver);
        bookingCategoryPage.waitForPageLoad();

        ConfigReader config = new ConfigReader();
        Random randomCount = new Random();

        List<Map<String, String>> testData = ExcelUtil.getTestData(config.getProperty("PriceGuideDataPath"), "Item");
        List<Map<String, String>> categoryItems = testData.stream()
                .filter(d -> testCategory.equals(d.get("Category")))
                .collect(Collectors.toList());

        Collections.shuffle(categoryItems);

        int maxSelect = Math.min(categoryItems.size(), 5);
        int countToSelect = (maxSelect <= 2) ? maxSelect : randomCount.nextInt(maxSelect - 1) + 2;

        List<Map<String, String>> selectedItems = categoryItems.subList(0, countToSelect);

        // 상품 선택 및 수량 설정
        for (Map<String, String> item : selectedItems) {
            String itemName = item.get("Item_Name").trim();
            long itemPrice = Long.parseLong(item.get("Item_Price").trim().replaceAll("[^0-9]", ""));
            int quantity = randomCount.nextInt(9) + 1;

            bookingCategoryPage.clickSelectBox();
            bookingCategoryPage.selectProductByName(itemName);
            bookingCategoryPage.setLastProductQuantity(quantity);

            // 필드(chosenProducts)에 데이터 저장
            Map<String, Object> productInfo = new HashMap<>();
            productInfo.put("name", itemName);
            productInfo.put("price", itemPrice);
            productInfo.put("qty", quantity);
            chosenProducts.add(productInfo);

            expectedTotalAmount += (itemPrice * quantity);
        }

        // 날짜 입력
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter expectedFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        expectedBookingDate = today.format(expectedFormatter);
        String strToday = today.format(formatter);
        bookingCategoryPage.inputBookingDate(strToday);

        bookingCategoryPage.clickBookingButton();
    }

    @BeforeMethod(onlyForGroups = {"ExistUserData"})
    public void goToBooking_PaymentPage_ExistUserData() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToMyinfoPage();

        MyinfoPage myInfoPage = new MyinfoPage(driver);
        myInfoPage.waitForPageLoad();

        //주소 및 유저아이디 취득
        expectedUserId = myInfoPage.getUserId();
        expectedAddress = myInfoPage.getAddress();

        myInfoPage.navi.goToBookingInfoPage();
        BookingInfoPage bookingInfoPage = new BookingInfoPage(driver);
        bookingInfoPage.waitForPageLoad();

        String[] categories = {"의류", "침구", "신발", "리빙"};
        Random random = new Random();
        String testCategory = categories[random.nextInt(categories.length)];

        bookingInfoPage.clickCategoryBox(testCategory);

        BookingCategoryPage bookingCategoryPage = new BookingCategoryPage(driver);
        bookingCategoryPage.waitForPageLoad();

        ConfigReader config = new ConfigReader();

        List<Map<String, String>> testData = ExcelUtil.getTestData(config.getProperty("PriceGuideDataPath"), "Item");
        List<Map<String, String>> categoryItems = testData.stream()
                .filter(d -> testCategory.equals(d.get("Category")))
                .collect(Collectors.toList());
        Collections.shuffle(categoryItems);
        Map<String, String> item = categoryItems.get(0);
        String itemName = item.get("Item_Name").trim();

        bookingCategoryPage.clickSelectBox();
        bookingCategoryPage.selectProductByName(itemName);

        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String strToday = today.format(formatter);
        bookingCategoryPage.inputBookingDate(strToday);

        bookingCategoryPage.clickBookingButton();
    }

    @BeforeMethod(onlyForGroups = {"Default"})
    public void goToBooking_PaymentPage() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToBookingInfoPage();

        BookingInfoPage bookingInfoPage = new BookingInfoPage(driver);
        bookingInfoPage.waitForPageLoad();

        String[] categories = {"의류", "침구", "신발", "리빙"};
        Random random = new Random();
        String testCategory = categories[random.nextInt(categories.length)];

        bookingInfoPage.clickCategoryBox(testCategory);

        BookingCategoryPage bookingCategoryPage = new BookingCategoryPage(driver);
        bookingCategoryPage.waitForPageLoad();

        ConfigReader config = new ConfigReader();

        List<Map<String, String>> testData = ExcelUtil.getTestData(config.getProperty("PriceGuideDataPath"), "Item");
        List<Map<String, String>> categoryItems = testData.stream()
                .filter(d -> testCategory.equals(d.get("Category")))
                .collect(Collectors.toList());
        Collections.shuffle(categoryItems);
        Map<String, String> item = categoryItems.get(0);
        String itemName = item.get("Item_Name").trim();

        bookingCategoryPage.clickSelectBox();
        bookingCategoryPage.selectProductByName(itemName);

        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String strToday = today.format(formatter);
        bookingCategoryPage.inputBookingDate(strToday);

        bookingCategoryPage.clickBookingButton();
    }

    @Test(testName = "Booking Payment Page ExistProductData Test", groups = {"ExistProductData"})
    public void bookingPaymentPage_ExistProductDataTest() {
        BookingPaymentPage bookingPaymentPage = new BookingPaymentPage(driver);
        bookingPaymentPage.waitForPageLoad();

        SoftAssert softAssert = new SoftAssert();

        List<String> expectedParts = new ArrayList<>();
        for (Map<String, Object> product : chosenProducts) {
            String name = (String) product.get("name");
            int qty = (int) product.get("qty");
            expectedParts.add(name + " " + qty + "개");
        }
        String expectedProductString = String.join(", ", expectedParts);

        // 실제 데이터 추출
        String actualProductString = bookingPaymentPage.getInputbox_productCountInputBox();
        String actualTotalAmountRaw = bookingPaymentPage.getInputbox_amountInputBox();
        String actualBookingDate = bookingPaymentPage.getInputbox_bookingDateInputBox();
        long actualTotalAmount = Long.parseLong(actualTotalAmountRaw.replaceAll("[^0-9]", ""));


        softAssert.assertEquals(actualProductString, expectedProductString);
        softAssert.assertEquals(actualTotalAmount, expectedTotalAmount);
        softAssert.assertEquals(actualBookingDate, expectedBookingDate);

        softAssert.assertAll();
    }

    @Test(testName = "Booking Payment Page ExistUserData Test", groups = {"ExistUserData"})
    public void bookingPaymentPage_ExistUserDataTest() {
        BookingPaymentPage bookingPaymentPage = new BookingPaymentPage(driver);
        bookingPaymentPage.waitForPageLoad();

        SoftAssert softAssert = new SoftAssert();

        String actualUserId = bookingPaymentPage.getInputbox_userIdInputBox();
        String actualAddress = bookingPaymentPage.getInputbox_addressInputBox();

        softAssert.assertEquals(actualUserId, expectedUserId);
        softAssert.assertEquals(actualAddress, expectedAddress);


        softAssert.assertAll();
    }

    @Test(testName = "Booking Payment Page NewAddress SearchButton Test", groups = {"Default"})
    public void bookingPaymentPage_NewAddressSearchButtonTest() {
        BookingPaymentPage bookingPaymentPage = new BookingPaymentPage(driver);
        bookingPaymentPage.waitForPageLoad();

        bookingPaymentPage.clickNewAddressCheckbox();
        bookingPaymentPage.clickNewAddressSearchButton();

        boolean result = bookingPaymentPage.isDaumPostcodePopupDisplayed();

        Assert.assertTrue(result,"다음 주소 찾기 API 창이 정상적으로 표시되지 않았습니다.");


    }

    //결제버튼테스트
    @Test(testName = "Booking Payment Page PaymentButton Test", groups = {"Default"})
    public void bookingPaymentPage_PaymentButtonTest() {
        BookingPaymentPage bookingPaymentPage = new BookingPaymentPage(driver);
        bookingPaymentPage.waitForPageLoad();

        SoftAssert softAssert = new SoftAssert();

        //1. 배송지 미기입시 alert 테스트
        bookingPaymentPage.clickNewAddressCheckbox();
        bookingPaymentPage.clickPaymentButton();
        bookingPaymentPage.waitForAlert();
        String addressAlertMsg = bookingPaymentPage.alertGetText();

        softAssert.assertEquals(addressAlertMsg, AppMessages.bookingPaymentPage_AddressEmpty_AlertMsg);
        bookingPaymentPage.alertAccept();

        //2. 이메일 미기입시 alert 테스트
        driver.navigate().refresh();
        bookingPaymentPage.waitForPageLoad();
        bookingPaymentPage.clickPaymentButton();
        bookingPaymentPage.waitForAlert();
        String emailAlertMsg = bookingPaymentPage.alertGetText();

        softAssert.assertEquals(emailAlertMsg, AppMessages.bookingPaymentPage_EmailEmpty_AlertMsg);
        bookingPaymentPage.alertAccept();

        //3. 팝업 표시테스트
        driver.navigate().refresh();
        bookingPaymentPage.waitForPageLoad();
        ConfigReader config = new ConfigReader();
        bookingPaymentPage.inputEmailInputBox(config.getProperty("useremail"));

        bookingPaymentPage.clickPaymentButton();
        boolean result = bookingPaymentPage.isInicisPaymentPopupDisplayed();

        softAssert.assertTrue(result,"KG이니시스 API 창이 정상적으로 표시되지 않았습니다.");


        softAssert.assertAll();
    }

    @Test(testName = "Booking Payment Page PaymentSuccess Test", groups = {"Default"})
    public void bookingPaymentPage_PaymentSuccessTest()  {
        BookingPaymentPage bookingPaymentPage = new BookingPaymentPage(driver);
        bookingPaymentPage.waitForPageLoad();

        ConfigReader config = new ConfigReader();
        SoftAssert softAssert = new SoftAssert();

        bookingPaymentPage.inputEmailInputBox(config.getProperty("useremail"));
        bookingPaymentPage.clickPaymentButton();

        bookingPaymentPage.bypassPayment();
        String PaymentSuccessMsg = bookingPaymentPage.alertGetText();
        bookingPaymentPage.alertAccept();
        bookingPaymentPage.closePopupIfPresent();

        UseHistoryPage useHistoryPage = new UseHistoryPage(driver);

        String currentUrl = useHistoryPage.getCurrentUrl();
        String PageTittle = useHistoryPage.getPageTitle();

        softAssert.assertTrue(PaymentSuccessMsg.contains(AppMessages.bookingPaymentPage_PatmentSuccessMsg));
        softAssert.assertEquals(currentUrl, config.getProperty("UseHistoryPageURL"));
        softAssert.assertEquals(PageTittle, PageLabels.useHistoryPageTittle); //현재 DF

        softAssert.assertAll();
    }

    @Test(testName = "Booking Payment Page PaymentFail Test", groups = {"Default"})
    public void bookingPaymentPage_PaymentFailTest()  {
        BookingPaymentPage bookingPaymentPage = new BookingPaymentPage(driver);
        bookingPaymentPage.waitForPageLoad();

        ConfigReader config = new ConfigReader();
        SoftAssert softAssert = new SoftAssert();

        bookingPaymentPage.inputEmailInputBox(config.getProperty("useremail"));
        bookingPaymentPage.clickPaymentButton();

        bookingPaymentPage.simulatePaymentFailure();
        String PaymentFailMsg = bookingPaymentPage.alertGetText();
        bookingPaymentPage.alertAccept();
        bookingPaymentPage.closePopupIfPresent();

        softAssert.assertTrue(PaymentFailMsg.contains(AppMessages.bookingPaymentPage_PatmentFailMsg));

        softAssert.assertAll();
    }


    @AfterMethod
    private void Logout() {
        BookingPaymentPage bookingPaymentPage = new BookingPaymentPage(driver);
        bookingPaymentPage.navi.clickLogoutLink();
    }

}
