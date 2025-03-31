package com.database_testing.FirstSQLDatabaseConnect.services.impl;

import com.database_testing.FirstSQLDatabaseConnect.domain.entities.UserEntity;
import com.database_testing.FirstSQLDatabaseConnect.domain.entities.TransactionEntity;
import com.database_testing.FirstSQLDatabaseConnect.repositories.UserRepository;
import com.database_testing.FirstSQLDatabaseConnect.repositories.TransactionRepository;
import com.database_testing.FirstSQLDatabaseConnect.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository, TransactionRepository transactionRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity createUser(UserEntity userEntity, String rfidcode) {
        return userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return StreamSupport.stream(
        userRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public boolean userExists(String rfidcode) {
        return userRepository.existsById(rfidcode);
    }
}
