package com.tjazi.security.core.service.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tjazi.security.core.service.core.SecurityProfileAuthenticator;
import com.tjazi.security.messages.RegisterNewUserCredentialsRequestCommand;
import com.tjazi.security.messages.UserAuthenticationRequestMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Krzysztof Wasiak on 30/12/2015.
 */

@RestController
@RequestMapping(value = "/security")
public class SecurityProfileAuthenticatorController {

    private static final Logger log = LoggerFactory.getLogger(SecurityProfileAuthenticatorController.class);

    @Autowired
    private SecurityProfileAuthenticator securityProfileAuthenticator;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @HystrixCommand(fallbackMethod = "authenticateProfileFallback")
    public boolean registerNewProfileCredentials(
            @RequestBody UserAuthenticationRequestMessage requestMessage) throws Exception {

        try
        {
            return securityProfileAuthenticator.authenticateProfile(requestMessage);
        }
        catch (Exception ex) {
            log.error("Got unhandled exception during Security Profile Authentication (UserAuthenticationRequestMessage): " + ex.toString());
            throw ex;
        }
    }

    /**
     * Fallback method called by Hystrix when profile authentication fails
     * @param requestMessage
     * @return
     */
    public boolean authenticateProfileFallback(RegisterNewUserCredentialsRequestCommand requestMessage) {
        log.error("authenticateProfileFallback called, something went wrong with security authentication");

        return false;
    }
}
