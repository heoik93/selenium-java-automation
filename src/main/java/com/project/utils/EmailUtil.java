package com.project.utils;

import config.ConfigReader;
import jakarta.activation.DataHandler;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;

public class EmailUtil {
    public static void sendReport(String suiteName, int passed, int failed, int skipped) {
        ConfigReader config = new ConfigReader();
        File reportDir = new File("./test-output/");
        File[] files = reportDir.listFiles((dir, name) -> name.endsWith(".html"));

        if (files == null || files.length == 0) {
            System.err.println("첨부할 리포트 파일을 찾을 수 없습니다.");
            return;
        }

        // 최신 파일 탐색
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        File latestReport = files[0];
        System.out.println("첨부할 최신 리포트: " + latestReport.getName());

        try {
            String user = config.getProperty("email.user");
            String password = config.getProperty("email.password");
            String to = config.getProperty("email.to");

            // 메일 서버 설정 (SSL 465 기반)
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            // 타임아웃 설정 추가 (네트워크 안정성)
            props.put("mail.smtp.connectiontimeout", "10000");
            props.put("mail.smtp.timeout", "10000");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });

            String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("[자동화 테스트 결과] " + currentTime);

            message.setSubject("[" + suiteName + " 결과] " + currentTime);

            // 1. 메일 본문 작성
            MimeBodyPart textPart = new MimeBodyPart();
            String htmlContent = "<div style='font-family: Arial, sans-serif; border: 1px solid #ddd; padding: 20px; border-radius: 10px;'>"
                    + "<h2 style='color: #2e6da4;'>📊 " + suiteName + " 테스트 요약</h2>"
                    + "<table style='width: 100%; border-collapse: collapse;'>"
                    + "  <tr style='background-color: #f8f8f8;'>"
                    + "    <th style='padding: 10px; border: 1px solid #ddd; text-align: left;'>항목</th>"
                    + "    <th style='padding: 10px; border: 1px solid #ddd; text-align: left;'>결과</th>"
                    + "  </tr>"
                    + "  <tr>"
                    + "    <td style='padding: 10px; border: 1px solid #ddd;'>성공 (Pass)</td>"
                    + "    <td style='padding: 10px; border: 1px solid #ddd; color: green; font-weight: bold;'>" + passed + "건</td>"
                    + "  </tr>"
                    + "  <tr>"
                    + "    <td style='padding: 10px; border: 1px solid #ddd;'>실패 (Fail)</td>"
                    + "    <td style='padding: 10px; border: 1px solid #ddd; color: red; font-weight: bold;'>" + failed + "건</td>"
                    + "  </tr>"
                    + "  <tr>"
                    + "    <td style='padding: 10px; border: 1px solid #ddd;'>제외 (Skip)</td>"
                    + "    <td style='padding: 10px; border: 1px solid #ddd; color: orange; font-weight: bold;'>" + skipped + "건</td>"
                    + "  </tr>"
                    + "  <tr>"
                    + "    <td style='padding: 10px; border: 1px solid #ddd;'>상세 리포트</td>"
                    + "    <td style='padding: 10px; border: 1px solid #ddd;'>첨부파일 참조</td>"
                    + "  </tr>"
                    + "</table>"
                    + "<p style='margin-top: 20px; font-size: 12px; color: #888;'>* 발송 시간: " + currentTime + "</p>"
                    + "</div>";

            // 본문에 HTML 설정
            textPart.setContent(htmlContent, "text/html; charset=utf-8");

            MimeBodyPart attachPart = new MimeBodyPart();
            byte[] fileContent = Files.readAllBytes(latestReport.toPath()); // 파일을 데이터로 읽음
            ByteArrayDataSource dataSource = new ByteArrayDataSource(fileContent, "text/html");
            attachPart.setDataHandler(new DataHandler(dataSource));
            attachPart.setFileName(latestReport.getName()); // 첨부파일명 설정

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("메일 전송 성공: " + currentTime);

        } catch (Exception e) {
            System.err.println("메일 전송 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }
}