package com.example.demowithtests.service;

import com.example.demowithtests.domain.UserEntity;
import com.example.demowithtests.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserEntity createUser(UserEntity user){
        return userRepository.save(user);
    }
}
