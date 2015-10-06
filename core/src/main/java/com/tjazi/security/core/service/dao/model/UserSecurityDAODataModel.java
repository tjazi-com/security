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

    @Column(name = "UUID")
    @Type(type = "uuid-char")
    private UUID userUuid;

    @Column(name="MD5Password")
    private String md5Password;



}
