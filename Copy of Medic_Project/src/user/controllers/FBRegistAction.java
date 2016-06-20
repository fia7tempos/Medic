package user.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.controllers.AbstractController;
import main.controllers.ModelAndView;
import user.model.UserDAO;
import user.model.UserDAOImpl;
import user.model.UserVO;

public class FBRegistAction extends AbstractController {

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/WEB-INF/views/result.jsp");
		
		UserVO userVO = new UserVO();
		userVO.setUser_id(request.getParameter("user_id"));
		userVO.setUser_name(request.getParameter("user_name"));
		userVO.setUser_email(request.getParameter("user_email"));
		userVO.setUser_gender(request.getParameter("user_gender"));
		userVO.setUser_photo(request.getParameter("user_photo"));
		
		UserDAO userDAO = UserDAOImpl.getInstance();
		boolean result = userDAO.registFBUser(userVO);
		
		if(!result){
			mav.addObject("url", "history.back();");
			mav.addObject("msg", "회원 가입 실패");			
		}
		else {
			mav.addObject("url", "/Main");
			mav.addObject("msg", "회원 가입 성공");			
		}
		
		return mav;
	}

}
