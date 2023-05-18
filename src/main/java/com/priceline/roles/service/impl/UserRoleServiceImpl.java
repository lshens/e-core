package com.priceline.roles.service.impl;

import com.priceline.roles.exception.NotFoundException;
import com.priceline.roles.model.UserRole;
import com.priceline.roles.model.UserRoleId;
import com.priceline.roles.repository.UserRoleRepository;
import com.priceline.roles.service.RoleService;
import com.priceline.roles.service.UserRoleService;
import com.priceline.roles.service.client.UserClientService;
import com.priceline.roles.service.client.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final RoleService roleService;
    private final UserClientService userClientService;
    private final UserRoleRepository repository;

    @Override
    public UserRole assign(String userId, String roleId) {
        exists(userId, roleId);
        UserRole assign = new UserRole(UserRoleId.builder().roleId(roleId).userId(userId).build());
        return repository.save(assign);
    }

    private void exists(String userId, String roleId) {
        userClientService.find(userId).orElseThrow(() -> new NotFoundException(UserDTO.class));
        roleService.find(roleId);
    }
}
