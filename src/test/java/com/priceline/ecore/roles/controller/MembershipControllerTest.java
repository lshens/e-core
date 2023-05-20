package com.priceline.ecore.roles.controller;

import com.priceline.ecore.roles.controller.dto.MembershipDTO;
import com.priceline.ecore.roles.controller.dto.RoleDTO;
import com.priceline.ecore.roles.mapper.MembershipMapper;
import com.priceline.ecore.roles.mapper.RoleMapper;
import com.priceline.ecore.roles.model.MembershipRole;
import com.priceline.ecore.roles.model.Role;
import com.priceline.ecore.roles.service.MembershipRoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MembershipControllerTest {
    @Mock
    private MembershipRoleService membershipRoleService;
    @Mock
    private RoleMapper roleMapper;
    @Mock
    private MembershipMapper membershipMapper;
    @InjectMocks
    private MembershipController controller;

    @Test
    void assign() {
        final RoleDTO mock = RoleDTO.builder().id("test.id").name("test.name").build();

        when(membershipRoleService.assign(anyString(), anyString(), anyString())).thenReturn(new Role());
        when(roleMapper.from(any(Role.class))).thenReturn(mock);

        RoleDTO actual = controller.assign("team.id", "user.id", "role.id");

        InOrder inOrder = Mockito.inOrder(membershipRoleService, membershipMapper, roleMapper);
        inOrder.verify(membershipRoleService).assign(anyString(), anyString(), anyString());
        inOrder.verify(roleMapper).from(any(Role.class));
        inOrder.verifyNoMoreInteractions();

        assertEquals(mock, actual);
    }

    @Test
    void findAll() {
        final String teamId = "team.id";
        final String userId = "user.id";

        when(membershipRoleService.findAll(teamId, userId)).thenReturn(Collections.singleton(new Role()));
        when(roleMapper.from(new Role())).thenReturn(new RoleDTO());

        Set<RoleDTO> actual = controller.findAll(teamId, userId);

        InOrder inOrder = Mockito.inOrder(membershipRoleService, membershipMapper, roleMapper);
        inOrder.verify(membershipRoleService).findAll(anyString(), anyString());
        inOrder.verify(roleMapper).from(any(Role.class));
        inOrder.verifyNoMoreInteractions();

        assertNotNull(actual);
    }

    @Test
    void findAllMemberships() {
        final String roleId = "team.id";
        when(membershipRoleService.findAll(roleId)).thenReturn(Collections.singleton(new MembershipRole()));
        when(membershipMapper.from(new MembershipRole())).thenReturn(new MembershipDTO());

        Set<MembershipDTO> actual = controller.findAll(roleId);

        InOrder inOrder = Mockito.inOrder(membershipRoleService, membershipMapper, roleMapper);
        inOrder.verify(membershipRoleService).findAll(anyString());
        inOrder.verify(membershipMapper).from(any(MembershipRole.class));
        inOrder.verifyNoMoreInteractions();

        assertNotNull(actual);
    }
}