package com.priceline.roles.repository;

import com.priceline.roles.model.UserRole;
import com.priceline.roles.model.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}
