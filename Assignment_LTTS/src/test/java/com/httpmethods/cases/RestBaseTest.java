package com.httpmethods.cases;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.assignment.utility.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.restassured.response.Response;

public class RestBaseTest {
	
	ExtentTest test;
	ExtentReports extent;
	ThreadLocal<ExtentTest> extentTest;
	
	@BeforeClass
	public void setUp() {
		extent = ExtentReporterNG.getReportObject();
		extentTest = new ThreadLocal<ExtentTest>();
	}
	
	@AfterClass
	public void tearDown() {
		extent.flush();
	}

	public void validateNodeValuePresence(String actual, String NodeName) {
		if (actual.length() > 0) {
			Assert.assertTrue(actual.length() > 0, NodeName + " has value present ");
		} else {
			Assert.assertTrue(actual.length() > 0, NodeName + " has No value present ");
		}
	}
	
	public static void createReport(ExtentTest extendTest, String jsonBody , Response response) {
		
		extendTest.log(Status.PASS, response.header("Content-type"));
		
		extendTest.log(Status.PASS, "Request :\n\n" + jsonBody);
		
		extendTest.log(Status.PASS, "Response :\n\n" + response.asPrettyString());
		
	}
	
	public void createReport(ExtentTest extendTest, Response response, String Endpoint) {
		
		extendTest.log(Status.PASS, response.header("Content-type"));
		
		extendTest.log(Status.PASS, "Endpoint :\n\n" + Endpoint);
		
		extendTest.log(Status.PASS, "Response :\n\n" + response.asPrettyString());
		
	}
	
	public boolean validateOptionalNodePresence(boolean isPresent, String nodeName) {
		if (isPresent) {
			Assert.assertTrue(true, nodeName + " is optional and present ");
			return true;
		} else {
			Assert.assertTrue(false, nodeName + " is optional and is not present ");
			return false;
		}
	}
	
	public void verifyUserdetails(JSONObject root) {
		
		JSONArray users = root.getJSONArray("data");
		
		for (int i = 0; i < users.length(); i++) {
			JSONObject user = users.getJSONObject(i);
			if (validateOptionalNodePresence(user.has("id"), "user.id")) {
				validateNodeValuePresence(user.optString("id"), "user.id.value");
			}
			validateNodeValuePresence(user.optString("first_name"), "user.first_name");
			validateNodeValuePresence(user.optString("last_name"), "user.last_name");
		}
	}	
	
	 public void verifyDetails(JSONObject root, JSONObject expectedroot) {
		 
		 if(root.has("updatedAt")) {
		
			int actualid =root.getInt("id");
			int Expectedid =expectedroot.getInt("id");
			
			String Actualname =root.getString("name");
			String Expectedname =root.getString("name");
			
			Assert.assertEquals(actualid, Expectedid);
			Assert.assertEquals(Actualname, Expectedname);
		
	}
}

}
