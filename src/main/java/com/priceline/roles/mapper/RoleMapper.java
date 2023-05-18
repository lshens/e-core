package com.priceline.roles.mapper;

import com.priceline.roles.controller.dto.RoleDTO;
import com.priceline.roles.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {

    RoleDTO from(Role role);

    Role from(RoleDTO dto);
}
