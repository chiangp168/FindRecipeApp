package recipe.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import recipe.dal.CommentsDao;
import recipe.dal.RecipesDao;
import recipe.dal.UsersDao;
import recipe.model.Comments;
import recipe.model.Recipes;
import recipe.model.Users;

@WebServlet("/commentscreate")
public class CommentCreate extends HttpServlet {

  private CommentsDao commentsDao;
  private RecipesDao recipesDao;
  private UsersDao usersDao;
  @Override
  public void init() throws ServletException {
    commentsDao = CommentsDao.getInstance();
    recipesDao = RecipesDao.getInstance();
    usersDao = UsersDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    //Just render the JSP.
    req.getRequestDispatcher("/CommentCreate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    // Retrieve and validate name.
    Integer recipeId = Integer.valueOf(req.getParameter("recipeId"));
    Integer userId = Integer.valueOf(req.getParameter("userId"));
    if (recipeId == null) {
      messages.put("success", "Invalid RecipeId");
    } else if (userId == null) {
      messages.put("success", "Invalid UserId");
    }
    else {
      String content = req.getParameter("content");
      try {
        Recipes recipe = recipesDao.getRecipeById(recipeId);
        Users user = usersDao.getUsersByUserId(recipeId);
        if (recipe == null) {
          messages.put("success", "Invalid RecipeId");
        } else if (user == null) {
          messages.put("success", "Invalid UserId");
        } else {
          Comments newComment = new Comments(recipeId, userId, content, LocalDateTime.now());
          Comments comments = commentsDao.create(newComment);
          messages.put("success", "Successfully created comment " + comments.getCommentId() + " with content" + comments.getContent());
        }
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }
    req.getRequestDispatcher("/CommentCreate.jsp").forward(req, resp);
  }


}
