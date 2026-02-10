package com.project.ui.booking;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.booking.BookingCategoryPage;
import com.project.page.booking.BookingCategoryPage.BookingCategoryLabel;
import com.project.page.booking.BookingInfoPage;
import com.project.utils.ExcelUtil;
import config.ConfigReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookingCategoryPageTest extends BaseTest {

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
                { "의류" , PageLabels.bookingPage_Clothes_productName, "Category_clothes_Img_src"},
                { "침구" , PageLabels.bookingPage_Bedding_productName, "Category_bedding_Img_src"},
                { "신발" , PageLabels.bookingPage_Shoes_productName, "Category_shoes_Img_src"},
                { "리빙" , PageLabels.bookingPage_Living_productName, "Category_living_Img_src"}
        };
    }

    //라벨 텍스트 확인
    @Test(testName = "Booking Category Page Text Test", dataProvider = "categoryProvider")
    public void bookingCategoryPage_TextTest(String categoryName, String expectedProductName, String configKey) {
        BookingInfoPage bookingInfoPage = new BookingInfoPage(driver);
        bookingInfoPage.waitForPageLoad();
        bookingInfoPage.clickCategoryBox(categoryName);

        BookingCategoryPage bookingCategoryPage = new BookingCategoryPage(driver);
        bookingCategoryPage.waitForPageLoad();
        SoftAssert softAssert = new SoftAssert();

        String Category_Title = bookingCategoryPage.getLabel(BookingCategoryLabel.PAGE_TITLE);
        softAssert.assertEquals(Category_Title, PageLabels.bookingPage_Category_Title);

        String Category_SubTitle = bookingCategoryPage.getLabel(BookingCategoryLabel.PAGE_SUBTITLE);
        softAssert.assertEquals(Category_SubTitle, PageLabels.bookingPage_Category_SubTitle);

        String Category_productBuyLabel = bookingCategoryPage.getLabel(BookingCategoryLabel.PRODUCT_BUY);
        softAssert.assertEquals(Category_productBuyLabel, PageLabels.bookingPage_Category_productBuyLabel);

        String Category_productNameLabel = bookingCategoryPage.getLabel(BookingCategoryLabel.PRODUCT_NAME);
        softAssert.assertEquals(Category_productNameLabel, PageLabels.bookingPage_Category_productNameLabel);

        String Category_productOptionLabel = bookingCategoryPage.getLabel(BookingCategoryLabel.PRODUCT_OPTION);
        softAssert.assertEquals(Category_productOptionLabel, PageLabels.bookingPage_Category_productOptionLabel);

        String Category_productBookingdateLabel = bookingCategoryPage.getLabel(BookingCategoryLabel.PRODUCT_BOOkINGDATE);
        softAssert.assertEquals(Category_productBookingdateLabel, PageLabels.bookingPage_Category_productBookingdateLabel);

        String Category_productAmountLabel = bookingCategoryPage.getLabel(BookingCategoryLabel.PRODUCT_AMOUNT);
        softAssert.assertEquals(Category_productAmountLabel, PageLabels.bookingPage_Category_productAmountLabel);

        String Category_productName = bookingCategoryPage.getLabel(BookingCategoryLabel.PRODUCTNAME);
        softAssert.assertEquals(Category_productName, expectedProductName);

        String Category_productBookingButton = bookingCategoryPage.getLabel(BookingCategoryLabel.PRODUCTBOOKINGBTOON);
        softAssert.assertEquals(Category_productBookingButton, PageLabels.bookingPage_Category_productBookingButton);

        String Category_explainTitle = bookingCategoryPage.getLabel(BookingCategoryLabel.EXPLAIN_TITLE);
        softAssert.assertEquals(Category_explainTitle, PageLabels.bookingPage_Category_explainTitle);

        String Category_explainText_1 = bookingCategoryPage.getLabel(BookingCategoryLabel.EXPLAIN_TEXT1);
        softAssert.assertEquals(Category_explainText_1, PageLabels.bookingPage_Category_explainText_1);

        String Category_explainText_2 = bookingCategoryPage.getLabel(BookingCategoryLabel.EXPLAIN_TEXT2);
        softAssert.assertEquals(Category_explainText_2, PageLabels.bookingPage_Category_explainText_2);

        String Category_explainText_3 = bookingCategoryPage.getLabel(BookingCategoryLabel.EXPLAIN_TEXT3);
        softAssert.assertEquals(Category_explainText_3, PageLabels.bookingPage_Category_explainText_3);

        String Category_explainFooterText = bookingCategoryPage.getLabel(BookingCategoryLabel.EXPLAIN_FOOTERTEXT);
        softAssert.assertEquals(Category_explainFooterText, PageLabels.bookingPage_Category_explainFooterText);

        softAssert.assertAll();
    }

    //src확인
    @Test(testName = "Booking Category Page Src Test", dataProvider = "categoryProvider")
    public void bookingCategoryPage_SrcTest(String categoryName, String expectedProductName, String configKey) {
        BookingInfoPage bookingInfoPage = new BookingInfoPage(driver);
        bookingInfoPage.waitForPageLoad();
        bookingInfoPage.clickCategoryBox(categoryName);

        BookingCategoryPage bookingCategoryPage = new BookingCategoryPage(driver);
        bookingCategoryPage.waitForPageLoad();
        SoftAssert softAssert = new SoftAssert();
        ConfigReader config = new ConfigReader();

        String getImgSrc_productImg = bookingCategoryPage.getImgSrc_productImg();
        softAssert.assertEquals(getImgSrc_productImg, config.getProperty(configKey));

        String getImgSrc_explainImg_1 = bookingCategoryPage.getImgSrc_explainImg_1();
        softAssert.assertEquals(getImgSrc_explainImg_1, config.getProperty("explainImg_1"));

        String getImgSrc_explainImg_2 = bookingCategoryPage.getImgSrc_explainImg_2();
        softAssert.assertEquals(getImgSrc_explainImg_2, config.getProperty("explainImg_2"));

        String getImgSrc_explainImg_3 = bookingCategoryPage.getImgSrc_explainImg_3();
        softAssert.assertEquals(getImgSrc_explainImg_3, config.getProperty("explainImg_3"));

        String getImgSrc_explainImg_4 = bookingCategoryPage.getImgSrc_explainImg_4();
        softAssert.assertEquals(getImgSrc_explainImg_4, config.getProperty("explainImg_4"));

        softAssert.assertAll();
    }

    @Test(testName = "Option List Test", dataProvider = "categoryProvider")
    public void bookingCategoryPage_OptionListTest(String categoryName, String expectedProductName, String configKey) {
        BookingInfoPage bookingInfoPage = new BookingInfoPage(driver);
        bookingInfoPage.waitForPageLoad();
        bookingInfoPage.clickCategoryBox(categoryName);

        BookingCategoryPage bookingCategoryPage = new BookingCategoryPage(driver);
        bookingCategoryPage.waitForPageLoad();
        ConfigReader config = new ConfigReader();
        SoftAssert softAssert = new SoftAssert();

        String PriceGuideData_path = config.getProperty("PriceGuideDataPath");
        List<Map<String, String>> testData = ExcelUtil.getTestData(PriceGuideData_path, "Item");

        List<String> expectedOptions = testData.stream()
                .filter(d -> categoryName.equals(d.get("Category"))) // 엑셀의 'Category' 컬럼 확인
                .map(d -> d.get("Item_Name"))                        // 아이템 이름만 추출
                .collect(Collectors.toList());


        bookingCategoryPage.clickSelectBox();
        List<String> actualOptions = bookingCategoryPage.getAllProductOptions();

        softAssert.assertEquals(actualOptions.size(), expectedOptions.size(),categoryName + " 옵션 개수가 맞지 않습니다.");
        softAssert.assertTrue(actualOptions.containsAll(expectedOptions), "기대하는 옵션이 실제 리스트에 누락되었습니다.");

        softAssert.assertAll();
    }

    @AfterMethod
    private void Logout() {
        BookingCategoryPage bookingCategoryPage = new BookingCategoryPage(driver);
        bookingCategoryPage.navi.clickLogoutLink();
    }
}
