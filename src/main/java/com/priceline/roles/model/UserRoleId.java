package com.priceline.roles.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserRoleId implements Serializable {
    @Column(name = "role_id")
    private String roleId;
    @Column(name = "team_id")
    private String teamId;
    @Column(name = "user_id")
    private String userId;
}
