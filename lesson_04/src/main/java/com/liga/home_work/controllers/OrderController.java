package com.liga.home_work.controllers;

import com.liga.home_work.entitty.Customer;
import com.liga.home_work.entitty.Order;
import com.liga.home_work.services.CustomerService;
import com.liga.home_work.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private OrderService orderService;
    private CustomerService customerService;

    @Autowired
    public OrderController(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @PostMapping("/api/order")
    public Order createOrder(Order order){
        Customer customer = customerService.getCustomer(1);
//        if (order==null) {
//            order = new Order(5,"Fifth Order", 130, customer.getId());
//        }
        orderService.createOrder(order);
        return order;
    }

    @GetMapping("/api/order")
    public Order getOrder(){
        return orderService.getOrder(1);
    }

}
