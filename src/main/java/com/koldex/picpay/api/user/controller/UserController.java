package com.koldex.picpay.api.user.controller;

import com.koldex.picpay.api.user.domain.dto.UserDTO;
import com.koldex.picpay.api.user.domain.entity.User;
import com.koldex.picpay.api.user.service.UserSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserSaveService userSaveService;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userSaveService.apply(User.of(userDTO)));
    }

}
