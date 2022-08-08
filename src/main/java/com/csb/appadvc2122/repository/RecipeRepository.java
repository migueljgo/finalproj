package com.csb.appadvc2122.repository;

import com.csb.appadvc2122.model.Recipe;
import com.csb.appadvc2122.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
