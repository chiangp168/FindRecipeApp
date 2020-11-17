package recipe.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import recipe.model.*;

public class ModeratorsDao  extends PersonDao {
	private static ModeratorsDao instance = null;
	protected ModeratorsDao() {
		super();
	}
	
	public static ModeratorsDao getInstance() {
		if(instance == null) {
			instance = new ModeratorsDao();
		}
		return instance;
	}
	
	public Moderators create(Moderators moderators) throws SQLException {
		create(new Person(moderators.getUserId(), moderators.getUserName(), moderators.getPassword(), moderators.getFirstName(),
				moderators.getLastName(), moderators.getEmail(), moderators.getPhone()));
		String insertModerator = 
				"INSERT INTO Moderators(UserId, NumOfRecipesDeleted, NumOfRecipesApproved) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertModerator);
			
			insertStmt.setInt(1, moderators.getUserId());
			insertStmt.setInt(2, moderators.getNumOfRecipesDeleted());
			insertStmt.setInt(3, moderators.getNumOfRecipesApproved());
			
			insertStmt.executeUpdate();
			return moderators;
			
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
        }
        
	}
	

	public Moderators getModeratorById(int moderatorId) throws SQLException {
		String selectModerator =
			"SELECT Moderators.UserId, UserName, UserPassword, FirstName, LastName, Email, Phone, NumOfRecipesDeleted, NumOfRecipesApproved "
			+ "FROM Moderators INNER JOIN Person ON Moderators.UserId = Person.UserId WHERE Moderators.UserId=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectModerator);
			selectStmt.setInt(1, moderatorId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultUserId = results.getInt("UserId");
				String userName = results.getString("UserName");
				String password = results.getString("UserPassword");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phone = results.getString("Phone");
				int numOfRecipesDeleted = results.getInt("NumOfRecipesDeleted");
				int numOfRecipesApproved = results.getInt("NumOfRecipesApproved");
				Moderators moderator = new Moderators(resultUserId, userName, password, firstName, lastName, email, phone, numOfRecipesDeleted, numOfRecipesApproved);
				return moderator;
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
	
	public Moderators getModeratorByUserName(String username) throws SQLException {
		String selectModerator =
			"SELECT Moderators.UserId, UserName, UserPassword, FirstName, LastName, Email, Phone, NumOfRecipesDeleted, NumOfRecipesApproved "
			+ "FROM Moderators INNER JOIN Person ON Moderators.UserId = Person.UserId WHERE Moderators.UserName=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectModerator);
			selectStmt.setString(1, username);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultUserId = results.getInt("UserId");
				String userName = results.getString("UserName");
				String password = results.getString("UserPassword");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phone = results.getString("Phone");
				int numOfRecipesDeleted = results.getInt("NumOfRecipesDeleted");
				int numOfRecipesApproved = results.getInt("NumOfRecipesApproved");
				Moderators moderator = new Moderators(resultUserId, userName, password, firstName, lastName, email, phone, numOfRecipesDeleted, numOfRecipesApproved);
				return moderator;
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
	
	public List<Moderators> getModeratorByNumDeleted(int numOfDelete) throws SQLException {
		List<Moderators> moderators = new ArrayList<Moderators>();
		
		String selectModerator =
			"SELECT Moderators.UserId, UserName, UserPassword, FirstName, LastName, Email, Phone, NumOfRecipesDeleted, NumOfRecipesApproved"
			+ " FROM Moderators INNER JOIN Person"
			+ " ON Moderators.UserId = Person.UserId"
			+ " WHERE NumOfRecipesDeleted=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectModerator);
			selectStmt.setInt(1, numOfDelete);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultUserId = results.getInt("UserId");
				String userName = results.getString("UserName");
				String password = results.getString("UserPassword");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phone = results.getString("Phone");
				int numOfRecipesDeleted = results.getInt("NumOfRecipesDeleted");
				int numOfRecipesApproved = results.getInt("NumOfRecipesApproved");
				Moderators moderator = new Moderators(resultUserId, userName, password, firstName, lastName, email, phone, numOfRecipesDeleted, numOfRecipesApproved);
				moderators.add(moderator);
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
		return moderators;
	}
	
	
	public Moderators addRecipesDeleted(Moderators moderator, int numOfRecipes) throws SQLException {
		String updateRecipes = "UPDATE Moderators  SET NumOfRecipesDeleted=? WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			
			int totalRecipesDeleted = moderator.getNumOfRecipesDeleted() + numOfRecipes;
			
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRecipes);
			updateStmt.setDouble(1, totalRecipesDeleted);
			updateStmt.setInt(2, moderator.getUserId());
			
			updateStmt.executeUpdate();

			moderator.setNumOfRecipesDeleted(totalRecipesDeleted);
			
			return moderator;
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
	
	public Moderators subtractRecipesDeleted(Moderators moderator, int numOfRecipes) throws SQLException {
		String updateRecipes = "UPDATE Moderators SET NumOfRecipesDeleted=? WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			int totalRecipesDeleted;
			if (moderator.getNumOfRecipesDeleted() <= 0) {
				totalRecipesDeleted = 0;
			} else {
				 totalRecipesDeleted = moderator.getNumOfRecipesDeleted() - numOfRecipes;
			}
			
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRecipes);
			updateStmt.setDouble(1, totalRecipesDeleted);
			updateStmt.setInt(2, moderator.getUserId());
			
			updateStmt.executeUpdate();

			moderator.setNumOfRecipesDeleted(totalRecipesDeleted);
			
			return moderator;
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
	
	
	
	public Moderators delete(Moderators moderator) throws SQLException {
		String deleteModerator = "DELETE FROM Moderators WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteModerator);
			deleteStmt.setInt(1, moderator.getUserId());
			deleteStmt.executeUpdate();

			super.delete(moderator);

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
