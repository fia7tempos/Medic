package comment.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.controllers.AbstractController;
import main.controllers.ModelAndView;

public class CommentViewer extends AbstractController{
private static final Logger logger = LoggerFactory.getLogger("CommentViewer.java");
	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		
		long article_no = Long.parseLong(request.getParameter("article_no"));
//			logger.info("article_no = "+ article_no);
				
		CommentDAO commentDAO = CommentDAOImpl.getInstance();
		List<CommentVO> list = commentDAO.CommentViewer(article_no);
//		System.out.println(list);
		return new ModelAndView("/WEB-INF/views/comment/commentViewer.jsp", "list", list);
	}
	
}
