package recipe.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipe.dal.UsersDao;
import recipe.dal.RatingsDao;
import recipe.dal.RecipesDao;
import recipe.model.Recipes;
import recipe.model.Ratings;
import recipe.model.Users;

@WebServlet("/ratingcreate")
public class RatingCreate extends HttpServlet {
	
	protected RatingsDao ratingDao;
	
	@Override
	public void init() throws ServletException {
		ratingDao = RatingsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/RatingCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Retrieve and validate userId and recipeId.
        String userId = req.getParameter("userId");
        String recipeId = req.getParameter("recipeId");
        if (userId == null || userId.trim().isEmpty()) {
            messages.put("success", "Invalid UserId");
        } 
        else if (recipeId == null || recipeId.trim().isEmpty()) {
            messages.put("success", "Invalid recipeId");
        }
        else {
        	// Create the BlogUser.
	        try {
	        	Users user = UsersDao.getInstance().getUsersByUserId(Integer.valueOf(userId));
	        	Recipes recipe = RecipesDao.getInstance().getRecipeById(Integer.valueOf(recipeId));
	        	if(user == null || recipe == null) {
	        		 messages.put("success", "Didn't find relevant Recipe or User");
	        	}
            	int ratingPoint = Integer.parseInt(req.getParameter("ratingPoint"));
	        	// Exercise: parse the input for StatusLevel.
	        	Ratings ratings = new Ratings(ratingPoint,user,recipe);
	        	ratings = ratingDao.create(ratings);
	        	messages.put("success", "Successfully created " + "rating");
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RatingCreate.jsp").forward(req, resp);
    }
}

