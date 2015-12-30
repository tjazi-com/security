package com.tjazi.security.core.service.core;

import com.tjazi.security.core.service.dao.UserSecurityDAO;
import com.tjazi.security.core.service.dao.model.UserSecurityDAODataModel;
import com.tjazi.security.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 05/10/15.
 */
@Service
public class SecurityProfileAuthenticatorImpl implements SecurityProfileAuthenticator {

    private static final Logger log = LoggerFactory.getLogger(SecurityProfileAuthenticatorImpl.class);

    @Autowired
    private UserSecurityDAO userSecurityDAO;

    @Override
    public boolean authenticateProfile(UserAuthenticationRequestMessage authenticationRequestMessage) {

        this.assertRequestMessage(authenticationRequestMessage);

        UUID profileUuid = authenticationRequestMessage.getProfileUuid();
        String passwordHash = authenticationRequestMessage.getPasswordHash();

        log.debug("Looking for security profile with profileUuid: '{}' and passwordHash: '{}'",
                profileUuid, passwordHash);

        boolean authenticationResult = userSecurityDAO.existsByProfileUuidPasswordHash(profileUuid, passwordHash);

        log.debug("Authentication result for profile with profileUuid: '{}' and passwordHash: '{}': {}",
                profileUuid, passwordHash, authenticationResult);

        return authenticationResult;
    }

    private void assertRequestMessage(UserAuthenticationRequestMessage requestMessage) {
        if (requestMessage == null) {
            String errorMessage = "authenticationRequestMessage is null";

            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        UUID profileUuid = requestMessage.getProfileUuid();

        if (profileUuid == null) {
            String errorMessage = "requestMessage.ProfileUUID is null";

            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        String passwordHash = requestMessage.getPasswordHash();

        if (passwordHash == null || passwordHash.isEmpty()) {
            String errorMessage = "requestMessage.PasswordHash is null or empty";

            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
