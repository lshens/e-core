package com.priceline.ecore.roles.mapper;

import com.priceline.ecore.roles.controller.dto.RoleDTO;
import com.priceline.ecore.roles.model.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleMapperTest {

    private final RoleMapper mapper = new RoleMapperImpl();

    @Test
    void fromRole() {
        final String id = "test.id";
        final String name = "test.name";
        final Role role = Role.builder().id(id).name(name).build();

        RoleDTO actual = mapper.from(role);

        assertEquals(id, actual.getId());
        assertEquals(name, actual.getName());
    }

    @Test
    void fromRoleDTO() {
        final String id = "test.id";
        final String name = "test.name";
        final RoleDTO dto = RoleDTO.builder().id(id).name(name).build();

        Role actual = mapper.from(dto);

        assertEquals(id, actual.getId());
        assertEquals(name, actual.getName());
    }
}