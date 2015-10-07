package com.tjazi.security.messages;

import com.tjazi.security.messages.enums.UserAuthenticationResponseStatus;

import java.util.UUID;

/**
 * Created by kr329462 on 07/10/15.
 */
public class UserAuthenticationResponseMessage {

    private UserAuthenticationResponseStatus authenticationResponseStatus;
    private String authorizationToken;
    private UUID userUuid;

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

    public UUID getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
    }
}
