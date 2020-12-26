package com.generic.project.ui.test_cases;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.generic.project.pages.BasicClass;
import com.generic.project.util.OracleDB;
import com.generic.project.util.ReadFile;
import com.generic.project.util.SingleTon;
import com.generic.project.util.UIAction;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
@Listeners(com.generic.project.util.ListenerFile.class)			


public class HomePageTest extends BasicClass{
	ReadFile read;
	WebDriver driver;
	UIAction act ;
	OracleDB db;
	Connection conn;
	ResultSet rs;
	Statement stmt;
	PreparedStatement pStmt;
    static Logger logger = Logger.getLogger(HomePageTest.class); 

	@BeforeTest
	public void setUp() {
		driver  = launch_browser("Chrome");
		act = new UIAction(driver);
		db = new OracleDB();
		read = new ReadFile();
	//	db.makeConnectionWithDB();
	}
	@Link("https://www.atlassian.com/software/jira1")
	@Description("Description for assignment test 1")
	@Test(priority=1)
	public void testOne() {
		logger.info("Inside test one ");
		SingleTon sTon1 = SingleTon.getInstance();
		int hCode1 = sTon1.hashCode();
		logger.info("Hash code for Singleton class object one "+hCode1);
		act.attachScreenShotToReport();
	}
	
	@Link("https://www.atlassian.com/software/jira2")
	@Description("Description for assignment test 2")
	@Test(priority=2)
	public void testTwo() {
		logger.info("Inside test two ");
		SingleTon sTon2 = SingleTon.getInstance();
		int hCode2 = sTon2.hashCode();
		logger.info("Hash code for Singleton class object two "+hCode2);
		act.attachScreenShotToReport();
	//	db.getDBResultUsingPreparedStatement(conn, pStmt, rs, query, val1, val2);
	}
	
	@Link("https://www.atlassian.com/software/jira3")
	@Description("Description for assignment test 3")
	@Test(priority=3)
	public void testThree() {
		logger.info("Inside test three ");
		SingleTon sTon3 = SingleTon.getInstance();
		int hCode3 = sTon3.hashCode();
		logger.info("Hash code for Singleton class object three "+hCode3);
		act.attachScreenShotToReport();
	}
	
	@Link("https://www.atlassian.com/software/jira4")
	@Description("Description for assignment test 4")
	@Test(priority=4)
	public void testFour() {
		logger.info("Inside test four ");
		SingleTon sTon4 = SingleTon.getInstance();
		int hCode4 = sTon4.hashCode();
		logger.info("Hash code for Singleton class object three "+hCode4);
		act.attachScreenShotToReport();
	}
	
	@Link("https://www.atlassian.com/software/jira5")
	@Description("Description for parameterized test 5")
	@Test(priority=5,dataProvider = "csvData")
	public void testFive(Object getData) {
		logger.info("Inside parameterized test five ");
		logger.info("Data from csv file"+getData);
	}
	
	@DataProvider(name = "csvData")
	public Object[] sendData() throws FileNotFoundException {
		return read.readCsvLineByLine("home_page");
	}
	@AfterTest
	public void cleanUp() {
		db.closeDbConnection(conn, stmt, rs, pStmt);
		logout();		
	}
}
