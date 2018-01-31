package com.restapi.testcase;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restapi.common.RestUtilities;
import com.restapi.constant.EndPoints;

import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class PutFunctionWIthOther {
	RequestSpecification reqSpec;
	ResponseSpecification reSpec;
	String path;
	String pathRegister;
	@BeforeClass
	public void settingUp(){
		reqSpec=RestUtilities.getRequestSpecification();
		reSpec=RestUtilities.getResponseSpecification();
		path="C:\\Users\\User\\Desktop\\JsonFiles\\update.json";
		pathRegister="C:\\Users\\User\\Desktop\\JsonFiles\\resgister.json";
	}
// @Test
  public void updateUser() {
	  File f=new File(path);
	 given()
	     .log()
	     .all()
	     .body(path)
	     .contentType("application/json")
	     .spec(reqSpec)
	 .when()
	    .put(EndPoints.BASE_PATH_UPDATE)
	  .then()
	   .log()
	   .all()
	   .spec(reSpec);
  }
  
@Test
  public void register(){
	File f=new File(pathRegister);
	 given()
	     .log()
	     .all()
	     .body(pathRegister)
	    .contentType("application/json")
	     .spec(reqSpec)
	 .when()
	    .post(EndPoints.BASE_PATH_REGISTER_SUCESSFULL)
	  .then()
	   .log()
	   .all()
	   .statusCode(201);
  }


  
}
