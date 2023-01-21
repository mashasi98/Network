package com.example.network.service;

import com.example.network.custom.CustomError;

import com.example.network.custom.CustomResponse;
import com.example.network.custom.ErrorEnum;
import com.example.network.dto.UserDTO;
import com.example.network.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserService userService;


    public CustomResponse joinFromEmail(UserDTO user){
        UserDTO userDB = userService.getUserByEmail(user.getEmail());
        if (userDB != null) {
            return new CustomResponse(new CustomError(ErrorEnum.PERSON_ALREADY_EXIST.getMessage(), ErrorEnum.PERSON_ALREADY_EXIST.getKey()));
        }
        String result = userService.createUser(user);
        return new CustomResponse(null, result);
    }
}
