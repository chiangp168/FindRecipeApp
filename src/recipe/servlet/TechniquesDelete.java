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


@WebServlet("/techniquesdelete")
public class TechniquesDelete extends HttpServlet {

  protected TechniquesDao techniquesDao;
  protected RecipesDao recipesDao;

  @Override
  public void init() throws ServletException {
    techniquesDao = techniquesDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    // Provide a title and render the JSP.
    messages.put("title", "Delete Technique");
    req.getRequestDispatcher("/TechniquesDelete.jsp").forward(req, resp);
  }


  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
	Map<String, String> messages = new HashMap<String, String>();
    Integer techniqueId = Integer.valueOf(req.getParameter("techniqueId"));
    if (techniqueId == null) {
      messages.put("title", "Invalid Technique Id");
      messages.put("disableSubmit", "true");
    } else {
      Techniques toDelete = new Techniques(techniqueId);
      try {
        toDelete = techniquesDao.delete(toDelete);
        // Update the message.
        if (toDelete == null) {
          messages.put("title", "Successfully deleted " + techniqueId);
          messages.put("disableSubmit", "true");
        } else {
          messages.put("title", "Failed to delete " + techniqueId);
          messages.put("disableSubmit", "false");
        }
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }
    req.setAttribute("messages", messages);
    req.getRequestDispatcher("/TechniquesDelete.jsp").forward(req, resp);
  }
}
