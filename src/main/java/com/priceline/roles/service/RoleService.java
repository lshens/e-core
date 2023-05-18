package com.priceline.roles.service;

import com.priceline.roles.model.Role;

public interface RoleService {

    Role create(Role role);

    Role find(String id);
}
