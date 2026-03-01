package com.project.ui.booking;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.booking.BookingInfoPage;
import config.ConfigReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.project.utils.ScreenshotSoftAssert;


public class BookingInfoPageTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void goToBooking_InfoPage() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToBookingInfoPage();
    }

    //텍스트 테스트
    @Test(testName = "Booking Info Page Text Test")
    public void bookingInfoPage_TextTest() {
        BookingInfoPage bookingInfoPage = new BookingInfoPage(driver);
        bookingInfoPage.waitForPageLoad();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        String TitleText = bookingInfoPage.getBookingPageTitleText();
        softAssert.assertEquals(TitleText, PageLabels.bookingPage_TitleText);

        String ClothesText = bookingInfoPage.getClothesText();
        softAssert.assertEquals(ClothesText, PageLabels.bookingPage_ClothesText);

        String BeddingText = bookingInfoPage.getBeddingText();
        softAssert.assertEquals(BeddingText, PageLabels.bookingPage_BeddingText);

        String ShoesText = bookingInfoPage.getShoesText();
        softAssert.assertEquals(ShoesText, PageLabels.bookingPage_ShoesText);

        String LivingText = bookingInfoPage.getLivingText();
        softAssert.assertEquals(LivingText, PageLabels.bookingPage_LivingText);

        String ClothesTitleText = bookingInfoPage.getClothesTitleText();
        softAssert.assertEquals(ClothesTitleText, PageLabels.bookingPage_ClothesTitleText);

        String BeddingTitleText = bookingInfoPage.getBeddingTitleText();
        softAssert.assertEquals(BeddingTitleText, PageLabels.bookingPage_BeddingTitleText);

        String ShoesTitleText = bookingInfoPage.getShoesTitleText();
        softAssert.assertEquals(ShoesTitleText, PageLabels.bookingPage_ShoesTitleText);

        String LivingTitleText = bookingInfoPage.getLivingTitleText();
        softAssert.assertEquals(LivingTitleText, PageLabels.bookingPage_LivingTitleText);

        softAssert.assertAll();
    }

    //src 테스트
    @Test(testName = "Booking Info Page Src Test")
    public void bookingInfoPage_SrcTest() {
        BookingInfoPage bookingInfoPage = new BookingInfoPage(driver);
        bookingInfoPage.waitForPageLoad();

        ConfigReader config = new ConfigReader();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        String clothes_Img_src = bookingInfoPage.getClothesImgSrc();
        softAssert.assertEquals(clothes_Img_src, config.getProperty("clothes_Img_src"));

        String bedding_Img_src = bookingInfoPage.getBeddingImgSrc();
        softAssert.assertEquals(bedding_Img_src, config.getProperty("bedding_Img_src"));

        String shoes_Img_src = bookingInfoPage.getShoesImgSrc();
        softAssert.assertEquals(shoes_Img_src, config.getProperty("shoes_Img_src"));

        String living_Img_src = bookingInfoPage.getLivingImgSrc();
        softAssert.assertEquals(living_Img_src, config.getProperty("living_Img_src"));

        softAssert.assertAll();
    }

    @AfterMethod
    private void Logout() {
        BookingInfoPage booking_logout = new BookingInfoPage(driver);
        booking_logout.navi.clickLogoutLink();
    }


}
