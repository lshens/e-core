package com.priceline.ecore.roles.mapper;

import com.priceline.ecore.roles.controller.dto.MembershipDTO;
import com.priceline.ecore.roles.model.MembershipRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MembershipMapper {

    @Mapping(target = "userId", source = "id.userId")
    @Mapping(target = "teamId", source = "id.teamId")
    MembershipDTO from(MembershipRole role);
}
