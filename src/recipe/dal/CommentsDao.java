package recipe.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import recipe.model.Comments;

public class CommentsDao {
  private ConnectionManager connectionManager;
  private static CommentsDao instance = null;
  protected CommentsDao() {
    connectionManager = new ConnectionManager();
  }

  public static CommentsDao getInstance() {
    if (instance == null) {
      instance = new CommentsDao();
    }
    return instance;
  }

  public Comments create(Comments comment) throws SQLException {
    String insertComment = "INSERT INTO Comments (UserId,RecipeId,ShortComment,Created) VALUES(?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertComment);
      insertStmt.setInt(1, comment.getUserId());
      insertStmt.setInt(2, comment.getRecipeId());
      insertStmt.setString(3, comment.getContent());
      insertStmt.setTimestamp(4, Timestamp.valueOf(comment.getCreatedTime()));
      insertStmt.executeUpdate();

      resultKey = insertStmt.getGeneratedKeys();
      int commentId = -1;
      if (resultKey.next()) {
        commentId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      comment.setCommentId(commentId);
      return comment;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(insertStmt != null) {
        insertStmt.close();
      }
      if(resultKey != null) {
        resultKey.close();
      }
    }
  }

  public Comments getCommentById(int commentId)throws SQLException {
    String selectComment = "SELECT * FROM Comments WHERE CommentId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectComment);
      selectStmt.setInt(1, commentId);
      results = selectStmt.executeQuery();
      if(results.next()) {
        Integer userId = results.getInt("UserId");
        Integer recipeId = results.getInt("RecipeId");
        String content = results.getString("ShortComment");
        LocalDateTime created = results.getTimestamp("Created").toLocalDateTime();
        Comments comment = new Comments(commentId, userId, recipeId, content, created);
        return comment;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return null;
  }


  public List<Comments> getCommentsByUserId(int userId) throws SQLException{
    List<Comments> list = new ArrayList<Comments>();
    String selectRecipe =
        "SELECT * FROM Comments WHERE UserId =?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectRecipe);
      selectStmt.setInt(1, userId);
      results = selectStmt.executeQuery();
      while(results.next()) {
        Integer commentId = results.getInt("CommentId");
        Integer recipeId = results.getInt("RecipeId");
        String content = results.getString("ShortComment");
        LocalDateTime created = results.getTimestamp("Created").toLocalDateTime();
        Comments comment = new Comments(commentId, userId, recipeId, content, created);
        list.add(comment);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return list;
  }

  public List<Comments> getCommentsByRecipeId(int recipeId) throws SQLException{
    List<Comments> list = new ArrayList<Comments>();
    String selectRecipe =
        "SELECT * FROM Comments WHERE RecipeId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectRecipe);
      selectStmt.setInt(1, recipeId);
      results = selectStmt.executeQuery();
      while(results.next()) {
        Integer commentId = results.getInt("CommentId");
        Integer userId = results.getInt("UserId");
        String content = results.getString("ShortComment");
        LocalDateTime created = results.getTimestamp("Created").toLocalDateTime();
        Comments comment = new Comments(commentId, userId, recipeId, content, created);
        list.add(comment);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return list;
  }

  public Comments updateByCommentId(Comments comments, String newContent) throws SQLException {
    String updateRecipe = "UPDATE Comments set ShortComment=? where CommentId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateRecipe);
      updateStmt.setString(1, newContent);
      updateStmt.setInt(2, comments.getCommentId());
      updateStmt.executeUpdate();

      comments.setContent(newContent);
      return comments;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(updateStmt != null) {
        updateStmt.close();
      }
    }
  }

  public Comments deleteById(Comments comments) throws SQLException {
    String deleteComment = "DELETE FROM Comments WHERE CommentId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteComment);
      deleteStmt.setInt(1, comments.getCommentId());
      deleteStmt.executeUpdate();
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }
}
