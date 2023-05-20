package com.priceline.ecore.roles.controller;

import com.priceline.ecore.roles.controller.dto.RoleDTO;
import com.priceline.ecore.roles.mapper.RoleMapper;
import com.priceline.ecore.roles.model.Role;
import com.priceline.ecore.roles.service.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class RoleControllerTest {
    @Mock
    private RoleService service;
    @Mock
    private RoleMapper mapper;
    @InjectMocks
    private RoleController controller;

    @Test
    void create() {
        when(mapper.from(any(RoleDTO.class))).thenReturn(new Role());
        when(service.create(any(Role.class))).thenReturn(new Role());
        when(mapper.from(any(Role.class))).thenReturn(new RoleDTO());

        RoleDTO actual = controller.create(new RoleDTO());

        InOrder inOrder = Mockito.inOrder(service, mapper);
        inOrder.verify(mapper).from(any(RoleDTO.class));
        inOrder.verify(service).create(any(Role.class));
        inOrder.verify(mapper).from(any(Role.class));
        inOrder.verifyNoMoreInteractions();

        assertNotNull(actual);
    }
}