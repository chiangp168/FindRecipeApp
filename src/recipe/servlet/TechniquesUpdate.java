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


@WebServlet("/techniquesupdate")
public class TechniquesUpdate extends HttpServlet {

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

    Integer techniqueId = Integer.valueOf(req.getParameter("techniqueId"));
    if (techniqueId == null ) {
      messages.put("success", "Invalid Id");
    } else {
      try {
        Techniques technique = techniquesDao.getTechniquesById(techniqueId);
        if (technique == null) {
          messages.put("success", "Invalid RecipeId");
        } else {
          messages.put("technique",technique.getDescription());
        }
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }
    req.getRequestDispatcher("/TechniquesUpdate.jsp").forward(req, resp);
  }


  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    Integer techniqueId = Integer.valueOf(req.getParameter("techniqueId"));
    if (techniqueId == null) {
      messages.put("success", "Invalid ID");
    } else {
      try {
        Techniques technique = techniquesDao.getTechniquesById(techniqueId);
        if (technique == null) {
          messages.put("success", "Technique does not exist. No update to perform.");
        } else {
          String newTechnique = req.getParameter("technique");
          if (newTechnique == null || newTechnique.trim().isEmpty()) {
            messages.put("success", "Please enter a valid technique.");
          } else {
            technique = techniquesDao.updateTechniqueDescription(technique, newTechnique);
            messages.put("success", "Successfully updated " + technique.getTechniqueId());
          }
        }
        req.setAttribute("technique", technique);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      req.getRequestDispatcher("/TechniquesUpdate.jsp").forward(req, resp);
    }
  }
}
