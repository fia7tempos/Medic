package user.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import follow.model.FollowDAO;
import follow.model.FollowDAOImpl;
import follow.model.FollowListVO;
import follow.model.FollowVO;
import main.controllers.AbstractController;
import main.controllers.ModelAndView;
import user.model.UserDAO;
import user.model.UserDAOImpl;
import user.model.UserVO;

public class UserMain extends AbstractController{

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		
		UserDAO userDAO = UserDAOImpl.getInstance();
		HttpSession session = request.getSession();
		UserVO userInfo = (UserVO)session.getAttribute("userInfo");
		
		//프로필 정보 가져오기
		UserVO userVO = userDAO.getUserProfile(userInfo.getUser_id());
		
		//친구
		FollowDAO followDAO = FollowDAOImpl.getInstance();
		FollowListVO followListVO = new FollowListVO();
		
		//친구 수 가져오기
		Long followCnt = followDAO.getFollowCnt(userInfo.getUser_id());
		
		//게시글 수 가져오기
		Long getUserArticle = userDAO.CountUserArticle(userInfo.getUser_id());
		
		//친구 리스트 가져오기
		followListVO.setFollowCount(followCnt);
		List<UserVO> followList = followDAO.getFollowList(userInfo.getUser_id(), followListVO);
		
		ModelAndView mav = new ModelAndView("/WEB-INF/views/user/user_main.jsp");
		mav.addObject("userVO", userVO);
		mav.addObject("followList", followList);
		mav.addObject("getUserArticle", getUserArticle);
		mav.addObject("followCnt", followCnt);
		
		return mav;
	}

}
