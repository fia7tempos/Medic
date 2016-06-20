package follow.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.controllers.AbstractController;
import main.controllers.ModelAndView;
import user.model.UserDAO;
import user.model.UserDAOImpl;
import user.model.UserVO;

public class searchFollw extends AbstractController {

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/WEB-INF/views/follow/searchFollow.jsp");
		String user_id = request.getParameter("user_id");
		
		UserDAO userDAO = UserDAOImpl.getInstance();
		List<UserVO> searchFollowList = userDAO.getUserFollow(user_id);
		mav.addObject("searchFollowList", searchFollowList);
		
		return mav;
	}
	
}
