package com.priceline.ecore.roles.service.impl;

import com.priceline.ecore.roles.exception.NotFoundException;
import com.priceline.ecore.roles.service.UserService;
import com.priceline.ecore.roles.service.client.UserClientService;
import com.priceline.ecore.roles.service.client.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserClientService client;

    @Cacheable("user")
    @Override
    public UserDTO find(String id) {
        return client.find(id).orElseThrow(() -> new NotFoundException(UserDTO.class));
    }
}
