package recipe.dal;

import recipe.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
				"INSERT INTO NutritionFacts(Calories,Total_fat,Sugar, Sodium, Protein, Saturated_fat, "
				+ "Carb, RecipeId) VALUES(?,?,?,?,?,?,?,?);";
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
			insertStmt.setInt(8, nutritionFact.getRecipe().getRecipeId());
			
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
	public NutritionFacts updateNutritionFact(NutritionFacts nutritionFact, double calories, int totalFat, 
			int sugar, int sodium, int protein, int saturatedFat, int carb, Recipes recipe) throws SQLException {
		String updateNutritionFact = "UPDATE NutritionFacts SET calories=?, total_fat=?, sugar=?, sodium=?, "
				+ "protein=?, Saturated_fat=?, carb=?, recipeId=? WHERE nutritionFactsId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateNutritionFact);
			updateStmt.setDouble(1, calories);
			updateStmt.setInt(2, totalFat);
			updateStmt.setInt(3, sugar);
			updateStmt.setInt(4, sodium);
			updateStmt.setInt(5, protein);
			updateStmt.setInt(6, saturatedFat);
			updateStmt.setInt(7, carb);
			updateStmt.setInt(8, recipe.getRecipeId());
			updateStmt.setInt(9, nutritionFact.getNutritionFactsId());
			updateStmt.executeUpdate();

			nutritionFact.setCalories(calories);
			nutritionFact.setTotalFat(totalFat);
			nutritionFact.setSugar(sugar);
			nutritionFact.setSodium(sodium);
			nutritionFact.setProtein(protein);
			nutritionFact.setSaturatedFat(saturatedFat);
			nutritionFact.setCarb(carb);
			nutritionFact.setRecipe(recipe);
			
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
	
	public NutritionFacts getNutritionFactById(int nutritionFactId) throws SQLException {
		String selectNutrition =
			"SELECT  NutritionFactsId, Calories, Total_fat, Sugar, Sodium, Protein, Saturated_fat, Carb, RecipeId " +
			"FROM NutritionFacts " +
			"WHERE NutritionFactsId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNutrition);
			selectStmt.setInt(1, nutritionFactId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultNutritionId = results.getInt("NutritionFactsId");
				double calories = results.getDouble("Calories");
				int totalFat = results.getInt("Total_Fat");
				int sugar = results.getInt("Sugar");
				int sodium = results.getInt("Sodium");
				int protein = results.getInt("Protein");
				int saturatedFat = results.getInt("Saturated_fat");
				int carb = results.getInt("Carb");			
				int recipeId = results.getInt("RecipeId");
				Recipes recipe = RecipesDao.getInstance().getRecipeById(recipeId);
				
				
				NutritionFacts rsNutritionFact = new NutritionFacts(resultNutritionId, calories, totalFat, sugar, sodium, protein, saturatedFat, carb, recipe);

				return rsNutritionFact;
				}	
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}  finally {
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
	
	public NutritionFacts getNutritionFactByRecipeId(int recipeId) throws SQLException {
		String selectNutrition =
			"SELECT  RecipeId, Calories, Total_fat, Sugar, Sodium, Protein, Saturated_fat, Carb, NutritionFactsId " +
			"FROM NutritionFacts " +
			"WHERE RecipeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNutrition);
			selectStmt.setInt(1, recipeId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultRecipeId = results.getInt("RecipeId");
				double calories = results.getDouble("Calories");
				int totalFat = results.getInt("Total_Fat");
				int sugar = results.getInt("Sugar");
				int sodium = results.getInt("Sodium");
				int protein = results.getInt("Protein");
				int saturatedFat = results.getInt("Saturated_fat");
				int carb = results.getInt("Carb");
				int nutritionFactId = results.getInt("NutritionFactsId");
				
				Recipes recipe = RecipesDao.getInstance().getRecipeById(resultRecipeId);
				
				NutritionFacts rsNutritionFact = new NutritionFacts(nutritionFactId, calories, totalFat, sugar, sodium, protein, saturatedFat, carb, recipe);

				return rsNutritionFact;
				}	
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}  finally {
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
	
	public List<NutritionFacts> getNutritionFactsByProtein(int proteinPercentage) throws SQLException {
		List<NutritionFacts> nutritionFacts = new ArrayList<NutritionFacts>();
		
		String selectNutritionFact =
			"SELECT RecipeId, Calories, Total_fat, Sugar, Sodium, Protein, Saturated_fat, Carb, NutritionFactsId"
			+ " FROM NutritionFacts WHERE Protein=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNutritionFact);
			selectStmt.setInt(1, proteinPercentage);
			results = selectStmt.executeQuery();
			
			while (results.next()) {
				int nutritionFactsId = results.getInt("NutritionFactsId");
				int recipeId = Integer.parseInt(results.getString("RecipeId"));
				double calories = Double.parseDouble(results.getString("Calories"));
				int totalFat = Integer.parseInt(results.getString("Total_fat"));
				int sugar = Integer.parseInt(results.getString("Sugar"));
				int sodium = Integer.parseInt(results.getString("Sodium"));
				int protein = Integer.parseInt(results.getString("Protein"));
				int saturatedFat = Integer.parseInt(results.getString("Saturated_fat"));
				int carb = Integer.parseInt(results.getString("Carb"));
				try {
					Recipes recipe = RecipesDao.getInstance().getRecipeById(recipeId);
					NutritionFacts nutritionFact = new NutritionFacts(nutritionFactsId, calories, totalFat, sugar, sodium, protein, saturatedFat, carb, recipe);
					nutritionFacts.add(nutritionFact);
				} catch (SQLException e) {
					e.printStackTrace();
					throw e;
				}
				
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
		return nutritionFacts;
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
			
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for NutritionFactsId=" + nutritionFact.getNutritionFactsId());
			}
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

