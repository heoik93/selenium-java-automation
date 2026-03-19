package com.project.page.booking;

import com.project.page.BasePage;
import com.project.page.NavigationBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookingInfoPage extends BasePage {
    public NavigationBar navi;

    public BookingInfoPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        this.navi = new NavigationBar(driver);
    }

    @FindBy(css = "li[class='subNavItem'] a")
    private WebElement bookingPageTitleText;

    @FindBy(css = "a[href='/Reserve/Select?category=clothes']")
    private WebElement clothes_SelectBox;

    @FindBy(css = "a[href='/Reserve/Select?category=bedding']")
    private WebElement bedding_SelectBox;

    @FindBy(css = "a[href='/Reserve/Select?category=shoes']")
    private WebElement shoes_SelectBox;

    @FindBy(css = "a[href='/Reserve/Select?category=living']")
    private WebElement living_SelectBox;

    @FindBy(xpath = "(//p[@class='sm_text'])[1]")
    private WebElement clothes_TextBox;

    @FindBy(xpath = "(//p[@class='sm_text'])[2]")
    private WebElement bedding_TextBox;

    @FindBy(xpath = "(//p[@class='sm_text'])[3]")
    private WebElement shoes_TextBox;

    @FindBy(xpath = "(//p[@class='sm_text'])[4]")
    private WebElement living_TextBox;

    @FindBy(css = "body > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > p:nth-child(2)")
    private WebElement clothes_TitleText;

    @FindBy(css = "body > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > a:nth-child(1) > p:nth-child(2)")
    private WebElement bedding_TitleText;

    @FindBy(css = "body > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > a:nth-child(1) > p:nth-child(2)")
    private WebElement shoes_TitleText;

    @FindBy(css = "body > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > a:nth-child(1) > p:nth-child(2)")
    private WebElement living_TitleText;

    @FindBy(xpath = "//img[@alt='생활빨래']")
    private WebElement clothes_Img;

    @FindBy(xpath = "//img[@alt='침구류']")
    private WebElement bedding_Img;

    @FindBy(xpath = "//img[@alt='운동화']")
    private WebElement shoes_Img;

    @FindBy(xpath = "//img[@alt='리빙']")
    private WebElement living_Img;

    public void clickClothesBox(){  click(clothes_SelectBox); }
    public void clickBeddingBox(){  click(bedding_SelectBox); }
    public void clickShoesBox(){  click(shoes_SelectBox); }
    public void clickLivingBox(){  click(living_SelectBox); }

    public String getBookingPageTitleText(){  return getText(bookingPageTitleText); }
    public String getClothesText(){  return getText(clothes_TextBox); }
    public String getBeddingText(){  return getText(bedding_TextBox); }
    public String getShoesText(){  return getText(shoes_TextBox); }
    public String getLivingText(){  return getText(living_TextBox); }

    public String getClothesTitleText(){  return getText(clothes_TitleText); }
    public String getBeddingTitleText(){  return getText(bedding_TitleText); }
    public String getShoesTitleText(){  return getText(shoes_TitleText); }
    public String getLivingTitleText(){  return getText(living_TitleText); }

    public String getClothesImgSrc(){  return clothes_Img.getAttribute("src"); }
    public String getBeddingImgSrc(){  return bedding_Img.getAttribute("src"); }
    public String getShoesImgSrc(){  return shoes_Img.getAttribute("src"); }
    public String getLivingImgSrc(){  return living_Img.getAttribute("src"); }

    public void clickCategoryBox(String category) {
        switch (category) {
            case "의류":   clickClothesBox(); break;
            case "침구":   clickBeddingBox(); break;
            case "신발":   clickShoesBox(); break;
            case "리빙":   clickLivingBox(); break;
            default: throw new IllegalArgumentException("정의되지 않은 카테고리: " + category);
        }
    }


}


