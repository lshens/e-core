package com.priceline.ecore.roles.scheduler;

import com.priceline.ecore.roles.service.MembershipRoleService;
import com.priceline.ecore.roles.service.client.TeamClientService;
import com.priceline.ecore.roles.service.client.UserClientService;
import com.priceline.ecore.roles.service.client.dto.TeamDTO;
import com.priceline.ecore.roles.service.client.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Executes the daily data check to maintain the DB updated.
 */
@RequiredArgsConstructor
@Component
public class DataScheduler {

    private final MembershipRoleService membershipRoleService;
    private final TeamClientService teamClientService;
    private final UserClientService userClientService;

    @Scheduled(cron = "${scheduler.data.check.cron}")
    public void run() {
        // FIXME: Split the list into chunks to run using async tasks
        membershipRoleService.findAll().forEach(mr -> {
            final String teamId = mr.getId().getTeamId();
            Optional<TeamDTO> team = teamClientService.find(teamId);
            if (team.isEmpty()) {
                membershipRoleService.deleteForTeam(teamId);
            } else {
                final String userId = mr.getId().getUserId();
                Optional<UserDTO> user = userClientService.find(userId);
                if (user.isEmpty()) {
                    membershipRoleService.deleteForUser(userId);
                } else {
                    if (!team.get().getTeamMemberIds().contains(userId)) {
                        membershipRoleService.delete(teamId, userId);
                    }
                }
            }
        });
    }
}
