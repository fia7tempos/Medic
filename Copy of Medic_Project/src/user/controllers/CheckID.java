package user.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.controllers.AbstractController;
import main.controllers.ModelAndView;
import user.model.UserDAO;
import user.model.UserDAOImpl;

public class CheckID extends AbstractController {
	

	@Override
	public ModelAndView handleRequestInternal(
		HttpServletRequest request, HttpServletResponse response) {
		String user_id = request.getParameter("user_id");
		
		UserDAO userDAO = UserDAOImpl.getInstance();
		int count = userDAO.CountUserID(user_id);
		
		return new ModelAndView("/WEB-INF/views/user/checkID.jsp", "count", count);
	}
}
