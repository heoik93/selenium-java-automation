package com.project.ui.mypage;

import com.project.base.BaseTest;
import com.project.constants.PageLabels;
import com.project.page.HomePage;
import com.project.page.myinfo.MyinfoPage;
import com.project.utils.ScreenshotSoftAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyinfoPageTest extends BaseTest {

    @BeforeMethod
    public void setupLogin() {
        loginAsDefaultUser();
        HomePage afterLogin = new HomePage(driver);
        afterLogin.navi.waitForPageLoad();
        afterLogin.navi.goToMyinfoPage();
    }

    @Test(testName = "MyinfoPage LabelText Test")
    public void myinfoPage_LabelTextTest() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.waitForPageLoad();

        ScreenshotSoftAssert softAssert = new ScreenshotSoftAssert(driver);

        String myinfoTabText = myinfoPage.getLabel(MyinfoPage.MyinfoPageLabel.MYINFOTAB);
        softAssert.assertEquals(myinfoTabText, PageLabels.myinfoPage_myinfoTab);

        String useHistoryTabText = myinfoPage.getLabel(MyinfoPage.MyinfoPageLabel.USEHISTORYTAB);
        softAssert.assertEquals(useHistoryTabText, PageLabels.myinfoPage_useHistoryTab);

        String profileLabelText = myinfoPage.getLabel(MyinfoPage.MyinfoPageLabel.PROFILELABEL);
        softAssert.assertEquals(profileLabelText, PageLabels.myinfoPage_profileLabel);

        String userIdLabelText = myinfoPage.getLabel(MyinfoPage.MyinfoPageLabel.USERIDLABEL);
        softAssert.assertEquals(userIdLabelText, PageLabels.myinfoPage_userIdLabel);

        String addressLabelText = myinfoPage.getLabel(MyinfoPage.MyinfoPageLabel.ADDRESSLABEL);
        softAssert.assertEquals(addressLabelText, PageLabels.myinfoPage_addressLabel);

        String emailLabelText = myinfoPage.getLabel(MyinfoPage.MyinfoPageLabel.EMAILLABEL);
        softAssert.assertEquals(emailLabelText, PageLabels.myinfoPage_emailLabel);

        String phoneLabelText = myinfoPage.getLabel(MyinfoPage.MyinfoPageLabel.PHONELABEL);
        softAssert.assertEquals(phoneLabelText, PageLabels.myinfoPage_phoneLabel);

        String signupDateLabelText = myinfoPage.getLabel(MyinfoPage.MyinfoPageLabel.SIGNUDATEPLABEL);
        softAssert.assertEquals(signupDateLabelText, PageLabels.myinfoPage_signupDateLabel);

        String profileTextLabelText = myinfoPage.getLabel(MyinfoPage.MyinfoPageLabel.PROFILETEXTLABEL);
        softAssert.assertEquals(profileTextLabelText, PageLabels.myinfoPage_profileTextLabel);

        String modifyButtonText = myinfoPage.getLabel(MyinfoPage.MyinfoPageLabel.MODIFYBUTTON);
        softAssert.assertEquals(modifyButtonText, PageLabels.myinfoPage_modifyButton);

        String changePasswordButtonText = myinfoPage.getLabel(MyinfoPage.MyinfoPageLabel.CHANGEPASSWORDBUTTON);
        softAssert.assertEquals(changePasswordButtonText, PageLabels.myinfoPage_changePasswordButton);

        String withdrawButtonText = myinfoPage.getLabel(MyinfoPage.MyinfoPageLabel.WITHDRAWBUTTON);
        softAssert.assertEquals(withdrawButtonText, PageLabels.myinfoPage_withdrawButton);

        softAssert.assertAll();
    }

    @AfterMethod
    private void Logout() {
        MyinfoPage myinfoPage = new MyinfoPage(driver);
        myinfoPage.navi.clickLogoutLink();
    }

}
