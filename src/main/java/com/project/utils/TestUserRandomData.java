package com.project.utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestUserRandomData {

    Faker faker = new Faker(new Locale("ko"));
    Faker fakerEn = new Faker(Locale.ENGLISH);

    public String randomUserName = faker.name().fullName().replace(" ", "");
    public String randomUserEmail = "test_" + System.currentTimeMillis() + "@RandomUser.com";
    public String randomUserId = "testuser" + faker.number().digits(6);
    public String randomUserPassword = "Pwd!" + faker.number().digits(6);
    public String randomUserPhone = "010" + faker.number().digits(8);

    public String randomUserCountry = fakerEn.country().name();
}
