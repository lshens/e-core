package com.priceline.ecore.roles.integration.tests.controller;

import com.priceline.ecore.roles.controller.dto.RoleDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class RoleControllerIntegrationTest extends AbstractControllerIntegrationTest {

    @Test
    void create() {
        client.post().uri("/v1/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(RoleDTO.builder().name("integration.test").build())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(RoleDTO.class).consumeWith(actual -> {
                    RoleDTO dto = actual.getResponseBody();
                    assertNotNull(dto);
                    assertEquals("integration.test", dto.getName());
                });
    }

    @Test
    void createBlank() {
        client.post().uri("/v1/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void createLargeName() {
        RoleDTO body = RoleDTO.builder().name("Pedro de Alcântara Francisco Antônio João Carlos Xavier de Paula " +
                "Miguel Rafael Joaquim José Gonzaga Pascoal Cipriano Serafim de Bragança e Bourbon").build();
        client.post().uri("/v1/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void createExistentName() {
        RoleDTO body = RoleDTO.builder().name("it.conflict").build();
        client.post().uri("/v1/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .exchange()
                .expectStatus().isCreated();
        client.post().uri("/v1/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }
}
