package com.jwt.jsonwebtokenproject.Role;

public enum AppPermission {
    USER_READ("user:read"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write");

    private final String permission;


    AppPermission(String permission) {
        this.permission = permission;
    }
}
