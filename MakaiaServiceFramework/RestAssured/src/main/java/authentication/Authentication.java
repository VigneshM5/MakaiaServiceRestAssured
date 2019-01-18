package authentication;

import org.testng.annotations.BeforeTest;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.PreemptiveAuthProvider;
import io.restassured.specification.RequestSpecification;

public class Authentication {

	public static String issue_id;
	public static String key_id;
	public static RequestSpecification reqSpec;
	
	@BeforeTest
	public void authentication() {
		RestAssured.baseURI = "https://vigneshm.atlassian.net/rest/api/2/issue";
		PreemptiveAuthProvider preAuthScheme = new PreemptiveAuthProvider();
		AuthenticationScheme scheme = preAuthScheme.basic("vikytnt@gmail.com", "vicky20$5");
		
		RestAssured.authentication = scheme;
		
		reqSpec = RestAssured
				.given()
				.contentType("application/json")
				.log().all();
	}
}
