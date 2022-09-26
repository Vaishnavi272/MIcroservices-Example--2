package com.order_service.controller;

import com.order_service.common.Payment;
import com.order_service.common.TransactionRequest;
import com.order_service.common.TransactionResponse;
import com.order_service.entity.Order;
import com.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order/")
public class OrderController
{
    @Autowired
    private OrderService orderService;

    @PostMapping("/bookOrders")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest transactionRequest)
    {


        return orderService.saveOrder(transactionRequest);

        //do a rest call to payment and pass the order id
    }

}
