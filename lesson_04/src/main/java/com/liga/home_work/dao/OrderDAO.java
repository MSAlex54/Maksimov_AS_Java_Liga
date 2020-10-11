package com.liga.home_work.dao;


import com.liga.home_work.entitty.Customer;
import com.liga.home_work.entitty.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public interface OrderDAO {
    RowMapper<Order> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Order(resultSet.getInt("order_id"), resultSet.getString("name"), resultSet.getInt("price"), resultSet.getInt("customer_id"));
    };
    Order getOrder(int id);
    List<Order> getAll();
    List<Order> getAllByCustomer(Customer customer);
    Order save(Order order);
    Order update(Order order);
    void delete(Order order);
}
