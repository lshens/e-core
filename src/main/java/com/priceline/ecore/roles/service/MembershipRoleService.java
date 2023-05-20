package com.priceline.ecore.roles.service;

import com.priceline.ecore.roles.model.MembershipRole;
import com.priceline.ecore.roles.model.Role;

import java.util.List;
import java.util.Set;

public interface MembershipRoleService {

    Role assign(String teamId, String userId, String roleId);

    Set<Role> findAll(String teamId, String userId);

    Set<MembershipRole> findAll(String roleId);

    List<MembershipRole> findAll();

    void deleteForTeam(String teamId);

    void deleteForUser(String userId);

    void delete(String teamId, String userId);
}
