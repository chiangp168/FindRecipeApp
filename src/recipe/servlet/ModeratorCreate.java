package recipe.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipe.dal.*;
import recipe.model.Moderators;


@WebServlet("/moderatorcreate")
public class ModeratorCreate extends HttpServlet {
	protected ModeratorsDao moderatorsDao;
	
	@Override
	public void init() throws ServletException {
		moderatorsDao = ModeratorsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/ModeratorCreate.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		String userIdStr = req.getParameter("userId");
		if (userIdStr == null || userIdStr.trim().isEmpty()) {
          messages.put("success", "Invalid UserId");
      } else {
    	  String userName = req.getParameter("userName");
    	  String pw = req.getParameter("password");
    	  String firstName = req.getParameter("firstName");
    	  String lastName = req.getParameter("lastName"); 
    	  String email = req.getParameter("email");
    	  String phone = req.getParameter("phone");
    	  String recipeDeletedStr = req.getParameter("numOfRecipesDeleted");
    	  String recipeApprovedStr = req.getParameter("numOfRecipesApproved");
    	  int numOfRecipesDeleted = Integer.parseInt(recipeDeletedStr);
    	  int numOfRecipesApproved = Integer.parseInt(recipeApprovedStr);
    	  int userId = Integer.parseInt(userIdStr);
    	  try {
    		  Moderators mod = new Moderators(userId, userName, pw, firstName, lastName, email, phone, numOfRecipesDeleted, numOfRecipesApproved);
    		  moderatorsDao.create(mod);
    		  messages.put("success", "Successfully created moderator with id " + userId);
    	  } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
      }
		req.getRequestDispatcher("/ModeratorCreate.jsp").forward(req, resp);
	}
}
