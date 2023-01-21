package com.example.network.controller;


import com.example.network.custom.CustomResponse;
import com.example.network.dto.AuthenticationRequest;
import com.example.network.dto.AuthenticationResponse;
import com.example.network.dto.RegisterRequest;
import com.example.network.dto.UserDTO;
import com.example.network.service.AuthenticationService;
import com.example.network.service.RegistrationService;
import com.example.network.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainController {

    private final RegistrationService registrationService;
    private final AuthenticationService authService;

    @PostMapping("/registration/email")
    private ResponseEntity<CustomResponse> joinFromEmail(@RequestBody UserDTO userDTO)  {
        CustomResponse response = registrationService.joinFromEmail(userDTO);
        return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    private ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping("/authenticate")
    private ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(authService.authenticate(request));
    }


}
