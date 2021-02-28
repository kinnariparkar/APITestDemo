package unit;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestSetup;

public class Test_Comments extends TestSetup {

	/**
	 * DataProvider to provide test-data for fetching comments based on postIds
	 * 
	 * @return Object of postId integers
	 */
	@DataProvider(name = "postId-data")
	public Object[] createPostIdData() {
		return new Object[] { 81 };
	}

	/**
	 * This method is a unit test case for the comments API - GET method
	 * 
	 * @param postId
	 */
	@Test(description = "Test to get comments api", dataProvider = "postId-data")
	public void getComments(Integer postId) {

		response = given().queryParam("postId", postId).when().get("/comments").then().extract().response();

		Assert.assertEquals(response.getStatusCode(), 200);
		// TODO - Other Assertions for schema validations and response body
	}

	@AfterClass
	void tearDown() {
		logger.info("*********  Finished  " + Test_Comments.class + " **********");
	}

}
