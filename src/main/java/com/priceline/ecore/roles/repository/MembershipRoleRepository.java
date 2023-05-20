package com.priceline.ecore.roles.repository;

import com.priceline.ecore.roles.model.MembershipRole;
import com.priceline.ecore.roles.model.MembershipRoleId;
import com.priceline.ecore.roles.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MembershipRoleRepository extends JpaRepository<MembershipRole, MembershipRoleId> {

    Set<MembershipRole> findAllByIdTeamIdAndIdUserId(String teamId, String userId);

    Set<MembershipRole> findAllByIdRoleId(String roleId);

    void deleteByIdTeamId(String teamId);

    void deleteByIdUserId(String userId);

    void deleteByIdTeamIdAndIdUserId(String teamId, String userId);
}
