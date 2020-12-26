package com.generic.project.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.generic.project.pages.BasicClass;

import io.qameta.allure.Attachment;

public class ListenerFile extends BasicClass implements ITestListener {
	
	WebDriver driver;
    static Logger logger = Logger.getLogger(ListenerFile.class); 
     
    @Override		
    public void onStart(ITestContext result) {					
    	logger.info("The name of the testcase starting is :" + result.getName());
    }	
    
    @Override		
    public void onFinish(ITestContext result) {					
    	logger.info("The name of the test suite  executed is :" + result.getName());
    }	
    
    @Override		
    public void onTestSuccess(ITestResult result) {					
    	logger.info("The name of the testcase passed is :" + result.getName());
   }

    @Override
	public void onTestFailure(ITestResult result) {
    	logger.info("The name of the testcase failed is :" + result.getName());
    	ITestContext iT = result.getTestContext();
 	}

   @Override
	public void onTestStart(ITestResult result) {
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
 }
