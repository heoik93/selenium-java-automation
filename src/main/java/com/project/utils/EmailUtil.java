package com.project.utils;

import config.ConfigReader;
import jakarta.activation.DataHandler;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.mail.util.ByteArrayDataSource;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;

public class EmailUtil {
    public static void sendReport() {
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

            // 1. 메일 본문 작성
            MimeBodyPart textPart = new MimeBodyPart();
            String htmlContent = "<div style='font-family: Arial, sans-serif; border: 1px solid #ddd; padding: 20px; border-radius: 10px;'>"
                    + "<h2 style='color: #2e6da4;'>📊 테스트 실행 요약</h2>"
                    + "<table style='width: 100%; border-collapse: collapse;'>"
                    + "  <tr style='background-color: #f8f8f8;'>"
                    + "    <th style='padding: 10px; border: 1px solid #ddd; text-align: left;'>항목</th>"
                    + "    <th style='padding: 10px; border: 1px solid #ddd; text-align: left;'>상세 내용</th>"
                    + "  </tr>"
                    + "  <tr>"
                    + "    <td style='padding: 10px; border: 1px solid #ddd;'>실행 시간</td>"
                    + "    <td style='padding: 10px; border: 1px solid #ddd;'>" + currentTime + "</td>"
                    + "  </tr>"
                    + "  <tr>"
                    + "    <td style='padding: 10px; border: 1px solid #ddd;'>리포트 파일</td>"
                    + "    <td style='padding: 10px; border: 1px solid #ddd;'>" + latestReport.getName() + "</td>"
                    + "  </tr>"
                    + "  <tr>"
                    + "    <td style='padding: 10px; border: 1px solid #ddd;'>결과 확인</td>"
                    + "    <td style='padding: 10px; border: 1px solid #ddd; color: #d9534f; font-weight: bold;'>첨부파일 참조</td>"
                    + "  </tr>"
                    + "</table>"
                    + "<p style='margin-top: 20px; font-size: 12px; color: #888;'>* 본 메일은 전용 테스트 서버에서 자동 발송되었습니다.</p>"
                    + "</div>";

            // 본문에 HTML 설정
            textPart.setContent(htmlContent, "text/html; charset=utf-8");

            //textPart.setText("테스트가 완료되었습니다. 상세 리포트는 첨부파일을 확인하세요.\n발송 시간: " + currentTime);

            // 2. [핵심] 파일 점유 문제를 피하기 위해 바이트 배열로 첨부
            MimeBodyPart attachPart = new MimeBodyPart();
            byte[] fileContent = Files.readAllBytes(latestReport.toPath()); // 파일을 데이터로 읽음
            ByteArrayDataSource dataSource = new ByteArrayDataSource(fileContent, "text/html");
            attachPart.setDataHandler(new DataHandler(dataSource));
            attachPart.setFileName(latestReport.getName()); // 첨부파일명 설정

            // 3. 합치기
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachPart);

            message.setContent(multipart);

            // 4. 전송
            Transport.send(message);
            System.out.println("메일 전송 성공: " + currentTime);

        } catch (Exception e) {
            System.err.println("메일 전송 실패: " + e.getMessage());
            e.printStackTrace(); // 상세 에러 추적을 위해 추가
        }
    }
}