package com.generic.project.pages;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BasicClass {

    static Logger logger = Logger.getLogger(BasicClass.class); 
	public WebDriver driver;
	String downloadPath = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "test" + File.separator + "resources" + File.separator +"Download";

	public WebDriver launch_browser(String browserType) {

		if (browserType.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "src"
					+ File.separator + "test" + File.separator + "resources" + File.separator +"Driver"+File.separator +"chromedriver");
			Proxy proxy = new Proxy(); 
			proxy.setHttpProxy("url:portno"); 
			proxy.setSslProxy("url:portno"); 
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadPath);
			ChromeOptions options = new ChromeOptions();
		    options.addArguments("--enable-javascript");
			options.setExperimentalOption("prefs", chromePrefs);
			DesiredCapabilities cap = DesiredCapabilities.chrome();
	//		cap.setCapability("proxy", proxy); 
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			logger.info("Launching chrome browser");
		    driver = new ChromeDriver(cap);
			login();
		}
		else if (browserType.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "src"
					+ File.separator + "test" + File.separator + "resources" + File.separator +"Driver"+File.separator + "geckodriver");
			logger.info("Launching Firefox browser");
			driver = new FirefoxDriver();
			login();
		}
		else if (browserType.equalsIgnoreCase("Internet")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + File.separator + "src"
					+ File.separator + "test" + File.separator + "resources" + File.separator + "Driver"+File.separator +"IEDriverServer.exe");
			logger.info("Launching internetexplorer browser");
	//    	driver = new InternetExplorerDriver();
	//		login();
		} else {
			logger.info("Launching default Chrome browser");
			driver = new ChromeDriver();
		}
		return driver;
	}

	public WebDriver launch_browser_on_grid(String browserType) throws MalformedURLException {
		if (browserType.equalsIgnoreCase("Chrome")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
			String Node = "http://IP1:Port1/wd/hub";
			driver = new RemoteWebDriver(new URL(Node), cap);
			login();
		} else if (browserType.equalsIgnoreCase("Firefox")) {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");
			String Node = "http://IP2:Port2/wd/hub";
			driver = new RemoteWebDriver(new URL(Node), cap);
			login();
		} else if (browserType.equalsIgnoreCase("Ie")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setBrowserName("ie");
			String Node = "http://IP3:Port3/wd/hub";
			driver = new RemoteWebDriver(new URL(Node), cap);
			login();
		} else {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
			String Node = "http://IP1:Port1/wd/hub";
			driver = new RemoteWebDriver(new URL(Node), cap);
			login();
		}
		return driver;
	}
	public void login() {
		logger.info("Inside login function");
		driver.manage().window().maximize();
	}

	public void logout() {
		logger.info("Inside logout function");
		if (!(driver == null)) {
			driver.quit();
		}
	}
}
