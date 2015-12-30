package com.tjazi.security.core.service.core;

import com.tjazi.security.core.service.dao.UserSecurityDAO;
import com.tjazi.security.core.service.dao.model.UserSecurityDAODataModel;
import com.tjazi.security.messages.RegisterNewUserCredentialsRequestCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 30/12/2015.
 */

@Service
public class SecurityProfilePersisterImpl implements SecurityProfilePersister {

    private static final Logger log = LoggerFactory.getLogger(SecurityProfilePersisterImpl.class);

    @Autowired
    private UserSecurityDAO userSecurityDAO;

    @Override
    public boolean registerNewProfileCredentials(
            RegisterNewUserCredentialsRequestCommand requestMessage) throws Exception {

        if (requestMessage == null) {
            String errorMessage = "requestMessage is null";

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

        try {
            List<UserSecurityDAODataModel> duplicateProfiles = userSecurityDAO.findByProfileUuid(profileUuid);

            int numberOfDuplicatedProfiles = duplicateProfiles.size();

            if (numberOfDuplicatedProfiles > 0) {
                log.error("Found {} duplicated security profiles for Profile UUID: {}",
                        numberOfDuplicatedProfiles, profileUuid);

                throw new IllegalArgumentException("Duplicated Profile UUID.");
            }

            UserSecurityDAODataModel daoDataModel = new UserSecurityDAODataModel();
            daoDataModel.setPasswordHash(passwordHash);
            daoDataModel.setProfileUuid(profileUuid);

            userSecurityDAO.save(daoDataModel);

            return true;

        } catch (Exception ex) {

            log.error("Exception when calling userSecurityDAO.save()");

            throw ex;
        }
    }
}
