package unit;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestSetup;

public class Test_Posts extends TestSetup {

	/**
	 * DataProvider to provide test-data for fetching posts created by specific
	 * userId
	 * 
	 * @return Object of userId integers
	 */
	@DataProvider(name = "userId-data")
	public Object[] createUserIdData() {
		return new Object[] { 9 };
	}

	/**
	 * This method is a unit test case for the post API - GET method
	 * 
	 * @param userId
	 */
	@Test(description = "Test to get user posts", dataProvider = "userId-data")
	public void getPosts(Integer userId) {

		response = 
				given().
					queryParam("userId", userId).
				when().
					get("/posts").
				then().
					extract().response();
		Assert.assertEquals(response.getStatusCode(), 200);
		// TODO - Other Assertions for schema validations and response body

	}

	@AfterClass
	void tearDown() {
		logger.info("*********  Finished  " + Test_Posts.class + " **********");
	}

}
