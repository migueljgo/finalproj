package com.csb.appadvc2122.repository;

import com.csb.appadvc2122.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long>  {

    /**
     * We don't need to create an implementation of this method since Spring provides dynamic implementation for this
     * interface.
     * See {@link https://docs.spring.io/spring-data/jpa/docs/current/reference/html/}
     *
     * @param name target role name
     * @return Role
     */
    Role findByName(String name);

}
