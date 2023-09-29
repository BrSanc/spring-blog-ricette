package org.learning.java.springblogricette.repository;

import org.learning.java.springblogricette.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe,Integer> {
}
