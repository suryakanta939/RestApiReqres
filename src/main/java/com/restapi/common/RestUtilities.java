package com.restapi.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.restapi.constant.Path;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestUtilities {
	
	static RequestSpecBuilder REQ_BUILDER;
	static RequestSpecification REQ_SPEC;
	static ResponseSpecBuilder RESP_BUILDER;
	static ResponseSpecification RESP_SPEC;
	
	/**
	 * this function is to set the request with uri and path
	 * */
	
	public static RequestSpecification getRequestSpecification(){
		REQ_BUILDER=new RequestSpecBuilder();
		REQ_BUILDER.setBaseUri(Path.BASE_URI);
		REQ_BUILDER.setBasePath(Path.BASE_PATH);
		return REQ_SPEC=REQ_BUILDER.build();
	}
	
	/**
	 * this function is to set the response specification
	 * */
	
	public static ResponseSpecification getResponseSpecification(){
		RESP_BUILDER=new ResponseSpecBuilder();
		RESP_BUILDER.expectStatusCode(200);
		RESP_BUILDER.expectResponseTime(lessThan(4L),TimeUnit.SECONDS);
		return RESP_SPEC=RESP_BUILDER.build();
	}
	
	/**
	 * This function is to set the query param in request
	 * 
	 * */
	
	public static RequestSpecification createQueryParam(RequestSpecification res,String param,String value){
		return res.queryParam(param, value);
	}
	
	/**
	 * This function is to add multiple query param to the request
	 * */
	
	public static RequestSpecification createQueryParam(RequestSpecification res,Map<String,String> queryParm){
		return res.queryParams(queryParm);
	}
	
	/**
	 * this function is to create path param
	 * 
	 * */
	
	public static RequestSpecification createPathParam(RequestSpecification res,String param,String value){
		return res.pathParam(param, value);
	}
	
	/**
	 * This function is to create path param if there is more than one path to add
	 * */
	
	public static RequestSpecification createPathParam(RequestSpecification res,Map<String,String> pathParm){
		return res.pathParams(pathParm);
	}
	
	/**
	 * this function is get the json path
	 * */
	
	public static JsonPath getJsonPath(Response res){
		String resBody=res.asString();
		JsonPath jsonpath=new JsonPath(resBody);
		return jsonpath;
	}
	
	/**
	 * This function to get the xml path
	 * */
	
	public static XmlPath getXmlPath(Response res){
		String resBody=res.asString();
		XmlPath xmlpath=new XmlPath(resBody);
		return xmlpath;
	}
	/**
	 * This function is to convert the json file to string
	 * 
	 * */
	 public static String generateStringFromResource(String path) throws IOException {

		    return new String(Files.readAllBytes(Paths.get(path)));

		}
}
