package com.tjazi.security.messages;

import java.util.UUID;

/**
 * Created by kr329462 on 07/10/15.
 */
public class RegisterNewUserCredentialsRequestMessage {

    private UUID userUuid;

    private String md5Password;

    public UUID getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
    }

    public String getMd5Password() {
        return md5Password;
    }

    public void setMd5Password(String md5Password) {
        this.md5Password = md5Password;
    }
}
