package com.database_testing.FirstSQLDatabaseConnect.repositories;


import com.database_testing.FirstSQLDatabaseConnect.domain.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
}
