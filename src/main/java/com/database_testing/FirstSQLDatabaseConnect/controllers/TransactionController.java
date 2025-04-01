package com.database_testing.FirstSQLDatabaseConnect.controllers;

import com.database_testing.FirstSQLDatabaseConnect.domain.DTO.TransactionDTO;
import com.database_testing.FirstSQLDatabaseConnect.domain.entities.TransactionEntity;
import com.database_testing.FirstSQLDatabaseConnect.mappers.Mapper;
import com.database_testing.FirstSQLDatabaseConnect.services.TransactionService;
import com.database_testing.FirstSQLDatabaseConnect.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TransactionController {
    private TransactionService transactionService;
    private UserService userService;
    private Mapper<TransactionEntity, TransactionDTO> transactionMapper;

    @Autowired
    public TransactionController(TransactionService transactionService, Mapper<TransactionEntity, TransactionDTO> transactionMapper,UserService userService){
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
        this.userService = userService;
    }
    @PostMapping("/transactions")
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO.createInput createInput){
        if(!userService.userExists(createInput.getRfidcode())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(!userService.sufficientBalance(createInput.getAmount(),createInput.getRfidcode())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userService.deductBalance(createInput.getRfidcode(),createInput.getAmount());
        TransactionEntity transactionEntity = transactionService.createTransaction(createInput);
        return new ResponseEntity<>(transactionMapper.mapTo(transactionEntity),HttpStatus.CREATED);

    }
    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions(){
        List<TransactionDTO> DTOList = transactionService.getAllTransactions().stream().map(transactionMapper::mapTo).collect(Collectors.toList());
        return new ResponseEntity<>(DTOList,HttpStatus.OK);
    }
    @DeleteMapping("/transactions")
    public ResponseEntity<?> deleteAllTransactions(){
        transactionService.deleteAllTransactions();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
