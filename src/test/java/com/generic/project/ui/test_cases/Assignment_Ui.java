package com.generic.project.ui.test_cases;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.generic.project.pages.BasicClass;
import com.generic.project.pages.LandingPage;
import com.generic.project.pages.OrderSummary;
import com.generic.project.pages.ShoppingCartPage;
import com.generic.project.util.OracleDB;
import com.generic.project.util.ReadFile;
import com.generic.project.util.UIAction;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
//@Listeners(com.generic.project.util.ListenerFile.class)			

public class Assignment_Ui extends BasicClass {
	
	ReadFile read;
	WebDriver driver;
	UIAction act ;
	OracleDB db;
	Connection conn;
	ResultSet rs;
	Statement stmt;
	PreparedStatement pStmt;
	LandingPage landingpage ;
    static Logger logger = Logger.getLogger(Assignment_Ui.class); 

    @BeforeTest
	public void setUp() {
		driver  = launch_browser("Chrome");
		landingpage = new LandingPage(driver);
		act = new UIAction(driver);
		db = new OracleDB();
		read = new ReadFile();
//		db.makeConnectionWithDB();
	}
	@Link("https://www.atlassian.com/software/jira1")
	@Description("Description for assignment test 1")
	@Test(priority=1,dataProvider="transactionData")
	public void verifyTransactionThroughCreditCard(String txn_amt, String cust_name, String cust_email, String cust_mob,
			String cust_city, String cust_address, String cust_pin, String cust_pay_method, String cust_credit_card,
			String card_expiry, String card_cvv, String card_otp) throws IOException {
		logger.info("Inside test one ");
//		read.readCsvLineByLine("home_page");
		ShoppingCartPage cart = landingpage.navigateToShoppingCart();
		cart.fillInCustomerDetails(txn_amt, cust_name, cust_email, cust_mob,cust_city, cust_address, cust_pin); 
		OrderSummary orderSummary  = cart.naviagteToOrderSummary(); 
		orderSummary.goToPayment();
		orderSummary.selectPaymentMethod(cust_pay_method);
		orderSummary.enterCreditCardDetails(cust_credit_card,card_expiry,card_cvv);
		if (cust_credit_card.contains("14")) {
			logger.info("Positive data set assertion ");
			orderSummary.enterOtp(card_otp);
			Assert.assertTrue(landingpage.verifyTransaction());
		} else if (cust_credit_card.contains("13")) {
			logger.info("Negative data set assertion ");
	//		orderSummary.enterOtp(card_otp);
			Assert.assertFalse(landingpage.verifyIfTransactionFailed());
		}
		act.attachScreenShotToReport();
	}
	
		@AfterTest
		public void cleanUp() {
		//	db.closeDbConnection(conn, stmt, rs, pStmt);
			logout();		
	}
	
	  @DataProvider(name="transactionData")
	    public Object[][] getDataFromDataprovider(){
	    return new Object[][] 
	    	{
	            { "100", "test1","test1@email.com", "0000055555","london","Lane1","000001","Credit Card","4811111111111114","1221","123","112233"},
	            { "200", "test2","test2@email.com", "0000066666","london","Lane2","000002","Credit Card","4811111111111114","1221","123","112233"},
	            { "300", "test3","test3@email.com", "0000077777","london","Lane3","000003","Credit Card","4911111111111113","0220","123","112233"}
       
	        };
	    }
}
