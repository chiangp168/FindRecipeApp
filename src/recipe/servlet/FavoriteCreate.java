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
import recipe.dal.RecipesDao;
import recipe.dal.UsersDao;
import recipe.model.Favorites;
import recipe.model.Recipes;
import recipe.model.Users;

@WebServlet("/favoritecreate")
public class FavoriteCreate extends HttpServlet {
	
	protected FavoritesDao favoritesDao;
	
	@Override
	public void init() throws ServletException {
		favoritesDao = FavoritesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/FavoriteCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        Users user = null;
        Recipes recipe = null;
        // Retrieve and validate name.
        String userId = req.getParameter("userId");
        String recipeId = req.getParameter("recipeId");
        if (userId == null || userId.trim().isEmpty()) {
            messages.put("success", "Invalid UserId");
        } 
        else if (recipeId == null || recipeId.trim().isEmpty()) {
            messages.put("success", "Invalid RecipeId");
        }
        else {
        	
			try {
				user = UsersDao.getInstance().getUsersByUserId(Integer.valueOf(userId));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				recipe = RecipesDao.getInstance().getRecipeById(Integer.valueOf(recipeId));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(recipe == null || user == null) {
        		messages.put("success", "Didn't find relevant Recipe or User");
        	}
			else {
	        	try {
		        	// Exercise: parse the input for StatusLevel.
		        	Favorites favorites = new Favorites(user,recipe);
		        	favorites = favoritesDao.create(favorites);
		        	messages.put("success", "Successfully created " + "favorite");
		        } catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
		        }
			}
        }

        req.getRequestDispatcher("/FavoriteCreate.jsp").forward(req, resp);
        	}
	        
        }
