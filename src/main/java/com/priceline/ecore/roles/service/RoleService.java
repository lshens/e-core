package com.priceline.ecore.roles.service;

import com.priceline.ecore.roles.model.Role;

public interface RoleService {

    Role create(Role role);

    Role find(String id);
}
