package com.tjazi.security.messages;

/**
 * Created by kr329462 on 07/10/15.
 */
public enum UserAuthenticationResponseStatus {

    UNKNOWN,

    OK,
    PROFILE_UUID_NOT_FOUND,
    WRONG_PASSWORD,
    GENERAL_ERROR
}
