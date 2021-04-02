package com.lansrod.api.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
public enum Role implements GrantedAuthority {
    ROLE_ADMIN, ROLE_CLIENT;

    @Override
    public String getAuthority() {
        return name();
    }
}
