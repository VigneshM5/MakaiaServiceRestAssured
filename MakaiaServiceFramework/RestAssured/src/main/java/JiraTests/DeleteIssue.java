package JiraTests;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.PreemptiveAuthProvider;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DeleteIssue {
	
	@Test
	public void deleteIssue() {
		RestAssured.baseURI = "https://vigneshm.atlassian.net/rest/api/2/issue";
		PreemptiveAuthProvider preAuthScheme = new PreemptiveAuthProvider();
		AuthenticationScheme scheme = preAuthScheme.basic("vikytnt@gmail.com", "vicky20$5");
		
		RestAssured.authentication = scheme;
		
		Response response = RestAssured
				.given()
				.contentType("application/json")
				.body(new File("C:\\Users\\Sangeetha\\Desktop\\Newman\\JiraInputBody.json"))
				.post();
		
		JsonPath jsonResponse = response.jsonPath();
		
		String keyCreated = jsonResponse.get("key");
		System.out.println(keyCreated);
		
		String idCreated = jsonResponse.get("id");
		System.out.println(idCreated);
		
		RestAssured.baseURI = "https://vigneshm.atlassian.net/rest/api/2/issue/"+ keyCreated;
		
		Response response1 = RestAssured
				.given()
				.contentType("application/json")				
				.delete();
		
		response1.prettyPrint();
		
		System.out.println(response1.statusCode());
		
	}

}
