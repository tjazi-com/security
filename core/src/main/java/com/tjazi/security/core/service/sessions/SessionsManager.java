package com.tjazi.security.core.service.sessions;

/**
 * Created by kr329462 on 04/10/15.
 */
public interface SessionsManager {

    void storeSessionObject(String sessionKey, Object sessionObject);

    Object getSessionObject(String sessionKey);

}
