package recipe.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import recipe.dal.*;
import recipe.model.*;

/**
 * Servlet implementation class RecipeDelete
 */
@WebServlet("/recipedelete")
public class RecipeDelete extends HttpServlet {
	protected RecipesDao recipesDao;
	
	@Override
	public void init() throws ServletException {
		recipesDao = RecipesDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Recipe");        
        req.getRequestDispatcher("/RecipeDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String recipename = req.getParameter("recipename");
        if (recipename == null || recipename.trim().isEmpty()) {
            messages.put("title", "Invalid recipename");
            messages.put("disableSubmit", "true");
        }else {
        	// Delete the Recipe.
	        List<Recipes> recipelist = new ArrayList<Recipes>();
			try {
				recipelist = recipesDao.getRecipesByRecipeName(recipename);
				System.out.println(recipelist.size());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        try {
	        	for(Recipes r: recipelist) {
	        		Recipes deletedrecipe = recipesDao.delete(r);
	        	}
	        	
	        	for(Recipes r : recipelist) {
	        		if(r != null) {
	        			messages.put("title", "Failed to delete " + recipename);
			        	messages.put("disableSubmit", "false");
			        	break;
	        		}
	        	}
	        	// Update the message.
		        messages.put("title", "Successfully deleted all recipes with name " + recipename);
		        messages.put("disableSubmit", "true");
		        
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RecipeDelete.jsp").forward(req, resp);
    }

}
