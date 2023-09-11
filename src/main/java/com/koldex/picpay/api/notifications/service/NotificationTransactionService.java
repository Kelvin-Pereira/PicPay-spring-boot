package com.koldex.picpay.api.notifications.service;

import com.koldex.picpay.api.user.domain.entity.User;
import com.koldex.picpay.config.exceptions.NegocioException;
import com.koldex.picpay.config.ws.notification.NotificationRepository;
import com.koldex.picpay.config.ws.notification.dto.NotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
public class NotificationTransactionService implements BiConsumer<User, String> {

    private final NotificationRepository repository;

    @Override
    public void accept(User user, String message) {

        String email = user.getEmail();

        NotificationDTO notificationDTO = new NotificationDTO(email, message);

        ResponseEntity<Void> stringResponseEntity = repository.sendNotification(notificationDTO);

        if (stringResponseEntity.getStatusCode().value() != HttpStatus.OK.value()) {
            throw new NegocioException("Service de notificacao está fora");
        }
    }
}
