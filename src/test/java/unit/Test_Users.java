package unit;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test_Users {

	// This method will provide data to any test method that declares that its Data
	// Provider
	// is named "test1"
	@DataProvider(name = "userdata")
	public Object[] createUserData() {
		return new Object[] { "Delphine", "Bret" };
	}

	@Test(description = "Test to get user details from users api", dataProvider = "userdata")
	public void get_user(String username) {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		Response response = given().queryParam("username", username).when().get("/users");

		Assert.assertEquals(response.getStatusCode(), 200);
		// TODO - Other Assertions for schema validations and response body
	}
}
