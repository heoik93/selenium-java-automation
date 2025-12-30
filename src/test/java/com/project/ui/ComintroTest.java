package com.project.ui;

import com.project.base.BaseTest;
import com.project.page.HomePage;
import com.project.page.brandinfo.ComintroPage;
import com.project.constants.PageLabels;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ComintroTest extends BaseTest {

    @BeforeMethod
    public void goToComintroPage() {
        connectToUrl();
        HomePage homepage = new HomePage(driver);
        homepage.navi.waitForPageLoad();
        homepage.navi.goToComintroPage();
    }
    //탭 엑티브체크
    @Test(testName = "Comitro page tab active test")
    public void comintroPageTabActiveTest() {
        ComintroPage comintropage = new ComintroPage(driver);
        comintropage.waitForPageLoad();

        Assert.assertTrue(comintropage.activeTabText());
    }



    //탭 문구체크
    @Test(testName = "Comitro page tab text test")
    public void comintroPageTabTextTest() {
        ComintroPage comintropage = new ComintroPage(driver);
        comintropage.waitForPageLoad();
        SoftAssert softAssert = new SoftAssert();

        String comintroTab_Label = comintropage.getComintroTabText();
        softAssert.assertEquals(comintroTab_Label, PageLabels.comintroTabLabel);

        String historyTab_Label = comintropage.getHistoryTabText();
        softAssert.assertEquals(historyTab_Label, PageLabels.historyTabLabel);

        String startupTab_Label = comintropage.getStartupTabText();
        softAssert.assertEquals(startupTab_Label, PageLabels.startupTabLabel);

        softAssert.assertAll();
    }


    //라벨 문구체크
    @Test(testName = "Comintro page label text test")
    public void comintroPageLabelTextTest() {
        ComintroPage comintropage = new ComintroPage(driver);
        comintropage.waitForPageLoad();
        SoftAssert softAssert = new SoftAssert();

        String h1_Label = comintropage.getComintro_h1_LabelText();
        softAssert.assertEquals(h1_Label, PageLabels.comintroPageLabel_h1);

        String h2_Label = comintropage.getComintro_h2_LabelText();
        softAssert.assertEquals(h2_Label, PageLabels.comintroPageLabel_h2);

        String h2p1_Label = comintropage.getComintro_h2p1_LabelText();
        softAssert.assertEquals(h2p1_Label, PageLabels.comintroPageLabel_h2p1);

        String h2p2_Label = comintropage.getComintro_h2p2_LabelText();
        softAssert.assertEquals(h2p2_Label, PageLabels.comintroPageLabel_h2p2);

        String h3_1Label = comintropage.getComintro_1h3_LabelText();
        softAssert.assertEquals(h3_1Label, PageLabels.comintroPageLabel_1h3);

        String h3_1pLabel = comintropage.getComintro_1h3p_LabelText();
        softAssert.assertEquals(h3_1pLabel, PageLabels.comintroPageLabel_1h3p);

        String h3_2Label = comintropage.getComintro_2h3_LabelText();
        softAssert.assertEquals(h3_2Label, PageLabels.comintroPageLabel_2h3);

        String h3_2pLabel = comintropage.getComintro_2h3p_LabelText();
        softAssert.assertEquals(h3_2pLabel, PageLabels.comintroPageLabel_2h3p);

        softAssert.assertAll();
    }
}
