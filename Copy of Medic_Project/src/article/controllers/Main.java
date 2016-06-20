package article.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.PageNation;
import main.controllers.AbstractController;
import main.controllers.ModelAndView;

public class Main extends AbstractController{

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/WEB-INF/views/main.jsp");
		PageNation pagenation = new PageNation(1);
		mav.addObject("pagenation", pagenation);
		
		return mav;
	}

}
