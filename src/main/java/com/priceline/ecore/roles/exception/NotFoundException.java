package com.priceline.ecore.roles.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
public class NotFoundException extends RuntimeException {
    @Getter
    private Class<?> entity;

    public NotFoundException(String message) {
        super(message);
    }
}
