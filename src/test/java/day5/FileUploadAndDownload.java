package day5;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class FileUploadAndDownload {
	
	@Test()
	void fileUpload() {
		File datafile=new File("\\body.json");
		given()
			.multiPart("file",datafile)
			.contentType("multipart/form-data")
		
		.when()
			.post("")
		.then()
			.statusCode(200)
			.body("filename", equalTo("body"))
			.log().all();
	}

}
