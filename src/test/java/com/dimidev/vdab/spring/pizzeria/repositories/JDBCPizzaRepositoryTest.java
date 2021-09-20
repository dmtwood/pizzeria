package com.dimidev.vdab.spring.pizzeria.repositories;

import com.dimidev.vdab.spring.pizzeria.domain.Pizza;

import com.dimidev.vdab.spring.pizzeria.exceptions.PizzaNotFoundException;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


/*

 */
@JdbcTest
@Import(JDBCPizzaRepository.class)
@Sql("/insertPizzas.sql")
public class JDBCPizzaRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

// MEMBER VARS

    private static final String PIZZAS = "pizzas";
    private static final String TEST_PIZZA = "test_pizza";

    private final JDBCPizzaRepository pizzaRepository;

// CONSTRUCTORS

    public JDBCPizzaRepositoryTest(JDBCPizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }


// TEST METHODS

    @Test
    void findPizzaCount() {
        assertThat(
                pizzaRepository.findPizzaCount()
        ).isEqualTo(
                super.countRowsInTable(PIZZAS)
        );
    }

    @Test
    void findAll_returnsAllPizzasSortedOnId() {
        assertThat(
                pizzaRepository.findAll()
        ).hasSize(
                super.countRowsInTable(PIZZAS)
        ).extracting(
                Pizza::getId
        ).isSorted();
    }

    @Test
    void create() {
        long createdId = pizzaRepository.create(new Pizza(0, TEST_PIZZA, BigDecimal.TEN, true));

        assertThat(
                createdId
        ).isPositive();

        assertThat(
                super.countRowsInTableWhere(PIZZAS, "id=" + createdId)
        ).isOne();
    }

    // we can use the id of the created TEST_PIZZA object in other tests
    private long idFromTestPizza() {
        return super.jdbcTemplate.queryForObject(
                "select id from pizzas where name = 'test'",
                Long.class
        );
    }

    @Test
    void delete() {
        long id = idFromTestPizza();
        pizzaRepository.delete(id);
        assertThat(
                super.countRowsInTableWhere(
                        PIZZAS,
                        "id=" + id
                )
        ).isZero();
    }

    @Test
    void findById() {
        long id = idFromTestPizza();
        assertThat(
                pizzaRepository.findById(id).get().getName()
        ).isEqualTo("test");
    }

    @Test
    void findByIdNonExisting() {
        assertThat(
                pizzaRepository.findById(-1)
        ).isEmpty();
    }

    // set the price of the testpizza to 1 and check if it is 1
    @Test
    void update() {
        long id = idFromTestPizza();
        Pizza testPizza = new Pizza(id, "test3", BigDecimal.ONE, false);
        pizzaRepository.update(testPizza);

        assertThat(
                super.jdbcTemplate.queryForObject(
                        "select price from pizzas where id=?",
                        BigDecimal.class,
                        id
                )
        ).isOne();
    }

    @Test
    void updateNonExistingThrowsCustomError() {
        assertThatExceptionOfType(PizzaNotFoundException.class)
                .isThrownBy(
                        () -> pizzaRepository.update(new Pizza(-1, "x", BigDecimal.ONE, true))
                );
    }

    @Test
    void findByPriceBetween() {
        assertThat(
                pizzaRepository.findByPriceBetween(BigDecimal.ONE, BigDecimal.TEN)
        ).hasSize(
                super.countRowsInTableWhere(PIZZAS, " price = 10")
        ).allSatisfy(
                pizza -> assertThat(pizza.getPrice()).isBetween(BigDecimal.ONE, BigDecimal.TEN)
        ).extracting(
                pizza -> pizza.getPrice()
        ).isSorted();
    }

    @Test
    void findUniquePrices() {
        assertThat(
                pizzaRepository.findUniquePrices()
        ).hasSize(
                super.jdbcTemplate.queryForObject(
                        "select count(distinct price) from pizzas",
                        Integer.class)
        ).doesNotHaveDuplicates()
        .isSorted();
    }

    @Test
    void findByPrice(){
        assertThat(pizzaRepository.findByPrice(BigDecimal.TEN)
        ).hasSize(
                super.countRowsInTableWhere(PIZZAS, "price=10")
        ).extracting(
                pizza -> pizza.getName().toLowerCase()
        ).isSorted();
    }

    @Test
    void findByIds(){
        long id = idFromTestPizza();

        assertThat(
                pizzaRepository.findByIds( Collections.singleton(id) )
        ).extracting(
                Pizza::getId
        ).containsOnly(id);
    }

    @Test
    void findByIdsEmptyCollection(){
        assertThat(
                pizzaRepository.findByIds( Collections.EMPTY_SET )
        ).isEmpty();
    }

    @Test
    void findByIdsNonExisting(){
        assertThat(
                pizzaRepository.findByIds( Collections.singleton(-1L) )
        ).isEmpty();
    }
}
