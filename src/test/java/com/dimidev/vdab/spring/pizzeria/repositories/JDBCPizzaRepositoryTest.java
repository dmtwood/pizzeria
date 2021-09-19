package com.dimidev.vdab.spring.pizzeria.repositories;


import com.dimidev.vdab.spring.pizzeria.domain.Pizza;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@JdbcTest
@Import(JDBCPizzaRepository.class)
@Sql("/insertPizzas.sql")
public class JDBCPizzaRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

// MEMBER VARS

    private static final String PIZZAS = "pizzas";
    private final JDBCPizzaRepository pizzaRepository;

// CONSTRUCTORS

    public JDBCPizzaRepositoryTest(JDBCPizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }


// TEST METHODS

    @Test
    void findPizzaCount(){
        assertThat(
                pizzaRepository.findPizzaCount()
        ).isEqualTo(
                super.countRowsInTable(PIZZAS)
        );
    }

    @Test
    void findAll_returnsAllPizzasSortedOnId(){
        assertThat(
                pizzaRepository.findAll()
        ).hasSize(
                super.countRowsInTable(PIZZAS)
        ).extracting(
                Pizza::getId
        ).isSorted();
    }

// OVERRIDDEN METHODS

}
