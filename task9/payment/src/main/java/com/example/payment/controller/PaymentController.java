package com.example.payment.controller;

import com.example.payment.config.AccountConfig;

import com.example.payment.domain.Payment;

import com.example.payment.service.PaymentService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Контроллер для работы с платежами.
 */
@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    @Autowired
    private AccountConfig accountConfig;

    private final Counter requestTransferFundsCounter = Metrics.counter(
            "request_transfer_funds_count");

   
    @PostMapping("/transfer")
    public ResponseEntity<Payment> transferFunds(@RequestParam Long fromAccountId,
                                              @RequestParam(required = false) Long toAccountId,
                                              @RequestParam BigDecimal amount,
                                              @RequestParam Long reservationId) {
        if (toAccountId == null) {
            toAccountId = Long.parseLong(accountConfig.getToAccountId());
        }
        requestTransferFundsCounter.increment();
        return ResponseEntity.ok(paymentService.transferFunds(
                fromAccountId, toAccountId, amount, reservationId));
    }

    
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments(){
        return ResponseEntity.ok(paymentService.getAllPayments());
    }
}
