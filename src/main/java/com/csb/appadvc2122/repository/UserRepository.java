package com.csb.appadvc2122.repository;

import com.csb.appadvc2122.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * We don't need to create an implementation of this method since Spring provides dynamic implementation for this
     * interface.
     * See {@link https://docs.spring.io/spring-data/jpa/docs/current/reference/html/}
     *
     * @param emailAddress target email address
     * @return User
     */
    User findByEmailAddress(String emailAddress);

}
