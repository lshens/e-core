package com.priceline.roles.service.client.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeamDTO {
    private String id;
    private String name;
    private String teamLeadId;
    private List<String> teamMemberIds;
}
