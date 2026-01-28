package com.project.ui;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.useguide.AreaGuidePage.AreaLabel;
import com.project.page.useguide.AreaGuidePage;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AreaGuidePageTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void goToCAreaGuidePage() {
        connectToUrl();
        HomePage homepage = new HomePage(driver);
        homepage.navi.waitForPageLoad();
        homepage.navi.goToAreaGuidePage();
    }
    //탭 액티브체크
    @Test(testName = "AreaGuidepage tab active test")
    public void areaGuidePageTabActiveTest() {
        AreaGuidePage areaGuidePage = new AreaGuidePage(driver);
        areaGuidePage.waitForPageLoad();

        Assert.assertTrue(areaGuidePage.activeTabText());
    }

    //탭 문구체크
    @Test(testName = "AreaGuidepage tab text test")
    public void areaGuidePageTabTextTest() {
        AreaGuidePage areaGuidePage = new AreaGuidePage(driver);
        areaGuidePage.waitForPageLoad();
        SoftAssert softAssert = new SoftAssert();

        String areaTabLabel = areaGuidePage.getAreaGuideTabText();
        softAssert.assertEquals(areaTabLabel, PageLabels.areaTabLabel);

        String priceTabLabel = areaGuidePage.getPriceGuideTabText();
        softAssert.assertEquals(priceTabLabel, PageLabels.priceTabLabel);

        softAssert.assertAll();
    }

    //라벨 문구체크
    @Test(testName = "Area page label text test")
    public void areaPageLabelTextTest() {
        AreaGuidePage areaGuidePage = new AreaGuidePage(driver);
        areaGuidePage.waitForPageLoad();
        SoftAssert softAssert = new SoftAssert();

        String areaTitle = areaGuidePage.getLabel(AreaLabel.SERVICE_TITLE);
        softAssert.assertEquals(areaTitle, PageLabels.areaPage_AreaTitle);

        String areaLabel_1 = areaGuidePage.getLabel(AreaLabel.SERVICE_L1);
        softAssert.assertEquals(areaLabel_1, PageLabels.areaPage_AreaLabel_1);

        String areaLabel_2 = areaGuidePage.getLabel(AreaLabel.SERVICE_L2);
        softAssert.assertEquals(areaLabel_2, PageLabels.areaPage_AreaLabel_2);

        String areaLabel_3 = areaGuidePage.getLabel(AreaLabel.SERVICE_L3);
        softAssert.assertEquals(areaLabel_3, PageLabels.areaPage_AreaLabel_3);

        String areaLabel_4 = areaGuidePage.getLabel(AreaLabel.SERVICE_L4);
        softAssert.assertEquals(areaLabel_4, PageLabels.areaPage_AreaLabel_4);

        String nonAreaTitle = areaGuidePage.getLabel(AreaLabel.NON_SERVICE_TITLE);
        softAssert.assertEquals(nonAreaTitle, PageLabels.areaPage_noAreaTitle);

        String nonAreaText = areaGuidePage.getLabel(AreaLabel.NON_SERVICE_TEXT);
        softAssert.assertEquals(nonAreaText, PageLabels.areaPage_noAreaText);

        String nonAreaLabel_1 = areaGuidePage.getLabel(AreaLabel.NON_SERVICE_L1);
        softAssert.assertEquals(nonAreaLabel_1, PageLabels.areaPage_noAreaLabel_1);

        String nonAreaLabel_2 = areaGuidePage.getLabel(AreaLabel.NON_SERVICE_L2);
        softAssert.assertEquals(nonAreaLabel_2, PageLabels.areaPage_noAreaLabel_2);

        String nonAreaLabel_3 = areaGuidePage.getLabel(AreaLabel.NON_SERVICE_L3);
        softAssert.assertEquals(nonAreaLabel_3, PageLabels.areaPage_noAreaLabel_3);

        String nonAreaLabel_4 = areaGuidePage.getLabel(AreaLabel.NON_SERVICE_L4);
        softAssert.assertEquals(nonAreaLabel_4, PageLabels.areaPage_noAreaLabel_4);

        String nonAreaLabel_5 = areaGuidePage.getLabel(AreaLabel.NON_SERVICE_L5);
        softAssert.assertEquals(nonAreaLabel_5, PageLabels.areaPage_noAreaLabel_5);

        String nonAreaLabel_6 = areaGuidePage.getLabel(AreaLabel.NON_SERVICE_L6);
        softAssert.assertEquals(nonAreaLabel_6, PageLabels.areaPage_noAreaLabel_6);

        String nonAreaLabel_7 = areaGuidePage.getLabel(AreaLabel.NON_SERVICE_L7);
        softAssert.assertEquals(nonAreaLabel_7, PageLabels.areaPage_noAreaLabel_7);

        String nonAreaLabel_8 = areaGuidePage.getLabel(AreaLabel.NON_SERVICE_L8);
        softAssert.assertEquals(nonAreaLabel_8, PageLabels.areaPage_noAreaLabel_8);

        String operTimeTitle = areaGuidePage.getLabel(AreaLabel.OPER_TIME_TITLE);
        softAssert.assertEquals(operTimeTitle, PageLabels.areaPage_operTimeTitle);

        String operTimeLabel_1 = areaGuidePage.getLabel(AreaLabel.OPER_L1);
        softAssert.assertEquals(operTimeLabel_1, PageLabels.areaPage_operTimeLabel_1);

        String operTimeLabel_2 = areaGuidePage.getLabel(AreaLabel.OPER_L2);
        softAssert.assertEquals(operTimeLabel_2, PageLabels.areaPage_operTimeLabel_2);

        String operTimeLabel_3 = areaGuidePage.getLabel(AreaLabel.OPER_L3);
        softAssert.assertEquals(operTimeLabel_3, PageLabels.areaPage_operTimeLabel_3);

        String operTimeLabel_4 = areaGuidePage.getLabel(AreaLabel.OPER_L4);
        softAssert.assertEquals(operTimeLabel_4, PageLabels.areaPage_operTimeLabel_4);

        softAssert.assertAll();
    }

    //이미지 src확인
    @Test(testName = "Area page image src test")
    public void areaPageImgSrcTest() {
        AreaGuidePage areaGuidePage = new AreaGuidePage(driver);
        areaGuidePage.waitForPageLoad();
        ConfigReader config = new ConfigReader();

        String imgSrc = areaGuidePage.getSrcAreaImage();
        Assert.assertEquals(imgSrc, config.getProperty("areaIMG"));
    }

    }

