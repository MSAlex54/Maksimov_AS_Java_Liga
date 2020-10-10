package com.liga.home_work.dao;

import com.liga.home_work.entitty.Customer;
import com.liga.home_work.entitty.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class OrderDAOImpl implements OrderDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Order getOrder(int id) {
        Order order = jdbcTemplate.queryForObject("select * from table_orders where order_id = ?", new Object[]{id}, ROW_MAPPER);
        return order;
    }

    @Override
    public List<Order>  getAll() {
        return jdbcTemplate.query("select * from table_orders", new Object[]{}, ROW_MAPPER);
    }

    @Override
    public List<Order> getAllByCustomer(Customer customer) {
        return jdbcTemplate.query("select * from table_orders WHERE CUSTOMER_ID = ?",new Object[]{customer.getId()}, ROW_MAPPER);
    }

    @Override
    public Order save(Order order) {
        jdbcTemplate.update("insert into table_orders (ORDER_ID, NAME , PRICE, CUSTOMER_ID) values (?, ?, ?, ?)", order.getId(), order.getName(), order.getPrice(), order.getCustomer_id());
        return getOrder(order.getId());
    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public void delete(Order order) {

    }


}
