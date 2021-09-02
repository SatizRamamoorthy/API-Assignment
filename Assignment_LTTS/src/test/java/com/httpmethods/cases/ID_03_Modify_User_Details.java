package com.httpmethods.cases;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.assignment.utility.Constants;
import com.assignment.utility.PayloadReader;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ID_03_Modify_User_Details extends RestBaseTest {
	
	String jsonBody, URL, jsonBody_modify;
	
	@Test
	public void modify_UserDetails() throws Exception {

		test = extent.createTest("Modify user details");

		// creating request payload
		jsonBody_modify = PayloadReader.getPayload_modify("Modify_UserDetails");

		Map<String, String> header = new HashMap<String, String>();

		header.put("Content-type", "application/json");

		test.info("Calling API to modify user details");

		// PUT API call
		Response response_modify = RestAssured.given().headers(header).body(jsonBody_modify).put(Constants.ModifyURL);

		Assert.assertEquals(response_modify.getStatusCode(), Constants.SUCCESS);

		createReport(test, jsonBody, response_modify);

		JSONObject root = new JSONObject(response_modify.asPrettyString());
		
		JSONObject Expectedroot = new JSONObject(jsonBody_modify);
		
		verifyDetails(root, Expectedroot);

		
		}
}
