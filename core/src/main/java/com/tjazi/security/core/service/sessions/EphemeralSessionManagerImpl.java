package com.tjazi.security.core.service.sessions;

import java.util.HashMap;

/**
 * Created by Krzysztof Wasiak on 04/10/15.
 */
public class EphemeralSessionManagerImpl implements SessionsManager {

    private HashMap<String, Object> sessionsHashMap;

    public EphemeralSessionManagerImpl() {
        sessionsHashMap = new HashMap<String, Object>();
    }

    @Override
    public void storeSessionObject(String sessionKey, Object sessionObject) {

        if (sessionKey == null || sessionKey.isEmpty()){
            throw new IllegalArgumentException("sessionKey is null or empty");
        }

        if (sessionObject == null) {
            throw new IllegalArgumentException("sessionObject is null");
        }

        sessionsHashMap.put(sessionKey, sessionObject);
    }

    @Override
    public Object getSessionObject(String sessionKey) {

        if (sessionKey == null || sessionKey.isEmpty()){
            throw new IllegalArgumentException("sessionKey is null or empty");
        }

        return sessionsHashMap.get(sessionKey);
    }
}
