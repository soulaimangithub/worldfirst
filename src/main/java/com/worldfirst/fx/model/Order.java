package com.worldfirst.fx.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Order {
    private Long id;
    private String userId;
    private OrderType orderType;
    private Currency currency;
    private BigDecimal price;
    private Long amount;

    public Order() {
    }

    public Order(String userId, OrderType orderType, Currency currency, BigDecimal price, Long amount) {
        this.userId = userId;
        this.orderType = orderType;
        this.currency = currency;
        this.price = price.setScale(4, RoundingMode.HALF_UP);
        this.amount = amount;
    }

    public Order(Long id, String userId, OrderType orderType, Currency currency, BigDecimal price, Long amount) {
        this.id = id;
        this.userId = userId;
        this.orderType = orderType;
        this.currency = currency;
        this.price = price.setScale(4, RoundingMode.HALF_UP);
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getAmount() {
        return amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isBid() {
        return OrderType.BID.equals(orderType);
    }

    public boolean isAsk() {
        return OrderType.ASK.equals(orderType);
    }
}
