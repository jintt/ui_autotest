package me.ele.opensite.util;

import org.ho.yaml.Yaml;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Locator {
	private String yamlfile;
	
	private WebDriver driver;
	
	private Map<String,Map<String,String>> ml;
	
	public Locator(WebDriver driver, String yamlfile) {
		this.driver = driver;
		
		this.yamlfile = yamlfile;
		this.getYamlFile();
	}
	
	@SuppressWarnings("unchecked")
	public void getYamlFile() {
		File f = new File("locator/" + yamlfile + ".yaml");
		
		try {
			ml = Yaml.loadType(new FileInputStream(f.getAbsolutePath()), HashMap.class);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private By getBy(String type, String value) {
		By by = null;
		if("id".equals(type)) {
			by = By.id(value);
		}
		else if("xpath".equals(type)) {
			by = By.xpath(value);
		}
		return by;	
	}

	public WebElement getElement(String key) {
		return this.getLocator(key, null, true);
	}
	
	public WebElement getElementNoWait(String key) {
		return this.getLocator(key, null, false);
	}
	
	public WebElement getElement(String key, String[] replace) {
		return this.getLocator(key, replace, true);
	}
	
	public WebElement getElementNoWait(String key, String[] replace) {
		return this.getLocator(key, replace, false);
	}
	
	
	private WebElement waitForElement(final By by) {
		WebElement element = null;
		int waitTime = Config.waitTime;
		
		try {
			element = new WebDriverWait(driver, waitTime).until(new ExpectedCondition<WebElement>() {				
				@Override
				public WebElement apply(WebDriver d) {
					return d.findElement(by);
				}
			});
		} catch (Exception e) {
			Log.logError(by.toString() + "is not exist until " + waitTime);
		}
		
		return element;
	}
	
	private boolean waitElementToBeDisplayed(final WebElement element) {
		boolean wait = false;
		if (element != null) {
		
			try {
				wait = new WebDriverWait(driver, Config.waitTime).until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						return element.isDisplayed();
					}
				});
			} catch (Exception e) {
				Log.logError(element.toString() + "is not displayed");
			}			
		
		}
		return wait;
	}
	
	public boolean waitElementToBeNonDisplayed(final WebElement element) {
		boolean wait = false;
		if (element != null) {
		
			try {
				wait = new WebDriverWait(driver, Config.waitTime).until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						return !element.isDisplayed();
					}
				});
			} catch (Exception e) {
				Log.logError(element.toString() + "is also displayed");
			}			
		
		}
		return wait;
	}

	private WebElement getLocator(String key, String[] replace, boolean wait) {
		WebElement element = null;
		if(ml.containsKey(key)) {
			Map<String, String> m = ml.get(key);
			String type = m.get("type");
			String value = m.get("value");
			
			if (replace != null) {
				value = this.getLocatorString(value, replace);
			}
			By by = this.getBy(type, value);
			
			if(wait) {
				element = this.waitForElement(by);
				boolean flag = this.waitElementToBeDisplayed(element);
				if(!flag) {
					element = null;
				}
			}
			else {
				element = driver.findElement(by);
			}
		}
		else {
			Log.logError(key + "is not exist in " + yamlfile +".yaml");
			
		}
		
		return element;
	}
	
	
	private String getLocatorString(String locatorString, String[] ss) {
		for(String s : ss) {
			locatorString = locatorString.replaceFirst("%s", s);
		}
		return locatorString;
	}
	
	public void setLocatorVariableValue(String variable, String value) {
		if(ml != null) {
			Set<String> keys = ml.keySet();
			for (String key :keys) {
				String v = ml.get(key).get("value").replace("%" + variable + "%", value);
				ml.get(key).put("value", v);
			}
		}
	}

	public WebDriver getDriver() {
		return driver;
	}
}
