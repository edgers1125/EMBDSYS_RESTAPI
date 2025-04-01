package com.database_testing.FirstSQLDatabaseConnect.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO {
    private String id;
    private Integer amount;
    private LocalDateTime dateTime;
    private UserDTO user;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class createInput {
        private String rfidcode;
        private Integer amount;
    }
}
