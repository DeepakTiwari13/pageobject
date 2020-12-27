package com.generic.project.api.test_cases;

import org.apache.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
@Listeners(com.generic.project.util.ListenerFile.class)			

public class APITest {

	static Logger logger = Logger.getLogger(APITest.class);
	
	
	@Link("https://www.atlassian.com/software/jira6")
	@Description("Description for assignment test 6")
	@Test(priority=1,invocationCount=11)
	public void testApiOne() {
		logger.info("Inside api test case one");
	}
}
