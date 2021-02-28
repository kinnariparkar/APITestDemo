package utilities;


import io.restassured.response.Response;

public class ResponseValidator {
	public static int headerValidator(Response response) {
		String headersContentType = response.getHeaders().getValue("Content-Type");
		int val = headersContentType.compareTo("application/json; charset=utf-8");
		return val;
	}

	public static int responseTypeValidator(Response response) {
		String responseContentType = response.getContentType();
		int val = responseContentType.compareTo("application/json; charset=utf-8");
		return val;
	}

}
