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

@WebServlet("/commentsdelete")
public class CommentDelete extends HttpServlet {

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
    req.getRequestDispatcher("/CommentDelete.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    // Retrieve and validate name.
    Integer commentId = Integer.valueOf(req.getParameter("commentId"));
    if (commentId == null) {
      messages.put("title", "Invalid CommentId");
    } else {
      try {
        Comments comments = commentsDao.getCommentById(commentId);
        if (comments == null) {
          messages.put("title", "Invalid CommentId");
        } else {
          comments = commentsDao.deleteById(comments);
          // Update the message.
          if (comments == null) {
            messages.put("title", "Successfully deleted comment with comment id " + commentId);
            messages.put("disableSubmit", "true");
          } else {
            messages.put("title", "Failed to delete comment with comment id" + commentId);
            messages.put("disableSubmit", "false");
          }
        }
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }
    req.getRequestDispatcher("/CommentDelete.jsp").forward(req, resp);
  }
}
