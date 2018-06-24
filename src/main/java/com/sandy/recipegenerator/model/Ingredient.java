package com.sandy.recipegenerator.model;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Ingredient Bean
 * @author Sandeep
 *
 */
public class Ingredient implements Serializable {

  private static final long serialVersionUID = -5591615938337870591L;

  private String title;

  @JsonProperty("best-before")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date bestBefore;

  @JsonProperty("use-by")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date useBy;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getBestBefore() {
    return bestBefore;
  }

  public void setBestBefore(Date bestBefore) {
    this.bestBefore = bestBefore;
  }

  public Date getUseBy() {
    return useBy;
  }

  public void setUseBy(Date useBy) {
    this.useBy = useBy;
  }

  @Override
  public String toString() {
    return "Ingredient [title=" + title + ", bestBefore=" + bestBefore + ", useBy=" + useBy + "]";
  }

}
