package recipe.dal;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
				"INSERT INTO Recipes(RecipeName, UserId, TimeToCook, NumOfStep) " +
				"VALUES(?,?,?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;
			try {
				connection = connectionManager.getConnection();
				
				insertStmt = connection.prepareStatement(insertRecipe,
					Statement.RETURN_GENERATED_KEYS);
				
				insertStmt.setString(1, recipe.getRecipeName());
				insertStmt.setInt(2, recipe.getUser().getUserId());//needs double check when Users is done
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
						"WHERE RecipeId=?;";
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
					
					int userId = results.getInt("UserId");
					UsersDao usersDao = UsersDao.getInstance();//needs double check when UsersDao is done
					Users rsuser = usersDao.getUsersByUserId(userId);//needs double check when UsersDao is done
					
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
					
					int userId = results.getInt("UserId");
					UsersDao usersDao = UsersDao.getInstance();//needs double check when UsersDao is done
					Users rsuser = usersDao.getUsersByUserId(userId);//needs double check when UsersDao is done
					
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
	
	public List<Recipes> getRecipesByUserId(int userId) throws SQLException{
		List<Recipes> list = new ArrayList<Recipes>();
		String selectRecipe =
				"SELECT * FROM Recipes WHERE UserId =?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectRecipe);
				selectStmt.setInt(1, userId);
				results = selectStmt.executeQuery();
				
				while(results.next()) {
					int rsrecipeId = results.getInt("RecipeId");
					String rsrecipeName = results.getString("RecipeName");
					int timeToCook = results.getInt("TimeToCook");
					int numOfStep = results.getInt("NumOfStep");
					
					int rsuserId = results.getInt("UserId");
					UsersDao usersDao = UsersDao.getInstance();//needs double check when UsersDao is done
					Users rsuser = usersDao.getUsersByUserId(rsuserId);//needs double check when UsersDao is done
					
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
	
	public List<Recipes> getRecipesByNumOfStep(int numOfStep) throws SQLException{
		List<Recipes> list = new ArrayList<Recipes>();
		String selectRecipe =
				"SELECT * FROM Recipes WHERE NumOfStep <=? LIMIT 10;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectRecipe);
				selectStmt.setInt(1, numOfStep);
				results = selectStmt.executeQuery();
				
				while(results.next()) {
					int rsrecipeId = results.getInt("RecipeId");
					String rsrecipeName = results.getString("RecipeName");
					int timeToCook = results.getInt("TimeToCook");
					int rsnumOfStep = results.getInt("NumOfStep");
					
					int rsuserId = results.getInt("UserId");
					UsersDao usersDao = UsersDao.getInstance();
					Users rsuser = usersDao.getUsersByUserId(rsuserId);
					
					Recipes rsRecipe = new Recipes(rsrecipeId, rsrecipeName, rsuser, timeToCook, rsnumOfStep);
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

	public List<Recipes> getRecipesByPrepTime(int prepTime) throws SQLException{
		List<Recipes> list = new ArrayList<Recipes>();
		String selectRecipe =
				"SELECT * FROM Recipes WHERE TimeToCook <=? LIMIT 10;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectRecipe);
				selectStmt.setInt(1, prepTime);
				results = selectStmt.executeQuery();
				
				while(results.next()) {
					int rsrecipeId = results.getInt("RecipeId");
					String rsrecipeName = results.getString("RecipeName");
					int timeToCook = results.getInt("TimeToCook");
					int rsnumOfStep = results.getInt("NumOfStep");
					
					int rsuserId = results.getInt("UserId");
					UsersDao usersDao = UsersDao.getInstance();
					Users rsuser = usersDao.getUsersByUserId(rsuserId);
					
					Recipes rsRecipe = new Recipes(rsrecipeId, rsrecipeName, rsuser, timeToCook, rsnumOfStep);
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
				"select RecipeName, RecipeId, TimeToCook, NumOfStep, Recipes.UserId, Person.UserName "
				+ "from " 
				+ "Recipes inner join "
				+ "Person "
				+ "on Recipes.UserId = Person.UserId "
				+ "where UserName = ?";
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
					
					int rsuserId = results.getInt("UserId");
					UsersDao usersDao = UsersDao.getInstance();//needs double check when UsersDao is done
					Users rsuser = usersDao.getUsersByUserId(rsuserId);//needs double check when UsersDao is done
					
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
	
	public List<Recipes> getRecipesByAverageRatingPoints(double aveRatingPoints) throws SQLException{
		List<Recipes> list = new ArrayList<Recipes>();
		String selectRecipe =
				"select RecipeName, Recipes.RecipeId,UserId,TimeToCook, NumOfStep, avg_table.avg_rating "
				+"from Recipes " 
				+"inner join "
				+"(select RecipeId,AVG(Ratings.RatingPoints) as avg_rating "
				+"from Ratings "
				+"group by RecipeId) as avg_table "
				+"on Recipes.RecipeId = avg_table.RecipeId "
				+"where avg_rating >= ? "
				+ "limit 10;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectRecipe);
				selectStmt.setDouble(1, aveRatingPoints);
				results = selectStmt.executeQuery();
				
				while(results.next()) {
					int rsrecipeId = results.getInt("RecipeId");
					String rsrecipeName = results.getString("RecipeName");
					int timeToCook = results.getInt("TimeToCook");
					int numOfStep = results.getInt("NumOfStep");
					int rsuserId = results.getInt("UserId");
					UsersDao usersDao = UsersDao.getInstance();//needs double check when UsersDao is done
					Users rsuser = usersDao.getUsersByUserId(rsuserId);//needs double check when UsersDao is done
					Recipes rsRecipe = new Recipes(rsrecipeId, rsrecipeName, rsuser,timeToCook, numOfStep);
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

	public List<Recipes> getRecipesByTag(String tagName) throws SQLException {
		List<Recipes> list = new ArrayList<Recipes>();
		String selectRecipe =
				"SELECT RecipeName, T.RecipeId, T.UserId, TimeToCook, NumOfStep " +
		        "FROM (SELECT Recipes.RecipeName, Recipes.RecipeId, Recipes.UserId, Recipes.TimeToCook, Recipes.NumOfStep  FROM  Recipes INNER JOIN Tags ON Recipes.RecipeId=Tags.RecipeId WHERE TagName = ?) AS T "+
						 "INNER JOIN Ratings ON T.RecipeId = Ratings.RecipeId GROUP BY RecipeId " +
				      "ORDER BY AVG(RatingPoints) ASC LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecipe);
			selectStmt.setString(1, tagName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int rsrecipeId = results.getInt("RecipeId");
				String rsrecipeName = results.getString("RecipeName");
				int timeToCook = results.getInt("TimeToCook");
				int numOfStep = results.getInt("NumOfStep");
				int rsuserId = results.getInt("UserId");
				UsersDao usersDao = UsersDao.getInstance();//needs double check when UsersDao is done
				Users rsuser = usersDao.getUsersByUserId(rsuserId);//needs double check when UsersDao is done
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
}
