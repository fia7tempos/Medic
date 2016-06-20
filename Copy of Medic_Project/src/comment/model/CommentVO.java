package comment.model;

public class CommentVO {

	private long no;
	private long article_no;
	private String user_id;
	private String content;
	private String regdate;
	private String user_photo;
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public long getArticle_no() {
		return article_no;
	}
	public void setArticle_no(long article_no) {
		this.article_no = article_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getUser_photo() {
		return user_photo;
	}
	public void setUser_photo(String user_photo) {
		this.user_photo = user_photo;
	}
	@Override
	public String toString() {
		return "CommentVO [no=" + no + ", article_no=" + article_no + ", user_id=" + user_id + ", content=" + content
				+ ", regdate=" + regdate + ", user_photo=" + user_photo + "]";
	}
	

	
	
	
}		
