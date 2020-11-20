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

@WebServlet("/personcreate")
public class PersonCreate extends HttpServlet {
	protected PersonDao personDao;
	
	@Override
	public void init() throws ServletException {
		personDao = PersonDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		//Just render the JSP.   
        req.getRequestDispatcher("/PersonCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String userId = req.getParameter("userId");
        if (userId == null || userId.trim().isEmpty()) {
            messages.put("success", "Invalid userId");
        } else {
			// Create the recipe
			String userName = req.getParameter("username");
			String userPassword = req.getParameter("userpassword");
			String firstName = req.getParameter("firstname");
			String lastName = req.getParameter("lastname");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
        	
        	try {
        		Person person = new Person(Integer.parseInt(userId), userName, userPassword, firstName, lastName, email, phone);
	        	person = personDao.create(person);
	        	messages.put("success", "Successfully created " + userId);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/PersonCreate.jsp").forward(req, resp);
    }
}
