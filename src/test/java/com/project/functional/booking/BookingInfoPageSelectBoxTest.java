package com.project.functional.booking;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.booking.BookingInfoPage;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.project.utils.ScreenshotSoftAssert;

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

        String currentUrl = bookingInfoPage.getCurrentUrl();
        String PageTittle = bookingInfoPage.getPageTitle();

        Assert.assertEquals(currentUrl,config.getProperty("BookinginfoPageURL") );
        Assert.assertEquals(PageTittle, PageLabels.bookingInfoPageTitle);
    }

    @DataProvider(name = "categoryURL")
    public Object[][] categoryDataProvider() {
        ConfigReader config = new ConfigReader();
        return new Object[][] {
                { "clothes", "BookingClothesURL" },
                { "bedding", "BookingBeddingURL" },
                { "shoes",   "BookingShoesRUL" },
                { "living",  "BookingLivingURL" }
        };
    }

    //셀렉박스 클릭 테스트
    @Test(testName = "Booking Info Page SelectBox Test 1", dataProvider ="categoryURL")
    public void bookingInfoPage_SelectBoxTest_1(String category, String configKey) {
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

        softAssert.assertEquals(currentUrl, config.getProperty(configKey));
        softAssert.assertEquals(PageTittle, PageLabels.bookingCategoryPageTitle);

        softAssert.assertAll();
    }

    @AfterMethod
    private void Logout() {
        BookingInfoPage booking_logout = new BookingInfoPage(driver);
        booking_logout.navi.clickLogoutLink();
    }
}
