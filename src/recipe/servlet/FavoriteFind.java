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

import recipe.dal.FavoritesDao;
import recipe.model.Favorites;

@WebServlet("/favoritefind")
public class FavoriteFind extends HttpServlet {
	
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
        List<Favorites> favorites = new ArrayList<Favorites>();
        
        // Retrieve and validate favoriteId.
        // firstname is retrieved from the URL query string.
        String userId = req.getParameter("userId");
        if (userId == null || userId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid userId.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		favorites = favoriteDao.getFavoriteByUserId(Integer.valueOf(userId));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + userId);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousFavoriteId", userId);
        }
        req.setAttribute("favorites",favorites );
        
        req.getRequestDispatcher("/FavoriteFind.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
         List<Favorites> favorite = new ArrayList<Favorites>();
        
        // Retrieve and validate favoriteId.
        // firstname is retrieved from the URL query string.
        String userId = req.getParameter("userId");
        if (userId == null || userId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid userId.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		favorite = favoriteDao.getFavoriteByUserId(Integer.valueOf(userId));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + userId);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        }
        req.setAttribute("favorites", favorite);
        
        req.getRequestDispatcher("/FavoriteFind.jsp").forward(req, resp);
        
	}
	
}
