package com.citylife.trackup.backend.auth;


public enum Roles {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_REG("ROLE_REG"),
    ROLE_GUEST("ROLE_GUEST");

    private String key;
    private Roles(String key) {
        this.key = key;
    }

    @Override
    public String toString(){
        return key;
    }
}
