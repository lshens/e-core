package com.priceline.ecore.roles.service.client.dto;

import lombok.Data;

import java.util.Set;

@Data
public class TeamDTO {
    private String id;
    private Set<String> teamMemberIds;
}
