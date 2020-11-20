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

@WebServlet("/findnutritionfacts")
public class FindNutritionFacts extends HttpServlet {
	protected NutritionFactsDao nutritionFactsDao;
	
	@Override
	public void init() throws ServletException {
		nutritionFactsDao = NutritionFactsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<NutritionFacts> nutritionFacts = new ArrayList<NutritionFacts>();
        
        
        String proteinStr = req.getParameter("protein");
        int protein = Integer.parseInt(proteinStr);
        if (proteinStr == null || proteinStr.trim().isEmpty()) {
            messages.put("success", "Please enter a valid number.");
        } else {
        	try {
        		nutritionFacts = nutritionFactsDao.getNutritionFactsByProtein(protein);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results according to percentage of protein. Number entered:  " + protein);

        	messages.put("previous search", proteinStr);
        }
        req.setAttribute("nutritionFacts", nutritionFacts);
        
        req.getRequestDispatcher("/FindNutritionFacts.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<NutritionFacts> nutritionFacts = new ArrayList<NutritionFacts>();
        

        String proteinStr = req.getParameter("protein");
        int protein = Integer.parseInt(proteinStr);
        if (proteinStr == null || proteinStr.trim().isEmpty()) {
            messages.put("success", "Please enter a valid number.");
        } else {
        	try {
        		nutritionFacts = nutritionFactsDao.getNutritionFactsByProtein(protein);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results according to percentage of protein. Number entered:  " + protein);

        	messages.put("previous search", proteinStr);
        }
        req.setAttribute("nutritionFacts", nutritionFacts);
        
        req.getRequestDispatcher("/FindNutritionFacts.jsp").forward(req, resp);
	}
	
}

