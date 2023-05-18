package com.priceline.roles.service.impl;

import com.priceline.roles.exception.NotFoundException;
import com.priceline.roles.model.Role;
import com.priceline.roles.model.util.IdUtil;
import com.priceline.roles.repository.RoleRepository;
import com.priceline.roles.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private static final Role DEFAULT = new Role("e49a53c1-8703-42c5-971c-b1732aea5361", "Developer");

    private final RoleRepository repository;

    @Override
    public Role create(Role role) {
        role.setId(IdUtil.generate());
        return repository.save(role);
    }

    @Override
    public Role find(String id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(Role.class));
    }
}
