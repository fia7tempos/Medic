package user.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.controllers.AbstractController;
import main.controllers.ModelAndView;
import user.model.UserDAO;
import user.model.UserDAOImpl;
import user.model.UserVO;

public class LoginAction extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger("loginAction.java");

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		UserVO userVO = new UserVO();
		userVO.setUser_id(user_id);
		userVO.setUser_pw(user_pw);
		logger.info("Action 전달값: "+userVO);
		
		UserDAO userDAO = UserDAOImpl.getInstance();
		UserVO userInfo = userDAO.loginAction(userVO.getUser_id(), userVO.getUser_pw());
		
		logger.info("loginAction전달값: "+userInfo);
		ModelAndView mav = new ModelAndView("/WEB-INF/views/result.jsp");
		if(userInfo != null){
			mav.addObject("url", "/Main");
			mav.addObject("msg", "로그인에 성공했습니다.");
			
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", userInfo);
			
			
			Cookie c = new Cookie("user_id",user_id);
			if(request.getParameter("remember_me")!=null){
				c.setMaxAge(60*60*24);
				response.addCookie(c);
			}else{
				c.setMaxAge(0);
				response.addCookie(c);
			}
	
			
		}else{
			mav.addObject("url", "/user/Login");
			mav.addObject("msg", "로그인에 실패했습니다.");
		}		
		return mav;

	}


}
