package com.database_testing.FirstSQLDatabaseConnect;

import com.database_testing.FirstSQLDatabaseConnect.domain.DTO.UserDTO;
import com.database_testing.FirstSQLDatabaseConnect.domain.DTO.TransactionDTO;
import com.database_testing.FirstSQLDatabaseConnect.domain.entities.UserEntity;
import com.database_testing.FirstSQLDatabaseConnect.domain.entities.TransactionEntity;

public class TestDataUtil {
    private TestDataUtil(){

    }
    public static UserEntity createTestAuthor1() {
        return UserEntity.builder()
                .id(1L)
                .name("Noogers")
                .age(32)
                .build();
    }
    public static UserEntity createTestAuthor1upd() {
        return UserEntity.builder()
                .id(1L)
                .name("noopie")
                .age(12)
                .build();
    }
    public static UserEntity createTestAuthor2() {
        return UserEntity.builder()
                .id(2L)
                .name("Nawoot")
                .age(19)
                .build();
    }
    public static UserEntity createTestAuthor3() {
        return UserEntity.builder()
                .id(3L)
                .name("niglet")
                .age(21)
                .build();
    }

    public static TransactionDTO CreateTestBook(final UserDTO userDTO) {
        return TransactionDTO.builder()
                .isbn("ASD123")
                .title("The revenge of the nigger")
                .author(userDTO)
                .build();
    }
    public static TransactionEntity CreateTestBook2(final UserEntity userEntity) {
        return TransactionEntity.builder()
                .isbn("NANDo231")
                .title("The revenge of the nigger")
                .authorEntity(userEntity)
                .build();
    }
    public static TransactionEntity CreateTestBook3(final UserEntity userEntity) {
        return TransactionEntity.builder()
                .isbn("TRiple3")
                .title("The revenge of the nigger")
                .authorEntity(userEntity)
                .build();
    }
    public static TransactionEntity CreateTestBook4(final UserEntity userEntity) {
        return TransactionEntity.builder()
                .isbn("Nentowaps")
                .title("The revenge of the nigger")
                .authorEntity(userEntity)
                .build();
    }

    public static UserDTO createTestDTOauthor1() {
        return UserDTO.builder()
                .id(null)
                .name("Noogers")
                .age(32)
                .build();
    }
}
