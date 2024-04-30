package com.jwt.jsonwebtokenproject.Role;

import com.google.common.collect.Sets;

import java.util.Set;

public enum AppRole {
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(AppPermission.USER_READ,AppPermission.ADMIN_READ,AppPermission.ADMIN_WRITE));

    private final Set<AppPermission> permissions;


    AppRole(Set<AppPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<AppPermission> getPermissions() {
        return permissions;
    }
}
