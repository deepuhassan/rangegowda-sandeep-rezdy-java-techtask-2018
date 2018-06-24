package com.sandy.recipegenerator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandy.recipegenerator.model.Recipe;
import com.sandy.recipegenerator.model.repository.IngredientsRepository;
import com.sandy.recipegenerator.model.repository.RecipesRepository;
import com.sandy.recipegenerator.service.RecipeGeneratorService;

@RunWith(SpringRunner.class)
@SuppressWarnings("unchecked")
public class RecipeGeneratorServiceTest {

  public static ObjectMapper mapper = new ObjectMapper();

  private IngredientsRepository ingredients;

  private RecipesRepository recipes;

  @Before
  public void setUpMocks()
      throws JsonParseException, JsonMappingException, FileNotFoundException, IOException {

    recipes =
        mapper.readValue(ResourceUtils.getFile("classpath:recipes.json"), RecipesRepository.class);

  }
  
  @Test
  public void should_return_two_recipes_inorder_HamAndCheese_Hotdog()
      throws JsonParseException, JsonMappingException, FileNotFoundException, IOException {

    ingredients = mapper.readValue(ResourceUtils.getFile("classpath:ingredients-test1.json"),
        IngredientsRepository.class);

    RecipeGeneratorService service = new RecipeGeneratorService(ingredients, recipes);
    List<Recipe> recipes = service.getRecipesBasedOnIngredientsAvailability();

    assertTrue(!recipes.isEmpty());
    assertEquals(2, recipes.size());
    assertThat( recipes, contains(
        hasProperty("title", is("Ham and Cheese Toastie")), 
        hasProperty("title", is("Hotdog"))
    ));
    
  }
  
  @Test
  public void should_return_three_recipes_inorder_Salad_HamAndCheese_Hotdog()
      throws JsonParseException, JsonMappingException, FileNotFoundException, IOException {

    ingredients = mapper.readValue(ResourceUtils.getFile("classpath:ingredients-test2.json"),
        IngredientsRepository.class);

    RecipeGeneratorService service = new RecipeGeneratorService(ingredients, recipes);
    List<Recipe> recipes = service.getRecipesBasedOnIngredientsAvailability();

    assertTrue(!recipes.isEmpty());
    assertEquals(3, recipes.size());
    assertThat( recipes, contains(
        hasProperty("title", is("Salad")),
        hasProperty("title", is("Ham and Cheese Toastie")), 
        hasProperty("title", is("Hotdog"))
    ));
    
  }
  
  @Test
  public void shouldnot_return_any_recipes()
      throws JsonParseException, JsonMappingException, FileNotFoundException, IOException {

    ingredients = mapper.readValue(ResourceUtils.getFile("classpath:ingredients-test3.json"),
        IngredientsRepository.class);

    RecipeGeneratorService service = new RecipeGeneratorService(ingredients, recipes);
    List<Recipe> recipes = service.getRecipesBasedOnIngredientsAvailability();

    assertTrue(recipes.isEmpty());
    
  }


}
