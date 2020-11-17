package recipe.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import recipe.dal.CommentsDao;
import recipe.model.Comments;

@WebServlet("/commentsupdate")
public class CommentUpdate extends HttpServlet {

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
    req.getRequestDispatcher("/CommentUpdate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    // Retrieve and validate name.
    Integer commentId = Integer.valueOf(req.getParameter("commentId"));
    String newContent = req.getParameter("content");
    if (commentId == null) {
      messages.put("success", "Invalid CommentId");
    } else {
      try {
        Comments comments = commentsDao.getCommentById(commentId);
        if (comments == null) {
          messages.put("success", "CommentId does not exist. No update to perform");
        } else {
          commentsDao.updateByCommentId(comments, newContent);
          messages.put("success", "Successfully updated comment with id " + comments.getCommentId());
        }
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }
    req.getRequestDispatcher("/CommentUpdate.jsp").forward(req, resp);
  }
}
