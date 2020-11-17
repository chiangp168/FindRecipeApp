package recipe.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipe.dal.RatingsDao;
import recipe.model.Ratings;

@WebServlet("/ratingupdate")
public class RatingUpdate extends HttpServlet {
	
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

        // Retrieve user and validate.
        String ratingId = req.getParameter("ratingId");
        if (ratingId == null || ratingId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid ratingId.");
        } else {
        	try {
        		Ratings rating = ratingDao.getRatingById(Integer.valueOf(ratingId));
        		if(rating == null) {
        			messages.put("success", "rating does not exist.");
        		}
        		req.setAttribute("rating", rating);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RatingUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String ratingId = req.getParameter("ratingId");
        if (ratingId == null || ratingId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid RatingId.");
        } else {
        	try {
        		Ratings rating = ratingDao.getRatingById(Integer.valueOf(ratingId));
        		if(rating == null) {
        			messages.put("success", "Rating does not exist. No update to perform.");
        		} else {
        			String newRatingPoints = req.getParameter("ratingPoints");
        			if (newRatingPoints == null || newRatingPoints.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid RatingPoint.");
        	        } else {
        	        	rating = ratingDao.updateByRatingPoints(rating, Integer.valueOf(newRatingPoints));
        	        	messages.put("success", "Successfully updated " + ratingId);
        	        }
        		}
        		req.setAttribute("rating", rating);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RatingUpdate.jsp").forward(req, resp);
    }
}
