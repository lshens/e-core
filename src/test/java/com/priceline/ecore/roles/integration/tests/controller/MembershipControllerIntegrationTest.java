package com.priceline.ecore.roles.integration.tests.controller;

import com.priceline.ecore.roles.controller.dto.MembershipDTO;
import com.priceline.ecore.roles.controller.dto.RoleDTO;
import com.priceline.ecore.roles.exception.NotFoundException;
import com.priceline.ecore.roles.service.client.TeamClientService;
import com.priceline.ecore.roles.service.client.dto.TeamDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MembershipControllerIntegrationTest extends AbstractControllerIntegrationTest {
    private static final String PO_ROLE_ID = "ad0b3e55-3bd5-4b96-8e60-63a12898d0ca";
    private static final String URI_ROLE = "/roles/";
    private static final String URI_PO_ROLE_ID = URI_ROLE + PO_ROLE_ID;
    private static final String QA_ROLE_ID = "3de9e5bc-598f-458a-a9a7-0992536ac99f";
    private static final String URI_QA_ROLE_ID = URI_ROLE + QA_ROLE_ID;
    private static final String DEV_ROLE_ID = "e49a53c1-8703-42c5-971c-b1732aea5361";
    private static final String URI_DEV_ROLE_ID = URI_ROLE + DEV_ROLE_ID;
    @Autowired
    private TeamClientService service;

    private String teamId;
    private String userId;

    @BeforeEach
    void setupChild() {
        Supplier<NotFoundException> notFound = () -> new NotFoundException(TeamDTO.class);
        TeamDTO team = service.findAll().stream()
                .findFirst()
                .map(item -> service.find(item.getId()).orElseThrow(notFound))
                .orElseThrow(notFound);
        teamId = team.getId();
        userId = team.getTeamMemberIds().stream().findFirst().orElseThrow();
    }

    @Test
    void assign() {
        client.post().uri(getUri(URI_PO_ROLE_ID))
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(RoleDTO.class).consumeWith(actual -> {
                    RoleDTO dto = actual.getResponseBody();
                    assertNotNull(dto);
                    assertEquals(PO_ROLE_ID, dto.getId());
                    assertEquals("Product Owner", dto.getName());
                });
    }

    @Test
    void assignDuplicate() {
        client.post().uri(getUri(URI_QA_ROLE_ID))
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(RoleDTO.class).consumeWith(actual -> {
                    RoleDTO dto = actual.getResponseBody();
                    assertNotNull(dto);
                    assertEquals(QA_ROLE_ID, dto.getId());
                    assertEquals("Tester", dto.getName());
                });
        client.post().uri(getUri(URI_QA_ROLE_ID))
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void findAll() {
        client.post().uri(getUri(URI_DEV_ROLE_ID))
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(RoleDTO.class).consumeWith(actual -> {
                    RoleDTO dto = actual.getResponseBody();
                    assertNotNull(dto);
                    assertEquals(DEV_ROLE_ID, dto.getId());
                    assertEquals("Developer", dto.getName());
                });

        client.get().uri(getUri())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RoleDTO.class).consumeWith(actual -> {
                    List<RoleDTO> dto = actual.getResponseBody();
                    assertNotNull(dto);
                    assertTrue(dto.size() > 0);
                });
    }

    @Test
    void findAllMemberships() {
        RoleDTO role = client.post().uri("/v1/roles")
                .bodyValue(RoleDTO.builder().name("IT").build())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(RoleDTO.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(role);

        final String id = role.getId();

        client.post().uri(getUri("/roles/" + id))
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isCreated();

        client.get()
                .uri("/v1/roles/" + id + "/memberships")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(MembershipDTO.class).consumeWith(actual -> {
                    List<MembershipDTO> dto = actual.getResponseBody();
                    assertNotNull(dto);
                    assertTrue(dto.size() > 0);
                });
    }

    private String getUri() {
        return getUri(null);
    }

    private String getUri(String role) {
        return "/v1/teams/" + teamId + "/users/" + userId + Objects.requireNonNullElse(role, "/roles");
    }

}
