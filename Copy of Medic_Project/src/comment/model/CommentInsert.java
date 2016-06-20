package comment.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.controllers.AbstractController;
import main.controllers.ModelAndView;

public class CommentInsert extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger("CommentInsert.java");

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
	
		CommentVO  commentVO = new CommentVO();
		commentVO.setUser_id(request.getParameter("user_id"));
		commentVO.setContent(request.getParameter("content"));
		System.out.println(request.getParameter("article_no"));
		System.out.println(Long.parseLong(request.getParameter("article_no")));
		commentVO.setArticle_no(Long.parseLong(request.getParameter("article_no")));
		logger.info("commentInsert 전달값"+commentVO);

		CommentDAO commentDAO = CommentDAOImpl.getInstance();
		boolean result = commentDAO.saveComment(commentVO); 
		
		
		
		List<CommentVO> list = commentDAO.CommentViewer(commentVO.getArticle_no());
		
		return new ModelAndView("/WEB-INF/views/comment/commentViewer.jsp", "list", list);
	}

}
