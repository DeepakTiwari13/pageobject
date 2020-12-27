package com.generic.project.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.io.Files;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

public class UIAction {
	WebDriver driver;
    static Logger logger = Logger.getLogger(UIAction.class); 

	public UIAction(WebDriver driver) {
		logger.debug("Inside UIAction class constructor");
		this.driver = driver;
	}

	@Step("Checking element is present or not {0} ")
	public boolean isElementPresent(String locator) {
		int size;

		if (locator.contains("xpath")) {
			size = driver.findElements(By.xpath(locator)).size();
			if (size == 0) {
				return false;
			} else
				return true;
		} else if (locator.contains("linktext")) {
			size = driver.findElements(By.linkText(locator)).size();
			if (size == 0) {
				return false;
			} else
				return true;
		} else if (locator.contains("classname")) {
			size = driver.findElements(By.className(locator)).size();
			if (size == 0) {
				return false;
			} else
				return true;
		} else if (locator.contains("tagname")) {
			size = driver.findElements(By.tagName(locator)).size();
			if (size == 0) {
				return false;
			} else
				return true;
		} else
			return false;
	}
	
	@Step("Checking element is present or not {0} ")
	public boolean isElementPresent(WebElement element) {
		   if(element!=null) {
		    	if(getElement(element).isDisplayed() && getElement(element).isEnabled()) {
			    return true;
		    }else 
		             return false;
		    } else 
			        return false;
	 }

	public WebElement getElement(WebElement ele) {
		try {
			logger.debug("Inside get element function");
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(ele));
			highlightElement(ele);
		} catch (Exception e) {
			takeScreenshot();
		}
		return ele;
	}
	
	@Step("Taking screenshot ")
	public void takeScreenshot() {
		try {
			logger.debug("Taking screenshot");
			TakesScreenshot photo = ((TakesScreenshot) driver);
			File file = photo.getScreenshotAs(OutputType.FILE);
			File pathTOCopy = new File(System.getProperty("user.dir") + File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"Screenshotdirectory"+File.separator+getDateTime()+".jpg");
			Files.copy(file, pathTOCopy);
		} catch (IOException e) {
			logger.error("Failed to take screenshot");
			e.printStackTrace();
		}
	}
	
	@Step("Clicking on web element {} ")
	public void click(WebElement element) {
		logger.debug("Clicking on "+element);
		WebElement e =getElement(element);
		e.click();
	}
	
	@Step("Setting data {0} on web element {1} ")
	public void setData(String data,WebElement element) {
		logger.debug("sending "+data+" on "+element);
		getElement(element).sendKeys(data);
	}

	public void highlightElement(WebElement element) {
		logger.debug("Highlighting element "+element);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}
	
	@Step("Scrolling page till bottom ")
	public boolean goTOPageBottom() {
		logger.debug("Scrolling page till bottom");
	   JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		return true;
	}
	
	@Step("Scrolling up till coordinate ")
	public boolean scrollUpTillCoordinate() {
		try {
			logger.debug("Scrolling page up till defined coordinate");
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,-250)");
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Step("Scrolling down till coordinate ")
	public boolean scrollDownTillCoordinate() {
		try {
			logger.debug("Scrolling page down till defined coordinate");
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,250)");
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Step("Hovering the mouse over {}")
	public void mouseHover(WebElement ele) {
        Actions actions = new Actions(driver);
        WebElement elementToHover = getElement(ele);
    	actions.moveToElement(elementToHover).perform();
	}
	
	@Step("Returning year month date with hour minute second ")
	public String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	@Attachment
	 public byte[] attachScreenShotToReport(){
	    logger.info("Attaching screenshot to report ");
	    TakesScreenshot photo = ((TakesScreenshot) driver);
		byte[] file = photo.getScreenshotAs(OutputType.BYTES);
		return file;
	    }
}
