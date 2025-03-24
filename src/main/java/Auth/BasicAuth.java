package Auth;
import  static io.restassured.RestAssured.*;
import static io.restassured.matcher.ResponseAwareMatcher.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
public class BasicAuth {
	//@Test(priority=1)
	void testBasicAuth() {
		
		given()
			.auth().basic(DEFAULT_PATH, DEFAULT_BODY_ROOT_PATH)
		.when()
			.get()
		.then()
		.statusCode(200);

	}
		@Test(priority=2)
		void testDigestAuth() {
			String bToken="c343cc4d0298db7ebe3356c01016c4e24faafdc6c37e406a22444e2f3fe191f1";
			Response res= given()
				.header("Authorization", "Bearer"+bToken)
			.when()
				.get("https://gorest.co.in/public/v2/users?page=3");
			String currentLink= res.getHeader("x-links-current");
			Assert.assertEquals(currentLink, "https://gorest.co.in/public/v2/users?page=3");
					
					/*.then()
			.header("x-links-current", "https://gorest.co.in/public/v2/users?page=3")
			.statusCode(200);*/
		}
		
}
