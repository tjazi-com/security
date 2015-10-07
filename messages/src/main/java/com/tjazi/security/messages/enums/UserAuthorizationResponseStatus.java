package com.tjazi.security.messages.enums;

/**
 * Created by kr329462 on 07/10/15.
 */
public enum UserAuthorizationResponseStatus {

    OK,
    NO_USER_WITH_GIVEN_UUID_IN_AUTHORIZATION_RECORD,
    WRONG_AUTHORIZATION_TOKEN,
    GENERAL_AUTHORIZATION_ERROR,

    UNKNOWN
}
