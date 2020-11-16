package recipe.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import recipe.dal.IngredientsDao;
import recipe.dal.RecipesDao;
import recipe.model.Ingredients;
import recipe.model.Recipes;


@WebServlet("/ingredientsread")
public class IngredientsRead extends HttpServlet {

  protected IngredientsDao ingredientsDao;
  protected RecipesDao recipesDao;

  @Override
  public void init() throws ServletException {
    ingredientsDao = ingredientsDao.getInstance();
    recipesDao = recipesDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    //Just render the JSP.
    req.getRequestDispatcher("/IngredientsRead.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    List<Ingredients> foundIngredient = new ArrayList();

    Integer recipeId = Integer.valueOf(req.getParameter("recipeId"));
    if (recipeId == null) {
      messages.put("success", "Invalid RecipeId");
    } else {
      try {
        Recipes recipe = recipesDao.getRecipeById(recipeId);
        if (recipe == null) {
          messages.put("success", "Invalid RecipeId");
        } else{
          foundIngredient = ingredientsDao.getIngredientsByRecipe(recipe);
        }
        messages.put("success", "Displaying results for " + recipe.getRecipeName());
      }
      catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
        }
      req.setAttribute("ingredients", foundIngredient);
      req.getRequestDispatcher("/IngredientsRead.jsp").forward(req, resp);
      }
  }
}
