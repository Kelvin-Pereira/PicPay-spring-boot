package com.koldex.picpay.api.user.service;

import com.koldex.picpay.api.user.domain.entity.User;
import com.koldex.picpay.api.user.repository.UserRepository;
import com.koldex.picpay.config.exceptions.NegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.LongFunction;

@Service
@RequiredArgsConstructor
public class UserFindByIdService implements LongFunction<User> {

    private final UserRepository repository;

    @Override
    public User apply(long id) {
        return repository.findById(id).orElseThrow(() -> new NegocioException("User not found"));
    }

}
