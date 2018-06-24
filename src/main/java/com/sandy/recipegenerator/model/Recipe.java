package com.sandy.recipegenerator.model;

import java.io.Serializable;
import java.util.List;

/**
 * Recipe Bean
 * @author deepu
 *
 */
public class Recipe implements Serializable {

  private static final long serialVersionUID = 1840856126722401730L;

  private String title;

  private List<String> ingredients;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<String> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<String> ingredients) {
    this.ingredients = ingredients;
  }

  @Override
  public String toString() {
    return "Recipe [title=" + title + ", ingredients=" + ingredients + "]";
  }



}
