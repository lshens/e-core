package com.priceline.roles.controller;

import com.priceline.roles.controller.dto.RoleDTO;
import com.priceline.roles.mapper.RoleMapper;
import com.priceline.roles.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/roles")
@RestController
public class RoleController {
    private RoleService service;
    private RoleMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDTO create(RoleDTO role) {
        return mapper.from(service.create(mapper.from(role)));
    }
}
