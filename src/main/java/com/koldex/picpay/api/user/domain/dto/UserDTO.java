package com.koldex.picpay.api.user.domain.dto;

import com.koldex.picpay.api.user.domain.enums.UserTypeEnum;

import java.math.BigDecimal;

public record UserDTO(String fristName,
                      String lastName,
                      String email,
                      String document,
                      BigDecimal balance,
                      String password,
                      UserTypeEnum userType) {
}
