package com.tjazi.security.core.service.core;

import com.tjazi.security.core.service.dao.UserSecurityDAO;
import com.tjazi.security.messages.UserAuthenticationRequestMessage;
import com.tjazi.security.messages.UserAuthenticationResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 05/10/15.
 */
@Service
public class SecurityCoreImpl implements SecurityCore {

    private static final Logger log = LoggerFactory.getLogger(SecurityCoreImpl.class);

    @Autowired
    private UserSecurityDAO userSecurityDAO;

    @Override
    public UserAuthenticationResponseMessage authenticateUser(UserAuthenticationRequestMessage authenticationRequestMessage) {

        if (authenticationRequestMessage == null) {
            String errorMessage = "authenticationRequestMessage is null";

            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        UUID profileUuid = authenticationRequestMessage.getProfileUuid();

        if (profileUuid == null) {
            String errorMessage = "requestMessage.ProfileUUID is null";

            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        String passwordHash = authenticationRequestMessage.getPasswordHash();

        if (passwordHash == null || passwordHash.isEmpty()) {
            String errorMessage = "requestMessage.PasswordHash is null or empty";

            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }


        return null;
    }
}