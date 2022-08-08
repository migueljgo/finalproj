package com.csb.appadvc2122.services.impl;

import com.csb.appadvc2122.model.IModel;
import com.csb.appadvc2122.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IService {

    @Autowired
    private IRepository repo;

    public List<IModel> listAll() {
        return (List<IModel>) repo.findAll();
    }

    public void save(IModel ingredient) {
        repo.save(ingredient);
    }

    public IModel get(Integer id) throws IngredientNotFoundException {
        Optional<IModel> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new IngredientNotFoundException("Could not find any ingredient with ID " + id);
    }

    public void delete(Integer id) throws IngredientNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new IngredientNotFoundException("Could not find any ingredient with ID " + id);
        }
        repo.deleteById(id);
    }
}
