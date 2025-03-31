package com.database_testing.FirstSQLDatabaseConnect.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(nullable = false, unique = true)
    private String rfidcode;

    private String name;

    private Integer balance;
}
