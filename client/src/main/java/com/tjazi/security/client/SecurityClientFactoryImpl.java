package com.tjazi.security.client;

import com.tjazi.lib.messaging.rest.RestClientImpl;

import java.net.URI;

/**
 * Created by Krzysztof Wasiak on 10/10/15.
 */


public class SecurityClientFactoryImpl implements SecurityClientFactory {
    public SecurityClient createSecurityClient(URI targetUrl) {

        if (targetUrl == null){
            throw new IllegalArgumentException("'targetUrl' is null.");
        }

        return new SecurityClientImpl(new RestClientImpl(targetUrl));
    }
}
