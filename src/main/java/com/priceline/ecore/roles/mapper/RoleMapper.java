package com.priceline.ecore.roles.mapper;

import com.priceline.ecore.roles.controller.dto.RoleDTO;
import com.priceline.ecore.roles.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {

    RoleDTO from(Role role);

    Role from(RoleDTO dto);
}
