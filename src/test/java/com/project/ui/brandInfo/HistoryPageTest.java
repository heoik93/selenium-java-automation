package com.project.ui.brandInfo;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.brandinfo.HistoryPage;
import com.project.utils.ScreenshotSoftAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HistoryPageTest extends BaseTest {

    @BeforeMethod
    public void goToHistoryPage() {
        connectToUrl();
        HomePage homepage = new HomePage(driver);
        homepage.navi.waitForPageLoad();
        homepage.navi.goToHistoryPage();
    }

    //탭 엑티브체크
    @Test(testName = "History page tab active test")
    public void historyPageTabActiveTest() {
        HistoryPage historypage = new HistoryPage(driver);
        historypage.waitForPageLoad();

        Assert.assertTrue(historypage.activeTabText());
    }

    //탭 문구체크
    @Test(testName = "History page tab text test")
    public void historyPageTabTextTest() {
        HistoryPage historypage = new HistoryPage(driver);
        historypage.waitForPageLoad();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        String comintroTab_Label = historypage.getComintroTabText();
        softAssert.assertEquals(comintroTab_Label, PageLabels.comintroTabLabel);

        String historyTab_Label = historypage.getHistoryTabText();
        softAssert.assertEquals(historyTab_Label, PageLabels.historyTabLabel);

        String startupTab_Label = historypage.getStartupTabText();
        softAssert.assertEquals(startupTab_Label, PageLabels.startupTabLabel);

        softAssert.assertAll();
    }

    //라벨 문구체크
    @Test(testName = "History page label text test")
    public void historyPageLabelTextTest() {
        HistoryPage historypage = new HistoryPage(driver);
        historypage.waitForPageLoad();
        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        String main_Label = historypage.getLabelText(historypage.history_main_Label);
        softAssert.assertEquals(main_Label, PageLabels.historyPageLabel_main);

        String timelineYear_Label = historypage.getLabelText(historypage.timelineYear_Label);
        softAssert.assertEquals(timelineYear_Label, PageLabels.historyPageLabel_timelineYear);

        String timeline_1st_Label = historypage.getLabelText(historypage.timeline_1st_Label);
        softAssert.assertEquals(timeline_1st_Label, PageLabels.historyPageLabel_timeline_1st);
        String timeline_1st_textLabel = historypage.getLabelText(historypage.timeline_1st_textLabel);
        softAssert.assertEquals(timeline_1st_textLabel, PageLabels.historyPageLabel_timeline_1st_text);

        String timeline_2nd_Label = historypage.getLabelText(historypage.timeline_2nd_Label);
        softAssert.assertEquals(timeline_2nd_Label, PageLabels.historyPageLabel_timeline_2nd);
        String timeline_2nd_textLabel = historypage.getLabelText(historypage.timeline_2nd_textLabel);
        softAssert.assertEquals(timeline_2nd_textLabel, PageLabels.historyPageLabel_timeline_2nd_text);

        String timeline_3rd_Label = historypage.getLabelText(historypage.timeline_3rd_Label);
        softAssert.assertEquals(timeline_3rd_Label, PageLabels.historyPageLabel_timeline_3rd);
        String timeline_3rd_textLabel = historypage.getLabelText(historypage.timeline_3rd_textLabel);
        softAssert.assertEquals(timeline_3rd_textLabel, PageLabels.historyPageLabel_timeline_3rd_text);

        String timeline_4th_Label = historypage.getLabelText(historypage.timeline_4th_Label);
        softAssert.assertEquals(timeline_4th_Label, PageLabels.historyPageLabel_timeline_4th);
        String timeline_4th_textLabel = historypage.getLabelText(historypage.timeline_4th_textLabel);
        softAssert.assertEquals(timeline_4th_textLabel, PageLabels.historyPageLabel_timeline_4th_text);

        String timeline_5th_Label = historypage.getLabelText(historypage.timeline_5th_Label);
        softAssert.assertEquals(timeline_5th_Label, PageLabels.historyPageLabel_timeline_5th);
        String timeline_5th_textLabel = historypage.getLabelText(historypage.timeline_5th_textLabel);
        softAssert.assertEquals(timeline_5th_textLabel, PageLabels.historyPageLabel_timeline_5th_text);

        String timeline_6th_Label = historypage.getLabelText(historypage.timeline_6th_Label);
        softAssert.assertEquals(timeline_6th_Label, PageLabels.historyPageLabel_timeline_6th);
        String timeline_6th_textLabel = historypage.getLabelText(historypage.timeline_6th_textLabel);
        softAssert.assertEquals(timeline_6th_textLabel, PageLabels.historyPageLabel_timeline_6th_text);

        String timeline_7th_Label = historypage.getLabelText(historypage.timeline_7th_Label);
        softAssert.assertEquals(timeline_7th_Label, PageLabels.historyPageLabel_timeline_7th);
        String timeline_7th_textLabel = historypage.getLabelText(historypage.timeline_7th_textLabel);
        softAssert.assertEquals(timeline_7th_textLabel, PageLabels.historyPageLabel_timeline_7th_text);

        String timeline_8th_Label = historypage.getLabelText(historypage.timeline_8th_Label);
        softAssert.assertEquals(timeline_8th_Label, PageLabels.historyPageLabel_timeline_8th);
        String timeline_8th_textLabel = historypage.getLabelText(historypage.timeline_8th_textLabel);
        softAssert.assertEquals(timeline_8th_textLabel, PageLabels.historyPageLabel_timeline_8th_text);

        String timeline_9th_Label = historypage.getLabelText(historypage.timeline_9th_Label);
        softAssert.assertEquals(timeline_9th_Label, PageLabels.historyPageLabel_timeline_9th);
        String timeline_9th_textLabel = historypage.getLabelText(historypage.timeline_9th_textLabel);
        softAssert.assertEquals(timeline_9th_textLabel, PageLabels.historyPageLabel_timeline_9th_text);

        String timeline_10th_Label = historypage.getLabelText(historypage.timeline_10th_Label);
        softAssert.assertEquals(timeline_10th_Label, PageLabels.historyPageLabel_timeline_10th);
        String timeline_10th_textLabel = historypage.getLabelText(historypage.timeline_10th_textLabel);
        softAssert.assertEquals(timeline_10th_textLabel, PageLabels.historyPageLabel_timeline_10th_text);

        String timeline_11th_Label = historypage.getLabelText(historypage.timeline_11th_Label);
        softAssert.assertEquals(timeline_11th_Label, PageLabels.historyPageLabel_timeline_11th);
        String timeline_11th_textLabel = historypage.getLabelText(historypage.timeline_11th_textLabel);
        softAssert.assertEquals(timeline_11th_textLabel, PageLabels.historyPageLabel_timeline_11th_text);

        String timeline_12th_Label = historypage.getLabelText(historypage.timeline_12th_Label);
        softAssert.assertEquals(timeline_12th_Label, PageLabels.historyPageLabel_timeline_12th);
        String timeline_12th_textLabel = historypage.getLabelText(historypage.timeline_12th_textLabel);
        softAssert.assertEquals(timeline_12th_textLabel, PageLabels.historyPageLabel_timeline_12th_text);

        softAssert.assertAll();
    }


}
