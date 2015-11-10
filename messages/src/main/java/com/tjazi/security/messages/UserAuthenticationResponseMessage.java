package com.tjazi.security.messages;

import com.tjazi.security.messages.enums.UserAuthenticationResponseStatus;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 07/10/15.
 */
public class UserAuthenticationResponseMessage {

    private UserAuthenticationResponseStatus authenticationResponseStatus;
    private String authorizationToken;
    private UUID profileUuid;

    public UserAuthenticationResponseStatus getAuthenticationResponseStatus() {
        return authenticationResponseStatus;
    }

    public void setAuthenticationResponseStatus(UserAuthenticationResponseStatus authenticationResponseStatus) {
        this.authenticationResponseStatus = authenticationResponseStatus;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    public UUID getProfileUuid() {
        return profileUuid;
    }

    public void setProfileUuid(UUID profileUuid) {
        this.profileUuid = profileUuid;
    }
}
