package recipe.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import recipe.dal.*;
import recipe.model.*;

@WebServlet("/nutritiondelete")
public class NutritionFactDelete extends HttpServlet {
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
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        messages.put("title", "Delete Nutrition Fact");        
        req.getRequestDispatcher("/NutritionDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate
        Integer nutritionFactsId = Integer.valueOf(req.getParameter("nutritionFactsId"));

        if (nutritionFactsId == null) {
            messages.put("title", "Invalid Nutrition Fact Id");
            messages.put("disableSubmit", "true");
        } else {

	        try {
	        	NutritionFacts nfact = nutritionFactsDao.getNutritionFactById(nutritionFactsId);
	        	if (nfact != null) {
	        		nfact = nutritionFactsDao.delete(nfact);
	        	}
	        	// Update the message.
		        if (nfact == null) {
		            messages.put("title", "Successfully deleted nutrition fact with id " + nutritionFactsId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete nutrition fact with id" + nutritionFactsId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/NutritionDelete.jsp").forward(req, resp);
    }
		
}
