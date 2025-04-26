package com.stores.bookstore.controller;

import com.stores.bookstore.model.Order;
import com.stores.bookstore.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{userId}")
    public List<Order> getAllOrdersByUserId(@PathVariable int userId) {
        return orderService.getAllOrderByUserId(userId);
    }

    @PostMapping("/{userId}")
    public Order addOrder(@PathVariable int userId) {
        return orderService.addNewOrder(userId);
    }

}
