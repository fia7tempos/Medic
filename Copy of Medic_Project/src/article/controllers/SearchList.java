package article.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.ArticleDAO;
import article.model.ArticleDAOImpl;
import article.model.ArticleVO;
import article.model.PageNation;
import main.controllers.AbstractController;
import main.controllers.ModelAndView;

public class SearchList extends AbstractController {

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		long pg = 1;
		String keyword=null;
		try {
			pg = Long.parseLong(request.getParameter("pg"));
			keyword = request.getParameter("keyword");
		} catch(Exception e) {}
		

		PageNation pageNation = new PageNation(pg,keyword);
		
//		HttpSession session = request.getSession();
//		UVO userInfo = (MemberVO)session.getAttribute("userInfo");
		
		ArticleDAO articleDAO = ArticleDAOImpl.getInstance();
		java.util.List<ArticleVO> list = articleDAO.getArticleKeywordList(pageNation);	
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/WEB-INF/views/article/list_search.jsp");
		mav.addObject("list", list);
		mav.addObject("pageNation", pageNation);
		return mav;
	}
	

}
