package JiraTests;

import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.PreemptiveAuthProvider;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetIssuesfrom8Hours {

	@Test
	public void getAllIssues() {

		RestAssured.baseURI = "https://vigneshm.atlassian.net/rest/api/2/search?jql=project=SER";

		PreemptiveAuthProvider preAuthScheme = new PreemptiveAuthProvider();
		AuthenticationScheme scheme = preAuthScheme.basic("vikytnt@gmail.com", "vicky20$5");

		RestAssured.authentication = scheme;

		Response response = RestAssured 
				.get();
		//response.prettyPrint();								

		JsonPath responseJson = response.jsonPath();
		/*List<Object> allIssues = responseJson.getList("issues.key");
		System.out.println(allIssues.size());*/
		
		long timeSec = System.currentTimeMillis();
		System.out.println("Current Time is: " +timeSec);
		
		//List<long> list = responseJson.getList("issues.fields.created");
		//System.out.println(list.size());

		/*for (Object eachIssue : allIssues) {
			System.out.println(eachIssue);			
		}*/
		
		/*for (long eachItem : list) {
			System.out.println(eachItem);
			if ((timeSec - 28800000 <= eachItem)) {
				
			}
		}*/
	}
}