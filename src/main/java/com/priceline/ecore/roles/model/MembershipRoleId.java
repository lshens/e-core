package com.priceline.ecore.roles.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MembershipRoleId implements Serializable {
    @Column(name = "role_id")
    private String roleId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "team_id")
    private String teamId;
}
