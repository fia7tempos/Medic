package user.model;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

public class UserVO {

	private String user_id = null;
	private String user_name = null;
	private String user_email = null;
	private String user_gender = null;
	private String user_birth = null;
	private String user_photo = null;
	private String user_aboutme = null;
	private String user_pw = null;
	private Date regdate = null;
	
	public String getUser_id() {
		return user_id.trim().toLowerCase();
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id.trim().toLowerCase();
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}
	public String getUser_birth() {
		return user_birth;
	}
	public void setUser_birth(String user_birth) {
		this.user_birth = user_birth;
	}
	public String getUser_photo() {
		return user_photo;
	}
	public void setUser_photo(String user_photo) {
		this.user_photo = user_photo;
	}
	public String getUser_aboutme() {
		return user_aboutme;
	}
	public void setUser_aboutme(String user_aboutme) {
		this.user_aboutme = user_aboutme;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = DigestUtils.sha512Hex(user_pw);
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "UserVO [user_id=" + user_id + ", user_name=" + user_name + ", user_email=" + user_email
				+ ", user_gender=" + user_gender + ", user_birth=" + user_birth + ", user_photo=" + user_photo
				+ ", user_aboutme=" + user_aboutme + ", user_pw=" + user_pw + ", regdate=" + regdate + "]";
	}
	
	
	
}
