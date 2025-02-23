package com.practice.cardcollection;

public class OrderNotFoundException extends RuntimeException {
    OrderNotFoundException(Long id) {
        super(String.format("Order with id %s not found", id));
    }
}
