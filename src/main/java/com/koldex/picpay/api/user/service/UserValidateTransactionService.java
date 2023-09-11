package com.koldex.picpay.api.user.service;

import com.koldex.picpay.api.user.domain.entity.User;
import com.koldex.picpay.api.user.domain.enums.UserTypeEnum;
import com.koldex.picpay.config.exceptions.NegocioException;
import com.koldex.picpay.config.ws.autorization.AutorizationRepository;
import com.koldex.picpay.config.ws.autorization.dto.Autorization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
public class UserValidateTransactionService implements BiConsumer<User, BigDecimal> {

    private static final String AUTORIZADO = "Autorizado";
    private final AutorizationRepository autorizationRepository;

    @Override
    public void accept(User sender, BigDecimal amount) {
        typeUserHasPermitionTransactionValidator(sender);
        hasAutorization();
        balanceValidator(sender, amount);
    }

    private void typeUserHasPermitionTransactionValidator(User sender) {
        if (sender.getUserType().equals(UserTypeEnum.MERCHANT)) {
            throw new NegocioException("Usuario do tipo Logista nao está autorizado a realizar trasacao.");
        }
    }

    private void balanceValidator(User sender, BigDecimal amount) {
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new NegocioException("Saldo Insuficiente.");
        }
    }

    private void hasAutorization() {
        Autorization autorizationResponseEntity = autorizationRepository.validateOperation();
        if (!Objects.equals(autorizationResponseEntity.message(), AUTORIZADO)) {
            throw new NegocioException("Usuario nao possui permisao para realizar operacao.");
        }
    }


}
