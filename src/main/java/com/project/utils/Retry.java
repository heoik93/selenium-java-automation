package com.project.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private int count = 0;

    private static final int maxTry = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {

        if (!iTestResult.isSuccess()) {
            if (count < maxTry) {
                count++;
                System.out.println("⚠️ 테스트 실패 감지! 재시도를 시작합니다. (횟수: " + count + ")");
                return true;
            }
        }
        return false;
    }
}