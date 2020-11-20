package recipe.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipe.dal.*;
import recipe.model.*;

/**
 * Servlet implementation class RecipeUpdate
 */
@WebServlet("/recipeupdate")
public class RecipeUpdate extends HttpServlet {
	
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

        // Retrieve user and validate.
        String recipeName = req.getParameter("recipename");
        String userName = req.getParameter("username");
        if (recipeName == null || recipeName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid recipe name.");
        } else if(userName == null || userName.trim().isEmpty()){
        	messages.put("success", "Please enter a valid user name.");
        } else {
        	try {
        		boolean done = false;
        		List<Recipes> recipelist = recipesDao.getRecipesByRecipeName(recipeName);
        		if(recipelist.isEmpty()) {
        			done = true;
        			messages.put("success", "recipe name does not exist.");
        		}
        		for(Recipes r : recipelist) {
        			if(r.getUser().getUserName().equals(userName)) {
        				req.setAttribute("recipe", r);
        				done = true;
        				break;
        			}
        		}
        		if(!done) {
        			messages.put("success", "recipe with the input user name does not exist.");
        		}
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RecipeUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
		String recipeName = req.getParameter("recipename");
        String userName = req.getParameter("username");
        if (recipeName == null || recipeName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid recipe name.");
        } else if(userName == null || userName.trim().isEmpty()){
        	messages.put("success", "Please enter a valid user name.");
        }else {
        	try {
        		List<Recipes> recipelist = recipesDao.getRecipesByRecipeName(recipeName);
        		if(recipelist.isEmpty()) {
        			messages.put("success", "recipe name does not exist.");
        		}
        		boolean done = false;
        		Recipes recipeUpdate = null;
        		for(Recipes r : recipelist) {
        			if(r.getUser().getUserName().equals(userName)) {
        				if(req.getParameter("timetocook")=="") {
        					messages.put("success", "Please enter a valid time to cook.");
        					done = true;
        					break;
        				}
        				Integer newTimeToCook = Integer.parseInt(req.getParameter("timetocook"));
        				if (newTimeToCook == null || newTimeToCook == 0) {
        					messages.put("success", "Please enter a valid time to cook.");
        					done = true;
        					break;
        				}else {
        					recipesDao.updateByTimeToCook(r, newTimeToCook);
            				messages.put("success", "Successfully updated timeToCook for " + recipeName + " by user " + userName);
            				done = true;
            				recipeUpdate = r;
            				break;
        				}
        			}
        		}
        		if(!done) {
        			messages.put("success", "does not exit a recipename with input username.");
        		}
        		req.setAttribute("recipe", recipeUpdate);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RecipeUpdate.jsp").forward(req, resp);
    }

}
