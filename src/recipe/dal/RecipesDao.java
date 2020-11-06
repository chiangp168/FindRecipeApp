package recipe.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import recipe.dal.ConnectionManager;
import recipe.model.Recipes;
import recipe.model.Users;



public class RecipesDao {
	protected ConnectionManager connectionManager;

	private static RecipesDao instance = null;
	protected RecipesDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecipesDao getInstance() {
		if(instance == null) {
			instance = new RecipesDao();
		}
		return instance;
	}
	
	
	public Recipes create(Recipes recipe) throws SQLException {
		String insertRecipe =
				"INSERT INTO Recipes(recipeName, userName, timeToCook, numOfStep) " +
				"VALUES(?,?,?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;
			try {
				connection = connectionManager.getConnection();
				
				insertStmt = connection.prepareStatement(insertRecipe,
					Statement.RETURN_GENERATED_KEYS);
				
				insertStmt.setString(1, recipe.getRecipeName());
				insertStmt.setString(2, recipe.getUser().getUserName());
				insertStmt.setInt(3, recipe.getTimeToCook());
				insertStmt.setInt(4, recipe.getNumOfStep());
				
				insertStmt.executeUpdate();
				
				// Retrieve the auto-generated key and set it, so it can be used by the caller.
				resultKey = insertStmt.getGeneratedKeys();
				int recipeId = -1;
				if(resultKey.next()) {
					recipeId = resultKey.getInt(1);
				} else {
					throw new SQLException("Unable to retrieve auto-generated key.");
				}
				//set reservationId for input reservation
				recipe.setRecipeId(recipeId);;
				return recipe;
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
}
