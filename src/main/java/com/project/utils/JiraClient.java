package com.project.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class JiraClient {

    public static void createJiraIssue(String summary, String description) {
        String jiraDomain = System.getenv("JIRA_DOMAIN");
        String jiraEmail = System.getenv("JIRA_EMAIL");
        String jiraToken = System.getenv("JIRA_API_TOKEN");
        String projectKey = System.getenv("JIRA_PROJECT_KEY");

        if (jiraDomain == null || jiraToken == null) {
            System.out.println("[INFO] Jira 환경변수가 없어 실행을 건너뜁니다.");
            return;
        }

        RestAssured.baseURI = "https://" + jiraDomain;

        // 1. JSON 구조를 Map으로 생성 (Parsing 에러 방지)
        Map<String, Object> fields = new HashMap<>();

        // 프로젝트 키 설정
        Map<String, String> project = new HashMap<>();
        project.put("key", projectKey);
        fields.put("project", project);

        // 제목 및 내용 (특수문자 자동 치환됨)
        fields.put("summary", summary);
        fields.put("description", description);

        Map<String, String> issuetype = new HashMap<>();
        issuetype.put("name", "버그");
        fields.put("issuetype", issuetype);

        Map<String, Object> payload = new HashMap<>();
        payload.put("fields", fields);

        // 2. 전송
        try {
            RestAssured.given()
                    .auth().preemptive().basic(jiraEmail, jiraToken)
                    .contentType(ContentType.JSON)
                    .body(payload)
                    .when()
                    .post("/rest/api/2/issue")
                    .then()
                    .log().all()
                    .statusCode(201);

            System.out.println("[SUCCESS] Jira 티켓 생성 성공!");
        } catch (Exception e) {
            System.out.println("[ERROR] Jira 연동 실패: " + e.getMessage());
        }
    }
}