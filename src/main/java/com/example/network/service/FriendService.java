package com.example.network.service;

import com.example.network.convert.UserDtoToUserConv;
import com.example.network.convert.UserToUserDtoConv;
import com.example.network.custom.CustomError;
import com.example.network.custom.CustomResponse;
import com.example.network.custom.ErrorEnum;
import com.example.network.dto.UserDTO;
import com.example.network.entity.Friends;
import com.example.network.entity.User;
import com.example.network.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FriendService {

    private final UserToUserDtoConv userToUserDto;
    private final UserDtoToUserConv userDtoToUser;
    private final UserService userService;
    private final FriendRepository friendRepository;

    public List<UserDTO> getFriends(Long id) {
        List<User> friends = (friendRepository.findFriendsUsers(id));
        return friends.stream().map(userToUserDto::convert).toList();
    }

    public List<UserDTO> getFriendsUser(Long id) {
        List<User> friends = (friendRepository.findFriendsUsers(id));
        return friends.stream().map(userToUserDto::convert).toList();
    }

    public void addToFriends(UserDTO userDTO, Long friendId) {
        User friend = userService.findUserById(friendId);
        if (friend == null) {
            new CustomResponse(new CustomError(ErrorEnum.PERSON_NOT_FOUND.getMessage(), ErrorEnum.PERSON_NOT_FOUND.getKey()));
        }
        User user = userDtoToUser.convert(userDTO);
        friendRepository.save(new Friends(user, friend));
        friendRepository.save(new Friends(friend, user));
    }

    public void addToFriendsUser(User user, User friend) {
        if (friend == null) {
            new CustomResponse(new CustomError(ErrorEnum.PERSON_NOT_FOUND.getMessage(), ErrorEnum.PERSON_NOT_FOUND.getKey()));
        }
        friendRepository.save(new Friends(user, friend));
        friendRepository.save(new Friends(friend, user));
    }


    public void deleteFromFriends(UserDTO userDTO, Long friendId) {
        User friend = userService.findUserById(friendId);
        if (friend == null) {
            new CustomResponse(new CustomError(ErrorEnum.PERSON_NOT_FOUND.getMessage(), ErrorEnum.PERSON_NOT_FOUND.getKey()));
        }
        User user = userDtoToUser.convert(userDTO);
        friendRepository.deleteByUserAndAndUserFriend(user, friend);

    }

    public void deleteFromFriendsUser(User user, User friend) {
        if (friend == null) {
            new CustomResponse(new CustomError(ErrorEnum.PERSON_NOT_FOUND.getMessage(), ErrorEnum.PERSON_NOT_FOUND.getKey()));
        }
        friendRepository.deleteByUserAndAndUserFriend(user, friend);
    }


}
