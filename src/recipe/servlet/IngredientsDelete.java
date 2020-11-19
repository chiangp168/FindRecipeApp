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


@WebServlet("/ingredientsdelete")
public class IngredientsDelete extends HttpServlet {

  protected IngredientsDao ingredientsDao;

  @Override
  public void init() throws ServletException {
    ingredientsDao = ingredientsDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    // Provide a title and render the JSP.
    messages.put("title", "Delete Ingredient");
    req.getRequestDispatcher("/IngredientsDelete.jsp").forward(req, resp);
  }


  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
	Map<String, String> messages = new HashMap<String, String>();
    Integer ingredientId = Integer.valueOf(req.getParameter("ingredientId"));
    if (ingredientId == null) {
      messages.put("title", "Invalid Ingredient Id");
      messages.put("disableSubmit", "true");
    } else {
      Ingredients toDelete = new Ingredients(ingredientId);
      try {
        toDelete = ingredientsDao.delete(toDelete);
        // Update the message.
        if (toDelete == null) {
          messages.put("title", "Successfully deleted " + String.valueOf(ingredientId));
          messages.put("disableSubmit", "true");
        } else {
          messages.put("title", "Failed to delete " + String.valueOf(ingredientId));
          messages.put("disableSubmit", "false");
        }
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }
    req.setAttribute("messages", messages);
    req.getRequestDispatcher("/IngredientsDelete.jsp").forward(req, resp);
  }
}
