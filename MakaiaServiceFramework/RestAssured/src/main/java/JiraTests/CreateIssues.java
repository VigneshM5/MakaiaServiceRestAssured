package JiraTests;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.PreemptiveAuthProvider;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateIssues {
	
	@Test
	public void createIssue() {
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
		
		RestAssured.baseURI = "https://vigneshm.atlassian.net/rest/api/2/issue/"+ keyCreated + "/attachments";
		
		Response response1 = RestAssured
				.given()
				.contentType("multipart/form-data")
				.header(new Header("X-Atlassian-Token", "no-check"))
				/*.auth()
				.preemptive()
				.basic("vikytnt@gmail.com", "vicky20$5")
				.when()*/
				.body(new File("C:\\Users\\Sangeetha\\Desktop\\Newman\\jsonAttachments.json"))
				.post();
				//.post("https://vigneshm.atlassian.net/rest/api/2/issue/"+ keyCreated + "/attachments");
		
		response1.prettyPrint();
		
	}

}
