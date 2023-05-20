package com.priceline.ecore.roles.exception;

import com.priceline.ecore.roles.model.Role;
import com.priceline.ecore.roles.service.client.dto.TeamDTO;
import com.priceline.ecore.roles.service.client.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    private static final Map<Class<?>, String> NOT_FOUND_DETAILS = Map.of(
            Role.class, "The role does not exists",
            UserDTO.class, "The user does not exists",
            TeamDTO.class, "The team does not exists"
    );

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFound(NotFoundException ex) {
        AtomicReference<String> body = new AtomicReference<>(ex.getMessage());
        Optional.ofNullable(ex.getEntity())
                .ifPresent(key -> body.set(NOT_FOUND_DETAILS.get(key)));
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(body.get());
    }

    @ExceptionHandler(value = {ConstraintViolationException.class, ConflictException.class})
    public ResponseEntity<?> conflict(RuntimeException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
