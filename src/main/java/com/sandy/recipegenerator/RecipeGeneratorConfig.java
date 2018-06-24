package com.sandy.recipegenerator;

import java.io.IOException;
import java.net.URL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandy.recipegenerator.model.repository.IngredientsRepository;
import com.sandy.recipegenerator.model.repository.RecipesRepository;

/**
 * Config class to load recipes and ingredients bean from resource file
 * 
 * @author Sandeep
 *
 */
@Configuration
public class RecipeGeneratorConfig {

  private ResourceLoader resourceLoader;

  public RecipeGeneratorConfig(ResourceLoader resourceLoader) {
    super();
    this.resourceLoader = resourceLoader;
  }


  /**
   * Method to initialize Ingredients from ingredients.json
   * 
   * @return IngredientsRepository
   * @throws JsonParseException
   * @throws JsonMappingException
   * @throws IOException
   * @throws ClassNotFoundException
   */
  @Bean
  IngredientsRepository createIngredientsBean()
      throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException {

    ObjectMapper jsonMapper = new ObjectMapper();

    IngredientsRepository ingredients =
        jsonMapper.readValue(getResourceURL("ingredients.json"), IngredientsRepository.class);
    return ingredients;

  }


  /**
   * Method to retreive resource URL
   * @param fileName
   * @return URL
   */
  private URL getResourceURL(String fileName) {
    ClassLoader cLoader = this.getClass().getClassLoader();

    URL url = cLoader.getResource(fileName);
    return url;
  }

  /**
   * Method to initialize Recipes from recipies.json
   * 
   * @return RecipesRepository
   * @throws JsonParseException
   * @throws JsonMappingException
   * @throws IOException
   */
  @Bean
  RecipesRepository createRecipesBean()
      throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper jsonMapper = new ObjectMapper();

    RecipesRepository recipes =
        jsonMapper.readValue(getResourceURL("recipes.json"), RecipesRepository.class);

    return recipes;
  }



}
