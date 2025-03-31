package com.database_testing.FirstSQLDatabaseConnect.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO {
    private String isbn;
    private LocalDateTime dateTime;
    private UserDTO user;
}
