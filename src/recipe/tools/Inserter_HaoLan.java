package recipe.tools;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import javax.xml.crypto.Data;
import javax.xml.stream.events.Comment;
import recipe.dal.CommentsDao;
import recipe.model.Comments;

public class Inserter_HaoLan {

  public static void main(String[] args) throws SQLException {
    // create
    Comments newCommet = new Comments(27, 40, "new comment", LocalDateTime.now());
    CommentsDao commentsDao = CommentsDao.getInstance();
    commentsDao.create(newCommet);
    System.out.printf("create new comment with id: %d \n", newCommet.getCommentId());
    System.out.println("___________");

    // select by comment id
    System.out.printf("get new comment with content: %s \n", commentsDao.getCommentById(newCommet.getCommentId()).getContent());

    // select by users id
    System.out.println("___________");
    System.out.printf("select by users id: 27\n");
    List<Comments> comments = commentsDao.getCommentsByUserId(27);
    for (Comments comment : comments) {
      System.out.printf("User: %d, Content: %s \n", comment.getUserId(), comment.getContent());
    }

    // select by recipe id
    System.out.println("___________");
    System.out.printf("select by recipes id: 41\n");
    List<Comments> comments2 = commentsDao.getCommentsByRecipeId(41);
    for (Comments comment : comments2) {
      System.out.printf("RecipeID: %d, Content: %s \n", comment.getRecipeId(), comment.getContent());
    }

    // delete by comment id
    System.out.println("___________");
    System.out.printf("delete by comment id: %s\n", newCommet.getCommentId());
    commentsDao.deleteById(newCommet);
  }
}
