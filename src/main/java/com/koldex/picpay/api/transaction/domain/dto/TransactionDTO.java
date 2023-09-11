package com.koldex.picpay.api.transaction.domain.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionDTO(@NotNull Long senderId, @NotNull Long receiverId, @NotNull BigDecimal value) {
}
