package recipe.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipe.dal.RatingsDao;
import recipe.model.Ratings;


@WebServlet("/ratingfind")
public class RatingFind extends HttpServlet {
	
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
        List<Ratings> ratings = new ArrayList<Ratings>();
        
        // Retrieve and validate favoriteId.
        // firstname is retrieved from the URL query string.
        String ratingPoint = req.getParameter("ratingPoint");
        if (ratingPoint == null || ratingPoint.trim().isEmpty()) {
            messages.put("success", "Please enter a valid ratingPoint.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		ratings = ratingDao.getRatingsByRatingPoints(Integer.valueOf(ratingPoint));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + ratingPoint);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousRatingId", ratingPoint);
        }
        req.setAttribute("ratings", ratings);
        
        req.getRequestDispatcher("/RatingFind.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
		 Map<String, String> messages = new HashMap<String, String>();
	        req.setAttribute("messages", messages);
	        List<Ratings> ratings = new ArrayList<Ratings>();
	        
	        // Retrieve and validate favoriteId.
	        // firstname is retrieved from the URL query string.
	        String ratingPoint = req.getParameter("ratingPoint");
	        if (ratingPoint == null || ratingPoint.trim().isEmpty()) {
	            messages.put("success", "Please enter a valid ratingPoint.");
	        } else {
	        	// Retrieve BlogUsers, and store as a message.
	        	try {
	        		ratings = ratingDao.getRatingsByRatingPoints(Integer.valueOf(ratingPoint));
	            } catch (SQLException e) {
	    			e.printStackTrace();
	    			throw new IOException(e);
	            }
	        	messages.put("success", "Displaying results for " + ratingPoint);
	        	// Save the previous search term, so it can be used as the default
	        	// in the input box when rendering FindUsers.jsp.
	        }
	        req.setAttribute("ratings", ratings);
	        
	        req.getRequestDispatcher("/RatingFind.jsp").forward(req, resp);
	}
	
}
