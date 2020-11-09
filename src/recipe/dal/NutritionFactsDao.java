package recipe.dal;

import recipe.model.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import blog.dal.BlogPostsDao;
import blog.dal.BlogUsersDao;
import blog.model.BlogPosts;
import blog.model.BlogUsers;
import blog.model.Reshares;




public class NutritionFactsDao {
	protected ConnectionManager connectionManager;

	private static NutritionFactsDao instance = null;
	protected NutritionFactsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static NutritionFactsDao getInstance() {
		if (instance == null) {
			instance = new NutritionFactsDao();
		}
		return instance;
	}
	
	// CREATE
	public NutritionFacts create(NutritionFacts nutritionFact) throws SQLException {
		String insertNutritionFact = 
				"INSERT INTO NutritionFacts(Calories,Total_fat,Sugar, Sodium, Protein, Saturated_fat, Carb, RecipeId)"
				+ " VALUES(?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertNutritionFact,
					Statement.RETURN_GENERATED_KEYS);
			

			insertStmt.setDouble(1, nutritionFact.getCalories());
			insertStmt.setInt(2, nutritionFact.getTotalFat());
			insertStmt.setInt(3, nutritionFact.getSugar());
			insertStmt.setInt(4, nutritionFact.getSodium());
			insertStmt.setInt(5, nutritionFact.getProtein());
			insertStmt.setInt(6, nutritionFact.getSaturatedFat());
			insertStmt.setInt(7, nutritionFact.getCarb());
			insertStmt.setInt(8, nutritionFact.getRecipeId());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int nutritionFactsId = -1;
			
			if(resultKey.next()) {
				nutritionFactsId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			nutritionFact.setNutritionFactsId(nutritionFactsId);
			
			return nutritionFact;
			
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
	
	// UPDATE
	public NutritionFacts updateNutritionFact(NutritionFacts nutritionFact, double calories, int totalFat, int sodium, int protein, int saturatedFat, int carb, int recipeId) throws SQLException {
		String updateNutritionFact = "UPDATE NutritionFacts SET calories=?, totalFat=?, sugar=?, sodium=?, protein=?, saturatedFat=?, carb=?, recipeId=? WHERE nutritionFactsId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateNutritionFact);
			updateStmt.setDouble(1, calories);
			updateStmt.setInt(2, totalFat);
			updateStmt.setInt(3, sodium);
			updateStmt.setInt(4, protein);
			updateStmt.setInt(5, saturatedFat);
			updateStmt.setInt(6, carb);
			updateStmt.setInt(7, recipeId);
			updateStmt.setInt(8, nutritionFact.getNutritionFactsId());
			updateStmt.executeUpdate();

			nutritionFact.setCalories(calories);
			nutritionFact.setTotalFat(totalFat);
			nutritionFact.setSodium(sodium);
			nutritionFact.setProtein(protein);
			nutritionFact.setSaturatedFat(saturatedFat);
			nutritionFact.setCarb(carb);
			nutritionFact.setRecipeId(recipeId);
			
			return nutritionFact;
			
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
	
	
	// DELETE
	public NutritionFacts delete(NutritionFacts nutritionFact) throws SQLException {
		String deleteNutritionFact = 
				"DELETE FROM NutritionFacts WHERE NutritionFactsId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteNutritionFact);
			deleteStmt.setInt(1, nutritionFact.getNutritionFactsId());
			
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
	
	
//	public NutritionFacts getCaloriesByRecipeId(int recipeId) throws SQLException {
//		String selectReshare =
//			"SELECT recipeId ,calories " +
//			"FROM NutritionFacts " +
//			"WHERE RecipeId=?;";
//		Connection connection = null;
//		PreparedStatement selectStmt = null;
//		ResultSet results = null;
//		try {
//			connection = connectionManager.getConnection();
//			selectStmt = connection.prepareStatement(selectReshare);
//			selectStmt.setInt(1, recipeId);
//			results = selectStmt.executeQuery();
//			
//			NutritionFactsDao nutritionFactsDao = NutritionFactsDao.getInstance();
//			RecipesDao recipesDao = RecipesDao.getInstance();
//			if(results.next()) {
//				int resultReshareId = results.getInt("ReshareId");
//				String userName = results.getString("UserName");
//				int postId = results.getInt("PostId");
//				
//				BlogUsers blogUser = blogUsersDao.getBlogUserFromUserName(userName);
//				BlogPosts blogPost = blogPostsDao.getBlogPostById(postId);
//				Reshares reshare = new Reshares(resultReshareId, blogUser, blogPost);
//				return reshare;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			if(connection != null) {
//				connection.close();
//			}
//			if(selectStmt != null) {
//				selectStmt.close();
//			}
//			if(results != null) {
//				results.close();
//			}
//		}
//		return null;
//	}

}