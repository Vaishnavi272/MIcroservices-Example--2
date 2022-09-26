package com.payment_service.service;

import com.payment_service.entity.Payment;
import com.payment_service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service

public class PaymentService
{

     @Autowired
    private PaymentRepository paymentRepository;

     public Payment doPayment(Payment payment)
     {
         payment.setPaymentStatus(paymentProcessing());
         payment.setTransaction(UUID.randomUUID().toString());
         return  paymentRepository.save(payment);
     }
    public String paymentProcessing()
    {
        //api should be third party payment gateway
        return new Random().nextBoolean() ? "success" : "false";
    }

    public Payment findPaymentHistoryByOrderId(Long orderId)
    {
        return  paymentRepository.findByOrderId(orderId);
    }
}
