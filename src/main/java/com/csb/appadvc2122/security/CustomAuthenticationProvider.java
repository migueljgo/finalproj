package com.csb.appadvc2122.security;

import com.csb.appadvc2122.dto.UserDTO;
import com.csb.appadvc2122.model.User;
import com.csb.appadvc2122.repository.UserRepository;
import com.csb.appadvc2122.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String emailAddress = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDTO user = userService.validateUser(emailAddress, password);

        if (user != null) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, password,
                    Arrays.asList(new SimpleGrantedAuthority(user.getRole())));

            token.setDetails(authentication.getDetails());
            return token;
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
