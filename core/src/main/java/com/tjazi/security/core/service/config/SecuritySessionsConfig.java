package com.tjazi.security.core.service.config;

import com.tjazi.security.core.service.sessions.EphemeralSessionManagerImpl;
import com.tjazi.security.core.service.sessions.SessionsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Krzysztof Wasiak on 04/10/15.
 */
@Configuration
public class SecuritySessionsConfig {

    @Bean(name = "sessionsManager")
    public SessionsManager createSessionsManager() {return new EphemeralSessionManagerImpl(); }


}
