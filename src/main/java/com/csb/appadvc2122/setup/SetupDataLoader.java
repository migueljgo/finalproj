package com.csb.appadvc2122.setup;

import com.csb.appadvc2122.model.Role;
import com.csb.appadvc2122.model.User;
import com.csb.appadvc2122.repository.RoleRepository;
import com.csb.appadvc2122.repository.UserRepository;
import com.csb.appadvc2122.security.Hash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;

        createRoleIfNotFound("ROLE_ADMIN");
//        createAdminUser();
        createRoleIfNotFound("ROLE_USER");

        alreadySetup = true;
    }

	@Transactional
    Role createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }

        return role;
    }

	private void createAdminUser() {
		Role role = roleRepository.findByName("ROLE_ADMIN");
		
//		User user = userRepository.findByEmailAddress("admin@gmail.com");
		
//		if (user == null) {
		User user = new User();
		user.setRole(role);
		user.setEmailAddress("admin@gmail.com");
		user.setPasswordHash(Hash.toSHA256("password"));
		
		userRepository.save(user);
//		}
	}
}
