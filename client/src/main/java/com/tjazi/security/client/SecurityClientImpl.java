package com.tjazi.security.client;

import com.tjazi.security.messages.*;

import com.tjazi.lib.messaging.rest.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 09/10/15.
 */
public class SecurityClientImpl implements SecurityClient {

    private RestClient restClient;

    private final static Logger log = LoggerFactory.getLogger(SecurityClientImpl.class);

    private final static String REGISTER_NEW_USER_CREDENTIALS_PATH = "/security/register";
    private final static String AUTHENTICATE_USER_PROFILE_PATH = "/security/authenticate";

    public SecurityClientImpl(RestClient restClient){
        this.restClient = restClient;
    }

    public RegisterNewUserCredentialsResponseMessage registerNewUserCredentials(UUID profileUuid, String passwordHash) {

        if (profileUuid == null) {
            String errorMessage = "profileUuid is null";

            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        if (passwordHash == null || passwordHash.isEmpty()) {
            String errorMessage = "passwordHash is null or empty";

            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        RegisterNewUserCredentialsRequestMessage requestMessage = new RegisterNewUserCredentialsRequestMessage();

        requestMessage.setProfileUuid(profileUuid);
        requestMessage.setPasswordHash(passwordHash);
        return (RegisterNewUserCredentialsResponseMessage)restClient.sendRequestGetResponse(
                REGISTER_NEW_USER_CREDENTIALS_PATH, requestMessage, RegisterNewUserCredentialsResponseMessage.class);
    }

    public UserAuthenticationResponseMessage authenticateUser(UUID profileUuid, String passwordHash)
    {
        if (profileUuid == null) {
            String errorMessage = "profileUuid is null";

            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        if (passwordHash == null || passwordHash.isEmpty()) {
            String errorMessage = "passwordHash is null or empty";

            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        UserAuthenticationRequestMessage requestMessage = new UserAuthenticationRequestMessage();

        requestMessage.setProfileUuid(profileUuid);
        requestMessage.setPasswordHash(passwordHash);

        return (UserAuthenticationResponseMessage) restClient.sendRequestGetResponse(
                AUTHENTICATE_USER_PROFILE_PATH, requestMessage, UserAuthenticationResponseMessage.class);
    }
}
