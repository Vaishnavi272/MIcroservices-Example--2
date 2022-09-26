package com.order_service.service;

import com.order_service.common.Payment;
import com.order_service.common.TransactionRequest;
import com.order_service.common.TransactionResponse;
import com.order_service.entity.Order;
import com.order_service.repository.OrderRepository;
import jdk.jfr.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService
{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String endpoints_urls;

    public TransactionResponse saveOrder(TransactionRequest transactionRequest)

    {
        String response="";
        Order order=transactionRequest.getOrder();
        Payment payment=transactionRequest.getPayment();

//        payment.setOrderId(order.getOrd_Id());
          payment.setOrderId(order.getOrderId());
        payment.setAmount(order.getPrice());

        //rest call
       Payment payment1= restTemplate.postForObject(endpoints_urls,payment, Payment.class);

       response=payment1.getPaymentStatus().equals("success")?"payment Processing Successfull and order Placed":"there is a failure in payment api ,order added to cart";

       orderRepository.save(order);
       return new TransactionResponse(order, payment1.getAmount(),payment1.getTransaction(),response);
    }

}
