package com.tjazi.security.core.service.core;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 05/10/15.
 */
public interface Authenticator {

    /**
     * Authenticate user by his user UUID and password (as: MD5 string)
     * @param userUuid - User profile UUID
     * @param md5Password - User encrypted Md5 password
     * @return - Authorization token
     */
    String authenticateUser(UUID userUuid, String md5Password);
}
