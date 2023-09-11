package com.koldex.picpay.api.transaction.controller;

import com.koldex.picpay.api.transaction.domain.dto.TransactionDTO;
import com.koldex.picpay.api.transaction.domain.entity.Transaction;
import com.koldex.picpay.api.transaction.service.TransactionSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {


    private final TransactionSaveService transactionSaveService;

    @PostMapping
    public ResponseEntity<Transaction> save(@RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.ok(transactionSaveService.apply(transactionDTO));
    }


}
