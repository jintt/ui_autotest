package me.ele.opensite.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumDriver {
	
	private WebDriver driver;
	
	public SeleniumDriver() {
		initialDriver();
	}
	
	private void initialDriver() {
		if("firefox".equals(Config.browser)) {
			driver = new FirefoxDriver();
		}
		else if("chrome".equals(Config.browser)) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--user-agent=iphone");
			options.addArguments("--headless");
			//options.addArguments("--user-data-dir=/Users/jintingting/Library/Application Support/Google/Chrome/Default");
			//System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver");
			driver = new ChromeDriver(options);
		}
		else {
			Log.logError("浏览器匹配错误：" + Config.browser);
		}
		//driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Config.waitTime, TimeUnit.SECONDS);
	}
	
	public WebDriver getDriver() {
		return driver;
	}

}
