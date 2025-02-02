package ru.gb.shop.paymentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentAppApplication.class, args);
    }

}
