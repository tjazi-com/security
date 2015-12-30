package com.tjazi.security.core.service.core;

import com.tjazi.security.messages.RegisterNewUserCredentialsRequestCommand;
import com.tjazi.security.messages.UserAuthenticationRequestMessage;

/**
 * Created by Krzysztof Wasiak on 05/10/15.
 */
public interface SecurityProfileAuthenticator {

    /**
     * Authenticate profile by its UUID and password (as: password hash string)
     * @param authenticationRequestMessage Authentication request message
     * @return Message with authentication status
     */
    boolean authenticateProfile(UserAuthenticationRequestMessage authenticationRequestMessage);
}
