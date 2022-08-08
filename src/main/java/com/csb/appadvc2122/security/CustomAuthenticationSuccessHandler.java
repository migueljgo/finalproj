package com.csb.appadvc2122.security;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.csb.appadvc2122.dto.UserDTO;
import com.csb.appadvc2122.model.Log;
import com.csb.appadvc2122.repository.LogRepository;

@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	LogRepository log;
	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
         
    	UserDTO user = (UserDTO) authentication.getPrincipal();
    	
    	Log userLog = new Log();
    	if (user.getEmailAddress().equals("admin@gmail.com")) {
    		userLog.setName("Admin has login to the application");
    	} else {
    		userLog.setName(user.getFirstName() + " " + user.getLastName() +" has login to the application");
    	}
    	log.save(userLog);
    		
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/admin/user");
        } else {
            httpServletResponse.sendRedirect("/home");
        }
    }
}