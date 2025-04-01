package com.database_testing.FirstSQLDatabaseConnect.services;

import com.database_testing.FirstSQLDatabaseConnect.domain.DTO.TransactionDTO;
import com.database_testing.FirstSQLDatabaseConnect.domain.entities.TransactionEntity;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    TransactionEntity createTransaction(TransactionDTO.createInput createInput);

    List<TransactionEntity> getAllTransactions();

    void deleteAllTransactions();

}
