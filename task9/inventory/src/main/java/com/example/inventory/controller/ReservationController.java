package com.example.inventory.controller;

import com.example.inventory.domain.Reservation;
import com.example.inventory.exeption.NotEnoughQuantityException;
import com.example.inventory.service.ReservationService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    private final Counter requestCreateReservationCounter = Metrics.counter(
            "request_create_reservation_count");
    private final Timer createReservationTimer = Metrics.timer("request_create_reservation_duration");

    
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    
    @GetMapping("/products/{productId}")
    public ResponseEntity<List<Reservation>> getReservationsByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(reservationService.getReservationsByProductId(productId));
    }

    
    @PostMapping("/products/{productId}")
    public ResponseEntity<?> createReservation(@PathVariable Long productId,
                                               @RequestParam int quantity) {
        createReservationTimer.record(() -> {
            try {
                Reservation reservation = reservationService.createReservation(productId, quantity);
                return ResponseEntity.ok(reservation);
            } catch (NotEnoughQuantityException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(e.getMessage());
            }  finally {
                requestCreateReservationCounter.increment();
            }
        });


        return null;
    }

    
    @PutMapping("/{reservationId}/payment/{paymentId}")
    public ResponseEntity<Void> updatePaymentId(@PathVariable Long reservationId,
                                                @PathVariable Long paymentId) {
        reservationService.updateReservationPaymentId(reservationId, paymentId);
        return ResponseEntity.ok().build();
    }

}
