package recipe.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import recipe.model.Ingredients;
import recipe.model.Recipes;
import review.dal.ConnectionManager;


/**
 * The type Ingredients dao.
 */
public class IngredientsDao {

  /**
   * The Connection manager.
   */
  protected ConnectionManager connectionManager;
  private static IngredientsDao instance = null;

  /**
   * Instantiates a new Ingredients dao.
   */
  protected IngredientsDao() {
    connectionManager = new ConnectionManager();
  }

  /**
   * Gets instance.
   *
   * @return the instance
   */
  public static IngredientsDao getInstance() {
    if (instance == null) {
      instance = new IngredientsDao();
    }
    return instance;
  }

  /**
   * Create ingredients.
   *
   * @param recipe     the recipe
   * @param ingredient the ingredient
   * @return the ingredients
   * @throws SQLException the sql exception
   */
  public Ingredients create(Recipes recipe, Ingredients ingredient) throws SQLException {
    String insertIngredient = "INSERT INTO Ingredients(RecipeId, Ingredient) VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection
          .prepareStatement(insertIngredient, Statement.RETURN_GENERATED_KEYS);
      insertStmt.setInt(1, recipe.getRecipeId());
      insertStmt.setString(2, ingredient.getIngredient());
      resultKey = insertStmt.getGeneratedKeys();
      int ingredientId = -1;
      if (resultKey.next()) {
        ingredientId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      ingredient.setIngredientId(ingredientId);
      return ingredient;
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
   * Gets ingredients by recipe.
   *
   * @param recipe the recipe
   * @return the ingredients by recipe
   * @throws SQLException the sql exception
   */
  public List<Ingredients> getIngredientsByRecipe(Recipes recipe) throws SQLException {
    List<Ingredients> ingredients = new ArrayList<Ingredients>();
    String selectIngredients =
        "SELECT IngredientId,RecipeId, Ingredient FROM Ingredients WHERE RecipeId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectIngredients);
      selectStmt.setString(1, recipe.getRecipeId());
      results = selectStmt.executeQuery();
      while (results.next()) {
        Integer resultIngredientId = results.getInt("IngredientId");
        Integer resultRecipeId = results.getInt("RecipeId");
        Recipes newRecipe = new Recipes(resultRecipeId);
        String resultIngredient = results.getString("Ingredient");
        Ingredients ingredient = new Ingredients(resultIngredientId, newRecipe, resultIngredient);
        ingredients.add(ingredient);
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
    return ingredients;
  }

  /**
   * Update expiration ingredients.
   *
   * @param ingredient    the ingredient
   * @param newIngredient the new ingredient
   * @return the ingredients
   * @throws SQLException the sql exception
   */
  public Ingredients updateExpiration(Ingredients ingredient, String newIngredient) throws SQLException {
    String updateIngredient = "UPDATE Ingredients SET Ingredient=? WHERE IngredientId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateIngredient);
      updateStmt.setString(1, newIngredient);
      updateStmt.setLong(2, ingredient.getIngredientId());
      updateStmt.executeUpdate();

      ingredient.setIngredient(newIngredient);
      return ingredient;
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
   * Delete ingredients.
   *
   * @param ingredient the ingredient
   * @return the ingredients
   * @throws SQLException the sql exception
   */
  public Ingredients delete(Ingredients ingredient) throws SQLException {
    String deleteIngredient = "DELETE FROM Ingredients WHERE IngredientId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteIngredient);
      deleteStmt.setInt(1, ingredient.getIngredientId());
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
