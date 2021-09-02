package com.httpmethods.cases;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.assignment.utility.Constants;
import com.assignment.utility.PayloadReader;
import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ID_01_Create_Avengers_Characters extends RestBaseTest {
	
	String jsonBody;
	
	@Test
	public void createCharacter_Names() throws Exception {
		
		test = extent.createTest("Create Characters");
		
		//creating request payload
		jsonBody = PayloadReader.getPayload_create("Create_Characters");
		
		Map<String, String> header = new HashMap<String, String>();
		
		header.put("Content-type", "application/json");
	
		test.log(Status.INFO, "Calling API to create marvel characters");
		
		//Post API call
		Response response = RestAssured.given().headers(header).body(jsonBody).post(Constants.POSTURL);
		
		Assert.assertEquals(response.getStatusCode(), Constants.CREATED);
		
		createReport(test, jsonBody, response);
		
		JSONObject root = new JSONObject(response.asPrettyString());
		
		String id = root.get("id").toString();
		
		//validating id value is present in the response
		validateNodeValuePresence(id, "character.id");
		
	}

}