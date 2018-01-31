package com.restapi.testcase;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.IOException;

import com.restapi.common.RestUtilities;
import com.restapi.constant.EndPoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class PostFunctions {
	RequestSpecification reqSpec;
	ResponseSpecification reSpec;
	String path;
	
	@BeforeClass
	public void settingUp(){
		reqSpec=RestUtilities.getRequestSpecification();
		reSpec=RestUtilities.getResponseSpecification();
		path="C:\\Users\\User\\Desktop\\JsonFiles\\mynewfile.json";
	}
//@Test
public void createUserUsingJsonObject(){
	/**
	 * This test is to create an user by using json object
	 * */
	JSONObject request=new JSONObject();
	request.put("name", "suriyakishoore");
	request.put("job", "Q&A");
 Response res=
given()
	  .log()
	  .all()
	 .body(request.toString())
	  .contentType("application/json") 
	  .spec(reqSpec)
.when()
	  .post(EndPoints.BASE_PATH_CREATE)
.then()
	  .log()
	  .all()
	  .statusCode(201)
	  .extract().response();
    
 System.out.println(res.path("id"));
	
}


// @Test
   public void createUserUsingStringFile() throws IOException{
	  /**
	   * This test is to create an user by converting the json file to string.
	   * 
	   * */
	  String filePath=RestUtilities.generateStringFromResource(path);
given()
	  .log()
	  .all()
	  .body(filePath)
	  .contentType("application/json")
	  .spec(reqSpec)
.when()
     .post(EndPoints.BASE_PATH_CREATE)
 .then()
     .log()
     .all()
     .statusCode(201);
	  
  }
  @Test
  public void createUserUsingDirectFile() throws IOException{
	  /**
	   * This test is to create an user without converting the json file to string'
	   * here we are using the file directly in body
	   * 
	   * */
	  File f=new File(path);
given()
	  .log()
	  .all()
	  .body(f)
	  .contentType("application/json")
	  .spec(reqSpec)
.when()
    .post(EndPoints.BASE_PATH_CREATE)
.then()
    .log()
    .all()
    .statusCode(201);	  
}
}
