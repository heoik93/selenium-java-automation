package com.project.testutils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public class JiraClient {

    public static void createJiraIssue(String summary, String description) {
        String jiraDomain = System.getenv("JIRA_DOMAIN");
        String jiraEmail = System.getenv("JIRA_EMAIL");
        String jiraToken = System.getenv("JIRA_API_TOKEN");
        String projectKey = System.getenv("JIRA_PROJECT_KEY");

        if (jiraDomain == null || jiraToken == null) {
            System.out.println("[SKIP] Jira 설정이 활성화되지 않아 티켓을 생성하지 않습니다.");
            return;
        }

        RestAssured.baseURI = "https://" + jiraDomain;

        String issueBody = String.format(
                "{\n" +
                        "  \"fields\": {\n" +
                        "    \"project\": { \"key\": \"%s\" },\n" +
                        "    \"summary\": \"%s\",\n" +
                        "    \"description\": \"%s\",\n" +
                        "    \"issuetype\": { \"name\": \"Bug\" }\n" +
                        "  }\n" +
                        "}", projectKey, summary, description
        );

        try {
            given()
                    .auth().preemptive().basic(jiraEmail, jiraToken)
                    .contentType(ContentType.JSON)
                    .body(issueBody)
                    .when()
                    .post("/rest/api/2/issue")
                    .then()
                    .statusCode(201);

            System.out.println("[SUCCESS] Jira 버그 티켓 생성 완료!");
        } catch (Exception e) {
            System.out.println("[ERROR] Jira 연동 중 오류 발생: " + e.getMessage());
        }
    }
}