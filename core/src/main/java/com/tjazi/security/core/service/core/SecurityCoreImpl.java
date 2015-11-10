package com.tjazi.security.core.service.core;

import com.tjazi.security.core.service.dao.UserSecurityDAO;
import com.tjazi.security.core.service.dao.model.UserSecurityDAODataModel;
import com.tjazi.security.messages.UserAuthenticationRequestMessage;
import com.tjazi.security.messages.UserAuthenticationResponseMessage;
import com.tjazi.security.messages.UserAuthenticationResponseStatus;
import org.junit.rules.ExpectedException;
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

        List<UserSecurityDAODataModel> profilesByUuid = null;

        UserAuthenticationResponseMessage responseMessage = new UserAuthenticationResponseMessage();

        try
        {
            profilesByUuid = userSecurityDAO.findByProfileUuid(profileUuid);
        }
        catch (Exception ex) {

            log.error("Problem when getting record from database for profile UUID: " + profilesByUuid);
            responseMessage.setAuthenticationResponseStatus(UserAuthenticationResponseStatus.GENERAL_ERROR);
            return responseMessage;
        }

        int numberOfRecords = profilesByUuid.size();

        if (numberOfRecords == 0) {

            // this is problem, because we have authentication request for given profile
            // which most likely means there's already profile with that ID registered in Profiles service
            log.warn("There's no security record for profile UUID: " + profileUuid);

            responseMessage.setAuthenticationResponseStatus(UserAuthenticationResponseStatus.PROFILE_UUID_NOT_FOUND);
            return responseMessage;
        }

        if (numberOfRecords > 1) {
            log.error("Query for security record with UUID '{}' has returned {} records", profileUuid, numberOfRecords);

            responseMessage.setAuthenticationResponseStatus(UserAuthenticationResponseStatus.GENERAL_ERROR);
            return responseMessage;
        }

        UserSecurityDAODataModel extractedProfile = profilesByUuid.get(0);

        if (!extractedProfile.getPasswordHash().equals(passwordHash)) {
            log.debug("Given password hash doesn't match stored one for profile UUID: " + profileUuid);

            responseMessage.setAuthenticationResponseStatus(UserAuthenticationResponseStatus.WRONG_PASSWORD);
            return responseMessage;
        }

        // so far so good, authentication succeed
        responseMessage.setAuthenticationResponseStatus(UserAuthenticationResponseStatus.OK);

        return responseMessage;
    }
}
