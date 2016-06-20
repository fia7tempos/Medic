package user.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.controllers.AbstractController;
import main.controllers.ModelAndView;

public class FBRegist extends AbstractController{

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/WEB-INF/views/user/fbregist.jsp");
	}

}
