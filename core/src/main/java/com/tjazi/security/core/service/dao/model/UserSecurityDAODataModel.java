package com.tjazi.security.core.service.dao.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 05/10/15.
 */
@Entity
@Table(name = "UserProfileSecurityData")
public class UserSecurityDAODataModel {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ProfileUUID")
    @Type(type = "uuid-char")
    private UUID profileUuid;

    @Column(name="PasswordHash")
    private String passwordHash;



}
