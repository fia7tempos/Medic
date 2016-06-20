package article.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.ArticleDAO;
import article.model.ArticleDAOImpl;
import article.model.ArticleVO;
import main.controllers.AbstractController;
import main.controllers.ModelAndView;

public class UserList extends AbstractController{

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/WEB-INF/views/article/list_user.jsp");
		
		String user_id = request.getParameter("user_id");
		ArticleDAO articleDAO = ArticleDAOImpl.getInstance();
		java.util.List<ArticleVO> list = articleDAO.getUserArticleList(user_id);
		
		mav.addObject("list", list);
		
		
		
		return mav;
	}

}
