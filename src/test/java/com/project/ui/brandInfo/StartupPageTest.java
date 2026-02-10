package com.project.ui.brandInfo;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.brandinfo.StartupPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class StartupPageTest extends BaseTest {

    @BeforeMethod
    public void goToStartupPage() {
        connectToUrl();
        HomePage homepage = new HomePage(driver);
        homepage.navi.waitForPageLoad();
        homepage.navi.goToStartupPage();
    }

    //탭 엑티브체크
    @Test(testName = "startup page tab active test")
    public void startupPageTabActiveTest() {
        StartupPage startuppage = new StartupPage(driver);
        startuppage.waitForPageLoad();

        Assert.assertTrue(startuppage.activeTabText());
    }

    //탭 문구체크
    @Test(testName = "History page tab text test")
    public void historyPageTabTextTest() {
        StartupPage startuppage = new StartupPage(driver);
        startuppage.waitForPageLoad();
        SoftAssert softAssert = new SoftAssert();

        String comintroTab_Label = startuppage.getComintroTabText();
        softAssert.assertEquals(comintroTab_Label, PageLabels.comintroTabLabel);

        String historyTab_Label = startuppage.getHistoryTabText();
        softAssert.assertEquals(historyTab_Label, PageLabels.historyTabLabel);

        String startupTab_Label = startuppage.getStartupTabText();
        softAssert.assertEquals(startupTab_Label, PageLabels.startupTabLabel);

        softAssert.assertAll();
    }

    //라벨 문구체크
    @Test(testName = "Startup page label text test", retryAnalyzer = com.project.utils.Retry.class)
    public void startupPageLabelTextTest() {
        StartupPage startuppage = new StartupPage(driver);
        startuppage.waitForPageLoad();
        SoftAssert softAssert = new SoftAssert();

        String main_Label = startuppage.getLabelText(startuppage.startup_main_Label);
        softAssert.assertEquals(main_Label, PageLabels.startupPage_mainLabel);

        String table_Title1_Label = startuppage.getLabelText(startuppage.startup_table_Title1_Label);
        softAssert.assertEquals(table_Title1_Label, PageLabels.startupPage_table_Title1_Label);

        String table_Title2_Label = startuppage.getLabelText(startuppage.startup_table_Title2_Label);
        softAssert.assertEquals(table_Title2_Label, PageLabels.startupPage_table_Title2_Label);

        String table_Title3_Label = startuppage.getLabelText(startuppage.startup_table_Title3_Label);
        softAssert.assertEquals(table_Title3_Label, PageLabels.startupPage_table_Title3_Label);

        String row1col1_Label = startuppage.getLabelText(startuppage.startup_table_row1col1_Label);
        softAssert.assertEquals(row1col1_Label, PageLabels.startupPage_table_row1col1_Label);
        String row1col2_Label = startuppage.getLabelText(startuppage.startup_table_row1col2_Label);
        softAssert.assertEquals(row1col2_Label, PageLabels.startupPage_table_row1col2_Label);
        String row1col3_Label = startuppage.getLabelText(startuppage.startup_table_row1col3_Label);
        softAssert.assertEquals(row1col3_Label, PageLabels.startupPage_table_row1col3_Label);

        String row2col1_Label = startuppage.getLabelText(startuppage.startup_table_row2col1_Label);
        softAssert.assertEquals(row2col1_Label, PageLabels.startupPage_table_row2col1_Label);
        String row2col2_Label = startuppage.getLabelText(startuppage.startup_table_row2col2_Label);
        softAssert.assertEquals(row2col2_Label, PageLabels.startupPage_table_row2col2_Label);

        String row3col1_Label = startuppage.getLabelText(startuppage.startup_table_row3col1_Label);
        softAssert.assertEquals(row3col1_Label, PageLabels.startupPage_table_row3col1_Label);
        String row3col2_Label = startuppage.getLabelText(startuppage.startup_table_row3col2_Label);
        softAssert.assertEquals(row3col2_Label, PageLabels.startupPage_table_row3col2_Label);
        String row3col3_Label = startuppage.getLabelText(startuppage.startup_table_row3col3_Label);
        softAssert.assertEquals(row3col3_Label, PageLabels.startupPage_table_row3col3_Label);

        String row4col1_Label = startuppage.getLabelText(startuppage.startup_table_row4col1_Label);
        softAssert.assertEquals(row4col1_Label, PageLabels.startupPage_table_row4col1_Label);
        String row4col2_Label = startuppage.getLabelText(startuppage.startup_table_row4col2_Label);
        softAssert.assertEquals(row4col2_Label, PageLabels.startupPage_table_row4col2_Label);

        String NoteLabel = startuppage.getLabelText(startuppage.startup_NoteLabel);
        softAssert.assertEquals(NoteLabel, PageLabels.startupPage_noteLabel);

        String step1_Label = startuppage.getLabelText(startuppage.startup_step1_Label);
        softAssert.assertEquals(step1_Label, PageLabels.startupPage_step1_Label);
        String step2_Label = startuppage.getLabelText(startuppage.startup_step2_Label);
        softAssert.assertEquals(step2_Label, PageLabels.startupPage_step2_Label);
        String step3_Label = startuppage.getLabelText(startuppage.startup_step3_Label);
        softAssert.assertEquals(step3_Label, PageLabels.startupPage_step3_Label);
        String step4_Label = startuppage.getLabelText(startuppage.startup_step4_Label);
        softAssert.assertEquals(step4_Label, PageLabels.startupPage_step4_Label);
        String step5_Label = startuppage.getLabelText(startuppage.startup_step5_Label);
        softAssert.assertEquals(step5_Label, PageLabels.startupPage_step5_Label);
        String step6_Label = startuppage.getLabelText(startuppage.startup_step6_Label);
        softAssert.assertEquals(step6_Label, PageLabels.startupPage_step6_Label);

        softAssert.assertAll();
    }

}
