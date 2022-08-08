package com.csb.appadvc2122.services.impl;

import com.csb.appadvc2122.model.MPModel;
import com.csb.appadvc2122.repository.MPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MPService {

    @Autowired
    private MPRepository repo;

    public List<MPModel> listAll() {
        return (List<MPModel>) repo.findAll();
    }

    public void save(MPModel mealPlan) {
        repo.save(mealPlan);
    }

    public MPModel get(Integer id) throws PlanNotFoundException {
        Optional<MPModel> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new PlanNotFoundException("Could not find any plan with ID " + id);
    }

    public void delete(Integer id) throws PlanNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new PlanNotFoundException("Could not find any plan with ID " + id);
        }
        repo.deleteById(id);
    }
}
