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

	@DataProvider(name = "userdata")
	public Object[] createUserData() {
		List<String> usernames = IntegrationTestDataReader.getIntegrationData().getUsernameList();
		Object[] users = usernames.toArray();

		return users;
	}

	@Test(description = "Test to get user details from users api", dataProvider = "userdata")
	public void e2eflowUserPostComment(String username) {

		response = given().queryParam("username", username).when().get("/users");

		Assert.assertEquals(ResponseValidator.headerValidator(response), 0, "Invalid header");
		Assert.assertEquals(ResponseValidator.responseTypeValidator(response), 0, "Invalid response validation");
		Assert.assertEquals(response.getStatusCode(), 200, "Invalid response status code");
		Assert.assertEquals(response.getBody().asPrettyString().contains(username), true,
				"No user found in the response");

		user_list = Arrays.asList(response.getBody().as(User[].class));
		logger.info("User List: " + user_list);

		Assert.assertNotNull(user_list, "Users couldn't be converted into list of objects");
		User user = user_list.get(0);

		response = given().queryParam("userId", user.getId()).when().get("/posts");
		Assert.assertEquals(response.getBody().asString().contains(user.getId().toString()), true,
				"No posts found for the user");

		posts_list = Arrays.asList(response.getBody().as(Post[].class));
		logger.info("Posts List: " + posts_list);

		Assert.assertNotNull(posts_list, "Posts couldn't be converted into list of objects");

		for (Post post : posts_list) {
			response = given().queryParam("postId", post.getId()).when().get("/comments");
			Assert.assertEquals(response.getBody().asString().contains(post.getId().toString()), true,
					"No commnts found for the post");
			comments_list = Arrays.asList(response.getBody().as(Comment[].class));
			logger.info(comments_list);

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
