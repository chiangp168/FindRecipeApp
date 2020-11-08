package recipe.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import recipe.model.*
import review.dal.ConnectionManager;


/**
 * The type Techniques dao.
 */
public class TechniquesDao {

  /**
   * The Connection manager.
   */
  protected ConnectionManager connectionManager;
  private static TechniquesDao instance = null;

  /**
   * Instantiates a new Techniques dao.
   */
  protected TechniquesDao() {
    connectionManager = new ConnectionManager();
  }

  /**
   * Gets instance.
   *
   * @return the instance
   */
  public static TechniquesDao getInstance() {
    if (instance == null) {
      instance = new TechniquesDao();
    }
    return instance;
  }

  /**
   * Create techniques.
   *
   * @param recipe    the recipe
   * @param technique the technique
   * @return the techniques
   * @throws SQLException the sql exception
   */
  public Techniques create(Recipes recipe, Techniques technique) throws SQLException {
    String insertTechnique = "INSERT INTO Techniques(RecipeId, Description) VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection
          .prepareStatement(insertTechnique, Statement.RETURN_GENERATED_KEYS);
      insertStmt.setInt(1, recipe.getRecipeId());
      insertStmt.setString(2, technique.getDescription());
      resultKey = insertStmt.getGeneratedKeys();
      int techniqueId = -1;
      if (resultKey.next()) {
        techniqueId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      technique.setTechniqueId(techniqueId);
      return technique;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (insertStmt != null) {
        insertStmt.close();
      }
    }
  }

  /**
   * Gets techniques by recipe.
   *
   * @param recipe the recipe
   * @return the techniques by recipe
   * @throws SQLException the sql exception
   */
  public List<Techniques> getTechniquesByRecipe(Recipes recipe) throws SQLException {
    List<Techniques> techniques = new ArrayList<Techniques>();
    String selectTechniques =
        "SELECT TechniqueId,RecipeId,Description FROM Techniques WHERE RecipeId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectTechniques);
      selectStmt.setString(1, recipe.getRecipeId());
      results = selectStmt.executeQuery();
      while (results.next()) {
        Integer resultTechniqueId = results.getInt("TechniqueId");
        Integer resultRecipeId = results.getInt("RecipeId");
        Recipes newRecipe = new Recipes(resultRecipeId);
        String resultDescription = results.getString("Description");
        Techniques technique = new Techniques(resultTechniqueId, newRecipe, resultDescription);
        techniques.add(technique);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
      if (results != null) {
        results.close();
      }
    }
    return techniques;
  }

  /**
   * Update expiration techniques.
   *
   * @param technique    the technique
   * @param newTechnique the new technique
   * @return the techniques
   * @throws SQLException the sql exception
   */
  public Techniques updateExpiration(Techniques technique, String newTechnique) throws SQLException {
    String updateTechnique = "UPDATE Techniques SET Description=? WHERE TechniqueId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateTechnique);
      updateStmt.setString(1, newTechnique);
      updateStmt.setLong(2, technique.getTechniqueId());
      updateStmt.executeUpdate();

      technique.setDescription(newTechnique);
      return technique;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (updateStmt != null) {
        updateStmt.close();
      }
    }
  }

  /**
   * Delete techniques.
   *
   * @param technique the technique
   * @return the techniques
   * @throws SQLException the sql exception
   */
  public Techniques delete(Techniques technique) throws SQLException {
    String deleteTechnique = "DELETE FROM Techniques WHERE TechniqueId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteTechnique);
      deleteStmt.setInt(1, technique.getTechniqueId());
      deleteStmt.executeUpdate();
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }

}
