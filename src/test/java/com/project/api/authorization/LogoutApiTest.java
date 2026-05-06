package com.project.api.authorization;

import com.project.api.base.BaseApiTest;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.config.RedirectConfig.redirectConfig;
import static org.hamcrest.Matchers.containsString;

public class LogoutApiTest extends BaseApiTest {

    @Test(description = "기본유저 로그아웃후 세션만료 확인")
    public void defaultLogoutApiTest() {
        String sessionId = loginAsDefaultUser();
        System.out.println("[INFO]로그인 성공: " + sessionId);

        logout(sessionId);

            given()
                .spec(commonSpec)
                .config(RestAssured.config().redirect(redirectConfig().followRedirects(false)))
                .cookie("JSESSIONID", sessionId)
            .when()
                .get("/LoginInfo/Mypage/MyInfo")
            .then()
                .statusCode(302)
                .header("Location", containsString("/LoginInfo/Login"));

        System.out.println("[SUCCESS] 로그아웃 검증 완료: 해당 세션으로 마이페이지에 접근 불가 (기본유저)");
    }

    @Test(description = "관리자유저 로그아웃 기능 동작 확인")
    public void adminLogoutApiTest() {
        String sessionId = loginAsAdminUser();
        System.out.println("[INFO]로그인 성공: " + sessionId);

        logout(sessionId);

        given()
            .config(RestAssured.config().redirect(redirectConfig().followRedirects(false)))
            .spec(commonSpec)
            .cookie("JSESSIONID", sessionId)
        .when()
            .get("/LoginInfo/Mypage/MyInfo")
        .then()
            .statusCode(302)
            .header("Location", containsString("/LoginInfo/Login"));

        System.out.println("[SUCCESS] 로그아웃 검증 완료: 해당 세션으로 마이페이지에 접근 불가 (관리자유저)");
    }


}
