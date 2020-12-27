package com.generic.project.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.generic.project.util.UIAction;

public class ResourcesMenu {
	
	WebDriver driver;
	UIAction act;
    CaseStudy cStudy ;
    WhitePapers wPapers;
    Blog blog;
    static Logger logger = Logger.getLogger(ResourcesMenu.class); 
	
	public ResourcesMenu(WebDriver driver) {
		logger.info("Inside Resources Menu constructor ");
		this.driver = driver;
		act = new UIAction(driver);
		cStudy = new CaseStudy(driver);
		wPapers = new WhitePapers(driver);
		blog = new Blog(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[text()='Resources']")
	WebElement resourceLink;
	@FindBy(xpath = "//a[@href='/case-studies']")
	WebElement caseStudyLink;
	@FindBy(xpath = "//a[@href='/whitepapers']")
	WebElement whitePaperLink;
	@FindBy(xpath = "//a[@href='/blog/']")
	WebElement blogLink;
	
	public CaseStudy navigateToCaseStudy() {
	    act.click(caseStudyLink);
	    return cStudy;
	}
	
	public  WhitePapers navigateToWhitePapers() {
	    act.click(whitePaperLink);
	    return wPapers;
	}
	
	public   Blog navigateToBlog() {
	    act.click(blogLink);
	    return blog;
	}
	
}
