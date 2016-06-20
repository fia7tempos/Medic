package follow.model;


public class FollowVO {

	private Long no;
	private String user_id = null;
	private String follow_id = null;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getUser_id() {
		return user_id.trim().toLowerCase();
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id.trim().toLowerCase();
	}
	public String getFollow_id() {
		return follow_id.trim().toLowerCase();
	}
	public void setFollow_id(String follow_id) {
		this.follow_id = follow_id.trim().toLowerCase();
	}
	@Override
	public String toString() {
		return "FollowVO [no=" + no + ", user_id=" + user_id + ", follow_id=" + follow_id + "]";
	}
	
	
	
	
	
	
	
}
