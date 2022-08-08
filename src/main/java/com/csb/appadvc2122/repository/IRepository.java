package com.csb.appadvc2122.repository;

import com.csb.appadvc2122.model.IModel;
import org.springframework.data.repository.CrudRepository;

public interface IRepository extends CrudRepository<IModel, Integer> {

    public Long countById(Integer id);
}
