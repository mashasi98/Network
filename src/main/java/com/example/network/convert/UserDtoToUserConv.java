package com.example.network.convert;


import com.example.network.dto.UserDTO;
import com.example.network.entity.Role;
import com.example.network.entity.User;
import com.example.network.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserDtoToUserConv implements Converter<UserDTO, User> {

    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    @Override
    public User convert(UserDTO userDTO) {

        Role role = roleRepository.findByRoleName("USER");
        if (role == null) {
            role = checkRoleExist();
        }
        Set<Role> roles = Collections.singleton(role);
        User.UserBuilder user = User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .password(userDTO.getPassword())
                .secondName(userDTO.getSecondName())
                .roles(roles);

        if (userDTO.getPassword() != null)
            user.password(passwordEncoder.encode(userDTO.getPassword()));
        return user.build();
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setRoleName("USER");
        return roleRepository.save(role);
    }
}
