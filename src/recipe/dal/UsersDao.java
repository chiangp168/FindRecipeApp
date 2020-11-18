package recipe.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import recipe.model.*;

public class UsersDao extends PersonDao{	
	private static UsersDao instance = null;
	protected UsersDao() {
		super();
	}

	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
    }

    public Users create(Users users) throws SQLException {
		Person addedPerson = create(new Person(users.getUserId(), users.getUserName(), users.getPassword(), users.getFirstName(),
            users.getLastName(), users.getEmail(), users.getPhone()));

        String insertUser = 
            "INSERT INTO Users(UserId) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertUser);
            
            insertStmt.setInt(1, users.getUserId());

			insertStmt.executeUpdate();
			users.setUserId(addedPerson.getUserId());
			return users;
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

    public Users updateLastName(Users user, String newLastName) throws SQLException {
		super.updateLastName(user, newLastName);
		return user;
    }
    
    public Users updatePassword(Users user, String newPassword) throws SQLException {
		super.updateLastName(user, newPassword);
		return user;
	}


    public Users getUsersByUserId(Integer userId) throws SQLException {
		String selectUser =
			"SELECT Users.UserId AS UserId, UserName, UserPassword, FirstName, LastName, Email, Phone " +
			"FROM Users INNER JOIN Person " +
			"  ON Users.UserId = Person.UserId " +
			"WHERE Users.UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			if(results.next()) {
                Integer resultUserId = results.getInt("UserId");
				String userName = results.getString("UserName");
				String userPassword = results.getString("UserPassword");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
                String phone = results.getString("Phone");
                
				Users user = new Users(resultUserId, userName, userPassword, firstName, lastName, email, phone);
				return user;
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
      
    public Users delete(Users user) throws SQLException {
        String deleteUser = "DELETE FROM Users WHERE UserId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteUser);
            deleteStmt.setInt(1, user.getUserId());
            int affectedRows = deleteStmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No records available to delete for UserId=" + user.getUserName());
            }

            super.delete(user);

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
