package recipe.servlet;

import recipe.dal.*;
import recipe.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/finduser")
public class FindUser extends HttpServlet {
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        // Retrieve and validate UserId.
        // userId is retrieved from the URL query string.
        String userId = req.getParameter("userId");
        if (userId == null || userId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserId.");
        } else {
        	// Retrieve a User, and store as a message.
        	try {
                Users user = usersDao.getUsersByUserId(Integer.parseInt(userId));
                req.setAttribute("user", user);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for User with UserId " + userId);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousUserId", userId);
        }
        req.getRequestDispatcher("/FindUser.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        // Retrieve and validate userId.
        // UserId is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUser.jsp).
        
        String userId = req.getParameter("userId");
        if (userId == null || userId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserId.");
        } else {
        	// Retrieve a User, and store as a message.
        	try {
                Users user = usersDao.getUsersByUserId(Integer.parseInt(userId));
                req.setAttribute("user", user);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for User with UserId " + userId);
        }
        req.getRequestDispatcher("/FindUser.jsp").forward(req, resp);
    }
}
