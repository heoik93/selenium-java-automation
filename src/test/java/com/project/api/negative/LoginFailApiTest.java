package com.project.api.negative;

import com.project.api.base.BaseApiTest;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.config.RedirectConfig.redirectConfig;
import static org.hamcrest.Matchers.*;

public class LoginFailApiTest extends BaseApiTest {

    @Test(description = "로그인 실패 동작 확인")
    public void loginFailApiTest() {
        // 1. 틀린 비밀번호로 로그인 시도
        String sessionId =
                given()
                    .spec(commonSpec)
                    .formParam("userId", config.getProperty("username"))
                    .formParam("userPwd", config.getProperty("wrongpwd"))
                .when()
                    .post("/LoginInfo/LoginPost")
                .then()
                    .statusCode(anyOf(is(302), is(401)))
                    .header("Location", containsString("/LoginInfo/Login?error=true"))
                    .extract().cookie("JSESSIONID");

        System.out.println("[INFO] 로그인 차단 확인완료");

        // 2. 발급받은 '실패 세션'으로 마이페이지 접속 시도
        given()
            .spec(commonSpec)
            .config(RestAssured.config().redirect(redirectConfig().followRedirects(false)))
            .cookie("JSESSIONID", sessionId)
        .when()
            .get("/LoginInfo/Mypage/MyInfo")
        .then()
            .statusCode(302)
            .header("Location", containsString("Login"));

        System.out.println("[SUCCESS] 잘못된 세션으로는 마이페이지 접근 불가 (보안 검증 완료)");
    }

}
