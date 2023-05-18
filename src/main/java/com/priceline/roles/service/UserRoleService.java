package com.priceline.roles.service;

import com.priceline.roles.model.UserRole;

public interface UserRoleService {

    UserRole assign(String userId, String roleId);
}
