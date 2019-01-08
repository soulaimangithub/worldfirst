package com.worldfirst.fx.repository;

import com.worldfirst.fx.model.Currency;
import com.worldfirst.fx.model.Order;
import com.worldfirst.fx.model.OrderType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryStub {
    private static List<Order> orders = new ArrayList<>();
    private static Long idIndex = 0L;

    static {
        orders.add(new Order(getNextIdIndex(), "userId1", OrderType.ASK, Currency.GBP, new BigDecimal(1), 20L));
        orders.add(new Order(getNextIdIndex(), "userId2", OrderType.BID, Currency.GBP, new BigDecimal(1.343), 20L));
        orders.add(new Order(getNextIdIndex(), "userId3", OrderType.ASK, Currency.USD, new BigDecimal(1.324), 20L));
        orders.add(new Order(getNextIdIndex(), "userId4", OrderType.BID, Currency.USD, new BigDecimal(0.324), 20L));
    }

    private static Long getNextIdIndex() {
        return idIndex++;
    }

    public static List<Order> retrieveAll() {
        return orders;
    }


    public static Order create(Order order) {
        order.setId(getNextIdIndex());
        orders.add(order);
        return order;
    }

    public static boolean delete(Long id) {
        return orders.removeIf(order -> id.equals(order.getId()));
    }
}

