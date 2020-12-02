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


@WebServlet("/ingredientsearch")
public class IngredientsSearch extends HttpServlet {

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
    req.getRequestDispatcher("/IngredientsSearch.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    List<Recipes> foundRecipes = new ArrayList<Recipes>();

    Integer numIngredients = Integer.valueOf(req.getParameter("num_ingredient"));
    List<String> ingredientsRequired = new ArrayList<String>();
    
    for (int i = 1; i <= numIngredients; i++) {
    	String requiredField = "ingredient" + String.valueOf(i);
    	String ingredient = req.getParameter(requiredField);
    	if (ingredient != null || !ingredient.trim().isEmpty() ) {
    	ingredientsRequired.add(ingredient.trim().toLowerCase());
    	}
    }
    if (ingredientsRequired.size() == 0) {
      messages.put("success", "Invalid Ingredient. Please try again");
    } else {
      try {
 
    	foundRecipes = ingredientsDao.getRecipebyIngredientList(ingredientsRequired);
        messages.put("success", "Displaying results for " + String.join(", ", ingredientsRequired));
      }
      catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
        }
      req.setAttribute("messages", messages);
      req.setAttribute("recipes", foundRecipes );
      req.getRequestDispatcher("/IngredientsSearch.jsp").forward(req, resp);
      }
  }
}
