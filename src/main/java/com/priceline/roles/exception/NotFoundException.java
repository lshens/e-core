package com.priceline.roles.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotFoundException extends RuntimeException{
    private final Class<?> entity;
}
