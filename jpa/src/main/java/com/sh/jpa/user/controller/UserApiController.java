package com.sh.jpa.user.controller;


import com.sh.jpa.user.db.UserEntity;
import com.sh.jpa.user.db.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserRepository userRepository;

    @GetMapping("/find-all")
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/name")
    public void autoSave(@RequestParam String name) {
        var user = UserEntity.builder()
                .name(name)
                .build();
        userRepository.save(user);
    }

}
