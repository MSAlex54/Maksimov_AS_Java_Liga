package com.liga.home_work.services;

import com.liga.home_work.dao.OrderDAO;
import com.liga.home_work.entitty.Customer;
import com.liga.home_work.entitty.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class OrderService {

    private OrderDAO dao;

    @Autowired
    public OrderService(OrderDAO dao) {
        this.dao = dao;
    }

    public Order getOrder(int id){
        return dao.getOrder(id);
    }

    public Collection<Order> getByCustomer(Customer customer){
        return dao.getAllByCustomer(customer);
    }
    public Collection<Order> getAll(){
        return dao.getAll();
    }


    public Order createOrder(Order order){
        return dao.save(order);
    }

}
