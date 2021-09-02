package com.httpmethods.cases;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.assignment.utility.Constants;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ID_02_GET_User_Details extends RestBaseTest {
	
	String URL;
	
  @Test
  public void getUser_Details() throws Exception {
	  
	  	test = extent.createTest("Get User details from the endpoint");
		
		Map<String, String> header = new HashMap<String, String>();
		
		header.put("Content-type", "application/json");
		
		test.info("Calling API to get user details");
		
		//GET API call
		Response response = RestAssured.given().headers(header).get(Constants.GETURL);
		
		Assert.assertEquals(response.getStatusCode(), Constants.SUCCESS);
		
		//Log API details in report
		createReport(test, response,  URL);
		
		JSONObject root = new JSONObject(response.asPrettyString());
		
		//verifying user details
		verifyUserdetails(root);

	}
		
  
}