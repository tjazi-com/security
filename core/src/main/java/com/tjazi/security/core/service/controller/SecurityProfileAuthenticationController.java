package com.tjazi.security.core.service.controller;

import com.tjazi.security.core.service.core.SecurityCore;
import com.tjazi.security.messages.UserAuthenticationRequestMessage;
import com.tjazi.security.messages.UserAuthenticationResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Krzysztof Wasiak on 11/11/2015.
 */

@RestController
@RequestMapping(value = "/security")
public class SecurityProfileAuthenticationController {

    @Autowired
    private SecurityCore securityCore;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public UserAuthenticationResponseMessage authenticateProfile(
            @RequestBody UserAuthenticationRequestMessage requestMessage) {

        return securityCore.authenticateProfile(requestMessage);
    }
}
