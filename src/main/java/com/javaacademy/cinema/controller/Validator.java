package com.javaacademy.cinema.controller;

import com.javaacademy.cinema.exception.InvalidPassword;

import java.util.Objects;

public class Validator {

    public void checkAdmin(String password) {
        String truePassword = "secretadmin123";
        if (!Objects.equals(password, truePassword)) {
            throw new InvalidPassword("Неверный пароль администратора.");
        }
    }
}
