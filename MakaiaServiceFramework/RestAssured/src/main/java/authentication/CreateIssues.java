package authentication;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateIssues extends Authentication{

	@Test(dataProvider="get")
	public void createIssue(String file) {		
		Response response = reqSpec
				.body(new File(file))
				.expect()
				.statusCode(200)
				.when()
				.post();
		response.prettyPrint();

		JsonPath jsonResponse = response.jsonPath();

		key_id = jsonResponse.get("key");
		System.out.println(key_id);

		issue_id = jsonResponse.get("id");
		System.out.println(issue_id);		
	}

	@DataProvider(name="get")
	public String[][] getData(){

		String[][] data = new String[1][1];
		data[0][0] = "C:\\Users\\Sangeetha\\Desktop\\Newman\\JiraInputBody.json";
		return data;
	}
	
}
