package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;



import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/****
 * Post Request using HashMap
 * Post Request Body Using Prg.Json
 * Post Request Body Using POJO Class
 * Post Request Body Using External JSIN File
 ****/

public class DifferntWaystoCreatingPostRequesBody {
	
//	Post Request using HashMap
//	@Test
	void testUsingHashMap() {
		HashMap data=new HashMap();
		String CourceArr[]= {"JAVA",".Net"};
		data.put("name", "Abc");
		data.put("location", "London");
		data.put("Phone", "12121");
		data.put("Cources", CourceArr);
		
		given().contentType("application/json").body(data)
		
		.when().post("https://reqres.in/api/users/")
		
		.then()
		.statusCode(201)
		.body("name",equalTo("Abc"))
		.body("Cources[0]",equalTo("JAVA"))
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();
		
	}
	
//	Post Request Body Using Org.Json
//	@Test()
	void testUsingOrg_json(){
		JSONObject data=new JSONObject(); //  ---> This is the only difference instead of HashMap here we r using JSONObject
		String CourceArr[]= {"JAVA",".Net"};
		data.put("name", "Abc");
		data.put("location", "London");
		data.put("Phone", "12121");
		data.put("Cources", CourceArr);
		
		given().contentType("application/josn").body(data.toString()) //-->in body we need to convert json to string while passing
		
		.when()
		
		.then().statusCode(210);
	}
	
//	Post Request Body using POJO Class which most important and maximum used
	@Test()
	void postRequestUsingPojo() {
		Pojo_PostRequest pojo=new Pojo_PostRequest();
		String cources[]= {"JAVA",".Net"};
		pojo.setName("ABC");
		pojo.setLocation("London");
		pojo.setPhone("12345");
		pojo.setCources(cources);
		
		given().contentType("application/json").body(pojo)
		
		.when().post("")
		
		.then().statusCode(201);
	}

// Post Request Body Using External Json file
	@Test
	void postRequestUsingExternalJsonFile() throws FileNotFoundException {	
		File f=new File(".\\body.json");
		FileReader fr=new FileReader(f);
		JSONTokener jt= new JSONTokener(fr);
		JSONObject jo=new JSONObject(jt);
		
		given().contentType("application/josn").body(jo.toString())
		
		.when().post("")
		
		.then().statusCode(201)
		.body("name",equalTo("ABC"));
		
	}
	
}
