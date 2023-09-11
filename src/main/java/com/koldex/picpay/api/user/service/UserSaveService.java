package com.koldex.picpay.api.user.service;


import com.koldex.picpay.api.user.domain.entity.User;
import com.koldex.picpay.api.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.UnaryOperator;

@Service
@RequiredArgsConstructor
public class UserSaveService implements UnaryOperator<User> {

    private final UserRepository repository;

    @Transactional
    @Override
    public User apply(User user) {
        return repository.save(user);
    }

}
