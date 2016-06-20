package article.model;

public class ArticleVO {
	private long no = 0;
	private String user_id = null;
	private String content = null;
	private String image = null;
	private String video = null;
	private double latitude;
	private double longitude;
	private String hashtag = null;
	private String category = null;
	private int good;
	private String regdate = null;
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "ArticleVO [no=" + no + ", user_id=" + user_id + ", content=" + content + ", image=" + image + ", video="
				+ video + ", latitude=" + latitude + ", longitude=" + longitude + ", hashtag=" + hashtag + ", category="
				+ category + ", good=" + good + ", regdate=" + regdate + "]";
	}
	
	
	
	
}
