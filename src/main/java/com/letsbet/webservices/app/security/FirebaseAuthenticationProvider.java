package com.letsbet.webservices.app.security;

import com.google.api.core.ApiFuture;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class FirebaseAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private FirebaseAuth firebaseAuth;

    @Override
    public boolean supports(Class<?> authentication) {
        return (FirebaseAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken token) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String s,
                                       UsernamePasswordAuthenticationToken token) throws AuthenticationException {
//        return new FirebaseUserDetails("--confidential--", "--confidential--");
        String tokenValue = ((FirebaseAuthenticationToken) token).getToken();
        ApiFuture<FirebaseToken> task = firebaseAuth.verifyIdTokenAsync(tokenValue);
        try {
            FirebaseToken firebaseToken = task.get();
            return new FirebaseUserDetails(firebaseToken.getEmail(), firebaseToken.getUid());
        } catch (InterruptedException | ExecutionException e) {
            throw new SessionAuthenticationException(e.getMessage());
        }
    }
}
