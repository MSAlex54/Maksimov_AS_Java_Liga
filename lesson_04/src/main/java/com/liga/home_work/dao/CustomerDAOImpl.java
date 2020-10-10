package com.liga.home_work.dao;

import com.liga.home_work.entitty.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomerDAOImpl implements CustomerDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Customer getCustomer(int id) {
        Customer customer = jdbcTemplate.queryForObject("select * from table_customers where CUSTOMER_ID = ?", new Object[]{id}, ROW_MAPPER);
        return customer;
    }

    @Override
    public Collection<Customer> getAll() {
        return null;
    }

    @Override
    public Customer save(Customer customer) {
        return null;
    }

    @Override
    public Customer update(Customer customer) {
        return null;
    }

    @Override
    public void delete(Customer customer) {

    }




}
