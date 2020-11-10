package recipe.dal;

import java.sql.Connection;


public class FavoritesDao {
	protected ConnectionManager connectionManager;

	private static FavoritesDao instance = null;
	protected FavoritesDao() {
		connectionManager = new ConnectionManager();
	}
	public static FavoritesDao getInstance() {
		if(instance == null) {
			instance = new FavoritesDao();
		}
		return instance;
	}
	
	
	public Favorites create(Favorites favorite) throws SQLException {
		String insertFavorites =
				"INSERT INTO Favorites(FavoriteId, UserId, RecipeId) " +
				"VALUES(?,?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;
			try {
				connection = connectionManager.getConnection();
				
				insertStmt = connection.prepareStatement(insertRatings,
					Statement.RETURN_GENERATED_KEYS);
				
				insertStmt.setInt(1, Favorites.getFavoriteId());
				insertStmt.setInt(2, Favorites.getUser().getUserId());
				insertStmt.setInt(3, Favorites.getRecipe().getRecipeId());
				
				insertStmt.executeUpdate();
				
				// Retrieve the auto-generated key and set it, so it can be used by the caller.
				resultKey = insertStmt.getGeneratedKeys();
				int favoriteId = -1;
				if(resultKey.next()) {
					favoriteId = resultKey.getInt(1);
				} else {
					throw new SQLException("Unable to retrieve auto-generated key.");
				}
				//set reservationId for input reservation
				favorite.setFavoriteId(favoriteId);;
				return favorite;
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
	
	public Favorites getFavoriteById(int favoriteId)throws SQLException {
		String selectFavorite =
				"SELECT * " +
						"FROM Favorites " +
						"WHERE FavoriteId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectFavorite);
				selectStmt.setInt(1, favoriteId);
				results = selectStmt.executeQuery();
				
				if(results.next()) {
					int rsfavoriteId = results.getInt("FavoriteId");
					int userId = results.getInt("UserId");
					int recipeId = results.getInt("RecipeId");
					UsersDao usersDao = UsersDao.getInstance();//needs double check when UsersDao is done
					Users rsuser = usersDao.getUserByUserId(rsuserId);//needs double check when UsersDao is done
					
					RecipesDao recipesDao = RecipesIdDao.getInstance();
					Recipes rsrecipe = recipesDao.getRecipeByRecipeId(rsrecipeId)
					
					Favorites rsFavorite = new Favorites (rsfavoriteId rsuser,rsrecipe);
					return rsFavorite;
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
	
	public List<Favorites> getFavoriteByUserId(int userId) throws SQLException{
		List<Favorites> list = new ArrayList<Favorites>();
		String selectFavorite=
				"SELECT * FROM Ratings WHERE UserId =?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectFavorite);
				selectStmt.setInt(1, userId);
				results = selectStmt.executeQuery();
				
				while(results.next()) {
					int rsfavoriteId = results.getInt("FavoriteId");
					int userId = results.getInt("UserId");
					int recipeId = results.getInt("RecipeId");
					UsersDao usersDao = UsersDao.getInstance();//needs double check when UsersDao is done
					Users rsuser = usersDao.getUserByUserId(rsuserId);//needs double check when UsersDao is done
					
					RecipesDao recipesDao = RecipesIdDao.getInstance();
					Recipes rsrecipe = recipesDao.getRecipeByRecipeId(rsrecipeId)
					
					Favorites rsFavorite = new Favorites (rsfavoriteId rsuser,rsrecipe);
					list.add(rsFavorite)
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
	
	public List<Favorites> getFavoriteByRecipeId(int recipeId) throws SQLException{
		List<Favorites> list = new ArrayList<Favorites>();
		String selectFavorite=
				"SELECT * FROM Ratings WHERE RecipeId =?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectFavorite);
				selectStmt.setInt(1, userId);
				results = selectStmt.executeQuery();
				
				while(results.next()) {
					int rsfavoriteId = results.getInt("FavoriteId");
					int userId = results.getInt("UserId");
					int recipeId = results.getInt("RecipeId");
					UsersDao usersDao = UsersDao.getInstance();//needs double check when UsersDao is done
					Users rsuser = usersDao.getUserByUserId(rsuserId);//needs double check when UsersDao is done
					
					RecipesDao recipesDao = RecipesIdDao.getInstance();
					Recipes rsrecipe = recipesDao.getRecipeByRecipeId(rsrecipeId)
					
					Favorites rsFavorite = new Favorites (rsfavoriteId rsuser,rsrecipe);
					list.add(rsFavorite)
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
	
	
	//I am not sure whether favorite has a "update" method
	
	public Favorites delete(Favorites favorite) throws SQLException {
		String deleteFavorite = "DELETE FROM Favorites WHERE FavoriteId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFavorite);
			deleteStmt.setInt(1, favorite.getFavoriteId());
			int affectedRows = deleteStmt.executeUpdate();	
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for FavoriteId =" + favorite.getFavoriteId());
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


