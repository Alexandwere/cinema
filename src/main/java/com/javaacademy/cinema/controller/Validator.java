package com.javaacademy.cinema.controller;

import com.javaacademy.cinema.exception.InvalidPassword;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Validator {

    public void checkAdmin(String password) {
        String truePassword = "secretadmin123";
        if (!Objects.equals(password, truePassword)) {
            throw new InvalidPassword("Неверный пароль администратора.");
        }
    }
}
