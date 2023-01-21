package com.example.network.controller;

import com.example.network.convert.UserToUserDtoConv;

import com.example.network.custom.CustomResponse;
import com.example.network.dto.UserDTO;
import com.example.network.entity.User;
import com.example.network.service.FriendService;
import com.example.network.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendsController {

    private final FriendService friendsService;
    private final UserService userService;
    private final UserToUserDtoConv userToUserDtoConv;

    private String email="email1";

    @GetMapping("/allFriends")
    private ResponseEntity<CustomResponse> getFriendsList(HttpServletRequest request) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return getCustomResponseResponseEntity(user.getId());

        UserDTO userDTO = userService.getUserByEmail(email);
        return getCustomResponseResponseEntity(userDTO.getId());
    }

    @PostMapping("/addToFriends")
    private ResponseEntity<CustomResponse> addToFriends(@RequestBody Long friendId, HttpServletRequest request)  {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User friend = userService.findUserById(friendId);
//        friendsService.addToFriendsUser(user, friend);
//        return getCustomResponseResponseEntity(user.getId());
//
        UserDTO userDTO = userService.getUserByEmail(email);
        friendsService.addToFriends(userDTO, friendId);
        return getCustomResponseResponseEntity(userDTO.getId());
    }


    @DeleteMapping("/deleteFromFriends")
    private ResponseEntity<CustomResponse> getProfilePage(@RequestBody Long friendId, HttpServletRequest request) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User friend = userService.findUserById(friendId);
//        friendsService.deleteFromFriendsUser(user, friend);
//        return getCustomResponseResponseEntity(user.getId());

        UserDTO userDTO = userService.getUserByEmail(email);
        friendsService.deleteFromFriends(userDTO, friendId);
        return getCustomResponseResponseEntity(userDTO.getId());
    }

    private ResponseEntity<CustomResponse> getCustomResponseResponseEntity(Long userId) {
        List<UserDTO> friends = friendsService.getFriends(userId);
        String responseData = friends
                .stream()
                .map(UserDTO::friendView)
                .collect(Collectors.joining("-", "{", "}"));
        CustomResponse response = new CustomResponse(null, responseData);
        return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
    }


}
