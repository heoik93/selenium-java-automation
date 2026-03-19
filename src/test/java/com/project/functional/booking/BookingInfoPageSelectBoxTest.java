package com.project.functional.booking;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.booking.BookingInfoPage;
import com.project.utils.ScreenshotSoftAssert;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BookingInfoPageSelectBoxTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void goToBooking_InfoPage() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToBookingInfoPage();
    }

    //페이지 타이틀 및 URL 테스트
    @Test(testName = "Booking Info Page SelectBox Test 1")
    public void bookingInfoPage_defaultTest() {
        BookingInfoPage bookingInfoPage = new BookingInfoPage(driver);
        bookingInfoPage.waitForPageLoad();
        ConfigReader config = new ConfigReader();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        String currentUrl = bookingInfoPage.getCurrentUrl();
        String PageTittle = bookingInfoPage.getPageTitle();

        softAssert.assertEquals(currentUrl,config.getProperty("BookinginfoPageURL"),"[FAIL]예약안내 페이지의 URL이 올바르지 않습니다.");
        softAssert.assertEquals(PageTittle, PageLabels.bookingInfoPageTitle,"[FAIL]예약안내 페이지의 타이틀이 올바르지 않습니다.");

        softAssert.assertAll();
    }

    @DataProvider(name = "categoryURL")
    public Object[][] categoryDataProvider() {

        return new Object[][] {
                { "clothes", "BookingClothesURL" },
                { "bedding", "BookingBeddingURL" },
                { "shoes",   "BookingShoesRUL" },
                { "living",  "BookingLivingURL" }
        };
    }

    //셀렉박스 테스트
    @Test(testName = "Booking Info Page SelectBox Test", dataProvider ="categoryURL")
    public void bookingInfoPage_SelectBoxTest(String category, String configKey) {
        BookingInfoPage bookingInfoPage = new BookingInfoPage(driver);
        bookingInfoPage.waitForPageLoad();
        ConfigReader config = new ConfigReader();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        switch(category) {
            case "clothes": bookingInfoPage.clickClothesBox(); break;
            case "bedding": bookingInfoPage.clickBeddingBox(); break;
            case "shoes":   bookingInfoPage.clickShoesBox();   break;
            case "living":  bookingInfoPage.clickLivingBox();  break;
        }

        String currentUrl = bookingInfoPage.getCurrentUrl();
        String PageTittle = bookingInfoPage.getPageTitle();

        softAssert.assertEquals(currentUrl, config.getProperty(configKey),"[FAIL]예약안내 페이지에서 "+category+"를 선택시의 URL이 올바르지 않습니다.");
        softAssert.assertEquals(PageTittle, PageLabels.bookingCategoryPageTitle,
                "[FAIL]예약안내 페이지에서 "+category+"를 선택시의 페이지타이틀이 올바르지 않습니다.");

        softAssert.assertAll();
    }

    @AfterMethod
    private void Logout() {
        BookingInfoPage booking_logout = new BookingInfoPage(driver);
        booking_logout.navi.clickLogoutLink();
    }
}
