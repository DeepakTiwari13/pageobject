package com.generic.project.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.generic.project.util.UIAction;
import io.qameta.allure.Step;

public class ShoppingCartPage {
	
	WebDriver driver;
	UIAction act;
	OrderSummary ordSummary;
    static Logger logger = Logger.getLogger(ShoppingCartPage.class); 


	public ShoppingCartPage(WebDriver driver) {
		logger.info("Inside ShoppingCartPage constructor ");
		this.driver = driver;
		ordSummary = new OrderSummary(driver);
		act = new UIAction(driver);
		PageFactory.initElements(driver, this);
	}

//		Shoping Cart Page Object
	
		@FindBy(xpath = "//div[@class='cart-head']/span[1]")
		WebElement shopping_cart_header;
		@FindBy(xpath = "//table[1]/tbody/tr[1]/td[3]/input[1]")
		WebElement mid_tran_pillow_amount;
		@FindBy(xpath = "//table[1]/tbody/tr[2][@class='total']/td[3]")
		WebElement mid_tran_pillow_total;
		
// 	Customer Details Page Object
		
		@FindBy(xpath = "//div[@class='cart-section'][2]/table/tbody/tr[1]/td[2]/input[1]")
		WebElement customer_name;
		@FindBy(xpath = "//div[@class='cart-section'][2]/table/tbody/tr[2]/td[2]/input[1]")
		WebElement customer_email;
		@FindBy(xpath = "//div[@class='cart-section'][2]/table/tbody/tr[3]/td[2]/input[1]")
		WebElement customer_phone_number;
		@FindBy(xpath = "//div[@class='cart-section'][2]/table/tbody/tr[4]/td[2]/input[1]")
		WebElement customer_city;
		@FindBy(xpath = "//div[@class='cart-section'][2]/table/tbody/tr[5]/td[2]/textarea")
		WebElement customer_address;
		@FindBy(xpath = "//div[@class='cart-section'][2]/table/tbody/tr[6]/td[2]/input[1]")
		WebElement customer_postal_code;
		@FindBy(xpath = "//div[@class='cart-checkout']")
		WebElement customer_checkout_button;
		
		@Step("Navigating to Order summary")		
		public OrderSummary naviagteToOrderSummary() {
			act.click(customer_checkout_button);
			return ordSummary;
		}
		
		@Step("Entering customer detail")		
		public void fillInCustomerDetails(String amount,String name,String email,String phone_number,
				String city,String address, String postal_code ) {
			act.setData(amount, mid_tran_pillow_amount);
			act.setData(name, customer_name);
			act.setData(email, customer_email);
			act.setData(phone_number, customer_phone_number);
			act.setData(city, customer_city);
			act.setData(address, customer_address);
			act.setData(postal_code, customer_postal_code);
		}
}
