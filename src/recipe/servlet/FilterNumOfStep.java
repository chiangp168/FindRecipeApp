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

import recipe.dal.*;
import recipe.model.*;

/**
 * Servlet implementation class FindRecipe
 */
@WebServlet("/filterNumOfStep")
public class FilterNumOfStep extends HttpServlet {
	protected RecipesDao recipesDao;

	@Override
    public void init() throws ServletException {
		recipesDao = RecipesDao.getInstance();
    }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Recipes> recipes = new ArrayList<Recipes>();
        
        // Retrieve and validate name.
        String input = req.getParameter("numofstep");
        if (input == null || input.trim().isEmpty()) {
            messages.put("success", "Please enter a valid number of step.");
        } else {
	        Integer NumOfStep = Integer.parseInt(input);
	        if (NumOfStep<0) {
	            messages.put("success", "Please enter a valid number of step.");
	        } else {
	            // Retrieve Recipes, and store as a message.
	        	try {
	                recipes = recipesDao.getRecipesByNumOfStep(NumOfStep);
	            } catch (SQLException e) {
	                e.printStackTrace();
	                throw new IOException(e);
	            }
	            messages.put("success", "Displaying results that have less than " 
	            			+ NumOfStep + " steps");
	            // Save the previous search term, so it can be used as the default
	            // in the input box when rendering FindRecipes.jsp.
	            messages.put("previousNumOfStep", Integer.toString(NumOfStep));
	        }
        }
        req.setAttribute("recipes", recipes);
        
        req.getRequestDispatcher("/FilterNumOfStep.jsp").forward(req, resp);
    
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Recipes> recipes = new ArrayList<Recipes>();
        
        String input = req.getParameter("numofstep");
        if (input == null || input.trim().isEmpty()) {
            messages.put("success", "Please enter a valid number of step.");
        }else {
	        Integer NumOfStep = Integer.parseInt(input);
	        if (NumOfStep < 0) {
	            messages.put("success", "Please enter a valid number of step.");
	        } else {
	            // Retrieve Recipes, and store as a message.
	            try {
	            	recipes = recipesDao.getRecipesByNumOfStep(NumOfStep);
	            } catch (SQLException e) {
	                e.printStackTrace();
	                throw new IOException(e);
	            }
	            messages.put("success", "Displaying results that have less than " 
	        			+ NumOfStep + " steps");
	        }
        }
        req.setAttribute("recipes", recipes);
        
        req.getRequestDispatcher("/FilterNumOfStep.jsp").forward(req, resp);
    }
	

}
