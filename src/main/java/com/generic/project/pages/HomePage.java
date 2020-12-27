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
    ResourcesMenu rMenu;
	
	public HomePage(WebDriver driver) {
		logger.info("Inside HomePage constructor ");
		this.driver = driver;
		act = new UIAction(driver);
	    rMenu = new ResourcesMenu(driver);
		PageFactory.initElements(driver, this);
	}

//	Home Page Object
	
	@FindBy(xpath = "//a[@class='navbar-brand'][1]/img[1]")
	WebElement samaIcon;
	@FindBy(xpath = "//a[text()='Solutions']")
	WebElement solutionsLink;
	@FindBy(xpath = "//a[text()='Resources']")
	WebElement resourceLink;
	@FindBy(xpath = "//a[text()='Company']")
	WebElement companyLink;
	@FindBy(xpath = "//div[@id='search-toggle']")
	WebElement searchIcon;
	@FindBy(xpath = "//a[@class='btn btn-lg btn-outline-primary--orange' and  @href='/get-started/'][1]")
	WebElement getStartedLink;
	@FindBy(xpath = "//a[@class = 'btn btn-lg btn-outline-primary--orange two-lines' and @href='https://saamaanalytics.com/']")
	WebElement saamaAnalyticsLink;
	@FindBy(xpath = "//div[@class='col-lg-7 col-12']/div/a[1]/span")
	WebElement learnMoreLink;
	@FindBy(xpath = "//div[@class='col-lg-7 col-12']/div/a[2]/span")
	WebElement explainerVideoLink;
//	@FindBy(xpath = "//div[@class='container-fluid']/div[2]/div[2]/a")
//	WebElement outSourcingLink;
	@FindBy(xpath = "//h5[@class='postCard-info-title']")
	WebElement outSourcingLink;
	@FindBy(xpath = "//p[text()='Operations Insights']")
	WebElement operationsInsightsLink;
	@FindBy(xpath = "//p[text()='Clinical Insights']")
	WebElement clinicalInsightsLink;
	@FindBy(xpath = "//div[@class='container-fluid']/div[2]/div[2]/a")
	WebElement riskBasedMonitoringLink;
	@FindBy(xpath = "//div[@class='col-lg-4 col-md-6 col-12'][1]//a[@class='caseStudyBlock']")
	WebElement clinOpsSuccessStoryLink;
	@FindBy(xpath = "//div[@class='col-lg-4 col-md-6 col-12'][2]//a[@class='caseStudyBlock']")
	WebElement croLink;
	@FindBy(xpath = "//div[@class='col-lg-4 col-md-6 col-12'][3]//a[@class='caseStudyBlock']")
	WebElement dataTransParencyLink;
	
	public ResourcesMenu openResourceMenu() {
		act.mouseHover(resourceLink);
		return rMenu;
	}
	
	public boolean allUiElementsOnHomePage() {
		try {
				act.isElementPresent(solutionsLink);
				act.isElementPresent(companyLink);
				act.isElementPresent(getStartedLink);
				act.isElementPresent(saamaAnalyticsLink);
				act.isElementPresent(learnMoreLink);
				act.isElementPresent(explainerVideoLink);
				return true;
		} catch(Exception e) {
			    e.getSuppressed();
			    return false;
		}
	}
}
