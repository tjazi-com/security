package com.tjazi.security.messages;

/**
 * Created by kr329462 on 07/10/15.
 */
public enum RegisterNewUserCredentialsResponseStatus {

    /**
     * Unknown / not set status
     */
    UKNOWN,

    /**
     * Registration went successfully
     */
    OK,

    /**
     * There's already record with the given profile UUID
     */
    PROFILE_UUID_ALREADY_REGISTERED,

    /**
     * General error when registering profile, most likely: problems with database
     */
    GENERAL_ERROR
}
