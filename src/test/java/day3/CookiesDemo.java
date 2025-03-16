package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;


public class CookiesDemo {
	
//	@Test
	void testCookies() {
		
		given()
		
		.when().get("https://www.google.com/")
		
		.then().statusCode(200).cookie("AEC", "AVcja2dM01yfJrK-Q7bQujrEO7sj8Ofiuc1-zejIK3A1rGlf-vO8yKmh1w")
		.log().all();
		}
	
//	@Test
	void getCookieInfo() {
	Response res=given()
		
		.when().get("https://www.google.com/");
		
//		.then();
	
		String Cookie_Value=res.getCookie("AEC");
		System.out.println("Cookie value: "+Cookie_Value);
		Map<String,String>CookieMap=res.getCookies();
		for(String key:CookieMap.keySet()) {
			String value=res.getCookie(key);
			System.out.println(key+"  "+value);
		}
		
	}
	
//	@Test
	void getHeader() {
		
		Response res=given()
		
		.when().get("https://www.google.com/");
		
//		.then().header("Content-Type", "text/html; charset=ISO-8859-1")
//		.and()
//		.header("Content-Encoding", "gzip");
		String content_Type=res.getHeader("Content-type");
		System.out.println("content_Type: "+content_Type);
		
		Headers mapHeaders=res.getHeaders();
		for(Header hd:mapHeaders) {
			System.out.println(hd.getName()+"--->"+hd.getValue());
		}
		
	}
	
	@Test
	void getLogs() {
		given()
		
		.when().get("https://reqres.in/api/users?page=2")
		
		.then().log().body();
	}

}
