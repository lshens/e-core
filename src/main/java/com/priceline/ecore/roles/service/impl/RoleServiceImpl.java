package com.priceline.ecore.roles.service.impl;

import com.priceline.ecore.roles.exception.NotFoundException;
import com.priceline.ecore.roles.model.Role;
import com.priceline.ecore.roles.model.util.IdUtil;
import com.priceline.ecore.roles.repository.RoleRepository;
import com.priceline.ecore.roles.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @Override
    public Role create(Role role) {
        role.setId(IdUtil.generate());
        return repository.save(role);
    }

    @Cacheable("role")
    @Override
    public Role find(String id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(Role.class));
    }
}
