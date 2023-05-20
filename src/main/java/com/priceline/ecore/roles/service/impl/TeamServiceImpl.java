package com.priceline.ecore.roles.service.impl;

import com.priceline.ecore.roles.exception.NotFoundException;
import com.priceline.ecore.roles.service.TeamService;
import com.priceline.ecore.roles.service.client.TeamClientService;
import com.priceline.ecore.roles.service.client.dto.TeamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {
    private final TeamClientService client;

    @Cacheable("team")
    @Override
    public void hasTeamMember(String teamId, String userId) {
        boolean exists = find(teamId).getTeamMemberIds().contains(userId);
        if (!exists) {
            throw new NotFoundException("The membership does not exists");
        }
    }


    private TeamDTO find(String id) {
        return client.find(id).orElseThrow(() -> new NotFoundException(TeamDTO.class));
    }
}
