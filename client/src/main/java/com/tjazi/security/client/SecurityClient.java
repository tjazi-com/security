package com.tjazi.security.client;

import com.tjazi.security.messages.*;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 09/10/15.
 */
public interface SecurityClient {

    RegisterNewUserCredentialsResponseMessage registerNewUserCredentials(UUID userUuid, String md5Password);
    UserAuthenticationResponseMessage authenticateUser(UUID userUuid, String md5Password);
    UserAuthorizationResponseMessage authorizeUser(UUID userUuid, String authorizationToken);
}
