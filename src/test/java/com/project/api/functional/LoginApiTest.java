package com.project.api.functional;

import com.project.api.base.BaseApiTest;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LoginApiTest extends BaseApiTest {

    @Test(description = "기본 유저 로그인 기능 동작 확인")
    public void defaultLoginApiTest() {
        String sessionId = loginAsDefaultUser();

        assertThat("[FAIL]세션 ID가 정상적으로 발급되어야 합니다.", sessionId, is(not(emptyOrNullString())));
        System.out.println("[SUCCESS]기본 유저 로그인 성공: " + sessionId);
    }

    @Test(description = "관리자 유저 로그인 기능 동작 확인")
    public void adminLoginApiTest() {
        String sessionId = loginAsAdminUser();

        assertThat("[FAIL]세션 ID가 정상적으로 발급되어야 합니다.", sessionId, is(not(emptyOrNullString())));
        System.out.println("[SUCCESS]관리자 로그인 성공: " + sessionId);
    }

}
