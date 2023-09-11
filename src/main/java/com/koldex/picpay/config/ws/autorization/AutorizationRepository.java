package com.koldex.picpay.config.ws.autorization;

import com.koldex.picpay.config.ws.autorization.dto.Autorization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "AutorizationRepository", url = "${picpay.autorization.path}")
public interface AutorizationRepository {

    @GetMapping
    Autorization validateOperation();

}
