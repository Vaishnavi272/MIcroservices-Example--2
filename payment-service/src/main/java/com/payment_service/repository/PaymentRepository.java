package com.payment_service.repository;

import com.payment_service.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PaymentRepository  extends JpaRepository<Payment,Long> {
    Payment findByOrderId(Long orderId);
}
