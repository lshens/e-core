package com.priceline.ecore.roles.service.impl;

import com.priceline.ecore.roles.model.MembershipRole;
import com.priceline.ecore.roles.model.MembershipRoleId;
import com.priceline.ecore.roles.model.Role;
import com.priceline.ecore.roles.repository.MembershipRoleRepository;
import com.priceline.ecore.roles.service.MembershipRoleService;
import com.priceline.ecore.roles.service.RoleService;
import com.priceline.ecore.roles.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MembershipRoleServiceImplTest {
    @Mock
    private RoleService roleService;
    @Mock
    private TeamService teamService;
    @Mock
    private MembershipRoleRepository repository;

    private MembershipRoleService service;

    @BeforeEach
    void setup() {
        service = new MembershipRoleServiceImpl(roleService, teamService, repository);
    }

    @Test
    void assign() {
        final String userId = "user.test";
        final String roleId = "role.test";
        final String teamId = "team.test";
        MembershipRoleId id = MembershipRoleId.builder().teamId(teamId).roleId(roleId).userId(userId).build();
        final MembershipRole mock = new MembershipRole(id);

        when(roleService.find(anyString())).thenReturn(new Role());
        when(repository.save(any(MembershipRole.class))).thenReturn(mock);

        Role actual = service.assign(teamId, userId, roleId);

        InOrder inOrder = Mockito.inOrder(roleService, teamService, repository);
        inOrder.verify(roleService).find(roleId);
        inOrder.verify(teamService).hasTeamMember(teamId, userId);
        inOrder.verify(repository).findById(any(MembershipRoleId.class));
        inOrder.verify(repository).save(any(MembershipRole.class));
        inOrder.verifyNoMoreInteractions();

        assertNotNull(actual);
        assertNotNull(actual.getId());
    }
}