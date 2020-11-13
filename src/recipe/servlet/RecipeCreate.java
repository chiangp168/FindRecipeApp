package recipe.servlet;

import recipe.dal.*;
import recipe.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/recipecreate")
public class RecipeCreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/RecipeCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String recipeName = req.getParameter("recipename");
        if (recipeName == null || recipeName.trim().isEmpty()) {
            messages.put("success", "Invalid recipeName");
        } else {
        	// Create the recipe
        	String recipename = req.getParameter("recipename");
        	int userid = Integer.parseInt(req.getParameter("userid"));
        	int timetocook = Integer.parseInt(req.getParameter("timetocook"));
        	int numberofsteps = Integer.parseInt(req.getParameter("numberofsteps"));
        	try {
        		Recipes r = new Recipes(recipename, new Users(userid), timetocook, numberofsteps);
	        	r = recipesDao.create(r);
	        	messages.put("success", "Successfully created " + recipename);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RecipeCreate.jsp").forward(req, resp);
    }
}
