package com.tjazi.security.core.service.dao.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by kr329462 on 05/10/15.
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
    }

    public String getMd5Password() {
        return md5Password;
    }

    public void setMd5Password(String md5Password) {
        this.md5Password = md5Password;
    }
}
