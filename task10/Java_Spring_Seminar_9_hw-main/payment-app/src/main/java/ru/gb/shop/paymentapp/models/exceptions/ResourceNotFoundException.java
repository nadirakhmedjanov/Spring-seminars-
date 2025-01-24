package ru.gb.shop.paymentapp.models.exceptions;

/**
 * Отсутствие переданного счета.
 */
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
