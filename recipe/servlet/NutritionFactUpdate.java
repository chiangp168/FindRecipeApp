package recipe.servlet;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import recipe.dal.*;
import recipe.model.*;

@WebServlet("/nutritionupdate")
public class NutritionFactUpdate extends HttpServlet {
	protected NutritionFactsDao nutritionFactsDao;
	protected RecipesDao recipesDao;

	@Override
	  public void init() throws ServletException {
		nutritionFactsDao = nutritionFactsDao.getInstance();
	    recipesDao = recipesDao.getInstance();
	  }
	
	@Override
	  public void doGet(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException, IOException {
	    // Map for storing messages.
	    Map<String, String> messages = new HashMap<String, String>();
	    req.setAttribute("messages", messages);

	    Integer nutritionFactsId = Integer.valueOf(req.getParameter("nutritionFactsId"));
	    if (nutritionFactsId == null) {
	      messages.put("success", "Invalid Id");
	    } else {
	      try {
	        NutritionFacts nutritionFact = nutritionFactsDao.getNutritionFactById(nutritionFactsId);
	        if (nutritionFact == null) {
	          messages.put("success", "Invalid Id");
	        } 
	        req.setAttribute("nutritionFact", nutritionFact);
	      } catch (SQLException e) {
	        e.printStackTrace();
	        throw new IOException(e);
	      }
	    }
	    req.getRequestDispatcher("/NutritionFactUpdate.jsp").forward(req, resp);
	  }
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        Integer nutritionFactsId = Integer.valueOf(req.getParameter("nutritionFactsId"));
        if (nutritionFactsId == null) {
            messages.put("success", "Please enter a valid Id.");
        } else {
        	try {
    	        NutritionFacts nutritionFact = nutritionFactsDao.getNutritionFactById(nutritionFactsId);
        		if(nutritionFact == null) {
        			messages.put("success", "Nutrition Id does not exist. No update to perform.");
        		} else {
        			double calories = Double.parseDouble(req.getParameter("calories"));
        			int totalFat = Integer.parseInt(req.getParameter("totalFat"));
        			int sugar = Integer.parseInt(req.getParameter("sugar"));
        			int sodium = Integer.parseInt(req.getParameter("sodium"));
        			int protein = Integer.parseInt(req.getParameter("protein"));
        			int saturatedFat = Integer.parseInt(req.getParameter("saturatedFat"));
        			int carb = Integer.parseInt(req.getParameter("carb"));
        			int recipeId = Integer.parseInt(req.getParameter("recipeId"));
        			try {
        				Recipes recipe = recipesDao.getRecipeById(recipeId);
        				if (recipe == null) {
        					messages.put("success", "Recipe Id does not exist. No update to perform.");
        				} else {
        	
	        			if (req.getParameter("calories") == null) {
	        	            messages.put("success", "Please enter a valid number for calories.");
	        	        } if (req.getParameter("totalFat") == null) {
	        	            messages.put("success", "Please enter a valid number for total fat.");
	        	        } if (req.getParameter("sugar") == null) {
	        	            messages.put("success", "Please enter a valid number for sugar.");
	        	        } if (req.getParameter("sodium") == null) {
	        	            messages.put("success", "Please enter a valid number for sodium.");
	        	        } if (req.getParameter("protein") == null) {
	        	            messages.put("success", "Please enter a valid number for protein.");
	        	        } if (req.getParameter("saturatedFat") == null) {
	        	            messages.put("success", "Please enter a valid number for saturated fat.");
	        	        } if (req.getParameter("carb") == null) {
	        	            messages.put("success", "Please enter a valid number for carb.");
	        	        } if (req.getParameter("recipeId") == null) {
	        	            messages.put("success", "Please enter a valid number for recipeId.");
	        	        } else {	
	        	        	nutritionFact = nutritionFactsDao.updateNutritionFact(nutritionFact, calories, totalFat, sugar, sodium, protein, saturatedFat, carb, recipe);
	        	        	messages.put("success", "Successfully updated nutrition fact with Id " + nutritionFactsId);
	        	        }
        		}
        		req.setAttribute("nutritionFact", nutritionFact);
	        	} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
	        	}
        		}
        
        req.getRequestDispatcher("/NutritionFactUpdate.jsp").forward(req, resp);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        }
}
}
	

