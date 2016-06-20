package follow.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.controllers.AbstractController;
import main.controllers.ModelAndView;
import user.model.UserDAO;
import user.model.UserDAOImpl;
import user.model.UserVO;

public class addFollwer extends AbstractController{

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		String addFollwer_id = request.getParameter("addFollwer_id");
		HttpSession session = request.getSession();
		
		UserVO userVO = (UserVO) session.getAttribute("userInfo");
		
		UserDAO userDAO = UserDAOImpl.getInstance();
		
		boolean addFolwerResult = userDAO.addFollower(addFollwer_id, userVO.getUser_id());
		
		ModelAndView mav = new ModelAndView("/WEB-INF/views/follow/addFollowResult.jsp","addFolwerResult",addFolwerResult);
		
		
		return mav;
	}
	

}
