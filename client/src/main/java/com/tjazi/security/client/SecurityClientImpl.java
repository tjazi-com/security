package com.tjazi.security.client;

import com.tjazi.security.messages.*;

import com.tjazi.lib.messaging.rest.*;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 09/10/15.
 */
public class SecurityClientImpl implements SecurityClient {

    private RestClient restClient;

    public SecurityClientImpl(RestClient restClient){
        this.restClient = restClient;
    }

    public RegisterNewUserCredentialsResponseMessage registerNewUserCredentials(UUID profileUuid, String passwordHash) {

        RegisterNewUserCredentialsRequestMessage requestMessage = new RegisterNewUserCredentialsRequestMessage();

        requestMessage.setProfileUuid(profileUuid);
        requestMessage.setPasswordHash(passwordHash);
        return (RegisterNewUserCredentialsResponseMessage)restClient.sendRequestGetResponse(
                requestMessage, RegisterNewUserCredentialsResponseMessage.class);
    }

    public UserAuthenticationResponseMessage authenticateUser(UUID profileUuid, String passwordHash)
    {
        UserAuthenticationRequestMessage requestMessage = new UserAuthenticationRequestMessage();

        requestMessage.setProfileUuid(profileUuid);
        requestMessage.setPasswordHash(passwordHash);

        return (UserAuthenticationResponseMessage) restClient.sendRequestGetResponse(
                requestMessage, UserAuthenticationResponseMessage.class);
    }
}
