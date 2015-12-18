package com.tjazi.security.client;

import com.tjazi.security.messages.*;

import com.tjazi.lib.messaging.rest.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 09/10/15.
 */

@EnableBinding(Source.class)
public class SecurityClientImpl implements SecurityClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Output(Source.OUTPUT)
    private MessageChannel messageChannel;

    private final static Logger log = LoggerFactory.getLogger(SecurityClientImpl.class);

    private final static String SECURITY_SERVICE_NAME = "security-service-core";

    private final static String REGISTER_USER_PROFILE_PATH = "http://" + SECURITY_SERVICE_NAME + "/security/register";
    private final static String AUTHENTICATE_USER_PROFILE_PATH = "http://" + SECURITY_SERVICE_NAME + "/security/authenticate";

    public boolean registerNewUserCredentials(UUID profileUuid, String passwordHash) {

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

        RegisterNewUserCredentialsRequestCommand requestMessage = new RegisterNewUserCredentialsRequestCommand();

        requestMessage.setProfileUuid(profileUuid);
        requestMessage.setPasswordHash(passwordHash);

        this.messageChannel.send(MessageBuilder.withPayload(requestMessage).build());

        return restTemplate.postForObject(REGISTER_USER_PROFILE_PATH, requestMessage, boolean.class, (Object) null);
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

        return restTemplate.postForObject(AUTHENTICATE_USER_PROFILE_PATH, requestMessage,
                UserAuthenticationResponseMessage.class, (Object) null);
    }
}
