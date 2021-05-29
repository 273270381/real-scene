package com.suchness.realscene.common.security;

import org.apache.shiro.authc.AuthenticationToken;

/***
 *  author: wch
 *  create_time : 2020/6/19 10:30
 *******/
public class JwtToken implements AuthenticationToken {

    /**
     * 密钥
     */
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
