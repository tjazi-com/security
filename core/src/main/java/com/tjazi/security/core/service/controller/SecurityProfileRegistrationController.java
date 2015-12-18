package com.tjazi.security.core.service.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tjazi.security.core.service.core.SecurityCore;
import com.tjazi.security.messages.RegisterNewUserCredentialsRequestCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Krzysztof Wasiak on 18/12/2015.
 */
@RestController
@RequestMapping(value = "/security")
public class SecurityProfileRegistrationController {

    private static final Logger log = LoggerFactory.getLogger(SecurityProfileRegistrationController.class);

    @Autowired
    private SecurityCore securityCore;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @HystrixCommand(fallbackMethod = "registerNewProfileCredentialsFallback")
    public boolean registerNewProfileCredentials(
            @RequestBody RegisterNewUserCredentialsRequestCommand requestMessage) throws Exception {

        try
        {
            return securityCore.registerNewProfileCredentials(requestMessage);
        }
        catch (Exception ex) {
            log.error("Got unhandled exception: " + ex.toString());
            throw ex;
        }
    }

    public boolean registerNewProfileCredentialsFallback(RegisterNewUserCredentialsRequestCommand requestMessage) {
        log.error("registerNewProfileCredentialsFallback called, something went wrong with security profile registration");

        return false;
    }
}
