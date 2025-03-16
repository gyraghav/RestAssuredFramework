package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

//https://reqres.in/api/users?page=2&id=4

public class PathAndQuerParameter {

	@Test()
	void PathAndQuery() {
		
		given()
			.pathParam("mypath","users")
			.queryParam("page", "2")
			.queryParam("id", "4")
		
		
		.when()
		    .get("https://reqres.in/api/{mypath}")
		
		.then()
			.statusCode(200).log().all();
	}
}
