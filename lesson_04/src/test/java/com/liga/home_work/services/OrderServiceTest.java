package com.liga.home_work.services;

import com.liga.home_work.dao.OrderDAO;
import com.liga.home_work.entitty.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class OrderServiceTest {

    private OrderDAO dao;

    private OrderService service;

    @BeforeEach
    public void setUp(){
        dao = mock(OrderDAO.class);
        service = new OrderService(dao);
    }

    @Test
    void createOrder() {
        Order order = mock(Order.class);
        Mockito.doReturn(order).when(dao).save(order);
        service.createOrder(order);
        verify(dao.save(order));

    }
}