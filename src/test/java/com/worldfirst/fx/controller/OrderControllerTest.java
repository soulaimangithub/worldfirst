package com.worldfirst.fx.controller;


import com.worldfirst.fx.model.Order;
import com.worldfirst.fx.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void getShouldReturnOrdersMessage() throws Exception {
        when(orderService.retrieveAll()).thenReturn(new ArrayList<Order>());

        mockMvc.perform(get("/orders")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getNotMatchedShouldReturnNotMatchedOrdersMessage() throws Exception {
        when(orderService.retrieveAll()).thenReturn(new ArrayList<Order>());

        mockMvc.perform(get("/orders/notmatched")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getMatchedShouldReturnMatchedOrdersMessage() throws Exception {
        when(orderService.retrieveAll()).thenReturn(new ArrayList<Order>());

        this.mockMvc.perform(get("/orders/matched")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteShouldReturnOkStatus() throws Exception {
        when(orderService.delete(any(Long.class))).thenReturn(true);

        String anOrderId = "1";
        this.mockMvc.perform(delete("/orders/{id}", anOrderId))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNotFoundOrderShouldReturnNotFoundStatus() throws Exception {
        when(orderService.delete(any(Long.class))).thenReturn(false);

        String anOrderId = "1";
        this.mockMvc.perform(delete("/orders/{id}", anOrderId))
                .andExpect(status().isNotFound());
    }


    @Test
    public void postOrderShouldReturnOkStatus() throws Exception {
        String aJsonOrder = "{\"userId\":\"userId1\",\"orderType\":\"ASK\",\"currency\":\"GBP\",\"price\":100,\"amount\":20}";
        when(orderService.create(any(Order.class))).thenReturn(new Order());

        this.mockMvc.perform((post("/orders")
                .content(aJsonOrder)
                .contentType(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk());
    }
}
