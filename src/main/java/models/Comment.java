package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class Mapper for Comment API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {
	public Integer postId;
	public Integer id;
	public String name;
	public String email;

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Comment [postId=" + postId + ", id=" + id + ", email=" + email + "]";
	}

}
