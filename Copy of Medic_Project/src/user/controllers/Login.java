package user.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.controllers.AbstractController;
import main.controllers.ModelAndView;

public class Login extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger("login.java");
	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		String cookieValue = null;
		for(Cookie cookie2: cookies){
			if(cookie2.getName().equals("user_id")){
			cookieValue= cookie2.getValue();
			}
		}
		logger.info("cookieValue"+cookieValue);
	
		return new ModelAndView("/WEB-INF/views/user/login.jsp","cookieValue",cookieValue);
	}

}
