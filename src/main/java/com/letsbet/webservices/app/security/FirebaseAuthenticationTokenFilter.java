package com.letsbet.webservices.app.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FirebaseAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    private final static String TOKEN_HEADER = "X-Firebase-Auth";
    private final static String TOKEN_PARAMETER = "firebaseAuth";

    public FirebaseAuthenticationTokenFilter() {
        super("/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String authToken = request.getHeader(TOKEN_HEADER);
        if (StringUtils.isEmpty(authToken)) {
            authToken = request.getParameter(TOKEN_PARAMETER);
        }

        if (StringUtils.isEmpty(authToken)) {
            throw new RuntimeException("Invaild auth token");
        }

        return getAuthenticationManager().authenticate(new FirebaseAuthenticationToken(authToken));
    }

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
