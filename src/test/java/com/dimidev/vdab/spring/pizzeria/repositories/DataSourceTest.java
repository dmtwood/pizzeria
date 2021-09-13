package com.dimidev.vdab.spring.pizzeria.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@JdbcTest
public class DataSourceTest {

// MEMBER VARS

    private final DataSource dataSource;

// CONSTRUCTORS

    public DataSourceTest(DataSource dataSource) {
        this.dataSource = dataSource;
    }


// TEST METHODS

    @Test
    void getConnection_succeeds() throws SQLException {
        try (Connection connection = dataSource.getConnection() ) {

        }
    }

// OVERRIDDEN METHODS

}
