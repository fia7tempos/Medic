package user.controllers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import main.controllers.AbstractController;
import main.controllers.ModelAndView;
import user.model.UserDAO;
import user.model.UserDAOImpl;
import user.model.UserVO;

public class RegistAction extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger("RegistAction.java");

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		UserVO userVO = new UserVO();
		String uploadPath = "c:/dev64/upload";
		int size = 1024 * 1024 * 20;
		String file = null;
		String user_img = null;
		File dir = new File(uploadPath);
		String ext = null;
		boolean isImage = false;
		if(!dir.exists()){
			dir.mkdirs();
		}
		try {
			MultipartRequest multi = new MultipartRequest(
					request, uploadPath, size,"UTF-8", new DefaultFileRenamePolicy());
			Enumeration<String> fileNames = multi.getFileNames();
			file = fileNames.nextElement();
			user_img= multi.getFilesystemName(file);
			isImage=
					multi.getContentType(file).substring(0, 6).toLowerCase().equals("image");
			ext = multi.getContentType(file).substring(6).toLowerCase();

			
					
			userVO.setUser_id(multi.getParameter("user_id"));
			userVO.setUser_name(multi.getParameter("user_name"));
			userVO.setUser_pw(multi.getParameter("user_pw"));
			userVO.setUser_birth(multi.getParameter("user_birth"));
			userVO.setUser_email(multi.getParameter("user_email"));
			userVO.setUser_gender(multi.getParameter("user_gender"));
			userVO.setUser_photo(user_img);
			userVO.setUser_aboutme(multi.getParameter("user_aboutme"));
	
//			logger.info("userVO"+userVO+" 이미지"+user_img);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(isImage){
				ParameterBlock pb = new ParameterBlock();
				pb.add(uploadPath + "/" + user_img);
				RenderedOp rOp = JAI.create("fileload", pb);
				BufferedImage bi = rOp.getAsBufferedImage();
				BufferedImage thumb = new BufferedImage(
						100, 100, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = thumb.createGraphics();
				g.drawImage(bi, 0, 0, 100, 100, null);
				File f = new File(uploadPath + " /thumb_"+user_img);
				ImageIO.write(thumb, ext, f);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		UserDAO userDAO = UserDAOImpl.getInstance();
		boolean result = userDAO.registUser(userVO);
			if(result){
				return new ModelAndView("/WEB-INF/views/user/registAction.jsp", "userVO", userVO);
			} else {
				ModelAndView mav = new ModelAndView("/WEB-INF/views/result.jsp");
				mav.addObject("msg", "등록되지 않았습니다.");
				mav.addObject("url", "javascript:history.back();");
				return mav;
			}

		
		
	}

}
