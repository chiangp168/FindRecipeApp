package recipe.servlet;
import recipe.dal.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import recipe.model.Ingredients;
import recipe.model.Recipes;


@WebServlet("/ingredientscreate")
public class IngredientsCreate extends HttpServlet {

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
    req.getRequestDispatcher("/IngredientsCreate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve and validate name.
    Integer recipeId = Integer.valueOf(req.getParameter("recipeId"));
    if (recipeId == null) {
      messages.put("success", "Invalid RecipeId");
    } else {
      String ingredient = req.getParameter("ingredient");
      try {
        Recipes recipe = recipesDao.getRecipeById(recipeId);
        if (recipe == null) {
          messages.put("success", "Invalid RecipeId");
        } else{
          Ingredients newIngredient = new Ingredients(recipe,ingredient);
          newIngredient = ingredientsDao.create(newIngredient);
          messages.put("success", "Successfully created " + recipe.getRecipeName() + " with ingredient" + newIngredient.getIngredient());
        }
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/IngredientsCreate.jsp").forward(req, resp);
  }
}
