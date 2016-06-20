package user.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.controllers.AbstractController;
import main.controllers.ModelAndView;

public class UserLogout extends AbstractController{

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		session.invalidate();
		ModelAndView mav = new ModelAndView("/WEB-INF/views/result.jsp");
		mav.addObject("msg", "로그아웃 성공");
		mav.addObject("url", "Login");
		return mav;
	}
	

}
