package com.tjazi.security.core.service.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 05/10/15.
 */
@Service
public class AuthenticatorImpl implements Authenticator {

    private static final Logger log = LoggerFactory.getLogger(AuthenticatorImpl.class);

    @Override
    public String authenticateUser(UUID userUuid, String md5Password) {
        if (userUuid == null){
            throw new IllegalArgumentException("userUuid is null");
        }

        if (md5Password == null || md5Password.isEmpty()) {
            throw new IllegalArgumentException("md5Password is null or empty");
        }

        return null;
    }

    @Override
    public boolean isAuthorizationTokenValid(UUID userUuid, String authorizationToken) {
        return false;
    }
}
