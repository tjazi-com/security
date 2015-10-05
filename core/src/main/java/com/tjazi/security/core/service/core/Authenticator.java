package com.tjazi.security.core.service.core;

import java.util.UUID;

/**
 * Created by kr329462 on 05/10/15.
 */
public interface Authenticator {

    /**
     * Authenticate user by his user UUID and password (as: MD5 string)
     * @param userUuid - User profile UUID
     * @param md5Password - User encrypted Md5 password
     * @return - Authorization token
     */
    String authenticateUser(UUID userUuid, String md5Password);


    /**
     * Check if the given authorization token is valid for the given user UUID.
     * It checks if given authorization token has been created for the given user.
     * @param userUuid - User profile UUID
     * @param authorizationToken - Authorization token
     * @return True - yes, authorization token is valid
     */
    boolean isAuthorizationTokenValid(UUID userUuid, String authorizationToken);
}
