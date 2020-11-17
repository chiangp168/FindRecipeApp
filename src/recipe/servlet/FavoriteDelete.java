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

import recipe.dal.FavoritesDao;
import recipe.model.Favorites;

@WebServlet("/favoritedelete")
public class FavoriteDelete extends HttpServlet {
	
	protected FavoritesDao favoriteDao;
	
	@Override
	public void init() throws ServletException {
		favoriteDao = FavoritesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Favorite");        
        req.getRequestDispatcher("/FavoriteDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Retrieve and validate name.
        Favorites favorite = null;
        String favoriteId = req.getParameter("favoriteId");
        
        if (favoriteId == null || favoriteId.trim().isEmpty()) {
            messages.put("success", "Invalid favoriteId");
        } 
        else {
			try {
				favorite = favoriteDao.getFavoriteById(Integer.valueOf(favoriteId));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	if(favorite == null) {
        		messages.put("success", "Didn't find relevant favorite");
        	}
        		else {
        			try {
        	        	favorite = favoriteDao.delete(favorite);
        	        	// Update the message.
        		        if (favorite == null) {
        		            messages.put("title", "Successfully deleted " + "favorite");
        		            messages.put("disableSubmit", "true");
        		        } else {
        		        	messages.put("title", "Failed to delete " + "favorite");
        		        	messages.put("disableSubmit", "false");
        		        }
        			}
			         catch (SQLException e) {
						e.printStackTrace();
						throw new IOException(e);
			    }
        	}
        }
        req.getRequestDispatcher("/UserDelete.jsp").forward(req, resp);        
	}
}


