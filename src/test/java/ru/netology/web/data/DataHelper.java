package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getInvalidAuthInfo() {
        return new AuthInfo("vasya1", "qwerty1234");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor() {
        return new VerificationCode("12345");
    }

    public static VerificationCode getInvalidVerificationCodeFor() {
        return new VerificationCode("12347");
    }

    @Value
    public static class CardsInfo {
        private String number;
        private String balance;
    }

    public static CardsInfo getFirstCardsInfo() {
        return new CardsInfo("5559 0000 0000 0001", "10000");
    }

    public static CardsInfo getSecondCardsInfo() {
        return new CardsInfo("5559 0000 0000 0002", "10000");
    }
}
