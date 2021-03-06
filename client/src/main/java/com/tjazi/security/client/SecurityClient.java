package com.tjazi.security.client;

import com.tjazi.security.messages.*;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 09/10/15.
 */
public interface SecurityClient {

    boolean registerNewUserCredentials(UUID profileUuid, String passwordHash);
    boolean authenticateUser(UUID profileUuid, String passwordHash);
}
