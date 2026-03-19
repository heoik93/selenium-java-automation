package com.project.functional.useGuide;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.useguide.AreaGuidePage;
import com.project.page.useguide.PriceGuidePage;
import com.project.utils.ScreenshotSoftAssert;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UseGuidePageTabTest extends BaseTest {

    private final ConfigReader config = new ConfigReader();

    @BeforeMethod(alwaysRun = true)
    public void commonSetup() {
        connectToUrl();
    }

    //탭테스트(지역안내페이지 : 가격안내탭)
    @Test(testName = "AreaPage PriceGuideTab Test")
    public void AreaPage_PriceGuideTabTest() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.goToAreaGuidePage();
        homePage.navi.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        AreaGuidePage areaGuidePage = new AreaGuidePage(driver);
        areaGuidePage.waitForPageLoad();
        areaGuidePage.clickPriceGuideTab();

        String currentUrl = areaGuidePage.getCurrentUrl();
        String PageTittle = areaGuidePage.getPageTitle();

        softAssert.assertEquals(currentUrl, config.getProperty("PriceGuidePAgeURL"),"[FAIL]지역안내페이지에서 가격안내탭 클릭시의 URL이 올바르지 않습니다.");
        softAssert.assertEquals(PageTittle, PageLabels.priceGuidePageTitle,"[FAIL]지역안내페이지에서 가격안내탭 클릭시의 페이지타이틀이 올바르지 않습니다.");

        softAssert.assertAll();
    }

    //탭테스트(지역안내페이지 : 지역안내탭)
    @Test(testName = "AreaPage AreaGuideTab Test")
    public void AreaPage_AreaGuideTabTest() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.goToAreaGuidePage();
        homePage.navi.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        AreaGuidePage areaGuidePage = new AreaGuidePage(driver);
        areaGuidePage.waitForPageLoad();
        areaGuidePage.clickAreaGuideTab();

        String currentUrl = areaGuidePage.getCurrentUrl();
        String PageTittle = areaGuidePage.getPageTitle();

        softAssert.assertEquals(currentUrl, config.getProperty("AreaGuidePageURL"),"[FAIL]지역안내페이지에서 지역안내탭 클릭시의 URL이 올바르지 않습니다.");
        softAssert.assertEquals(PageTittle, PageLabels.areaGuidePageTitle,"[FAIL]지역안내페이지에서 지역안내탭 클릭시의 페이지타이틀이 올바르지 않습니다.");

        softAssert.assertAll();
    }

    //탭테스트(가격안내페이지 : 가격안내탭)
    @Test(testName = "PricePage PriceGuideTab Test")
    public void PricePage_PriceGuideTabTest() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.goToPriceGuidePage();
        homePage.navi.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        PriceGuidePage priceGuidePage = new PriceGuidePage(driver);
        priceGuidePage.waitForPageLoad();
        priceGuidePage.clickPriceGuideTab();

        String currentUrl = priceGuidePage.getCurrentUrl();
        String PageTittle = priceGuidePage.getPageTitle();

        softAssert.assertEquals(currentUrl, config.getProperty("PriceGuidePAgeURL"),"[FAIL]가격안내페이지에서 가격안내탭 클릭시의 URL이 올바르지 않습니다.");
        softAssert.assertEquals(PageTittle, PageLabels.priceGuidePageTitle,"[FAIL]가격안내페이지에서 가격안내탭 클릭시의 페이지타이틀이 올바르지 않습니다.");

        softAssert.assertAll();
    }

    //탭테스트(가격안내페이지 : 지역안내탭)
    @Test(testName = "PricePage AreaGuideTab Test")
    public void PricePage_AreaGuideTabTest() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.goToPriceGuidePage();
        homePage.navi.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        PriceGuidePage priceGuidePage = new PriceGuidePage(driver);
        priceGuidePage.waitForPageLoad();
        priceGuidePage.clickAreaGuideTab();

        String currentUrl = priceGuidePage.getCurrentUrl();
        String PageTittle = priceGuidePage.getPageTitle();

        softAssert.assertEquals(currentUrl, config.getProperty("AreaGuidePageURL"),"[FAIL]가격안내페이지에서 지역안내탭 클릭시의 URL이 올바르지 않습니다.");
        softAssert.assertEquals(PageTittle, PageLabels.areaGuidePageTitle,"[FAIL]가격안내페이지에서 지역안내탭 클릭시의 페이지타이틀이 올바르지 않습니다.");

        softAssert.assertAll();
    }
}
