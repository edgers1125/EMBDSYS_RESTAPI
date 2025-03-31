package com.database_testing.FirstSQLDatabaseConnect.mappers.impl;

import com.database_testing.FirstSQLDatabaseConnect.domain.DTO.TransactionDTO;
import com.database_testing.FirstSQLDatabaseConnect.domain.entities.TransactionEntity;
import com.database_testing.FirstSQLDatabaseConnect.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapperImpl implements Mapper<TransactionEntity, TransactionDTO> {
    private ModelMapper bookMapper;

    public TransactionMapperImpl(ModelMapper bookMapper){
        this.bookMapper = bookMapper;
    }

    @Override
    public TransactionDTO mapTo(TransactionEntity transactionEntity) {
        return bookMapper.map(transactionEntity, TransactionDTO.class);
    }

    @Override
    public TransactionEntity mapFrom(TransactionDTO transactionDTO) {
        return bookMapper.map(transactionDTO, TransactionEntity.class);
    }

}
