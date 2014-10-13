package com.citylife.backend.auth;

import com.google.common.collect.ImmutableMap;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * 
 */
public class AfterSecurityFilter extends GenericFilterBean {
    private AuthenticationManager authenticationManager;
    private AuthenticationEntryPoint authenticationEntryPoint;

    public AfterSecurityFilter(AuthenticationManager authenticationManager) {
        this(authenticationManager, new CustomAuthenticationEntryPoint());
    }

    public AfterSecurityFilter(AuthenticationManager authenticationManager, CustomAuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationManager = authenticationManager;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String token=request.getHeader(SessionKeys.TOKEN.toString());
        if (token!=null) {
            ImmutableMap<SessionKeys, ? extends Object> sessionData = SessionTokenProvider.getSessionTokenProvider().getSession(token);
            if (sessionData!=null){

                Authentication authentication = new RestToken(sessionData.get(SessionKeys.USERNAME).toString(), sessionData.get(SessionKeys.PASSWORD).toString());
                //Request the authentication manager to authenticate the token
                Authentication successfulAuthentication = authenticationManager.authenticate(authentication);
                //Pass the successful token to the SecurityHolder where it can be retrieved by this thread at any stage.
                SecurityContextHolder.getContext().setAuthentication(successfulAuthentication);
                //Continue with the Filters
                chain.doFilter(request, response);
                return;
            }
        }

        //Pull out the Authorization header
        String authorization = request.getHeader("Authorization");

        //If the Authorization header is null, let the ExceptionTranslationFilter provided by
        //the <http> namespace kick of the BasicAuthenticationEntryPoint to provide the username and password dialog box
        if (authorization == null) {
            chain.doFilter(request, response);
            return;
        }

        try {
            String[] credentials = decodeHeader(authorization);
            Assert.isTrue(credentials.length == 2);
            Authentication authentication = new RestToken(credentials[0], credentials[1]);

            //Request the authentication manager to authenticate the token
            Authentication successfulAuthentication = authenticationManager.authenticate(authentication);
            //Pass the successful token to the SecurityHolder where it can be retrieved by this thread at any stage.
            SecurityContextHolder.getContext().setAuthentication(successfulAuthentication);
            //Continue with the Filters
            chain.doFilter(request, response);
        } catch (AuthenticationException authenticationException) {
            //If it fails clear this threads context and kick off the authentication entry point process.
            SecurityContextHolder.clearContext();
            authenticationEntryPoint.commence(request, response, authenticationException);
        }
    }

    public String[] decodeHeader(String authorization) {
        //Decode the Auth Header to get the username and password
        String credentials = Encrypt.decrypt(authorization);
        return credentials.split(":");
    }
}
