package com.liga.home_work.dao;

import com.liga.home_work.entitty.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

public interface CustomerDAO{
    RowMapper<Customer> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Customer(resultSet.getInt("customer_id"), resultSet.getString("name"), resultSet.getString("email_addres"), new ArrayList<>());
    };
    Customer getCustomer(int id);
    Collection<Customer> getAll();
    Customer save(Customer customer);
    Customer update(Customer customer);
    void delete(Customer customer);
}
