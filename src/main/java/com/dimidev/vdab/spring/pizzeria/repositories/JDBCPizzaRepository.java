package com.dimidev.vdab.spring.pizzeria.repositories;

import com.dimidev.vdab.spring.pizzeria.domain.Pizza;
import com.dimidev.vdab.spring.pizzeria.exceptions.PizzaNotFoundException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class JDBCPizzaRepository implements PizzaRepository {

// MEMBER VARS

    /*
JdbcTemplate:
o	spring class makes Connections, Statements & ResultSets,
o	iterates ResultSets
o	closes all
o	JdbcTemplate bean   (JDBC dependency in pom.xml)
o	spring injects the DataSource-bean in JdbcTemplate
     */
    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert jdbcInsert;

    // functional interface (with mapRow()) -> implemented to convert a db-row to a Pizza object
    private final RowMapper<Pizza> pizzaRowMapper = (result, rowNr)
            -> new Pizza(
            result.getLong("id"),
            result.getString("name"),
            result.getBigDecimal("price"),
            result.getBoolean("spicy")
            );

    private final RowMapper<BigDecimal> priceMapper = (result, rowNr)
            -> result.getBigDecimal("price");

// CONSTRUCTORS

    public JDBCPizzaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("pizzas");
        jdbcInsert.usingGeneratedKeyColumns("id");
    }


// OVERRIDDEN METHODS

    @Override
    public long create(Pizza pizza) {
        Map<String, Object> columnValues = new HashMap<>();
        columnValues.put("name", pizza.getName());
        columnValues.put("price", pizza.getPrice());
        columnValues.put("spicy", pizza.isSpicy());
        Number id = jdbcInsert.executeAndReturnKey(columnValues);
        return id.longValue();
    }


    // update is executed in the if clause and returns nr of rows affected -> 1 if successful
    @Override
    public void update(Pizza pizza) {
        if (
                jdbcTemplate.update(
                        "update pizzas set name=?, price=?, spicy=? where id=?",
                        pizza.getName(),
                        pizza.getPrice(),
                        pizza.isSpicy(),
                        pizza.getId()

                ) == 0
        ) throw new PizzaNotFoundException();
    }


    @Override
    public void delete(long id) {
        jdbcTemplate.update(
                "delete from pizzas where id =?",
                id
        );
    }


    // use queryForObject() to read one record as a POJO
    @Override
    public Optional<Pizza> findById(long id) {
        String findByIdQuery = "select id, name, price, spicy from pizzas where id=?";
        try {
           return Optional.of(jdbcTemplate.queryForObject( findByIdQuery, pizzaRowMapper, id ) );
        } catch (IncorrectResultSizeDataAccessException exception) {
            return Optional.empty();
        }
    }


    @Override
    public List<Pizza> findAll() {
        String findAllQuery = "select id, name, price, spicy from pizzas order by id";
        return jdbcTemplate.query( findAllQuery, pizzaRowMapper );
    }


    // query() can take parameters
    @Override
    public List<Pizza> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        String findByPriceBetweenQuery = "select id, name, price, spicy from pizzas where price between ? and ?";
        return jdbcTemplate.query( findByPriceBetweenQuery, pizzaRowMapper, minPrice, maxPrice );
    }


    @Override
    public long findPizzaCount() {
        return jdbcTemplate.queryForObject(
                "select count(*) from pizzas",
                Long.class
        );
    }


    @Override
    public List<BigDecimal> findUniquePrices() {
        String findUniquePricesQuery = "select distinct price from pizzas order by price";
        return jdbcTemplate.query( findUniquePricesQuery, priceMapper );
    }


    @Override
    public List<Pizza> findByPrice(BigDecimal wantedPrice) {
        String findbyPriceQuery = "select id, name, price, spicy from pizzas where price = ? order by name";
        return jdbcTemplate.query(findbyPriceQuery, pizzaRowMapper, wantedPrice);
    }


    @Override
    public List<Pizza> findByIds(Set<Long> ids) {
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }
        StringBuilder findByIdsQueryBuilder = new StringBuilder("select id,name,price,spicy from pizzas where id in (");
        for (long id : ids) {
            findByIdsQueryBuilder.append("?,");
        }
        findByIdsQueryBuilder.setCharAt(findByIdsQueryBuilder.length() - 1, ')');
        findByIdsQueryBuilder.append(" order by id");
        String findByIdsQuery = findByIdsQueryBuilder.toString();
        return jdbcTemplate.query(findByIdsQuery, pizzaRowMapper, ids.toArray());
    }



}
