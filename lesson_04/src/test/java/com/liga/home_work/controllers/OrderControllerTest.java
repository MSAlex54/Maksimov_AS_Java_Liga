package com.liga.home_work.controllers;

import com.liga.home_work.entitty.Order;
import com.liga.home_work.services.CustomerService;
import com.liga.home_work.services.OrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class OrderControllerTest {

    private OrderService orderService;
    private OrderController orderController;

    @Mock
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        orderService = mock(OrderService.class);
        orderController = new OrderController(orderService, mock(CustomerService.class));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createOrder() {
        Order order = mock(Order.class);
        Mockito.doReturn(order).when(orderService).createOrder(order);
        orderController.createOrder(order);
        verify(orderService.createOrder(order));
    }
}