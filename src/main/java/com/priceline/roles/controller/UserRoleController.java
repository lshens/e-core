package com.priceline.roles.controller;

import com.priceline.roles.controller.dto.RoleDTO;
import com.priceline.roles.controller.dto.UserRoleDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users/{userId}/roles/{roleId}")
@RestController
public class UserRoleController {

    @PostMapping
    public UserRoleDTO assign(@PathVariable String userId, @PathVariable String roleId) {
return null;
    }
}
