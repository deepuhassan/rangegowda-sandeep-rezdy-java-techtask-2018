package com.sandy.recipegenerator.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sandy.recipegenerator.model.Recipe;
import com.sandy.recipegenerator.service.RecipeGeneratorService;

/**
 * Restcontroller which exposes getLunch method to retrieve the available lunch recipies.
 * @author Sandeep
 *
 */
@RestController
public class RecipieGeneratorController {

  private RecipeGeneratorService service;

  // Prefer constructor injection
  /**
   * @param service
   */
  private RecipieGeneratorController(RecipeGeneratorService service) {
    super();
    this.service = service;
  }

  /**
   * GET Method
   * @return
   */
  @RequestMapping(value = "/lunch", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Recipe> getLunch() {

    return service.getRecipesBasedOnIngredientsAvailability();
  }

}
