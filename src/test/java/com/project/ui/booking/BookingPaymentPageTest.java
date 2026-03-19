package com.project.ui.booking;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.booking.BookingCategoryPage;
import com.project.page.booking.BookingInfoPage;
import com.project.page.booking.BookingPaymentPage;
import com.project.utils.ExcelUtil;
import com.project.utils.ScreenshotSoftAssert;
import config.ConfigReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookingPaymentPageTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void goToBooking_PaymentPage() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToBookingInfoPage();

        BookingInfoPage bookingInfoPage = new BookingInfoPage(driver);
        bookingInfoPage.waitForPageLoad();

        String testCategory = "의류";   //테스트용 카테고리
        bookingInfoPage.clickCategoryBox(testCategory);

        BookingCategoryPage bookingCategoryPage = new BookingCategoryPage(driver);
        bookingCategoryPage.waitForPageLoad();

        ConfigReader config = new ConfigReader();
        List<Map<String, String>> testData = ExcelUtil.getTestData(config.getProperty("PriceGuideDataPath"), "Item");
        List<Map<String, String>> categoryItems = testData.stream()
                .filter(d -> testCategory.equals(d.get("Category")))
                .collect(Collectors.toList());
        String pickedItem = categoryItems.get(1).get("Item_Name");

        bookingCategoryPage.clickSelectBox();
        bookingCategoryPage.selectProductByName(pickedItem);

        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String strToday = today.format(formatter);
        bookingCategoryPage.inputBookingDate(strToday);

        bookingCategoryPage.clickBookingButton();
    }


    @Test(testName = "Booking Payment Page Text Test")
    public void bookingPaymentPage_TextTest(){
        BookingPaymentPage bookingPaymentPage = new BookingPaymentPage(driver);
        bookingPaymentPage.waitForPageLoad();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        //라벨체크
        String Payment_PageTitle = bookingPaymentPage.getLabel(BookingPaymentPage.BookingPaymentLabel.PAGETITLE);
        softAssert.assertEquals(Payment_PageTitle, PageLabels.bookingPage_Payment_pageTitle);

        String Payment_SubTitle = bookingPaymentPage.getLabel(BookingPaymentPage.BookingPaymentLabel.PAGESUBTITLE);
        softAssert.assertEquals(Payment_SubTitle, PageLabels.bookingPage_Payment_pageSubTitle);

        String Payment_ProductInfoLabel = bookingPaymentPage.getLabel(BookingPaymentPage.BookingPaymentLabel.PRODUCTINFOLABEL);
        softAssert.assertEquals(Payment_ProductInfoLabel, PageLabels.bookingPage_Payment_productInfoLabel);

        String Payment_UserIdLabel = bookingPaymentPage.getLabel(BookingPaymentPage.BookingPaymentLabel.USERIDLABEL);
        softAssert.assertEquals(Payment_UserIdLabel, PageLabels.bookingPage_Payment_userIdLabel);

        String Payment_ProductCountLabel = bookingPaymentPage.getLabel(BookingPaymentPage.BookingPaymentLabel.PRODUCTCOUNTLABEL);
        softAssert.assertEquals(Payment_ProductCountLabel, PageLabels.bookingPage_Payment_productCountLabel);

        String Payment_AmountLabel = bookingPaymentPage.getLabel(BookingPaymentPage.BookingPaymentLabel.AMOUNTLABEL);
        softAssert.assertEquals(Payment_AmountLabel, PageLabels.bookingPage_Payment_amountLabel);

        String Payment_BookingDateLabel = bookingPaymentPage.getLabel(BookingPaymentPage.BookingPaymentLabel.BOOKINGDATELABEL);
        softAssert.assertEquals(Payment_BookingDateLabel, PageLabels.bookingPage_Payment_bookingDateLabel);

        String Payment_BookingAddressLabel = bookingPaymentPage.getLabel(BookingPaymentPage.BookingPaymentLabel.BOOKINGADDRESSLABEL);
        softAssert.assertEquals(Payment_BookingAddressLabel, PageLabels.bookingPage_Payment_bookingAddressLabel);

        String Payment_AddressLabel = bookingPaymentPage.getLabel(BookingPaymentPage.BookingPaymentLabel.ADDRESSLABEL);
        softAssert.assertEquals(Payment_AddressLabel, PageLabels.bookingPage_Payment_addressLabel);

        String Payment_RequestLabel = bookingPaymentPage.getLabel(BookingPaymentPage.BookingPaymentLabel.REQUESTLABEL);
        softAssert.assertEquals(Payment_RequestLabel, PageLabels.bookingPage_Payment_requestLabel);

        String Payment_EmailLabel = bookingPaymentPage.getLabel(BookingPaymentPage.BookingPaymentLabel.EMAILLABEL);
        softAssert.assertEquals(Payment_EmailLabel, PageLabels.bookingPage_Payment_emailLabel);

        String Payment_PaymentMethodLabel = bookingPaymentPage.getLabel(BookingPaymentPage.BookingPaymentLabel.PAYMENTMETHODLABEL);
        softAssert.assertEquals(Payment_PaymentMethodLabel, PageLabels.bookingPage_Payment_paymentMethodLabel);

        String Payment_PaymentButton = bookingPaymentPage.getLabel(BookingPaymentPage.BookingPaymentLabel.PAYMENTBUTTON);
        softAssert.assertEquals(Payment_PaymentButton, PageLabels.bookingPage_Payment_paymentButton);

        String Payment_OldAddressLabel = bookingPaymentPage.getLabel(BookingPaymentPage.BookingPaymentLabel.OLDADDRESSLABEL);
        softAssert.assertEquals(Payment_OldAddressLabel, PageLabels.bookingPage_Payment_oldAddressLabel);

        String Payment_NewAddressLabel = bookingPaymentPage.getLabel(BookingPaymentPage.BookingPaymentLabel.NEWADDRESSLABEL);
        softAssert.assertEquals(Payment_NewAddressLabel, PageLabels.bookingPage_Payment_newAddressLabel);

        softAssert.assertAll();
    }

    //placeholder확인
    @Test(testName = "Booking Payment Page Placeholder Test")
    public void bookingPaymentPage_PlaceholderTest() {
        BookingPaymentPage bookingPaymentPage = new BookingPaymentPage(driver);
        bookingPaymentPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        String requestPlaceholder = bookingPaymentPage.getPlaceholder_requestInputBox();
        softAssert.assertEquals(requestPlaceholder, PageLabels.bookingPage_Payment_requestInputBoxPlaceholder);

        String emailPlaceholder   = bookingPaymentPage.getPlaceholder_emailInputBox();
        softAssert.assertEquals(emailPlaceholder, PageLabels.bookingPage_Payment_emailInputBoxPlaceholder);

        softAssert.assertAll();
    }

    //주소영역 표시확인
    @Test(testName = "Booking Payment Page AreaDisplay Test")
    public void bookingPaymentPage_AreaDisplayTest() {
        BookingPaymentPage bookingPaymentPage = new BookingPaymentPage(driver);
        bookingPaymentPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        //신규배송지 선택
        bookingPaymentPage.clickNewAddressCheckbox();
        softAssert.assertFalse(bookingPaymentPage.isDisplayCheck_OldAddressArea());
        softAssert.assertTrue(bookingPaymentPage.isDisplayCheck_NewAddressArea());

        //기존배송지 선택
        bookingPaymentPage.clickOldAddressCheckbox();
        softAssert.assertTrue(bookingPaymentPage.isDisplayCheck_OldAddressArea());
        softAssert.assertFalse(bookingPaymentPage.isDisplayCheck_NewAddressArea());

        softAssert.assertAll();
    }

    @AfterMethod
    private void Logout() {
        BookingPaymentPage bookingPaymentPage = new BookingPaymentPage(driver);
        bookingPaymentPage.navi.clickLogoutLink();
    }

}

