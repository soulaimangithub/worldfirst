package com.worldfirst.fx.service;

import com.worldfirst.fx.model.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    List<Order> retrieveAll();

    Order create(Order order);

    boolean delete(Long id);

    List<Order> retrieveAllNotMatched();

    List<Order> retrieveAllMatched();

    boolean isTradePriceAndOrderPriceMatched(Order order, BigDecimal tradePrice);

}
