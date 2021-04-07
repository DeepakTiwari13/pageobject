package com.generic.project.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.generic.project.util.Configuration;
import com.generic.project.util.UIAction;

import io.qameta.allure.Step;

public class LandingPage {
	
	WebDriver driver;
	UIAction act;
	ShoppingCartPage sCartPage;
    static Logger logger = Logger.getLogger(LandingPage.class); 

	public LandingPage(WebDriver driver) {
		logger.info("Inside LandingPage constructor ");
		this.driver = driver;
		sCartPage = new ShoppingCartPage(driver);
		act = new UIAction(driver);
		PageFactory.initElements(driver, this);
	}

//		Landing page object
	
		@FindBy(xpath = "//div[@class='title']")
		WebElement midtran_text;
		@FindBy(xpath = "//div[@class='desc-cta']")
		WebElement snap_text;
		@FindBy(xpath = "//a[@class='btn buy']")
		WebElement button_buy_now;
		@FindBy(xpath = "//div[@class='trans-status trans-success']")
		WebElement is_txn_successful;
		
		@Step("Navigating to Shopping cart page")		
		public ShoppingCartPage navigateToShoppingCart() {
			act.getElement(button_buy_now).click();
			return sCartPage;
		}
		
		@Step("Verifying element for successful transaction")		
		public boolean verifyTransaction() {
			return act.isElementPresent(is_txn_successful);
		}
		
		@Step("Verifying element for failed transaction")		
		public boolean verifyIfTransactionFailed() {
			int s =driver.findElements(By.xpath(Configuration.getInstance().getProperty("successful_txn_msg"))).size();
			if (s==0) {
				return false;
			} else
				return true;
		}
		
}
