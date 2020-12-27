package com.generic.project.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.generic.project.util.UIAction;

public class HomePage {
	
	WebDriver driver;
	UIAction act;
    static Logger logger = Logger.getLogger(HomePage.class); 
	
	public HomePage(WebDriver driver) {
		logger.info("Inside HomePage constructor ");
		this.driver = driver;
		act = new UIAction(driver);
		PageFactory.initElements(driver, this);
	}

//	Home Page Object
	@FindBy(xpath = "")
	WebElement anylink;
}
