package com.koldex.picpay.config.ws.notification;

import com.koldex.picpay.config.ws.notification.dto.NotificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Notification", url = "${picpay.notification.path}")
public interface NotificationRepository {

    @PostMapping
    ResponseEntity<Void> sendNotification(@RequestBody NotificationDTO notificationDTO);

}
