package JiraTests;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.PreemptiveAuthProvider;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class createIssuewithDP {


	@Test(dataProvider="get")
	public void createNewIncident(String file) {

		RestAssured.baseURI = 
				"https://vigneshm.atlassian.net/rest/api/2/issue/";

		PreemptiveAuthProvider auth = new PreemptiveAuthProvider();
		AuthenticationScheme basic = auth.basic("vikytnt@gmail.com", "vicky20$5");
		RestAssured.authentication = basic;

		Response response = RestAssured
				.given()
				.log()
				.all()
				.contentType(ContentType.JSON)
				.when()
				.body(new File(file))
				.post();

		response.prettyPrint();

		long time = response.time();
		System.out.println(time);

		String statusCode = response.statusLine();
		System.out.println(statusCode);

		Map<String, String> cookies = response.getCookies();
		for (Entry<String, String> eachCookie : cookies.entrySet()) {
			System.out.println(eachCookie.getKey());
			System.out.println(eachCookie.getValue());

		}

		Headers headers = response.getHeaders();
		for (Header header : headers) {
			System.out.println(header.getName());
			System.out.println(header.getValue());

		}

		//JsonPath jsonResponse = response.jsonPath();



		/*String numberCreated = jsonResponse.get("id");
		System.out.println(numberCreated);
		 */
		/*Response post = RestAssured
		.given()
		.contentType("multipart/form-data")
		.header(new Header("X-Atlassian-Token","no-check"))
		.auth()
		.preemptive()
		.basic("qeagle1001@gmail.com", "leaf@123")
		.when()
		.body(new File("C:\\Users\\testleaf\\Desktop\\basics\\Attachment.json"))
		.post("https://webserv.atlassian.net/rest/api/2/issue/"+numberCreated+"/attachments");

		post.prettyPrint();*/
	}


	@DataProvider(name="get")
	public String[][] getData(){

		String[][] data = new String[1][1];
		data[0][0] = "C:\\Users\\testleaf\\Desktop\\basics\\Create.json";
		return data;


	}
}
