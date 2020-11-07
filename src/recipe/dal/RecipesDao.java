package recipe.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	public Recipes getRecipeById(int recipeId)throws SQLException {
		String selectRecipe =
				"SELECT * " +
						"FROM Recipes " +
						"WHERE RecipesId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectRecipe);
				selectStmt.setInt(1, recipeId);
				results = selectStmt.executeQuery();
				
				if(results.next()) {
					int rsrecipeId = results.getInt("RecipeId");
					String recipeName = results.getString("RecipeName");
					int timeToCook = results.getInt("TimeToCook");
					int numOfStep = results.getInt("NumOfStep");
					
					String userName = results.getString("UserName");
					UsersDao usersDao = UsersDao.getInstance();//needs double check when UsersDao is done
					Users rsuser = usersDao.getUserByUserName(userName);//needs double check when UsersDao is done
					
					Recipes rsRecipe = new Recipes(rsrecipeId, recipeName, rsuser, timeToCook, numOfStep);
					return rsRecipe;
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
	
	public List<Recipes> getRecipesByRecipeName(String recipeName) throws SQLException{
		List<Recipes> list = new ArrayList<Recipes>();
		String selectRecipe =
				"SELECT * FROM Recipes WHERE RecipeName =?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectRecipe);
				selectStmt.setString(1, recipeName);
				results = selectStmt.executeQuery();
				
				while(results.next()) {
					int rsrecipeId = results.getInt("RecipeId");
					String rsrecipeName = results.getString("RecipeName");
					int timeToCook = results.getInt("TimeToCook");
					int numOfStep = results.getInt("NumOfStep");
					
					String userName = results.getString("UserName");
					UsersDao usersDao = UsersDao.getInstance();//needs double check when UsersDao is done
					Users rsuser = usersDao.getUserByUserName(userName);//needs double check when UsersDao is done
					
					Recipes rsRecipe = new Recipes(rsrecipeId, rsrecipeName, rsuser, timeToCook, numOfStep);
					list.add(rsRecipe);
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
	
	public List<Recipes> getRecipesByUserName(String userName) throws SQLException{
		List<Recipes> list = new ArrayList<Recipes>();
		String selectRecipe =
				"SELECT * FROM Recipes WHERE userName =?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectRecipe);
				selectStmt.setString(1, userName);
				results = selectStmt.executeQuery();
				
				while(results.next()) {
					int rsrecipeId = results.getInt("RecipeId");
					String rsrecipeName = results.getString("RecipeName");
					int timeToCook = results.getInt("TimeToCook");
					int numOfStep = results.getInt("NumOfStep");
					
					String rsuserName = results.getString("UserName");
					UsersDao usersDao = UsersDao.getInstance();//needs double check when UsersDao is done
					Users rsuser = usersDao.getUserByUserName(rsuserName);//needs double check when UsersDao is done
					
					Recipes rsRecipe = new Recipes(rsrecipeId, rsrecipeName, rsuser, timeToCook, numOfStep);
					list.add(rsRecipe);
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
	
	
	public Recipes updateByTimeToCook(Recipes recipe, int newTimeToCook) throws SQLException {
		String updateRecipe = "UPDATE Recipes set TimeToCook=? where RecipeId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRecipe);
			updateStmt.setInt(1, newTimeToCook);
			updateStmt.setInt(2, recipe.getRecipeId());
			
			updateStmt.executeUpdate();
			recipe.setTimeToCook(newTimeToCook);
			return recipe;
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
	
	public Recipes delete(Recipes recipe) throws SQLException {
		String deleteRecipe = "DELETE FROM Recipes WHERE RecipeId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRecipe);
			deleteStmt.setInt(1, recipe.getRecipeId());
			int affectedRows = deleteStmt.executeUpdate();	
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for RecipeId =" + recipe.getRecipeId());
			}
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
