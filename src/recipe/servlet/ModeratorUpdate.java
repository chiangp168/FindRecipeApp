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


@WebServlet("/moderatorupdate")
public class ModeratorUpdate extends HttpServlet {
	
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

        // Retrieve user and validate.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
        	try {
        		Moderators mod = moderatorsDao.getModeratorByUserName(userName);
        		if(mod == null) {
        			messages.put("success", "UserName does not exist.");
        		}
        		req.setAttribute("moderator", mod);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ModeratorUpdate.jsp").forward(req, resp);
	}
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String userIdStr = req.getParameter("userId");
        int userId = Integer.parseInt(userIdStr);
        if (userIdStr == null || userIdStr.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
        	try {
        		Moderators mod = moderatorsDao.getModeratorById(userId);
        		if(mod == null) {
        			messages.put("success", "UserId does not exist. No update to perform.");
        		} else {
        			String numberAddStr = req.getParameter("noAddRecipesDelete");
        			String numberSubStr = req.getParameter("noSubRecipesDelete");
        			if (numberAddStr == null || numberAddStr.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid number to add.");
        	        } if (numberSubStr == null || numberSubStr.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid number to remove.");
        	        } else {
        	        	int numberAdd = Integer.parseInt(numberAddStr);
        	        	int numberSub = Integer.parseInt(numberSubStr);
        	        	mod = moderatorsDao.addRecipesDeleted(mod, numberAdd);
        	        	mod = moderatorsDao.subtractRecipesDeleted(mod, numberSub);
        	        	messages.put("success", "Successfully updated " + userIdStr);
        	        }
        		}
        		req.setAttribute("moderator", mod);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ModeratorUpdate.jsp").forward(req, resp);
    }
}
