package com.liga.home_work.dao;

import com.liga.home_work.entitty.Order;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.ArrayList;
import java.util.List;

class OrderDAOImplTest {

    private EmbeddedDatabase embeddedDatabase;

    private JdbcTemplate jdbcTemplate;

    private OrderDAO dao;

    @BeforeEach
    public void setUp(){
        // Создадим базу данных для тестирования
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addScript("data.sql")
                .setType(EmbeddedDatabaseType.H2)
                .build();

        // Создадим JdbcTemplate
        jdbcTemplate = new JdbcTemplate(embeddedDatabase);

        // Создадим OrderDAO
        dao = new OrderDAOImpl(jdbcTemplate);
    }

    @AfterEach
    public void endUIp(){
        embeddedDatabase.shutdown();
    }

    @Test
    public void testGetAll() {

        List<Order> expect = new ArrayList<>();
        expect.add(new Order(1,"First Order", 100, 1));
        expect.add(new Order(2,"Second Order", 110, 2));
        expect.add(new Order(3,"Third Order", 120, 1));
        expect.add(new Order(4,"Fourth Order", 130, 1));

        Assert.assertNotNull(dao.getAll()); //тестируем на null

        Assert.assertEquals(4, dao.getAll().size()); // тестируем на объем данных

        Assert.assertEquals(expect,dao.getAll()); // сравниваем данные

    }

    @Test
    void save() {
        List<Order> expect = new ArrayList<>();
        expect.add(new Order(1,"First Order", 100, 1));
        expect.add(new Order(2,"Second Order", 110, 2));
        expect.add(new Order(3,"Third Order", 120, 1));
        expect.add(new Order(4,"Fourth Order", 130, 1));

        expect.add(new Order(5,"Fifth Order", 140, 1));

        dao.save(new Order(5,"Fifth Order", 140, 1));


        Assert.assertEquals(expect,dao.getAll()); // тестируем полученные данные и то что в БД

    }
}