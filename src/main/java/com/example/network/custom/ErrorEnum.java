package com.example.network.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public enum ErrorEnum {


    PERSON_NOT_FOUND("PERSON_NOT_FOUND","Пользователь не найден"),
    ROLE_NOT_FOUND("Role_NOT_FOUND","Роль не найдена"),
    EMPTY_USER_LIST("EMPTY_USER_LIST","Список пользователей пуст"),
    PERSON_ALREADY_EXIST("PERSON_ALREADY_EXIST", "Пользователь уже зарегистрирован"),
    PHONE_NOT_MATCH("PHONE_NOT_MATCH","Номер телефона не верный"),
    EMAIL_NOT_MATCH("EMAIL_NOT_MATCH","Почта не верна");


    private final String key;
    private final String message;


    @Override
    public String toString() {
        return key + ": " + message;
    }
}
//@AllArgsConstructor
//public enum ErrorEnum(){
//
//        private final String key;
//        private final String message;
//
//
//
//
//
//}
