package com.project.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ExtentManager {
    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public static ExtentReports getInstance() {
        if (extent == null) {
            String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
            ExtentSparkReporter spark = new ExtentSparkReporter("./test-output/Report_" + timestamp + ".html");

            spark.viewConfigurer().viewOrder().as(new ViewName[]{
                    ViewName.DASHBOARD,
                    ViewName.TEST,
                    ViewName.EXCEPTION
            }).apply();

            spark.config().setTheme(Theme.DARK); //다크모드
            spark.config().setReportName("테스트 자동화 결과"); // 리포트 제목
            spark.config().setDocumentTitle("테스트 리포트"); // 브라우저 탭 제목
            spark.config().setTimelineEnabled(true); //타임라인 표시
            spark.config().setCss(".node.level-1  { font-size: 14px; } .log { font-family: 'Malgun Gothic', sans-serif; }"); //css주입
            spark.config().setTimelineEnabled(false);

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Project Name", "laundry365");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Tester", "Automaion Test User");
        }
        return extent;
    }



    public static ExtentTest getTest() {
        return extentTest.get();
    }

}