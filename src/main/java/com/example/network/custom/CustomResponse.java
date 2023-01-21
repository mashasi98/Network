package com.example.network.custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class CustomResponse {

    private CustomError error;
    private String data;

    public CustomResponse(CustomError error) {
        this.error = error;
    }
}
