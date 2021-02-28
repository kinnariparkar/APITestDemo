package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Class Mapper for Post API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {

	public Integer userId;
	public Integer id;
	public String title;
	public String body;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Post [userId=" + userId + ", id=" + id + ", title=" + title + "]";
	}
	
	
}
