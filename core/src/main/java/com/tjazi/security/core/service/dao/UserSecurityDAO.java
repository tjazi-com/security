package com.tjazi.security.core.service.dao;

import com.tjazi.security.core.service.dao.model.UserSecurityDAODataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM UserSecurityDAODataModel u WHERE u.profileUuid = ?1 AND u.passwordHash = ?2")
    boolean existsByProfileUuidPasswordHash(UUID profileUuid, String passwordHash);


}
