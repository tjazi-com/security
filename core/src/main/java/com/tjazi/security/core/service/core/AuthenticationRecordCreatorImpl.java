package com.tjazi.security.core.service.core;

import com.tjazi.security.core.service.dao.UserSecurityDAO;
import com.tjazi.security.core.service.dao.model.UserSecurityDAODataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Krzysztof Wasiak on 06/10/15.
 */
public class AuthenticationRecordCreatorImpl implements AuthenticationRecordCreator {

    @Autowired
    private UserSecurityDAO userSecurityDao;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationRecordCreatorImpl.class);


    @Override
    public void registerNewUserCredentials(Object newUserData) {

        if (newUserData == null){
            throw new IllegalArgumentException("newUserData is null");
        }

        UserSecurityDAODataModel daoDataModel = this.convertNewUserDataMessageToDaoModel(newUserData);


    }

    private UserSecurityDAODataModel convertNewUserDataMessageToDaoModel(Object newUserData) {

        /* TODO: replace with proper implementation */
        return null;
    }
}
