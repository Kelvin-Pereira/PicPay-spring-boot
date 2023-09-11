package com.koldex.picpay.api.transaction.service;

import com.koldex.picpay.api.notifications.service.NotificationTransactionService;
import com.koldex.picpay.api.transaction.domain.dto.TransactionDTO;
import com.koldex.picpay.api.transaction.domain.entity.Transaction;
import com.koldex.picpay.api.transaction.repository.TransactionRepository;
import com.koldex.picpay.api.user.domain.entity.User;
import com.koldex.picpay.api.user.service.UserFindByIdService;
import com.koldex.picpay.api.user.service.UserSaveService;
import com.koldex.picpay.api.user.service.UserValidateTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TransactionSaveService implements Function<TransactionDTO, Transaction> {

    private static final String TRANSACAO_SUCCESS = "Trasacao realizada com sucesso";
    private static final String TRANSACAO_RECEIVED = "Trasacao recebida com sucesso";

    private final UserSaveService userSaveService;
    private final TransactionRepository repository;
    private final UserFindByIdService userFindByIdService;
    private final NotificationTransactionService notificationTransactionService;
    private final UserValidateTransactionService userValidateTransactionService;

    @Override
    public Transaction apply(TransactionDTO transactionDTO) {

        User sender = userFindByIdService.apply(transactionDTO.senderId());

        userValidateTransactionService.accept(sender, transactionDTO.value());

        User receiver = userFindByIdService.apply(transactionDTO.receiverId());

        Transaction transaction = saveTrasaction(transactionDTO, sender, receiver);

        sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));

        userSaveService.apply(sender);
        userSaveService.apply(receiver);

        notificationTransactionService.accept(sender, TRANSACAO_SUCCESS);
        notificationTransactionService.accept(receiver, TRANSACAO_RECEIVED);

        return transaction;
    }

    private Transaction saveTrasaction(TransactionDTO transactionDTO, User sender, User receiver) {
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionDTO.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        return repository.save(newTransaction);
    }


}
