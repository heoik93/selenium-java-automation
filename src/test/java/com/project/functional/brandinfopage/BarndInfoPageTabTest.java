package com.project.functional.brandinfopage;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.brandinfo.ComintroPage;
import com.project.page.brandinfo.HistoryPage;
import com.project.page.brandinfo.StartupPage;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BarndInfoPageTabTest extends BaseTest {

    private final ConfigReader config = new ConfigReader();

    @BeforeMethod(alwaysRun = true)
    public void commonSetup() {
        connectToUrl();
    }

    @Test(testName = "ComeintroPage Tab Test1")
    public void comintroPageTabTest1() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.goToComintroPage();
        homePage.navi.waitForPageLoad();

        ComintroPage comintropage = new ComintroPage(driver);
        comintropage.waitForPageLoad();
        comintropage.clickTab(comintropage.comintroTab);

        String currentUrl = comintropage.getCurrentUrl();
        String PageTittle = comintropage.getPageTitle();

        Assert.assertEquals(currentUrl, config.getProperty("ComintroPageURL"));
        Assert.assertEquals(PageTittle, PageLabels.comintroTabLabel);
    }

    @Test(testName = "ComeintroPage Tab Test2")
    public void comintroPageTabTest2() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.waitForPageLoad();
        homePage.navi.goToComintroPage();

        ComintroPage comintropage = new ComintroPage(driver);
        comintropage.waitForPageLoad();
        comintropage.clickTab(comintropage.historyTab);

        String currentUrl = comintropage.getCurrentUrl();
        String PageTittle = comintropage.getPageTitle();

        Assert.assertEquals(currentUrl, config.getProperty("HistoryPageURL"));
        Assert.assertEquals(PageTittle, PageLabels.historyTabLabel);
    }

    @Test(testName = "ComeintroPage Tab Test3")
    public void comintroPageTabTest3() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.waitForPageLoad();
        homePage.navi.goToComintroPage();

        ComintroPage comintropage = new ComintroPage(driver);
        comintropage.waitForPageLoad();
        comintropage.clickTab(comintropage.startupTab);

        String currentUrl = comintropage.getCurrentUrl();
        String PageTittle = comintropage.getPageTitle();

        Assert.assertEquals(currentUrl, config.getProperty("StartupPageURL"));
        Assert.assertEquals(PageTittle, PageLabels.startupTabLabel);
    }

    @Test(testName = "HistoryPage Tab Test1")
    public void historyPageTabTest1() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.waitForPageLoad();
        homePage.navi.goToHistoryPage();

        HistoryPage historypage = new HistoryPage(driver);
        historypage.waitForPageLoad();
        historypage.clickTab(historypage.comintroTab);

        String currentUrl = historypage.getCurrentUrl();
        String PageTittle = historypage.getPageTitle();

        Assert.assertEquals(currentUrl, config.getProperty("ComintroPageURL"));
        Assert.assertEquals(PageTittle, PageLabels.comintroTabLabel);
    }

    @Test(testName = "HistoryPage Tab Test2")
    public void historyPageTabTest2() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.waitForPageLoad();
        homePage.navi.goToHistoryPage();

        HistoryPage historypage = new HistoryPage(driver);
        historypage.waitForPageLoad();
        historypage.clickTab(historypage.startupTab);

        String currentUrl = historypage.getCurrentUrl();
        String PageTittle = historypage.getPageTitle();

        Assert.assertEquals(currentUrl, config.getProperty("StartupPageURL"));
        Assert.assertEquals(PageTittle, PageLabels.startupTabLabel);
    }

    @Test(testName = "HistoryPage Tab Test3")
    public void historyPageTabTest3() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.waitForPageLoad();
        homePage.navi.goToHistoryPage();

        HistoryPage historypage = new HistoryPage(driver);
        historypage.waitForPageLoad();
        historypage.clickTab(historypage.historyTab);

        String currentUrl = historypage.getCurrentUrl();
        String PageTittle = historypage.getPageTitle();

        Assert.assertEquals(currentUrl, config.getProperty("HistoryPageURL"));
        Assert.assertEquals(PageTittle, PageLabels.historyTabLabel);
    }

    @Test(testName = "StartupPage Tab Test1")
    public void startupPageTabTest1() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.waitForPageLoad();
        homePage.navi.goToStartupPage();

        StartupPage startuppage = new StartupPage(driver);
        startuppage.waitForPageLoad();
        startuppage.clickTab(startuppage.comintroTab);

        String currentUrl = startuppage.getCurrentUrl();
        String PageTittle = startuppage.getPageTitle();

        Assert.assertEquals(currentUrl, config.getProperty("ComintroPageURL"));
        Assert.assertEquals(PageTittle, PageLabels.comintroTabLabel);
    }

    @Test(testName = "StartupPage Tab Test2")
    public void startupPageTabTest2() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.waitForPageLoad();
        homePage.navi.goToStartupPage();

        StartupPage startuppage = new StartupPage(driver);
        startuppage.waitForPageLoad();
        startuppage.clickTab(startuppage.historyTab);

        String currentUrl = startuppage.getCurrentUrl();
        String PageTittle = startuppage.getPageTitle();

        Assert.assertEquals(currentUrl, config.getProperty("HistoryPageURL"));
        Assert.assertEquals(PageTittle, PageLabels.historyTabLabel);
    }

    @Test(testName = "StartupPage Tab Test3")
    public void startupPageTabTest3() {
        HomePage homePage = new HomePage(driver);
        homePage.navi.waitForPageLoad();
        homePage.navi.goToStartupPage();

        StartupPage startuppage = new StartupPage(driver);
        startuppage.waitForPageLoad();
        startuppage.clickTab(startuppage.startupTab);

        String currentUrl = startuppage.getCurrentUrl();
        String PageTittle = startuppage.getPageTitle();

        Assert.assertEquals(currentUrl, config.getProperty("StartupPageURL"));
        Assert.assertEquals(PageTittle, PageLabels.startupTabLabel);
    }

}