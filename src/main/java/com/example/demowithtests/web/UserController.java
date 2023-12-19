package com.example.demowithtests.web;

import com.example.demowithtests.domain.UserEntity;
import com.example.demowithtests.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.example.demowithtests.util.Endpoints.API_BASE;
import static com.example.demowithtests.util.Endpoints.AUTH_ENDPOINT;

@RestController
@AllArgsConstructor
@RequestMapping(AUTH_ENDPOINT)
@Slf4j
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;


    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity saveUser(@RequestBody UserEntity user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userService.createUser(user);
    }
}
