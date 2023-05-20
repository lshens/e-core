package com.priceline.ecore.roles.controller;

import com.priceline.ecore.roles.controller.dto.MembershipDTO;
import com.priceline.ecore.roles.controller.dto.RoleDTO;
import com.priceline.ecore.roles.mapper.MembershipMapper;
import com.priceline.ecore.roles.mapper.RoleMapper;
import com.priceline.ecore.roles.model.Role;
import com.priceline.ecore.roles.service.MembershipRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/v1/")
@RestController
public class MembershipController {
    private final MembershipRoleService membershipRoleService;
    private final RoleMapper roleMapper;
    private final MembershipMapper membershipMapper;

    @GetMapping("teams/{teamId}/users/{userId}/roles")
    public Set<RoleDTO> findAll(@PathVariable String teamId, @PathVariable String userId) {
        return membershipRoleService.findAll(teamId, userId).stream()
                .map(roleMapper::from)
                .collect(Collectors.toSet());
    }

    @PostMapping("teams/{teamId}/users/{userId}/roles/{roleId}")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDTO assign(@PathVariable String teamId, @PathVariable String userId, @PathVariable String roleId) {
        Role role = membershipRoleService.assign(teamId, userId, roleId);
        return roleMapper.from(role);
    }

    @GetMapping("roles/{roleId}/memberships")
    public Set<MembershipDTO> findAll(@PathVariable String roleId) {
        return membershipRoleService.findAll(roleId).stream()
                .map(membershipMapper::from)
                .collect(Collectors.toSet());
    }
}
