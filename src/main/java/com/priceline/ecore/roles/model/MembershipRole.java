package com.priceline.ecore.roles.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "membership_roles")
public class MembershipRole implements Serializable {
    @EmbeddedId
    private MembershipRoleId id;

    // FIXME: Use NamedEntityGraphs to improve query performance.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;

    public MembershipRole(MembershipRoleId id) {
        setId(id);
    }
}
