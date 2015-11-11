package com.tjazi.security.core.service.core;

import com.tjazi.security.messages.RegisterNewUserCredentialsRequestMessage;
import com.tjazi.security.messages.RegisterNewUserCredentialsResponseMessage;
import com.tjazi.security.messages.UserAuthenticationRequestMessage;
import com.tjazi.security.messages.UserAuthenticationResponseMessage;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 05/10/15.
 */
public interface SecurityCore {

    /**
     * Authenticate profile by its UUID and password (as: password hash string)
     * @param authenticationRequestMessage Authentication request message
     * @return Message with authentication status
     */
    UserAuthenticationResponseMessage authenticateProfile(UserAuthenticationRequestMessage authenticationRequestMessage);

    /**
     * Register new security profile (profile UUID and password hash)
     * @param requestMessage Registration request message
     * @return Message with registration status
     */
    RegisterNewUserCredentialsResponseMessage registerNewProfileCredentials(RegisterNewUserCredentialsRequestMessage requestMessage);
}
