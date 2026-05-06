package com.project.api.functional;

import com.project.api.base.BaseApiTest;
import com.project.utils.ExcelUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static io.restassured.config.RedirectConfig.redirectConfig;
import static org.hamcrest.Matchers.containsString;
public class BookingApiTest extends BaseApiTest {

    @Test(description = "카테고리 선택부터 결제확인까지 시나리오 검증")
    public void bookingApiTest() {
        Response loginResponse = given()
                .baseUri(config.getUrl())
                .contentType(ContentType.URLENC)
                .formParam("id", config.getProperty("username"))
                .formParam("pwd", config.getProperty("password"))
                .config(RestAssured.config().redirect(redirectConfig().followRedirects(false))) // 302 확인용
                .when()
                .post("/LoginInfo/LoginPost");

        loginResponse.then().statusCode(302);
        String sessionId = loginResponse.getCookie("JSESSIONID");
        System.out.println("[STEP 1] 로그인 성공, 세션 ID: " + sessionId);

        String location = loginResponse.getHeader("Location");
        given()
                .baseUri(config.getUrl())
                .cookie("JSESSIONID", sessionId)
                .when()
                .get(location)
                .then()
                .statusCode(200);
        System.out.println("[STEP 2] 세션 활성화 완료");

        //랜덤카테고리 및 날짜생성
        List<String> categoryNameList = Arrays.asList("의류", "침구", "신발", "리빙");
        String tomorrowDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 00:00";
        Random random = new Random();
        String categoryName = categoryNameList.get(random.nextInt(categoryNameList.size()));
        String category = "";
        switch (categoryName) {
            case "의류": category = "clothing"; break;
            case "침구": category = "bedding"; break;
            case "신발": category = "shoes"; break;
            case "리빙": category = "living"; break;
        }


        given()
                .baseUri(config.getUrl())
                .config(RestAssured.config().redirect(redirectConfig().followRedirects(true)))
                .cookie("JSESSIONID", sessionId)
                .queryParam("category", category)
                .when()
                .get("/Reserve/Select")
                .then()
                .statusCode(200)
                .body(containsString(category));

        System.out.println("[STEP 3] 카테고리 진입 성공");

        //랜덤 상품 및 수량
        List<Map<String, String>> testData = ExcelUtil.getTestData(config.getProperty("PriceGuideDataPath"), "Item");
        List<Map<String, String>> categoryItems = testData.stream()
                .filter(d -> categoryName.equals(d.get("Category")))
                .collect(Collectors.toList());
        Collections.shuffle(categoryItems);
        Map<String, String> selectedItem = categoryItems.get(0);
        String itemName = selectedItem.get("Item_Name").trim();
        long itemPrice = Long.parseLong(selectedItem.get("Item_Price").trim().replaceAll("[^0-9]", ""));
        String itemNumber = selectedItem.get("Item_Number");
        int quantity = new Random().nextInt(9) + 1;
        String totalPrice = String.valueOf(itemPrice*quantity);
        System.out.println(">>> 선택된 랜덤 상품: " + itemName + " (가격: " + itemPrice + ", 수량: " + quantity + ", 총가격 : "+totalPrice+")");


        given()
                .baseUri(config.getUrl())
                .cookie("JSESSIONID", sessionId)
                .queryParam("name", "이름/" + itemName)
                .queryParam("price", "가격/" + totalPrice)
                .queryParam("count", "수량/" + quantity)
                .queryParam("date", "예약날짜/" + tomorrowDate)
                .queryParam("number", "번호/" + itemNumber)
                .queryParam("category", categoryName)
                .when()
                .get("/Reserve/Payment")
                .then()
                .statusCode(200)
                .body(containsString(categoryName))
                .body(containsString(itemName))
                .body(containsString("가격/"+totalPrice))
                .body(containsString(tomorrowDate));

        System.out.println("[STEP 4] 결제 확인 페이지 진입 성공");
        System.out.println("[SUCCESS] 결제 확인까지 테스트 성공");
    }

}
