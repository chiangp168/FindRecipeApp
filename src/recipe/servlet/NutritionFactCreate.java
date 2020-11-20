package recipe.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import recipe.dal.*;
import recipe.model.NutritionFacts;
import recipe.model.Recipes;


@WebServlet("/nutritioncreate")
public class NutritionFactCreate extends HttpServlet {
	protected NutritionFactsDao nutritionFactsDao;
	protected RecipesDao recipesDao;
	
	@Override
	public void init() throws ServletException {
		nutritionFactsDao = NutritionFactsDao.getInstance();
		recipesDao = RecipesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/NutritionFactCreate.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		String strNutritionFactId = req.getParameter("nutritionFactsId");
		if (strNutritionFactId == null || strNutritionFactId.isEmpty()) {
			messages.put("success", "Invalid Id for NutritionFact");
		} else {
			String stringCalories = req.getParameter("calories");
			String stringFat = req.getParameter("totalFat");
			String stringSugar = req.getParameter("sugar");
			String stringSodium = req.getParameter("sodium");
			String stringProtein = req.getParameter("protein");
			String stringSaturatedFat = req.getParameter("saturatedFat");
			String stringCarb = req.getParameter("carb");
			String stringRecipe = req.getParameter("recipeId");
			int nutritionFactId = Integer.parseInt(strNutritionFactId);
			double calories = Double.parseDouble(stringCalories);
			int totalFat = Integer.parseInt(stringFat);
			int sugar = Integer.parseInt(stringSugar);
			int sodium = Integer.parseInt(stringSodium);
			int protein = Integer.parseInt(stringProtein);
			int saturatedFat = Integer.parseInt(stringSaturatedFat);
			int carb = Integer.parseInt(stringCarb);
			int recipeId = Integer.parseInt(stringRecipe);
			try {
				Recipes recipe = recipesDao.getRecipeById(recipeId);
	        	NutritionFacts nutritionFact = new NutritionFacts(nutritionFactId, calories, totalFat, sugar, sodium, protein, saturatedFat, carb, recipe);
	        	nutritionFact = nutritionFactsDao.create(nutritionFact);
	        	messages.put("success", "Successfully created nutritionfact with Id: " + nutritionFactId);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
		}
		req.getRequestDispatcher("/NutritionFactCreate.jsp").forward(req, resp);

	}
}
	
