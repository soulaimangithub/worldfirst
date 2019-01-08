package com.worldfirst.fx.service;

import com.worldfirst.fx.model.Order;
import com.worldfirst.fx.repository.OrderRepositoryStub;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {

    @Value("${trade.price}")
    private String tradePrice;

    @Override
    public List<Order> retrieveAll() {
        return OrderRepositoryStub.retrieveAll();
    }

    @Override
    public Order create(Order order) {
        return OrderRepositoryStub.create(order);
    }

    @Override
    public boolean delete(Long id) {
        return OrderRepositoryStub.delete(id);
    }

    @Override
    public List<Order> retrieveAllNotMatched() {
        return OrderRepositoryStub
                .retrieveAll()
                .stream()
                .filter(order -> !isTradePriceAndOrderPriceMatched(order, new BigDecimal(tradePrice)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> retrieveAllMatched() {
        return OrderRepositoryStub
                .retrieveAll()
                .stream()
                .filter(order -> isTradePriceAndOrderPriceMatched(order, new BigDecimal(tradePrice)))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isTradePriceAndOrderPriceMatched(Order order, BigDecimal tradePrice) {
        if (order.isAsk()) {
            //if ask price < trade price return true
            return tradePrice.compareTo(order.getPrice()) >= 0;
        } else {
            //if bid price > trade price return true
            return tradePrice.compareTo(order.getPrice()) <= 0;
        }
    }

}
