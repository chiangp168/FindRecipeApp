package recipe.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
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


@WebServlet("/ingredientsupdate")
public class IngredientsUpdate extends HttpServlet {

  protected IngredientsDao ingredientsDao;

  @Override
  public void init() throws ServletException {
    ingredientsDao = ingredientsDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    Integer ingredientId = Integer.valueOf(req.getParameter("ingredientId"));
    if (ingredientId == null) {
      messages.put("success", "Invalid Id");
    } else {
      try {
        Ingredients ingredient = ingredientsDao.getIngredientsById(ingredientId);
        if (ingredient == null) {
          messages.put("success", "Invalid RecipeId");
        } else {
          messages.put("ingredient",ingredient.getIngredient());
        }
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }
    req.getRequestDispatcher("/IngredientsUpdate.jsp").forward(req, resp);
  }


  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    Integer ingredientId = Integer.valueOf(req.getParameter("ingredientId"));
    if (ingredientId == null) {
      messages.put("success", "Invalid ID");
    } else {
      try {
        Ingredients ingredient = ingredientsDao.getIngredientsById(ingredientId);
        if (ingredient == null) {
          messages.put("success", "Ingredient does not exist. No update to perform.");
        } else {
          String newIngredient = req.getParameter("ingredient");
          if (newIngredient == null || newIngredient.trim().isEmpty()) {
            messages.put("success", "Please enter a valid ingredient.");
          } else {
            ingredient = ingredientsDao.updateIngredient(ingredient, newIngredient);
            messages.put("success", "Successfully updated " + ingredient.getIngredientId());
          }
        }
        req.setAttribute("ingredient", ingredient);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      req.setAttribute("messages", messages);
      req.getRequestDispatcher("/IngredientsUpdate.jsp").forward(req, resp);
    }
  }
}
