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
import recipe.dal.TechniquesDao;
import recipe.dal.RecipesDao;
import recipe.model.Techniques;
import recipe.model.Recipes;


@WebServlet("/techniquescreate")
public class TechniquesCreate extends HttpServlet {

  protected TechniquesDao techniquesDao;
  protected RecipesDao recipesDao;

  @Override
  public void init() throws ServletException {
    techniquesDao = techniquesDao.getInstance();
    recipesDao = recipesDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    //Just render the JSP.
    req.getRequestDispatcher("/TechniquesCreate.jsp").forward(req, resp);
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
      String technique = req.getParameter("technique");
      try {
        Recipes recipe = recipesDao.getRecipeById(recipeId);
        if (recipe == null) {
          messages.put("success", "Invalid RecipeId");
        } else{
          Techniques newTechnique = new Techniques(recipe,technique);
          newTechnique = techniquesDao.create(newTechnique);
          messages.put("success", "Successfully created " + recipe.getRecipeName() + " with technique" + newTechnique.getTechnique());
        }
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/TechniquesCreate.jsp").forward(req, resp);
  }
}
