package com.tjazi.security.messages;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 07/10/15.
 */
public class UserAuthenticationResponseMessage {

    private UserAuthenticationResponseStatus authenticationResponseStatus;

    public UserAuthenticationResponseStatus getAuthenticationResponseStatus() {
        return authenticationResponseStatus;
    }

    public void setAuthenticationResponseStatus(UserAuthenticationResponseStatus authenticationResponseStatus) {
        this.authenticationResponseStatus = authenticationResponseStatus;
    }
}
