package com.generic.project.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.generic.project.util.UIAction;

import io.qameta.allure.Step;

public class OrderSummary {
	
	WebDriver driver;
	UIAction act;
	LandingPage landingpage;
    static Logger logger = Logger.getLogger(OrderSummary.class); 


	public OrderSummary(WebDriver driver) {
		logger.info("Inside OrderSummary constructor ");
		this.driver = driver;
		act = new UIAction(driver);
		PageFactory.initElements(driver, this);
	}

//		OrderSummary page object
	
		@FindBy(xpath = "//h1[@class='logo-store']")
		WebElement store_heading;
		@FindBy(xpath = "//div[@class='order-id-optional']/div")
		WebElement order_id_summary;
		@FindBy(xpath = "//div[@class='button-main show']")
		WebElement continue_button;
		
//		Select Payment page object
		
		@FindBy(xpath = "//span[@class='text-page-title']/p")
		WebElement payment_title;
		@FindBy(xpath = "//div[@id='payment-list']/div[1]/a[1]")
		WebElement credit_card_payment;
		@FindBy(xpath = "//div[@class='card-container'][1]/div[1]/input[1]")
		WebElement credit_card_number;
		@FindBy(xpath = "//div[@class='card-container'][1]/div[2]/input[1]")
		WebElement credit_card_expiry;
		@FindBy(xpath = "//div[@class='card-container'][1]/div[3]/input[1]")
		WebElement credit_card_cvv;
		@FindBy(xpath = "//a[@class='button-main-content']")
		WebElement pay_now;
		
//     3DS page object
		
		@FindBy(xpath = "//input[@id='PaRes']")
		WebElement enter_otp;
		@FindBy(xpath = "//button[@name='ok']")
		WebElement ok_button;
		@FindBy(xpath = "//*[contains(text(),'Transaction successful')]")
		WebElement successful_txn_msg;
		@FindBy(xpath = "//div[@class='text-failed text-bold']/span")
		WebElement failed_txn_msg;
		@FindBy(xpath = "//iframe[@id='snap-midtrans']")
		WebElement iFrame;
		
		@Step("Navigating to Payment")		
		public void goToPayment() {
			act.switchToFrame(iFrame);
			getOrderId();
			act.getElement(continue_button).click();
		}
		
		@Step("Retrieving order ID")		
		private void getOrderId() {
			String order_id = act.getElement(order_id_summary).getText();
			logger.info("Order Id is "+order_id);
		}
		
		@Step("Selecting Credit Card payment method")		
		public void selectPaymentMethod(String methodType) {
			if (methodType.equalsIgnoreCase("Credit Card")) {
			act.click(credit_card_payment);
			}
		}
		
		@Step("Entering Credit Card details")		
		public void enterCreditCardDetails(String card_number,String card_expiry,String card_cvv) {
			act.setData(card_number, credit_card_number);
			act.setData(card_expiry, credit_card_expiry);
			act.setData(card_cvv, credit_card_cvv);
			act.click(pay_now);
		}
		
		@Step("Entering OTP")		
		public void enterOtp(String otp) {
			driver.switchTo().frame(0);
			act.setData(otp, enter_otp);
			act.getElement(ok_button).click();
			driver.switchTo().parentFrame();
		}
}
