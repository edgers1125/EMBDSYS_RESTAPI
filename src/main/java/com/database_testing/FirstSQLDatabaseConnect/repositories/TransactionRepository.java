package com.database_testing.FirstSQLDatabaseConnect.repositories;

import com.database_testing.FirstSQLDatabaseConnect.domain.entities.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
}
