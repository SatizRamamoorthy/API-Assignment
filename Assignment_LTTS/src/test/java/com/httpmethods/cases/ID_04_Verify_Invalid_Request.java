package com.httpmethods.cases;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.assignment.utility.Constants;
import com.assignment.utility.PayloadReader;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ID_04_Verify_Invalid_Request extends RestBaseTest {
	
	String jsonBody, URL;
	
	@Test
	public void verify_Invalid_Request() throws Exception {
		
		test = extent.createTest("Validate Invalid payload");
		
		//creating request payload
		jsonBody = PayloadReader.getPayload_create("Invalid_Payload");
		
		Map<String, String> header = new HashMap<String, String>();
		
		header.put("Content-type", "application/json");
		
		test.info("Calling API to create marvel characters");
		
		//Post API call
		Response response = RestAssured.given().headers(header).body(jsonBody).post(Constants.POSTURL);
		
		Assert.assertEquals(response.getStatusCode(), Constants.BAD);
		
		createReport(test, jsonBody, response);
		
	}

}