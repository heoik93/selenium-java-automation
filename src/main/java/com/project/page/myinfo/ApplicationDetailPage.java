package com.project.page.myinfo;

import com.project.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ApplicationDetailPage extends BasePage {

    public ApplicationDetailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    //추후 내용확인후 작성예정
}

