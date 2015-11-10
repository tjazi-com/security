package com.tjazi.security.core.service.core;

import com.tjazi.security.messages.UserAuthenticationRequestMessage;
import com.tjazi.security.messages.UserAuthenticationResponseMessage;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 05/10/15.
 */
public interface SecurityCore {

    /**
     * Authenticate user by his user UUID and password (as: password hash string)
     * @param authenticationRequestMessage Authentication request message
     * @return - Authorization token
     */
    UserAuthenticationResponseMessage authenticateUser(UserAuthenticationRequestMessage authenticationRequestMessage);
}
