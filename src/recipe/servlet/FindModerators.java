package recipe.servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import recipe.dal.*;
import recipe.model.*;

@WebServlet("/findmoderators")
public class FindModerators extends HttpServlet {
	
	protected ModeratorsDao moderatorsDao;
	
	@Override
	public void init() throws ServletException {
		moderatorsDao = ModeratorsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Moderators> moderators = new ArrayList<Moderators>();
        
        
        String numOfDeleteStr = req.getParameter("numOfRecipesDeleted");
        int numOfDelete = Integer.parseInt(numOfDeleteStr);
        if (numOfDeleteStr == null || numOfDeleteStr.trim().isEmpty()) {
            messages.put("success", "Please enter a valid number.");
        } else {
        	try {
        		moderators = moderatorsDao.getModeratorByNumDeleted(numOfDelete);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for number of recipe delete. Number entered:  " + numOfDeleteStr);

        	messages.put("previous search", numOfDeleteStr);
        }
        req.setAttribute("moderators", moderators);
        
        req.getRequestDispatcher("/FindModerators.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Moderators> moderators = new ArrayList<Moderators>();
        
        String numOfDeleteStr = req.getParameter("numOfRecipesDeleted");
        int numOfDelete = Integer.parseInt(numOfDeleteStr);
        if (numOfDeleteStr == null || numOfDeleteStr.trim().isEmpty()) {
            messages.put("success", "Please enter a valid number.");
        } else {
        	try {
        		moderators = moderatorsDao.getModeratorByNumDeleted(numOfDelete);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for number of recipe delete. Number entered:  " + numOfDeleteStr);
        }
        req.setAttribute("moderators", moderators);
        
        req.getRequestDispatcher("/FindModerators.jsp").forward(req, resp);
    }
}