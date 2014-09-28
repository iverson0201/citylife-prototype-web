package com.citylife.trackup.backend.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.citylife.trackup.backend.domain.user.User;
import com.citylife.trackup.backend.service.UserService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * 
 */
public class RestAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RestToken restToken = (RestToken) authentication;

        String key = restToken.getKey();
        String credentials = restToken.getCredentials();

        User user = userService.findByPhoneNumAndPwd(key, credentials);

        if (user == null) {
            throw new BadCredentialsException("Authenticate failed.");
        }
        return getAuthenticatedUser(user);
    }

    private Authentication getAuthenticatedUser(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(getRole(user.getRole())));

        return new RestToken(user.getTel(), user.getPassword(), authorities);
    }

    private String getRole(int role) {
        if(role == 1)
            return Roles.ROLE_ADMIN.toString();
        else if(role == 2)
            return Roles.ROLE_REG.toString();
        else
            return Roles.ROLE_GUEST.toString();
    }

    @Override
    /*
        Determines if this class can support the token provided by the filter.
     */
    public boolean supports(Class<?> authentication) {
        return RestToken.class.equals(authentication);
    }
}
