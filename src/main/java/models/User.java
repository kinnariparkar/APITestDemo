package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class Mapper for User API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	public Integer id;
	public String name;
	public String username;
	public String email;
	public Object address;
	public String phone;
	public String website;
	public Object company;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", username=" + username + ", email=" + email + "]";
	}

}
