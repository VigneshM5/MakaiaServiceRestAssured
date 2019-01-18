package authentication;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class PatchIssues extends Authentication {

	@Test(dataProvider = "get", dependsOnMethods = "authentication.CreateIssues.createIssue")
	public void patchIssues(String file) {
		Response response = reqSpec
		.body(new File(file))
		.put("/"+issue_id);
		
		response.prettyPrint();
		
		int statuscode = response.statusCode();
		if (statuscode == 204) {
			System.out.println("Edited Correctly");
		}
		
		
	}
	
	@DataProvider(name="get")
	public String[][] getData(){

		String[][] data = new String[1][1];
		data[0][0] = "C:\\Users\\Sangeetha\\Desktop\\Newman\\JiraUpdateBody.json";
		return data;
	}

}
