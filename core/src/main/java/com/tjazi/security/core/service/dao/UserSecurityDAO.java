package com.tjazi.security.core.service.dao;

import com.tjazi.security.core.service.dao.model.UserSecurityDAODataModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 05/10/15.
 */
public interface UserSecurityDAO extends CrudRepository<UserSecurityDAODataModel, Long> {

    /**
     * Fine security record by the Profile UUID
     * @param profileUuid Profile UUID
     * @return List of security records matching given profile UUID
     */
    List<UserSecurityDAODataModel> findByProfileUuid(UUID profileUuid);
}
