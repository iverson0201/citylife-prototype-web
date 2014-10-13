package com.citylife.backend.auth;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月18日 下午5:23:49
 * 
 */
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
