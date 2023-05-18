package com.priceline.roles.controller.dto;

import lombok.Data;

@Data
public class UserRoleDTO {
    private UserDTO user;
    private RoleDTO role;
}
