package com.sandy.recipegenerator.service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.sandy.recipegenerator.model.Ingredient;
import com.sandy.recipegenerator.model.Recipe;
import com.sandy.recipegenerator.model.repository.IngredientsRepository;
import com.sandy.recipegenerator.model.repository.RecipesRepository;

/**
 * RecipeGeneratorService is an implementation class which contains to business logic to determine
 * available recipes.
 * 
 * @author Sandeep
 *
 */
@Service
public class RecipeGeneratorService {

  private IngredientsRepository ingredients;

  private RecipesRepository recipes;

  // Prefer Constructor injection
  public RecipeGeneratorService(IngredientsRepository ingredients, RecipesRepository recipes) {
    super();
    this.ingredients = ingredients;
    this.recipes = recipes;
  }

  /**
   * Method to determine available recipes
   * @return List<Recipe>
   */
  public List<Recipe> getRecipesBasedOnIngredientsAvailability() {

    // Filter if ingredients passed its use-by date from Ingredient Repository
    Date currentDateTime = Calendar.getInstance().getTime();

    List<Ingredient> ingredientListNotExpired = ingredients.getIngredients().stream()
        .filter(x -> currentDateTime.before(x.getUseBy())).collect(Collectors.toList());

    // Get all the titles of the ingredient to be used for filtering the recipes based on
    // availability
    List<String> titleOfAllNonExpiredIngredients =
        ingredientListNotExpired.stream().map(x -> x.getTitle()).collect(Collectors.toList());

    //Filters all the recipes from available ingredients
    List<Recipe> filteredRecipesFromAvailableIngredients = recipes.getRecipes().stream()
        .filter(p -> titleOfAllNonExpiredIngredients.containsAll(p.getIngredients()))
        .collect(Collectors.toList());

    // Sort the recipe to the bottom of the list if best-before is passed the current date

    List<String> titleOfAllWhichPassedBestBefore =
        ingredientListNotExpired.stream().filter(x -> currentDateTime.after(x.getBestBefore()))
            .map(x -> x.getTitle()).collect(Collectors.toList());

    LinkedList<Recipe> newSortedRecipes = new LinkedList<>();

    filteredRecipesFromAvailableIngredients.stream().forEach(x -> {
      if (CollectionUtils.containsAny(x.getIngredients(), titleOfAllWhichPassedBestBefore)) {
        newSortedRecipes.addLast(x);
      } else {
        newSortedRecipes.addFirst(x);
      }
    });

    return newSortedRecipes;

  }

}
