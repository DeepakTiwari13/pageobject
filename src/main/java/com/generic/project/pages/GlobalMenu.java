package com.generic.project.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.generic.project.util.UIAction;

public class GlobalMenu {
	
	WebDriver driver;
	UIAction act;
	ResourcesMenu rMenu;

	static Logger logger = Logger.getLogger(GlobalMenu.class); 

	public GlobalMenu(WebDriver driver){
		logger.info("Inside Global Menu constructor ");
		this.driver = driver;
		act =  new UIAction(driver);
		rMenu = new ResourcesMenu(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[text()='Solutions']")
	WebElement solutionsLink;
	@FindBy(xpath = "//a[text()='Resources']")
	WebElement resourceLink;
	@FindBy(xpath = "//a[text()='Company']")
	WebElement companyLink;
	
	public void openSolutionsMenu() {
		act.mouseHover(solutionsLink);
	}
	
	public ResourcesMenu openResourceMenu() {
		act.mouseHover(resourceLink);
		return rMenu;
	}
	
	public void openCompanyMenu() {
		act.mouseHover(companyLink);
	}
}
