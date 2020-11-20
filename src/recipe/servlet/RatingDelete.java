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

@WebServlet("/ratingdelete")
public class RatingDelete extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Ratingr");        
        req.getRequestDispatcher("/RatingDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        Ratings rating = null;
        // Retrieve and validate name.
       
        String ratingId = req.getParameter("ratingId");
        
        if (ratingId == null || ratingId.trim().isEmpty()) {
            messages.put("title", "Invalid ratingId");
        } 
        else {
        	try {
				rating = ratingDao.getRatingById(Integer.valueOf(ratingId));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	if(rating == null) {
        		 messages.put("title", "Didn't find relevant rating");
        }
        
        else {
        	// Delete the BlogUser.
        	try {
        		rating = ratingDao.delete(rating);
	        
	        	// Update the message.
		        if (rating == null) {
		            messages.put("title", "Successfully deleted " + "rating");
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + "rating");
		        	messages.put("disableSubmit", "false");
		        }
	        
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        }
        req.getRequestDispatcher("/RatingDelete.jsp").forward(req, resp); 
	}
}

