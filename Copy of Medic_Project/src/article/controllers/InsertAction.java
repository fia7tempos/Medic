package article.controllers;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import article.model.ArticleDAO;
import article.model.ArticleDAOImpl;
import article.model.ArticleVO;
import main.controllers.AbstractController;
import main.controllers.ModelAndView;

public class InsertAction extends AbstractController {
	public static final Logger logger = LoggerFactory.getLogger(InsertAction.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		ArticleVO articleVO = new ArticleVO();
		boolean result = false;

		MultipartRequest multi = null;
		String uploadPath = "c:/dev64/upload"; // 프로퍼티 이용하던가..
		String file = null;
		String contenttype = null;
		int size = 1024 * 1024 * 200; // 200메가
		String filename = null;
		String ext = null;
		try {
			File dir = new File(uploadPath);
			if (!dir.exists()) {
				dir.mkdir();
			}
			multi = new MultipartRequest(request, uploadPath, size, "UTF-8", new DefaultFileRenamePolicy()); // 중복파일
			Enumeration<String> filenames = (Enumeration<String>) multi.getFileNames();

			while (filenames.hasMoreElements()) {
				boolean isImage = false;
				boolean isVideo = false;
				file = filenames.nextElement();
				if (file == null)
					continue;
				logger.info("file" + file);
				filename = multi.getFilesystemName(file);
				contenttype = multi.getContentType(file);

				logger.info("filename:" + filename);

				if (contenttype != null) {
					isImage = contenttype.substring(0, 6).toLowerCase().equals("image/");
					isVideo = contenttype.substring(0, 6).toLowerCase().equals("video/");
					ext = contenttype.substring(6, contenttype.length());
				}

				if (isImage)
					articleVO.setImage(filename);
				if (isVideo)
					articleVO.setVideo(filename);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		articleVO.setContent(multi.getParameter("content"));
		articleVO.setUser_id(multi.getParameter("user_id"));
		articleVO.setLatitude(Double.parseDouble(multi.getParameter("latitude")));
		articleVO.setLongitude(Double.parseDouble(multi.getParameter("longitude")));
		articleVO.setHashtag(multi.getParameter("hashtag"));
		articleVO.setCategory(multi.getParameter("category"));
		logger.info(articleVO.toString());

		ArticleDAO articleDAO = ArticleDAOImpl.getInstance();
		result = articleDAO.insertArticle(articleVO);

		ModelAndView mav = new ModelAndView("/WEB-INF/views/result.jsp");
		if (result) {
			return new ModelAndView("/Main");
		} else {
			mav.addObject("msg", "글 등록 실패");
			mav.addObject("url", "javascript:history.back();");
		}
		return mav;
	}

}
