package com.sandy.recipegenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.sandy.recipegenerator.model.Recipe;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RecipieGeneratorControllerTests {

  @Value("${local.server.port}")
  private int port;

  @SuppressWarnings("unchecked")
  @Test
  public void shouldSuccess() {

    Response response = RestAssured.given().port(port).when().get("/lunch").peek();

    Assert.assertEquals(200, response.getStatusCode());

    List<Recipe> responseObj = response.getBody().as(new ArrayList<>().getClass());

    assertNotNull(responseObj);

    assertTrue(!responseObj.isEmpty());
    assertEquals(2, responseObj.size());
  }
  
}
