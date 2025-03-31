package com.database_testing.FirstSQLDatabaseConnect.mappers;

public interface Mapper<A,B> {
    B mapTo(A a);
    A mapFrom(B b);

}
