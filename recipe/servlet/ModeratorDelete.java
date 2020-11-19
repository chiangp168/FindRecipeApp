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


@WebServlet("/moderatordelete")
public class ModeratorDelete extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Moderator");        
        req.getRequestDispatcher("/ModeratorDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String userIdStr = req.getParameter("userId");
        int userId = Integer.parseInt(userIdStr);
        if (userIdStr == null || userIdStr.trim().isEmpty()) {
            messages.put("title", "Invalid User Id");
            messages.put("disableSubmit", "true");
        } else {

	        try {
	        	Moderators mod = moderatorsDao.getModeratorById(userId);
	        	if (mod != null) {
	        		mod = moderatorsDao.delete(mod);
	        	}
	        	// Update the message.
		        if (mod == null) {
		            messages.put("title", "Successfully deleted moderator with user id " + userIdStr);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete moderator with user id" + userIdStr);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ModeratorDelete.jsp").forward(req, resp);
    }
}
