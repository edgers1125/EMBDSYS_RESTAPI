package com.database_testing.FirstSQLDatabaseConnect.controllers;

import com.database_testing.FirstSQLDatabaseConnect.domain.DTO.TransactionDTO;
import com.database_testing.FirstSQLDatabaseConnect.domain.entities.TransactionEntity;
import com.database_testing.FirstSQLDatabaseConnect.mappers.Mapper;
import com.database_testing.FirstSQLDatabaseConnect.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    private TransactionService transactionService;
    private Mapper<TransactionEntity, TransactionDTO> transactionMapper;

    @Autowired
    public TransactionController(TransactionService transactionService, Mapper<TransactionEntity, TransactionDTO> transactionMapper){
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }
    @PostMapping("/transactions")
    public

}
