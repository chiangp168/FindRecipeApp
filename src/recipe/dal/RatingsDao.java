package recipe.dal;

import java.sql.Connection;


public class RatingsDao {
	protected ConnectionManager connectionManager;

	private static RatingsDao instance = null;
	protected RatingsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RatingsDao getInstance() {
		if(instance == null) {
			instance = new RatingsDao();
		}
		return instance;
	}
	
	
	public Ratings create(Ratings rating) throws SQLException {
		String insertRatings =
				"INSERT INTO Ratings(ratingId, ratingPoints, userId, recipeId) " +
				"VALUES(?,?,?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;
			try {
				connection = connectionManager.getConnection();
				
				insertStmt = connection.prepareStatement(insertRatings,
					Statement.RETURN_GENERATED_KEYS);
				
				insertStmt.setInt(1, rating.getRatingId());
				insertStmt.setInt(2, rating.getRatingPoints());
				insertStmt.setInt(3, rating.getUser().getUserId());
				insertStmt.setInt(4, recipe.getRecipe().getRecipeId());
				
				insertStmt.executeUpdate();
				
				// Retrieve the auto-generated key and set it, so it can be used by the caller.
				resultKey = insertStmt.getGeneratedKeys();
				int ratingId = -1;
				if(resultKey.next()) {
					ratingId = resultKey.getInt(1);
				} else {
					throw new SQLException("Unable to retrieve auto-generated key.");
				}
				//set reservationId for input reservation
				rating.setRatingId(ratingId);;
				return rating;
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
	
	public Ratings getRatingById(int ratingId)throws SQLException {
		String selectRating =
				"SELECT * " +
						"FROM Ratings " +
						"WHERE RatingId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectRating);
				selectStmt.setInt(1, ratingId);
				results = selectStmt.executeQuery();
				
				if(results.next()) {
					int rsratingId = results.getInt("RatingId");
					int ratingPoints = results.getInt("RatingPoints");
					int userId = results.getInt("UserId");
					int recipeId = results.getInt("RecipeId");
					UsersDao usersDao = UsersDao.getInstance();//needs double check when UsersDao is done
					Users rsuser = usersDao.getUserByUserId(rsuserId);//needs double check when UsersDao is done
					
					RecipesDao recipesDao = RecipesIdDao.getInstance();
					Recipes rsrecipe = recipesDao.getRecipeByRecipeId(rsrecipeId)
					
					Ratings rsRating = new Ratings(rsratingId, ratingPoints, rsuser,rsrecipe);
					return rsRating;
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
	
	public List<Ratings> getRatingsByRatingId(int RatingId) throws SQLException{
		List<Ratings> list = new ArrayList<Ratings>();
		String selectRating =
				"SELECT * FROM Ratings WHERE RatingId =?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectRating);
				selectStmt.setInt(1, ratingId);
				results = selectStmt.executeQuery();
				
				while(results.next()) {
					int rsratingId = results.getInt("RatingId");
					int ratingPoints = results.getInt("RatingPoints");
					int userId = results.getInt("UserId");
					int recipeId = results.getInt("RecipeId");
					UsersDao usersDao = UsersDao.getInstance();//needs double check when UsersDao is done
					Users rsuser = usersDao.getUserByUserId(rsuserId);//needs double check when UsersDao is done
					
					RecipesDao recipesDao = RecipesIdDao.getInstance();
					Recipes rsrecipe = recipesDao.getRecipeByRecipeId(rsrecipeId)
					
					Ratings rsRating = new Ratings(rsratingId, ratingPoints, rsuser,rsrecipe);
					list.add(rsRating)
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
	
	public List<Ratings> getRatingsByUserId(Int UserId) throws SQLException{
		List<Ratings> list = new ArrayList<Ratings>();
		String selectRecipe =
				"SELECT * FROM Ratings WHERE userId =?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectRating);
				selectStmt.setString(1, userId);
				results = selectStmt.executeQuery();
				
				while(results.next()) {
					int rsratingId = results.getInt("RatingId");
					int ratingPoints = results.getInt("RatingPoints");
					int userId = results.getInt("UserId");
					int recipeId = results.getInt("RecipeId");
					UsersDao usersDao = UsersDao.getInstance();//needs double check when UsersDao is done
					Users rsuser = usersDao.getUserByUserId(rsuserId);//needs double check when UsersDao is done
					
					RecipesDao recipesDao = RecipesIdDao.getInstance();
					Recipes rsrecipe = recipesDao.getRecipeByRecipeId(rsrecipeId)
					
					Ratings rsRating = new Ratings(rsratingId, ratingPoints, rsuser,rsrecipe);
					list.add(rsRating);
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
	
	
	public Ratings updateByRatingPoints(Ratings rating, int RatingPoints) throws SQLException {
		String updateRating = "UPDATE Ratings set RatingPoints=? where RatingId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRating);
			updateStmt.setInt(1, rating.getRatingPoints());
			updateStmt.setInt(2, rating.getRatingId());
			
			updateStmt.executeUpdate();
			rating.setRatingPoints(newRatingPoints);
			return rating;
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
	
	public Ratings delete(Ratings rating) throws SQLException {
		String deleteRating = "DELETE FROM Ratings WHERE RatingId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRating);
			deleteStmt.setInt(1, rating.getRatingId());
			int affectedRows = deleteStmt.executeUpdate();	
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for RatingId =" + rating.getRatingId());
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

