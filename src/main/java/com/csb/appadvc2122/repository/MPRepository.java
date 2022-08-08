package com.csb.appadvc2122.repository;

import com.csb.appadvc2122.model.MPModel;
import org.springframework.data.repository.CrudRepository;

public interface MPRepository extends CrudRepository<MPModel, Integer> {

    public Long countById(Integer id);
}
