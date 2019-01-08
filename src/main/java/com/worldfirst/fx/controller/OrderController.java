package com.worldfirst.fx.controller;

import com.worldfirst.fx.model.Order;
import com.worldfirst.fx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public List<Order> retrieveAll() {
        return orderService.retrieveAll();
    }

    @PostMapping("/orders")
    public Order create(@RequestBody Order order) {
        return orderService.create(order);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return orderService.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/orders/notmatched")
    public List<Order> retrieveAllNotMatched() {
        return orderService.retrieveAllNotMatched();
    }

    @GetMapping("/orders/matched")
    public List<Order> retrieveAllMatched() {
        return orderService.retrieveAllMatched();
    }

}
