package recipe.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import recipe.model.*;

public class PersonDao {
    protected ConnectionManager connectionManager;
	
	private static PersonDao instance = null;
	protected PersonDao() {
		connectionManager = new ConnectionManager();
	}

	public static PersonDao getInstance() {
		if(instance == null) {
			instance = new PersonDao();
		}
		return instance;
    }
    
    public Person create(Person person) throws SQLException {
		String insertPerson = "INSERT INTO Person(UserId,UserName,UserPassword,FirstName,LastName,Email,Phone) VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPerson);
		
			insertStmt.setInt(1, person.getUserId());
		    insertStmt.setString(2, person.getUserName());
		    insertStmt.setString(3, person.getPassword());
			insertStmt.setString(4, person.getFirstName());
			insertStmt.setString(5, person.getLastName());
			insertStmt.setString(6, person.getEmail());
			insertStmt.setString(7, person.getPhone());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int personId = -1;
			if(resultKey.next()) {
				personId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			person.setUserId(personId);
			return person;
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
	
	public Person getPersonByUserId(Integer userId) throws SQLException {
		String selectPerson = 
			"SELECT UserId,UserName,UserPassword,FirstName,LastName,Email,Phone FROM Person WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setInt(1, userId);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultUserId = results.getInt("UserId");
				String userName = results.getString("UserName");
				String userPassword = results.getString("UserPassword");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phone = results.getString("Phone");
				Person person = new Person(resultUserId, userName, userPassword, firstName, lastName, email, phone);
				return person;
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

	public Person updatePassword(Person person, String newPassword) throws SQLException {
		String updatePerson = "UPDATE Person SET UserPassword=? WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePerson);
			updateStmt.setString(1, newPassword);
			updateStmt.setInt(2, person.getUserId());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			person.setPassword(newPassword);
			return person;
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

	public Person updateLastName(Person person, String newLastName) throws SQLException {
		String updatePerson = "UPDATE Person SET LastName=? WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePerson);
			updateStmt.setString(1, newLastName);
			updateStmt.setInt(2, person.getUserId());
			updateStmt.executeUpdate();
		
			person.setLastName(newLastName);
			return person;
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
    
    public Person delete(Person person) throws SQLException {
		String deletePerson = "DELETE FROM Person WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePerson);
			deleteStmt.setInt(1, person.getUserId());
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
}
