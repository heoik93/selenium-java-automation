package com.project.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class JiraClient {

    public static String createJiraIssue(String summary, String description) {
        String jiraDomain = System.getenv("JIRA_DOMAIN");
        String jiraEmail = System.getenv("JIRA_EMAIL");
        String jiraToken = System.getenv("JIRA_API_TOKEN");
        String projectKey = System.getenv("JIRA_PROJECT_KEY");
        String epicKey = System.getenv("JIRA_EPIC_KEY");

        if (jiraDomain == null || jiraToken == null) {
            System.out.println("[INFO] Jira 환경변수가 없어 실행을 건너뜁니다.");
            return null;
        }

        RestAssured.baseURI = "https://" + jiraDomain;

        Map<String, Object> fields = new HashMap<>();

        Map<String, String> project = new HashMap<>();
        project.put("key", projectKey);
        fields.put("project", project);

        if (epicKey != null && !epicKey.isEmpty()) {
            Map<String, String> parent = new HashMap<>();
            parent.put("key", epicKey);
            fields.put("parent", parent);
        }

        fields.put("summary", summary);
        fields.put("description", description);

        Map<String, String> issuetype = new HashMap<>();
        issuetype.put("name", "버그");
        fields.put("issuetype", issuetype);

        Map<String, Object> payload = new HashMap<>();
        payload.put("fields", fields);

        //전송 및 Key 추출
        try {
            io.restassured.response.Response response = RestAssured.given()
                    .auth().preemptive().basic(jiraEmail, jiraToken)
                    .contentType(ContentType.JSON)
                    .body(payload)
                    .when()
                    .post("/rest/api/2/issue")
                    .then()
                    .log().all()
                    .statusCode(201)
                    .extract().response();

            String issueKey = response.path("key");
            System.out.println("[SUCCESS] 티켓 생성됨: " + issueKey);
            return issueKey;

        } catch (Exception e) {
            System.out.println("[ERROR] Jira 연동 실패: " + e.getMessage());
            return null;
        }
    }

    public static void addAttachment(String issueKey, String screenshotPath) {
        File file = new File(screenshotPath);
        if (!file.exists()) {
            System.out.println("[WARN] 스크린샷 파일을 찾을 수 없습니다: " + screenshotPath);
            return;
        }

        RestAssured.given()
                .header("X-Atlassian-Token", "no-check") // 지라 보안 정책상 필수
                .auth().preemptive().basic(System.getenv("JIRA_EMAIL"), System.getenv("JIRA_API_TOKEN"))
                .multiPart("file", file) // 실제 파일 첨부
                .when()
                .post("/rest/api/2/issue/" + issueKey + "/attachments")
                .then()
                .log().all()
                .statusCode(200);

        System.out.println("[SUCCESS] 스크린샷 업로드 완료: " + issueKey);
    }
}