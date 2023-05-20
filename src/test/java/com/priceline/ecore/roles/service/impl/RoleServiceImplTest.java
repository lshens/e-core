package com.priceline.ecore.roles.service.impl;

import com.priceline.ecore.roles.exception.NotFoundException;
import com.priceline.ecore.roles.model.Role;
import com.priceline.ecore.roles.repository.RoleRepository;
import com.priceline.ecore.roles.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class RoleServiceImplTest {
    @Mock
    private RoleRepository repository;
    private RoleService service;

    @BeforeEach
    void setup() {
        service = new RoleServiceImpl(repository);
    }

    @Test
    void create() {
        final Role mock = Role.builder().name("test").build();

        when(repository.save(mock)).thenReturn(mock);

        final Role actual = service.create(mock);

        InOrder inOrder = Mockito.inOrder(repository);
        inOrder.verify(repository).save(mock);
        inOrder.verifyNoMoreInteractions();

        assertEquals("test", actual.getName());
        assertNotNull(actual.getId());
    }

    @Test
    void find() {
        final String id = "d603d076-8a73-4440-b810-23e52300f0f2";
        final Role mock = Role.builder().name("test").id(id).build();

        when(repository.findById(id)).thenReturn(Optional.of(mock));

        final Role actual = service.find(id);

        InOrder inOrder = Mockito.inOrder(repository);
        inOrder.verify(repository).findById(id);
        inOrder.verifyNoMoreInteractions();

        assertEquals(mock, actual);
    }

    @Test
    void findNotFoundException() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        final NotFoundException exception = assertThrows(NotFoundException.class, () -> service.find(""));

        InOrder inOrder = Mockito.inOrder(repository);
        inOrder.verify(repository).findById(anyString());
        inOrder.verifyNoMoreInteractions();

        assertEquals(Role.class, exception.getEntity());
    }
}