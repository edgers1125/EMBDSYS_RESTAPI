package com.database_testing.FirstSQLDatabaseConnect.services.impl;

import com.database_testing.FirstSQLDatabaseConnect.domain.DTO.TransactionDTO;
import com.database_testing.FirstSQLDatabaseConnect.domain.entities.TransactionEntity;
import com.database_testing.FirstSQLDatabaseConnect.domain.entities.UserEntity;
import com.database_testing.FirstSQLDatabaseConnect.repositories.TransactionRepository;
import com.database_testing.FirstSQLDatabaseConnect.repositories.UserRepository;
import com.database_testing.FirstSQLDatabaseConnect.services.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private UserRepository userRepository;
    public TransactionServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository){
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }


    @Override
    public TransactionEntity createTransaction(TransactionDTO.createInput createInput) {
        UserEntity userEntity = userRepository.findById(createInput.getRfidcode()).map(foundauthor->{
            UserEntity userEntity1 = foundauthor;
            return userEntity1;
        }).orElse(null);

        TransactionEntity transactionEntity = TransactionEntity.builder()
                .id(null)
                .amount(createInput.getAmount())
                .userEntity(userEntity)
                .build();
        return transactionRepository.save(transactionEntity);
    }

    @Override
    public List<TransactionEntity> getAllTransactions() {
        return StreamSupport.stream(transactionRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public void deleteAllTransactions() {
        transactionRepository.deleteAll();
    }
}
