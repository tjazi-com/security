package com.tjazi.security.client;

import com.tjazi.security.messages.*;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 09/10/15.
 */
public interface SecurityClient {

    void registerNewUserCredentials(UUID profileUuid, String passwordHash);
    UserAuthenticationResponseMessage authenticateUser(UUID profileUuid, String passwordHash);
}
