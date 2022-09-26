package com.order_service.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Payment
{

    private Long paymentId;
    private String paymentStatus;
    private String transaction;
    private Long orderId;
    private double amount;

}
