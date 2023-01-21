package com.example.network.controller;


import com.example.network.convert.UserToUserDtoConv;
import com.example.network.custom.CustomError;
import com.example.network.custom.CustomResponse;
import com.example.network.custom.ErrorEnum;
import com.example.network.dto.UserDTO;
import com.example.network.entity.User;
import com.example.network.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserToUserDtoConv userToUserDtoConv;

    @GetMapping("/getAllUsers")
    private ResponseEntity<CustomResponse> getAllProfile(@RequestBody String email,HttpServletRequest request
    ) {
//        private ResponseEntity<CustomResponse> getAllProfile() {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        List<User> users=userService.getUsers(user.getId());
        UserDTO userDTO = userService.getUserByEmail(email);
        List<User> users = userService.getUsers(userDTO.getId());
        if (users.size()==0){
            CustomResponse response = new CustomResponse(new CustomError(ErrorEnum.EMPTY_USER_LIST.getMessage(), ErrorEnum.EMPTY_USER_LIST.getKey()),null);
            return new ResponseEntity<CustomResponse>(response, HttpStatus.NOT_FOUND);
        }
        String responseData = users.stream()
                .map(userToUserDtoConv::convert)
                .map(UserDTO::friendView)
                .collect(Collectors.joining(" ", "{", "}"));
        CustomResponse response = new CustomResponse( null, responseData);
        return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
    }


}
