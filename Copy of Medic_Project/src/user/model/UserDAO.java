package user.model;

import java.util.List;

public interface UserDAO {

	UserVO loginAction(String user_id, String user_pw);

	int CountUserID(String user_id);

	UserVO getUserProfile(String user_id);

	boolean registUser(UserVO userVO);

	boolean registFBUser(UserVO userVO);

	boolean updateProfile(UserVO userVO);

	Long CountUserArticle(String searchKeyword);

	List<UserVO> getUserFollow(String user_id);

	boolean addFollower(String addFollwer_id, String user_id);

}
