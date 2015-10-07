package com.tjazi.security.messages;

import java.util.UUID;

/**
 * Created by kr329462 on 07/10/15.
 */
public class UserAuthorizationRequestMessage {

    private UUID userUuid;
    private String authorizationToken;

    public UUID getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }
}
