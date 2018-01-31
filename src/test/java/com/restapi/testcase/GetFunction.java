package com.restapi.testcase;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import com.restapi.common.RestUtilities;
import com.restapi.constant.EndPoints;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GetFunction {
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	
	@BeforeClass
	public void settingUp(){
		reqSpec=RestUtilities.getRequestSpecification();
		resSpec=RestUtilities.getResponseSpecification();
	}
	
  @Test
  public void listUsers() {
	  Response res=
	  given()
	      .log()
	      .all()
	      .spec(RestUtilities.createQueryParam(reqSpec, "page", "2"))
	  .when()
	      .get(EndPoints.BASE_PATH_LIST_USERS)
	  .then()
	     .log()
	     .all()
	     .spec(resSpec)
	     .rootPath("data[0]")
	     .body("id", equalTo(4),
	    		 "first_name",equalTo("Eve"),
	    		 "last_name",equalTo("Holt"))
	     .extract().response();
	      
	  JsonPath path=RestUtilities.getJsonPath(res);
	  System.out.println(path.get("data[0].first_name"));
  }
  
 //@Test
 public void singleUser(){
given()
	 .log()
	 .all()
	 .spec(reqSpec)
.when()
	 .get(EndPoints.BASE_PATH_SINGLE_USER)
 .then()
	 .log()
	 .all()
	 .rootPath("data")
	 .body("id", equalTo(2),
			 "first_name",equalTo("Janet"),
			 "last_name",equalTo("Weaver"))
	 .spec(resSpec);
 }
 
 //@Test
 public void listUser(){
	 given()
	   .log()
	   .all()
	   .spec(reqSpec)
	 .when()
	   .get(EndPoints.BASE_PATH_SINGLE_RESOURSE)
	 .then()
	 .log()
	 .all()
	 .spec(resSpec);
 }
}
