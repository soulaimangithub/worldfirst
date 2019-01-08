package com.worldfirst.fx.service;

import com.worldfirst.fx.model.Currency;
import com.worldfirst.fx.model.Order;
import com.worldfirst.fx.model.OrderType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void askOrderPriceLessThanTradePriceShouldReturnTrue() {
        //given
        BigDecimal tradePrice = new BigDecimal(1.210);
        BigDecimal askPrice = new BigDecimal(1);
        Order anAskOrder = new Order("userId1", OrderType.ASK, Currency.GBP, askPrice, 20L);

        //when
        boolean sut = orderService.isTradePriceAndOrderPriceMatched(anAskOrder, tradePrice);

        //then
        Assert.assertEquals(true, sut);
    }


    @Test
    public void bidOrderPriceMoreThanTradePriceShouldReturnTrue() {
        //given
        BigDecimal tradePrice = new BigDecimal(1.210);
        BigDecimal bidPrice = new BigDecimal(1.220);
        Order aBidOrder = new Order("userId1", OrderType.BID, Currency.GBP, bidPrice, 20L);

        //when
        boolean sut = orderService.isTradePriceAndOrderPriceMatched(aBidOrder, tradePrice);

        //then
        Assert.assertEquals(true, sut);
    }


    @Test
    public void bidOrderPriceEqualTradePriceShouldReturnTrue() {
        //given
        BigDecimal tradePrice = new BigDecimal(1.210).setScale(4, RoundingMode.HALF_UP);;
        BigDecimal askPrice = tradePrice;
        Order aBidOrder = new Order("userId1", OrderType.ASK, Currency.GBP, askPrice, 20L);

        //when
        boolean sut = orderService.isTradePriceAndOrderPriceMatched(aBidOrder, tradePrice);

        //then
        Assert.assertEquals(true, sut);
    }

    @Test
    public void askOrderPriceEqualTradePriceShouldReturnTrue() {
        //given
        BigDecimal tradePrice = new BigDecimal(1.210).setScale(4, RoundingMode.HALF_UP);;
        BigDecimal askPrice = tradePrice;
        Order anAskOrder = new Order("userId1", OrderType.ASK, Currency.GBP, askPrice, 20L);

        //when
        boolean sut = orderService.isTradePriceAndOrderPriceMatched(anAskOrder, tradePrice);

        //then
        Assert.assertEquals(true, sut);
    }
}
