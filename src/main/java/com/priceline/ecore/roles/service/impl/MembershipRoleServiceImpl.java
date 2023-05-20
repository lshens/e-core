package com.priceline.ecore.roles.service.impl;

import com.priceline.ecore.roles.exception.ConflictException;
import com.priceline.ecore.roles.model.MembershipRole;
import com.priceline.ecore.roles.model.MembershipRoleId;
import com.priceline.ecore.roles.model.Role;
import com.priceline.ecore.roles.repository.MembershipRoleRepository;
import com.priceline.ecore.roles.service.MembershipRoleService;
import com.priceline.ecore.roles.service.RoleService;
import com.priceline.ecore.roles.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MembershipRoleServiceImpl implements MembershipRoleService {
    private static final Role DEFAULT_ROLE = Role.builder()
            .id("e49a53c1-8703-42c5-971c-b1732aea5361")
            .name("Developer")
            .build();
    private final RoleService roleService;
    private final TeamService teamService;
    private final MembershipRoleRepository repository;

    @Override
    public Role assign(String teamId, String userId, String roleId) {
        MembershipRoleId id = getId(teamId, userId, roleId);
        Role role = roleService.find(id.getRoleId());
        exists(id);
        repository.save(new MembershipRole(id));
        return role;
    }

    @Cacheable("roles")
    @Override
    public Set<Role> findAll(String teamId, String userId) {
        teamService.hasTeamMember(teamId, userId);
        Set<MembershipRole> result = repository.findAllByIdTeamIdAndIdUserId(teamId, userId);
        if (result.isEmpty()) {
            return Collections.singleton(DEFAULT_ROLE);
        }
        return result.stream()
                .map(MembershipRole::getRole)
                .collect(Collectors.toSet());
    }

    @Cacheable("memberships")
    @Override
    public Set<MembershipRole> findAll(String roleId) {
        return repository.findAllByIdRoleId(roleId);
    }

    @Override
    public List<MembershipRole> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteForTeam(String teamId) {
        repository.deleteByIdTeamId(teamId);
    }

    @Override
    public void deleteForUser(String userId) {
        repository.deleteByIdUserId(userId);
    }

    @Override
    public void delete(String teamId, String userId) {
        repository.deleteByIdTeamIdAndIdUserId(teamId, userId);
    }

    private void exists(MembershipRoleId id) {
        teamService.hasTeamMember(id.getTeamId(), id.getUserId());
        repository.findById(id).ifPresent(ur -> {
            throw new ConflictException();
        });
    }

    private MembershipRoleId getId(String teamId, String userId, String roleId) {
        return MembershipRoleId.builder().teamId(teamId).roleId(roleId).userId(userId).build();
    }
}
