package follow.model;

import java.util.List;

import user.model.UserVO;

public interface FollowDAO {

	List<UserVO> getFollowList(String user_id, FollowListVO followListVO);

	Long getFollowCnt(String user_id);

}
