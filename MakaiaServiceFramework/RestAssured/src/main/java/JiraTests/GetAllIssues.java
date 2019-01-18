package JiraTests;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.PreemptiveAuthProvider;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetAllIssues {

	@Test
	public void getAllIssues() {
		
		RestAssured.baseURI = "https://vigneshm.atlassian.net/rest/api/2/search?jql=project=SER";
		
		PreemptiveAuthProvider preAuthScheme = new PreemptiveAuthProvider();
		AuthenticationScheme scheme = preAuthScheme.basic("vikytnt@gmail.com", "vicky20$5");
		
		RestAssured.authentication = scheme;
		
		Response response = RestAssured 
						/*.given()
						.auth()
						.preemptive()
						.basic("vikytnt@gmail.com","vicky20$5")
						.when()*/
						.get();
		//response.prettyPrint();								
						
		JsonPath responseJson = response.jsonPath();
		List<Object> allIssues = responseJson.getList("issues.key");
		System.out.println(allIssues.size());
		
		for (Object eachIssue : allIssues) {
			System.out.println(eachIssue);
		}
	}
	
}
