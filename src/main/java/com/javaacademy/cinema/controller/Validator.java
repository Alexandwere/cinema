package com.javaacademy.cinema.controller;

import com.javaacademy.cinema.exception.InvalidPassword;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Validator {
    @Value("app.admin_token")
    String trueToken;
    @Value("app.admin_password")
    String truePassword;

    public void checkAdmin(String token, String password) {
        if (!Objects.equals(trueToken, truePassword)) {
            throw new InvalidPassword("Неверный токен или пароль.");
        }
    }
}
