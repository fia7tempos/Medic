package article.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import article.model.ArticleDAO;
import article.model.ArticleDAOImpl;
import article.model.ArticleVO;
import main.controllers.AbstractController;
import main.controllers.ModelAndView;
import user.model.UserVO;

public class View extends AbstractController{

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		long no = -1;
		if(request.getParameter("no")!=null){
			no = Long.parseLong(request.getParameter("no"));
		}
		ArticleDAO articleDAO = ArticleDAOImpl.getInstance();
		ArticleVO articleVO = articleDAO.getArticle(no);	
		
		HttpSession session = request.getSession();
		UserVO userInfo = (UserVO)session.getAttribute("userInfo");
		
		
		ModelAndView mav = new ModelAndView("/WEB-INF/views/article/view.jsp");
		mav.addObject("articleVO", articleVO);	
		mav.addObject("userInfo", userInfo);	
		
		return mav;
	}

}
