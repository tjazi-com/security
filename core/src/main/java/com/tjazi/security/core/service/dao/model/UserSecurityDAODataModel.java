package com.tjazi.security.core.service.dao.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 05/10/15.
 */
@Entity
@Table(name = "UserProfileSecurityData")
public class UserSecurityDAODataModel {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ProfileUUID", unique = true, nullable = false)
    @Type(type = "uuid-char")
    private UUID profileUuid;

    @Column(name="PasswordHash", unique = false, nullable = false)
    private String passwordHash;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getProfileUuid() {
        return profileUuid;
    }

    public void setProfileUuid(UUID profileUuid) {
        this.profileUuid = profileUuid;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
