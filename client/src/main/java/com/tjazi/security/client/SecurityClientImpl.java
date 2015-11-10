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

    public RegisterNewUserCredentialsResponseMessage registerNewUserCredentials(UUID userUuid, String md5Password) {

        RegisterNewUserCredentialsRequestMessage requestMessage = new RegisterNewUserCredentialsRequestMessage();

        requestMessage.setUserUuid(userUuid);
        requestMessage.setMd5Password(md5Password);
        return (RegisterNewUserCredentialsResponseMessage)restClient.sendRequestGetResponse(
                requestMessage, RegisterNewUserCredentialsResponseMessage.class);
    }

    public UserAuthenticationResponseMessage authenticateUser(UUID userUuid, String md5Password)
    {
        UserAuthenticationRequestMessage requestMessage = new UserAuthenticationRequestMessage();

        requestMessage.setUserUuid(userUuid);
        requestMessage.setMd5Password(md5Password);

        return (UserAuthenticationResponseMessage) restClient.sendRequestGetResponse(
                requestMessage, UserAuthenticationResponseMessage.class);
    }
}
