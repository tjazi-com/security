package com.tjazi.security.messages;

import com.tjazi.security.messages.enums.UserAuthorizationResponseStatus;

/**
 * Created by kr329462 on 07/10/15.
 */
public class UserAuthorizationResponseMessage {

    private UserAuthorizationResponseStatus userAuthorizationResponseStatus;

    public UserAuthorizationResponseStatus getUserAuthorizationResponseStatus() {
        return userAuthorizationResponseStatus;
    }

    public void setUserAuthorizationResponseStatus(UserAuthorizationResponseStatus userAuthorizationResponseStatus) {
        this.userAuthorizationResponseStatus = userAuthorizationResponseStatus;
    }
}
