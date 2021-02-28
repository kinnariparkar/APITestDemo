package integration;

import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import models.Comment;
import models.Post;
import models.User;
import utilities.EmailValidations;
import utilities.IntegrationTestDataReader;
import utilities.ResponseValidator;
import base.TestSetup;

public class Test_E2E extends TestSetup {

	List<User> user_list;
	List<Post> posts_list;
	List<Comment> comments_list;

	/**
	 * DataProvider to provide test-data of users for e2e test
	 * 
	 * @return Object of username Strings
	 */
	@DataProvider(name = "userdata")
	public Object[] createUserData() {
		List<String> usernames = IntegrationTestDataReader.getIntegrationData().getUsernameList();
		Object[] users = usernames.toArray();

		return users;
	}

	/**
	 * This method is a integration test case for fetching posts of a particular
	 * user and validating the emailIds in the comments of posts created by the user
	 * 
	 * @param username
	 */
	@Test(description = "E2E test flow validating emails in comments", dataProvider = "userdata")
	public void e2eflowUserPostComment(String username) {
		

		// Fetch user data from users API based on <username>
		response = given().queryParam("username", username).when().get("/users");

		// User data assertions
		Assert.assertEquals(ResponseValidator.headerValidator(response), 0, "Invalid header");
		Assert.assertEquals(ResponseValidator.responseTypeValidator(response), 0, "Invalid response validation");
		Assert.assertEquals(response.getStatusCode(), 200, "Invalid response status code");
		Assert.assertEquals(response.getBody().asPrettyString().contains(username), true,
				"No user found in the response");

		// Convert response to list of user bean objects
		user_list = Arrays.asList(response.getBody().as(User[].class));
		logger.info("User List: " + user_list);

		// User list bean assertions
		Assert.assertNotNull(user_list, "Users couldn't be converted into list of objects");
		User user = user_list.get(0);

		// Fetch post data from posts API based on <userId> from user bean object
		response = given().queryParam("userId", user.getId()).when().get("/posts");
		
		// Post data assertions
		Assert.assertEquals(ResponseValidator.headerValidator(response), 0, "Invalid header");
		Assert.assertEquals(ResponseValidator.responseTypeValidator(response), 0, "Invalid response validation");
		Assert.assertEquals(response.getStatusCode(), 200, "Invalid response status code");
		Assert.assertEquals(response.getBody().asString().contains(user.getId().toString()), true,
				"No posts found for the user");

		// Convert response to list of post bean objects
		posts_list = Arrays.asList(response.getBody().as(Post[].class));
		logger.info("Posts List: " + posts_list);

		// Post list bean assertions
		Assert.assertNotNull(posts_list, "Posts couldn't be converted into list of objects");

		//Looping for each post created by user
		for (Post post : posts_list) {
			// Fetch comment data from comments API based on <postId> from post bean object
			response = given().queryParam("postId", post.getId()).when().get("/comments");
			
			// Comment data assertions
			Assert.assertEquals(ResponseValidator.headerValidator(response), 0, "Invalid header");
			Assert.assertEquals(ResponseValidator.responseTypeValidator(response), 0, "Invalid response validation");
			Assert.assertEquals(response.getStatusCode(), 200, "Invalid response status code");
			Assert.assertEquals(response.getBody().asString().contains(post.getId().toString()), true,
					"No comments found for the post");
			
			// Convert comment to list of comment bean objects
			comments_list = Arrays.asList(response.getBody().as(Comment[].class));
			logger.info(comments_list);

			//Email Validator for each comment
			for (Comment comment : comments_list) {
				Assert.assertEquals(EmailValidations.validateEmailID(comment.getEmail()), true,
						"Email assertion failed for " + comment.getEmail());
			}
		}

	}

	@AfterClass
	void tearDown() {
		logger.info("*********  Finished" + Test_E2E.class + " **********");
	}

}
