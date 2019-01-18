package ServiceNowTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class GetCR {

	@Test
	public void getCR(){


		RestAssured.baseURI = "https://dev61667.service-now.com/api/now/table/change_request";

		BasicAuthScheme basicSch = new BasicAuthScheme();
		basicSch.setUserName("admin");
		basicSch.setPassword("Vicky20$5");
		RestAssured.authentication = basicSch;
		
		List<Header> allHeaders = new ArrayList<Header>();
		allHeaders.add(new Header("accept","application/xml"));
		allHeaders.add(new Header("content-type","application/xml"));
		Headers requestheaders = new Headers(allHeaders);
		
		Map<String, String> parametersMap = new HashMap<String, String>();
		//parametersMap.put("type", "normal");
		parametersMap.put("sysparm_fields", "type,number,approval");
		
		Response response = RestAssured
				.given()
				.log()
				.all()
				.headers(requestheaders)
				.params(parametersMap)
				.get();
			
		response.prettyPrint();
		
		XmlPath xmlPath = response.xmlPath();
		List<String> listTypes = xmlPath.getList("response.result.type");
		for (String obj : listTypes) {
			if(!obj.equals("normal")){
				System.err.println("CR listed is not normal");
			}else {
				System.out.println("CR listed is normal");
			}
		}
	}

}
