package com.tjazi.security.messages;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 07/10/15.
 */
public class UserAuthenticationRequestMessage {

    private UUID profileUuid;

    private String passwordHash;

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
