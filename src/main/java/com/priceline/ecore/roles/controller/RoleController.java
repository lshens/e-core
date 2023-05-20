package com.priceline.ecore.roles.controller;

import com.priceline.ecore.roles.controller.dto.RoleDTO;
import com.priceline.ecore.roles.mapper.RoleMapper;
import com.priceline.ecore.roles.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/v1/roles")
@RestController
public class RoleController {
    private final RoleService service;
    private final RoleMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDTO create(@RequestBody @Valid RoleDTO role) {
        return mapper.from(service.create(mapper.from(role)));
    }
}
