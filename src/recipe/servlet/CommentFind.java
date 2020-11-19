package recipe.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import recipe.dal.CommentsDao;
import recipe.model.Comments;

@WebServlet("/commentsfind")
public class CommentFind extends HttpServlet {

  private CommentsDao commentsDao;
  @Override
  public void init() throws ServletException {
    commentsDao = CommentsDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    //Just render the JSP.
    req.getRequestDispatcher("/CommentFind.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    // Retrieve and validate name.
    String recipeIdStr = req.getParameter("recipeId");
    String userIdStr = req.getParameter("userId");
    if (recipeIdStr.length() == 0 && userIdStr.length() == 0) {
      messages.put("title", "invalid recipeId and userId");
    } else {
      Integer recipeId = null;
      Integer userId = null;
      if (recipeIdStr.length() != 0) {
        try {
          recipeId = Integer.valueOf(recipeIdStr);
        } catch (NumberFormatException e) {
          messages.put("title", "invalid recipeId");
        }
      } if (userIdStr.length() != 0) {
        try {
          userId = Integer.valueOf(userIdStr);
        } catch (NumberFormatException e) {
          messages.put("title", "invalid userId");
        }
      }
      try {
        List<Comments> comments = new ArrayList<>();
        if (recipeId != null && userId != null) {
          comments = commentsDao.getCommentsByIds(recipeId, userId);
        } else if (recipeId != null) {
          comments = commentsDao.getCommentsByRecipeId(recipeId);
        } else if (userId != null) {
          comments = commentsDao.getCommentsByUserId(userId);
        }
        messages.put("success", "Displaying results for " + comments);
        req.setAttribute("comments", comments);
      }
      catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      req.getRequestDispatcher("/CommentFind.jsp").forward(req, resp);
    }
  }
}