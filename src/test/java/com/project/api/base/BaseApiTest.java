package com.project.api.base;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.given;
import static io.restassured.config.RedirectConfig.redirectConfig;
import static org.hamcrest.Matchers.*;

public class BaseApiTest {
    protected static RequestSpecification commonSpec;
    protected ConfigReader config = new ConfigReader();

    @BeforeSuite
    public void globalSetup() {
        RestAssured.baseURI = config.getUrl();

        commonSpec = new RequestSpecBuilder()
                .setContentType("application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .build();

        //디버깅용 로그 설정
        //RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    protected String loginAsDefaultUser() {
        Response response = given()
                .spec(commonSpec)
                .config(RestAssured.config().redirect(redirectConfig().followRedirects(true))) // 끝까지 따라가기
                .formParam("id", config.getProperty("username"))
                .formParam("pwd", config.getProperty("password"))
                .when()
                .post("/LoginInfo/LoginPost")
                .then()
                .statusCode(200) // 최종 목적지(메인 등)에 도착했는지 확인
                .extract().response();

        return response.getCookie("JSESSIONID");
    }

    protected String loginAsAdminUser() {
        return given()
                .spec(commonSpec)
                .config(RestAssured.config().redirect(redirectConfig().followRedirects(false)))
                .formParam("userId", config.getProperty("adminusername"))
                .formParam("userPwd", config.getProperty("adminpassword"))
                .when()
                .post("/LoginInfo/LoginPost")
                .then()
                .statusCode(302)
                .extract().cookie("JSESSIONID");
    }

    protected void logout(String sessionId) {
        given()
                .spec(commonSpec)
                .cookie("JSESSIONID", sessionId)
                .when()
                .get("/LoginInfo/Logout")
                .then()
                .statusCode(anyOf(is(200), is(302)));
    }


}