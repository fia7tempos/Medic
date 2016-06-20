package user.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.controllers.AbstractController;
import main.controllers.ModelAndView;
import user.model.UserDAO;
import user.model.UserDAOImpl;
import user.model.UserVO;

public class updateUserProfile extends AbstractController{

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		String user_profile = request.getParameter("user_profile");
		
		HttpSession session = request.getSession();
		
		UserVO userVO = (UserVO)session.getAttribute("userInfo");
		
		userVO.setUser_aboutme(user_profile);
		UserDAO userDAO = UserDAOImpl.getInstance();
		
		boolean resultUpdate = userDAO.updateProfile(userVO);
		
		
		ModelAndView mav = new ModelAndView("/WEB-INF/views/user/profile_action.jsp", "resultUpdate", resultUpdate);
		
		return mav;
	}

}
