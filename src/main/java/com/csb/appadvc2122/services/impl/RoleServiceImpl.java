package com.csb.appadvc2122.services.impl;

import com.csb.appadvc2122.repository.RoleRepository;
import com.csb.appadvc2122.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Long getRoleId(String name) {
        return roleRepository.findByName(name).getId();
    }

}
