package com.project.ui;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.useguide.PriceGuidePage;
import com.project.utils.ExcelUtil;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PriceGuideTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void goToCAreaGuidePage() {
        connectToUrl();
        HomePage homepage = new HomePage(driver);
        homepage.navi.waitForPageLoad();
        homepage.navi.goToPriceGuidePage();
    }

    //탭 액티브체크
    @Test(testName = "PriceGuidepage tab active test")
    public void priceGuidePageTabActiveTest() {
        PriceGuidePage priceGuidePage = new PriceGuidePage(driver);
        priceGuidePage.waitForPageLoad();

        Assert.assertTrue(priceGuidePage.activeTabText());
    }

    //버튼 텍스트체크
    @Test(testName = "PriceGuidepage button text test")
    public void priceGuidePageButtonTextTest() {
        PriceGuidePage priceGuidePage = new PriceGuidePage(driver);
        priceGuidePage.waitForPageLoad();
        SoftAssert softAssert = new SoftAssert();

        String buttonText_Clothes = priceGuidePage.getTextClothesButton();
        softAssert.assertEquals(buttonText_Clothes,PageLabels.PricePage_ClothesButton);

        String buttonText_Bedding = priceGuidePage.getTextBeddingButton();
        softAssert.assertEquals(buttonText_Bedding,PageLabels.PricePage_BeddingButton);

        String buttonText_Shoes = priceGuidePage.getTextShoesButton();
        softAssert.assertEquals(buttonText_Shoes,PageLabels.PricePage_ShoesButton);

        String buttonText_Living = priceGuidePage.getTextLivingButton();
        softAssert.assertEquals(buttonText_Living,PageLabels.PricePage_LivingButton);
    }

    //테이블 상단 라벨
    @Test(testName = "PriceGuiedpage Tabel TopLabel test")
    public void priceGuidePageTableLabelTextTest(){
        PriceGuidePage priceGuidePage = new PriceGuidePage(driver);
        priceGuidePage.waitForPageLoad();
        SoftAssert softAssert = new SoftAssert();

        String tableLabel_No = priceGuidePage.getTextItem_No();
        softAssert.assertEquals(tableLabel_No, PageLabels.PricePage_item_No);

        String tableLabel_Name = priceGuidePage.getTextItem_Name();
        softAssert.assertEquals(tableLabel_Name, PageLabels.PricePage_item_Name);

        String tableLabel_Price = priceGuidePage.getTextItem_Price();
        softAssert.assertEquals(tableLabel_Price, PageLabels.PricePage_item_Price);
    }

    //테이블 값 확인
    @DataProvider
    public Object[][] categoryProvider() {
        return new Object[][] {{PageLabels.PricePage_ClothesButton},
                                {PageLabels.PricePage_BeddingButton},
                                {PageLabels.PricePage_ShoesButton},
                                {PageLabels.PricePage_LivingButton}};
    }

    //의류 카테고리
    @Test(testName = "PriceGuiedpage Tabel Clothes value test", dataProvider = "categoryProvider")
    public void priceGuidePageTableValueTest(String categoryName){
        PriceGuidePage priceGuidePage = new PriceGuidePage(driver);
        priceGuidePage.waitForPageLoad();
        priceGuidePage.clickCategory(categoryName);

        ConfigReader config = new ConfigReader();
        String PriceGuideData_path = config.getProperty("PriceGuideDataPath");
        List<Map<String, String>> allData = ExcelUtil.getTestData(PriceGuideData_path, "Item");

        List<Map<String, String>> clothingData = new ArrayList<>();
        for (Map<String, String> data : allData) {
            if (data.get("Category").equals(categoryName)) {
                clothingData.add(data);
            }
        }

        for (int i = 0; i < clothingData.size(); i++) {
            String expectedName = clothingData.get(i).get("Item_Name");
            String expectedPrice = clothingData.get(i).get("Item_Price");

            Assert.assertEquals(priceGuidePage.getCellData(i, 1), expectedName);
            Assert.assertEquals(priceGuidePage.getCellData(i, 2), expectedPrice);
        }
    }
}
