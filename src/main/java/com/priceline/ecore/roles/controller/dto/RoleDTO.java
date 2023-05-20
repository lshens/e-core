package com.priceline.ecore.roles.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private String id;
    @NotBlank(message = "The role's name is required")
    @Size(max = 20, message = "The role's name max size is 20")
    private String name;
}
