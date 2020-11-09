package recipe.dal;

import recipe.model.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;




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
				"INSERT INTO NutritionFacts(NutritionFactsId,Calories,Total_fat,Sugar, Sodium, Protein, Saturated_fat, Carb, RecipeId)"
				+ " VALUES(?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertNutritionFact);
			
			// TODO id auto increment
			insertStmt.setDouble(2, nutritionFact.getCalories());
			insertStmt.setInt(3, nutritionFact.getTotalFat());
			insertStmt.setInt(4, nutritionFact.getSugar());
			insertStmt.setInt(5, nutritionFact.getSodium());
			insertStmt.setInt(5, nutritionFact.getProtein());
			insertStmt.setInt(7, nutritionFact.getSaturatedFat());
			insertStmt.setInt(8, nutritionFact.getCarb());
			insertStmt.setInt(10, nutritionFact.getRecipeId());
			
			insertStmt.executeUpdate();
			
			return nutritionFact;
			
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

}