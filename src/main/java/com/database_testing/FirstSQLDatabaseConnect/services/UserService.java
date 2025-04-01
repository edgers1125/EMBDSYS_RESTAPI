package com.database_testing.FirstSQLDatabaseConnect.services;

import com.database_testing.FirstSQLDatabaseConnect.domain.entities.UserEntity;
import com.database_testing.FirstSQLDatabaseConnect.domain.entities.TransactionEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserService {

    UserEntity createUser(UserEntity userEntity, String rfidcode);

    List<UserEntity> getAllUsers();

    boolean userExists(String rfidcode);

    boolean sufficientBalance(Integer amount, String rfidcode);

    Optional<UserEntity> getUser(String rfidcode);

    void deductBalance(String rfidcode, Integer amount);

    void removeUser(String rfidcode);

    void deleteAllUsers();
}
