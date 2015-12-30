package com.tjazi.security.core.service.core;

import com.tjazi.security.messages.RegisterNewUserCredentialsRequestCommand;
import org.springframework.stereotype.Service;

/**
 * Created by Krzysztof Wasiak on 30/12/2015.
 */

@Service
public interface SecurityProfilePersister {

    /**
     * Register new security profile (profile UUID and password hash)
     * @param requestMessage Registration request message
     * @return True - registration went OK
     */
    boolean registerNewProfileCredentials(RegisterNewUserCredentialsRequestCommand requestMessage) throws Exception;
}
