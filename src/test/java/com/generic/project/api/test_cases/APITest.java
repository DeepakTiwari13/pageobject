package com.generic.project.api.test_cases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.generic.project.util.FileValidator;
import com.generic.project.util.OracleDB;
import com.generic.projects.apis.ApiData;
import com.generic.projects.apis.RegresstionTest;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
@Listeners(com.generic.project.util.ListenerFile.class)			

public class APITest {

	static Logger logger = Logger.getLogger(APITest.class);
	HashMap<Integer, HashMap<String, String>> outPut;
	Map<String, Map<String, String>> testResponse;
	JSONObject value;
	OracleDB db;
	Connection conn;
	ResultSet rs;
	Statement stmt;
	PreparedStatement pStmt;
	RegresstionTest rTest;
	ApiData testData;
	FileValidator fValidator;

	@BeforeTest
	public void setUp() {
		db = new OracleDB();
		conn = db.makeConnectionWithDB();
	}
		
	@Link("https://www.atlassian.com/software/jira1")
	@Description("Description for assignment API test 1")
	@Test(priority=1,invocationCount=1)
	public void verifyTestOne() throws SQLException {
		logger.info("Inside api test case one");
		outPut = db.getDBResultUsingSelectQuery(conn, stmt, rs,testData.getDataInSequence01());
		testResponse = rTest.sendRequest(outPut.get(1).get("any_number"));
		String key = (String) testResponse.entrySet().toArray()[0];

		
		if(testResponse.get("response").containsKey("200")) {
				if (fValidator.isJason(testResponse.get("response").get("200")))  {
							value = fValidator.getJason(testResponse.get("response").get("200"));
							Assert.assertEquals("	",value.get("mid"));
							Assert.assertEquals("	",value.get("message"));
		} 		else {
				logger.info("Response is not Json");
		}
		} else
				logger.info("Apply different set of assertion for response code other than 200 ");
				if (fValidator.isJason(testResponse.get("response").get(key)))  {
							value = fValidator.getJason(testResponse.get("response").get(key));
							Assert.assertEquals("	",value.get("mid"));
							Assert.assertEquals("	",value.get("message"));
				} else {
					logger.info("Response is not Json");
				}
		}
}

