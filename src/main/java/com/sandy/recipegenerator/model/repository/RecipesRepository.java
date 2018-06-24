package com.sandy.recipegenerator.model.repository;


import java.util.ArrayList;
import java.util.List;
import com.sandy.recipegenerator.model.Recipe;

/**
 * RecipesRepository
 * @author Sandeep
 *
 */
public class RecipesRepository {

  private List<Recipe> recipes = new ArrayList<>();

  public List<Recipe> getRecipes() {
    return recipes;
  }

  public void setRecipes(List<Recipe> recipes) {
    this.recipes = recipes;
  }    
 
}
