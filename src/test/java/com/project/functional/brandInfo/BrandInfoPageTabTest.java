package com.project.functional.brandInfo;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.brandinfo.ComintroPage;
import com.project.page.brandinfo.HistoryPage;
import com.project.page.brandinfo.StartupPage;
import com.project.utils.ScreenshotSoftAssert;
import config.ConfigReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BrandInfoPageTabTest extends BaseTest {

    private final ConfigReader config = new ConfigReader();

    @BeforeMethod(alwaysRun = true)
    public void commonSetup() {
        connectToUrl();
    }

    //탭테스트 (회사소개페이지 : 회사소개탭)
    @Test(testName = "ComeintroPage ComintroTabTest")
    public void comintroPage_ComintroTabTest() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.goToComintroPage();
        homePage.navi.waitForPageLoad();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        ComintroPage comintropage = new ComintroPage(driver);
        comintropage.waitForPageLoad();
        comintropage.clickTab(comintropage.comintroTab);

        String currentUrl = comintropage.getCurrentUrl();
        String PageTittle = comintropage.getPageTitle();

        softAssert.assertEquals(currentUrl, config.getProperty("ComintroPageURL"),"[FAIL]회사소개페이지에서 회사소개탭 클릭후의 URL이 올바르지 않습니다.");
        softAssert.assertEquals(PageTittle, PageLabels.comintroTabLabel,"[FAIL]회사소개페이지에서 회사소개탭 클릭후의 페이지타이틀이 올바르지 않습니다.");
        softAssert.assertAll();
    }

    //탭테스트 (회사소개페이지 : 연혁탭)
    @Test(testName = "ComeintroPage HistoryTabTest")
    public void comintroPage_HistoryTabTest() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.waitForPageLoad();
        homePage.navi.goToComintroPage();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        ComintroPage comintropage = new ComintroPage(driver);
        comintropage.waitForPageLoad();
        comintropage.clickTab(comintropage.historyTab);

        String currentUrl = comintropage.getCurrentUrl();
        String PageTittle = comintropage.getPageTitle();

        softAssert.assertEquals(currentUrl, config.getProperty("HistoryPageURL"),"[FAIL]회사소개페이지에서 연혁탭 클릭후의 URL이 올바르지 않습니다.");
        softAssert.assertEquals(PageTittle, PageLabels.historyTabLabel,"[FAIL]회사소개페이지에서 연혁탭 클릭후의 페이지타이틀이 올바르지 않습니다.");
        softAssert.assertAll();
    }

    //탭테스트 (회사소개페이지 : 창업안내탭)
    @Test(testName = "ComeintroPage StartupTabTest")
    public void comintroPage_StartupTabTest() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.waitForPageLoad();
        homePage.navi.goToComintroPage();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        ComintroPage comintropage = new ComintroPage(driver);
        comintropage.waitForPageLoad();
        comintropage.clickTab(comintropage.startupTab);

        String currentUrl = comintropage.getCurrentUrl();
        String PageTittle = comintropage.getPageTitle();

        softAssert.assertEquals(currentUrl, config.getProperty("StartupPageURL"),"[FAIL]회사소개페이지에서 창업안내탭 클릭후의 URL이 올바르지 않습니다.");
        softAssert.assertEquals(PageTittle, PageLabels.startupTabLabel,"[FAIL]회사소개페이지에서 창업안내탭 클릭후의 페이지타이틀이 올바르지 않습니다.");
        softAssert.assertAll();
    }

    //탭테스트 (연혁페이지 : 회사소개탭)
    @Test(testName = "HistoryPage ComintroTabTest")
    public void historyPage_ComintroTabTest() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.waitForPageLoad();
        homePage.navi.goToHistoryPage();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        HistoryPage historypage = new HistoryPage(driver);
        historypage.waitForPageLoad();
        historypage.clickTab(historypage.comintroTab);

        String currentUrl = historypage.getCurrentUrl();
        String PageTittle = historypage.getPageTitle();

        softAssert.assertEquals(currentUrl, config.getProperty("ComintroPageURL"),"[FAIL]연혁페이지에서 회사소개탭 클릭후의 URL이 올바르지 않습니다.");
        softAssert.assertEquals(PageTittle, PageLabels.comintroTabLabel,"[FAIL]연혁페이지에서 회사소개탭 클릭후의 페이지타이틀이 올바르지 않습니다.");
        softAssert.assertAll();
    }

    //탭테스트 (연혁페이지 : 창업안내탭)
    @Test(testName = "HistoryPage HistoryTabTest")
    public void historyPage_HistoryTabTest() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.waitForPageLoad();
        homePage.navi.goToHistoryPage();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        HistoryPage historypage = new HistoryPage(driver);
        historypage.waitForPageLoad();
        historypage.clickTab(historypage.startupTab);

        String currentUrl = historypage.getCurrentUrl();
        String PageTittle = historypage.getPageTitle();

        softAssert.assertEquals(currentUrl, config.getProperty("StartupPageURL"),"[FAIL]연혁페이지에서 창업안내탭 클릭후의 URL이 올바르지 않습니다.");
        softAssert.assertEquals(PageTittle, PageLabels.startupTabLabel,"[FAIL]연혁페이지에서 창업안내탭 클릭후의 페이지타이틀이 올바르지 않습니다.");
        softAssert.assertAll();
    }

    //탭테스트 (연혁페이지 : 연혁탭)
    @Test(testName = "HistoryPage StartupTabTest")
    public void historyPage_StartupTabTest() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.waitForPageLoad();
        homePage.navi.goToHistoryPage();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        HistoryPage historypage = new HistoryPage(driver);
        historypage.waitForPageLoad();
        historypage.clickTab(historypage.historyTab);

        String currentUrl = historypage.getCurrentUrl();
        String PageTittle = historypage.getPageTitle();

        softAssert.assertEquals(currentUrl, config.getProperty("HistoryPageURL"),"[FAIL]연혁페이지에서 연혁탭 클릭후의 URL이 올바르지 않습니다.");
        softAssert.assertEquals(PageTittle, PageLabels.historyTabLabel,"[FAIL]연혁페이지에서 연혁탭 클릭후의 페이지타이틀이 올바르지 않습니다.");
        softAssert.assertAll();
    }

    //탭테스트 (창업안내페이지 : 회사소개탭)
    @Test(testName = "StartupPage ComintroTabTest")
    public void startupPage_ComintroTabTest() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.waitForPageLoad();
        homePage.navi.goToStartupPage();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        StartupPage startuppage = new StartupPage(driver);
        startuppage.waitForPageLoad();
        startuppage.clickTab(startuppage.comintroTab);

        String currentUrl = startuppage.getCurrentUrl();
        String PageTittle = startuppage.getPageTitle();

        softAssert.assertEquals(currentUrl, config.getProperty("ComintroPageURL"),"[FAIL]창업안내페이지 회사소개탭 클릭후의 URL이 올바르지 않습니다.");
        softAssert.assertEquals(PageTittle, PageLabels.comintroTabLabel,"[FAIL]창업안내페이지 회사소개탭 클릭후의 페이지타이틀이 올바르지 않습니다.");
        softAssert.assertAll();
    }

    //탭테스트 (창업안내페이지 : 연혁탭)
    @Test(testName = "StartupPage HistoryTabTest")
    public void startupPage_HistoryTabTest() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.waitForPageLoad();
        homePage.navi.goToStartupPage();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        StartupPage startuppage = new StartupPage(driver);
        startuppage.waitForPageLoad();
        startuppage.clickTab(startuppage.historyTab);

        String currentUrl = startuppage.getCurrentUrl();
        String PageTittle = startuppage.getPageTitle();

        softAssert.assertEquals(currentUrl, config.getProperty("HistoryPageURL"),"[FAIL]창업안내페이지 연혁탭 클릭후의 URL이 올바르지 않습니다.");
        softAssert.assertEquals(PageTittle, PageLabels.historyTabLabel,"[FAIL]창업안내페이지 연혁탭 클릭후의 페이지타이틀이 올바르지 않습니다.");
        softAssert.assertAll();
    }

    //탭테스트 (창업안내페이지 : 창업안내탭)
    @Test(testName = "StartupPage StartupTabTest")
    public void startupPage_StartupTabTest() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.waitForPageLoad();
        homePage.navi.goToStartupPage();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        StartupPage startuppage = new StartupPage(driver);
        startuppage.waitForPageLoad();
        startuppage.clickTab(startuppage.startupTab);

        String currentUrl = startuppage.getCurrentUrl();
        String PageTittle = startuppage.getPageTitle();

        softAssert.assertEquals(currentUrl, config.getProperty("StartupPageURL"),"[FAIL]창업안내페이지 창업안내탭 클릭후의 URL이 올바르지 않습니다.");
        softAssert.assertEquals(PageTittle, PageLabels.startupTabLabel,"[FAIL]창업안내페이지 창업안내탭 클릭후의 페이지타이틀이 올바르지 않습니다.");
        softAssert.assertAll();
    }

}