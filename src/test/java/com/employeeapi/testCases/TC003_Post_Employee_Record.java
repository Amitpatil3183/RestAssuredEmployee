

package com.employeeapi.testCases;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_Post_Employee_Record extends TestBase{
  
	RequestSpecification httpRequest;
	Response response;
	
	
	@BeforeClass @Parameters({"EmpName","EmpSalary","EmpAge"})
	public void createEmployee(String EmpName,String EmpSalary,String EmpAge) throws InterruptedException
	{
		logger.info("*********Started TC003_Post_Employee_Record **********");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();

		// JSONObject is a class that represents a simple JSON. We can add Key-Value pairs using the put method
		//{"name":"John123X","salary":"123","age":"23"}
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", EmpName); // Cast
		requestParams.put("salary", EmpSalary);
		requestParams.put("age", EmpAge);
		
		// Add a header stating the Request body is a JSON
		httpRequest.header("Content-Type", "application/json");

		// Add the Json to the body of the request
		httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.POST, "/create");
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(EmpName), true);
		Assert.assertEquals(responseBody.contains(EmpSalary), true);
		Assert.assertEquals(responseBody.contains(EmpAge), true); 
		 
		
		Thread.sleep(5000);

	}
		
	
	@Test
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode(); // Getting status code
		Assert.assertEquals(statusCode, 200);
	}
		
	@Test
	void checkstatusLine()
	{
		String statusLine = response.getStatusLine(); // Gettng status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
	@Test
	void checkContentType()
	{
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}

	@Test
	void checkserverType()
	{
		String serverType = response.header("Server");
		Assert.assertEquals(serverType, "Apache");
	}

	@Test
	void checkcontentEncoding()
	{
		String contentEncoding = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding, "gzip");

	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC003_Post_Employee_Record **********");
	}

}