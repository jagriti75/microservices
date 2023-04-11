package external.api.entity;

public class postEntity {
	
	private String userId;
	private String title;
	private String body;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public postEntity(String userId, String title, String body) {
		super();
		this.userId = userId;
		this.title = title;
		this.body = body;
	}
	public postEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
