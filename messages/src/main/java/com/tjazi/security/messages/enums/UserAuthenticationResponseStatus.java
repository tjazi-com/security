package com.tjazi.security.messages.enums;

/**
 * Created by kr329462 on 07/10/15.
 */
public enum UserAuthenticationResponseStatus {

    OK,
    UNKNOWN_USER_NAME,
    WRONG_PASSWORD,
    WRONG_PARAMETERS,
    GENERAL_AUTHENTICATION_ERROR,

    UNKNOWN
}
