package com.project.page.useguide;

import com.project.constants.PageLabels;
import com.project.page.BasePage;
import com.project.page.NavigationBar;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PriceGuidePage extends BasePage {

    public NavigationBar navi;

    public PriceGuidePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        this.navi = new NavigationBar(driver);
    }

    @FindBy(css = ".active")
    private WebElement priceGuideTab;

    @FindBy(css = "li[class='subNavItem'] a")
    private WebElement areaGuideTab;

    @FindBy(css = "#clothes")
    private WebElement category_ClothesButton;

    @FindBy(css = "#bedding")
    private WebElement category_BeddingButton;

    @FindBy(css = "#shoes")
    private WebElement category_ShoesButton;

    @FindBy(css = "#living")
    private WebElement category_LivingButton;

    @FindBy(css = "th:nth-child(1)")
    private WebElement item_No_Text;

    @FindBy(css = "th:nth-child(2)")
    private WebElement item_Name_Text;

    @FindBy(css = "th:nth-child(3)")
    private WebElement item_Price_Text;

    @FindBy(css = "tbody tr")
    private List<WebElement> priceTableRows;

    public String getCellData(int rowIndex, int colIndex) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody tr")));

        if (priceTableRows.isEmpty()) {
            throw new RuntimeException("테이블 행을 찾을 수 없습니다! 셀렉터를 확인하거나 로딩을 기다려주세요.");
        }

        WebElement row = priceTableRows.get(rowIndex);
        List<WebElement> cells = row.findElements(By.tagName("td"));
        return cells.get(colIndex).getText();
    }

    public boolean activeTabText(){
        return isTabActive(priceGuideTab);
    }

    public void clickAreaGuideTab() {
        click(areaGuideTab);
    }

    public void clickPriceGuideTab(){
        click(priceGuideTab);
    }

    public String getTextClothesButton(){
        return getText(category_ClothesButton);
    }

    public String getTextBeddingButton(){
        return getText(category_BeddingButton);
    }

    public String getTextShoesButton(){
        return getText(category_ShoesButton);
    }

    public String getTextLivingButton(){
        return getText(category_LivingButton);
    }

    public String getTextItem_No(){
        return getText(item_No_Text);
    }

    public String getTextItem_Name(){
        return getText(item_Name_Text);
    }

    public String getTextItem_Price(){
        return getText(item_Price_Text);
    }

    public void clickCategory(String categoryName){
        Map<String, WebElement> categoryMap = new HashMap<>();
        categoryMap.put(PageLabels.PricePage_ClothesButton, category_ClothesButton);
        categoryMap.put(PageLabels.PricePage_BeddingButton, category_BeddingButton);
        categoryMap.put(PageLabels.PricePage_ShoesButton, category_ShoesButton);
        categoryMap.put(PageLabels.PricePage_LivingButton, category_LivingButton);

        WebElement targetBtn = categoryMap.get(categoryName);

        if (targetBtn != null) {
            WebElement oldData = driver.findElement(By.cssSelector("tbody tr:nth-child(1)"));

            this.click(targetBtn);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            try {
                wait.until(ExpectedConditions.stalenessOf(oldData));
            } catch (TimeoutException e) {
                System.out.println("이미 데이터가 갱신되었거나 변경 사항이 없습니다.");
            }
        } else {
            throw new RuntimeException("카테고리 이름을 확인해주세요: " + categoryName);
        }
    }
}
