package unit;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestSetup;

public class Test_Users extends TestSetup {

	/**
	 * DataProvider to provide test-data for fetching users with specific username
	 * 
	 * @return Object of username strings
	 */
	@DataProvider(name = "userdata")
	public Object[] createUserData() {
		return new Object[] { "Delphine", "Bret" };
	}

	/**
	 * This method is a unit test case for the user API - GET method
	 * 
	 * @param username
	 */
	@Test(description = "Test to get user details from users api", dataProvider = "userdata")
	public void getUser(String username) {
		response = 
				given().
					queryParam("username", username).
				when().
					get("/users").
				then().
					extract().response();
		Assert.assertEquals(response.getStatusCode(), 200);
		// TODO - Other Assertions for schema validations and response body
	}

	@AfterClass
	void tearDown() {
		logger.info("*********  Finished  " + Test_Users.class + " **********");
	}
}
