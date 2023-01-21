package com.example.network.service;


import com.example.network.convert.UserDtoToUserConv;
import com.example.network.convert.UserToUserDtoConv;
import com.example.network.dto.UserDTO;
import com.example.network.entity.User;

import com.example.network.repository.RoleRepository;
import com.example.network.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final UserToUserDtoConv userToUserDto;
    private final UserDtoToUserConv userDtoToUser;

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public List<User> getUsers(Long userId) {

        return userRepository.findAllOtherUsers(userId);
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return userToUserDto.convert(user);
    }

    public String createUser(UserDTO userDTO)  {
        User user = userDtoToUser.convert(userDTO);
        if (user == null) {
            throw new UsernameNotFoundException("User  isn`t found");
        }
        if (!validate(user.getEmail(), user.getPassword())) {
            return "Wrong  email format";
        }else {
            userRepository.save(user);
            return user.toString();
        }

    }
    public boolean validate(String email,String  password) {

        return email.matches("^(.+)@(.+)$") ;
    }


}
