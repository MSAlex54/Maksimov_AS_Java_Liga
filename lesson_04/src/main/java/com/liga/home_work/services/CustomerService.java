package com.liga.home_work.services;

import com.liga.home_work.dao.CustomerDAO;
import com.liga.home_work.entitty.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private CustomerDAO customerDAO;

    @Autowired
    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }
    public Customer getCustomer (int id){
        return customerDAO.getCustomer(id);
    }

}
