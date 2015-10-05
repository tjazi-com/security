package com.tjazi.security.core.service.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by kr329462 on 05/10/15.
 */
public class SessionDataModel {

    private UUID userUuid;
    private Date timeStamp;
    private String authenticationToken;

    public UUID getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }
}
