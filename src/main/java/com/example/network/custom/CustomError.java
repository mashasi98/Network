package com.example.network.custom;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.val;

@Getter
@Setter
@AllArgsConstructor
public class CustomError {

    private String massage;
    private String key;
    private int code ;

    public CustomError(String massage, String key) {
        this.massage = massage;
        this.key = key;
    }
}


