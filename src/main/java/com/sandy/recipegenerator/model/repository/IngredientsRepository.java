package com.sandy.recipegenerator.model.repository;

import java.util.List;
import com.sandy.recipegenerator.model.Ingredient;

/**
 * IngredientsRepository
 * 
 * @author Sandeep
 *
 */
public class IngredientsRepository {

  private List<Ingredient> ingredients;

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }
  

}
