package com.project.functional.useGuide;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.useguide.AreaGuidePage;
import com.project.page.useguide.PriceGuidePage;
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

    @Test(testName = "AreaPage Tab Test1")
    public void AreaPageTabTest1() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.goToAreaGuidePage();
        homePage.navi.waitForPageLoad();

        AreaGuidePage areaGuiedPage = new AreaGuidePage(driver);
        areaGuiedPage.waitForPageLoad();
        areaGuiedPage.clickPriceGuideTab();

        String currentUrl = areaGuiedPage.getCurrentUrl();
        String PageTittle = areaGuiedPage.getPageTitle();

        Assert.assertEquals(currentUrl, config.getProperty("PriceGuidePAgeURL"));
        Assert.assertEquals(PageTittle, PageLabels.priceGuidePageTitle);
    }

    @Test(testName = "AreaPage Tab Test2")
    public void AreaPageTabTest2() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.goToAreaGuidePage();
        homePage.navi.waitForPageLoad();

        AreaGuidePage areaGuiedPage = new AreaGuidePage(driver);
        areaGuiedPage.waitForPageLoad();
        areaGuiedPage.clickAreaGuideTab();

        String currentUrl = areaGuiedPage.getCurrentUrl();
        String PageTittle = areaGuiedPage.getPageTitle();

        Assert.assertEquals(currentUrl, config.getProperty("AreaGuidePageURL"));
        Assert.assertEquals(PageTittle, PageLabels.areaGuidePageTitle);
    }

    @Test(testName = "PricePage Tab Test1")
    public void PricePageTabTest1() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.goToPriceGuidePage();
        homePage.navi.waitForPageLoad();

        PriceGuidePage priceGuiedPage = new PriceGuidePage(driver);
        priceGuiedPage.waitForPageLoad();
        priceGuiedPage.clickPriceGuideTab();

        String currentUrl = priceGuiedPage.getCurrentUrl();
        String PageTittle = priceGuiedPage.getPageTitle();

        Assert.assertEquals(currentUrl, config.getProperty("PriceGuidePAgeURL"));
        Assert.assertEquals(PageTittle, PageLabels.priceGuidePageTitle);
    }

    @Test(testName = "PricePage Tab Test2")
    public void PricePageTabTest2() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.goToPriceGuidePage();
        homePage.navi.waitForPageLoad();

        PriceGuidePage priceGuiedPage = new PriceGuidePage(driver);
        priceGuiedPage.waitForPageLoad();
        priceGuiedPage.clickAreaGuideTab();

        String currentUrl = priceGuiedPage.getCurrentUrl();
        String PageTittle = priceGuiedPage.getPageTitle();

        Assert.assertEquals(currentUrl, config.getProperty("AreaGuidePageURL"));
        Assert.assertEquals(PageTittle, PageLabels.areaGuidePageTitle);
    }
}
