package com.example.network.convert;

import com.example.network.dto.UserDTO;
import com.example.network.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component

public class UserToUserDtoConv  implements Converter<User, UserDTO> {

//    private final BCryptPasswordEncoder passwordEncoder;

//    public UserToUserDtoConv(BCryptPasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    @Override
    public UserDTO convert(User user) {
        if (user == null)
            return null;
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .build();
    }
}
