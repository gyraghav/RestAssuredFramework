package day4;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class ParsingJSONResponseData {
	
	@Test()
	void parsingBody() {
		
	// 1st Approach
	/*	given()
		.contentType(ContentType.JSON)
		.when().get("https://reqres.in/api/users?page=2")
		
		.then().statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.body("data[4].last_name",equalTo("Edwards")); */
		
	// 2nd Approach
		
		Response res=given()
				.contentType(ContentType.JSON)						
			.when()
				.get("https://reqres.in/api/users?page=2");
		
	/*	Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getHeader("Content-Type"), "application/json; charset=utf-8");
			
		String lastName=res.jsonPath().get("data[4].last_name").toString();
		Assert.assertEquals(lastName, "Edwards"); */
		
		JSONObject jo=new JSONObject(res.asString());
		
//		to print all first names from array
		/*
		  for(int i=0;i<jo.getJSONArray("data").length();i++) {
			  String firstName=jo.getJSONArray("data").getJSONObject(i).get("first_name").toString(); 
			  System.out.println(firstName);
		  } 
		  */
		 
			
//		Search for particular name in from the array
		boolean status=false;
		for(int i=0;i<jo.getJSONArray("data").length();i++) {
		String firstName=jo.getJSONArray("data").getJSONObject(i).get("first_name").toString();
		if(firstName.equalsIgnoreCase("Tobias")) {
			status=true;
			break;
		}
	} Assert.assertEquals(status, true);
	
	
//	to count all id's
	int total=0;
	for(int i=0;i<jo.getJSONArray("data").length();i++) {
		String id=jo.getJSONArray("data").getJSONObject(i).get("id").toString();
		total=total+Integer.parseInt(id);
		System.out.println("Total id: "+total);
		
	}
	}
}
