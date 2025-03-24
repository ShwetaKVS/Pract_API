package DemoParsing;

import  static io.restassured.RestAssured.*;
import static io.restassured.matcher.ResponseAwareMatcher.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

public class jsonParsing2 {
	
	//@Test(priority=1)
	void testBookJson() {
		
		given()
			
		.when()
			.get("http://localhost:3000/Store")
		.then()
		.header("Content-Type", "application/json")
		.header("Content-Length", "792")
		.statusCode(200);
		
	}
	@Test(priority=2)
	void testBookTitleJson() {
		
		Response res=given()
						.contentType(ContentType.JSON)
					.when()
						.get("http://localhost:3000/Store");
	
				String contentType = res.header("Content-Type");
				Assert.assertEquals(contentType, "application/json");
				Assert.assertEquals(200, res.getStatusCode());
				Assert.assertEquals(res.getHeader("Content-Type"),"application/json");
				
				String authorName = res.jsonPath().get("book[2].author");
				  
				Assert.assertEquals(authorName, "Cherry Andersen");
		
	}
	
	String getToken() {
		
		String token=null;
		
		Response res = given()
						.when()
							.body("{\r\n"
									+ "    \"email\": \"shweta11@example.com\"\r\n"
									+ "}")
							.post("https://valentinos-coffee.herokuapp.com/clients");
		
			token=res.jsonPath().get("token");
	
	
		return token;
		
	}
	
}
