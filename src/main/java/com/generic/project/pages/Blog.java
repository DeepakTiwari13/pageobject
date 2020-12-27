package com.generic.project.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.generic.project.util.UIAction;

public class Blog {
	
	WebDriver driver;
	UIAction act;
    static Logger logger = Logger.getLogger(Blog.class); 
	
	public Blog(WebDriver driver) {
		logger.info("Inside Blog page constructor ");
		this.driver = driver;
		act = new UIAction(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@class='navbar-brand'][1]/img[1]")
	WebElement samaIcon;
	
	public void navigateToHomePage() {
		act.click(samaIcon);
   }
}
