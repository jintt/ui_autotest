package me.ele.opensite.util;

import com.aventstack.extentreports.ExtentReports;
import org.apache.commons.io.FileUtils;
import org.dom4j.Element;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestBase {
	
	private ParseXml px;
	private Map<String, String> commonMap;

	public WebDriver driver;

	@DataProvider
	public Object[][] providerMethod(Method method) {
		this.initPx();
		this.initCommonMap();

		String methodName = method.getName();
		List<Element> elements = px.getElementObjects("/*/" + methodName);
		Object[][] object = new Object[elements.size()][];
		for (int i = 0; i<elements.size(); i++) {
//			Object[] temp = new Object[]{px.getChildrenInfoByElement(elements.get(i))};
//			Object[] temp = new Object[]{this.getMergeMapData(px.getChildrenInfoByElement(elements.get(i)), commonMap)};
			Map<String, String> mergeCommon = this.getMergeMapData(px.getChildrenInfoByElement(elements.get(i)), commonMap);
			Map<String, String> mergeGlobal = this.getMergeMapData(mergeCommon, GlobalXml.global);

			object[i] = new Object[]{mergeGlobal};
		}

		return object;
	}

	private void initPx() {
		if (px == null) {
			px = new ParseXml("test-data/" + this.getClass().getSimpleName()  + ".xml");
		}
	}
	
	private void initCommonMap() {
		if (commonMap == null)
			commonMap = px.getChildrenInfoByElement(px.getElementObject("/*/common"));
	}
	
	private Map<String, String> getMergeMapData(Map<String, String> map1, Map<String, String> map2) {
		Iterator<String> it = map2.keySet().iterator();
		
		while(it.hasNext()) {
			String key = it.next();
			String value = map2.get(key);
			if(!map1.containsKey(key)) {
				map1.put(key, value);
			}
		}
		
		return map1;
	}

	public WebDriver getDriver() {
		return driver;
	}


	public void takeScreenShoot( String nameScreenshot)
			throws Exception {
//		WebDriver augmentedDriver = new Augmenter().augment(driver);
		File screenshot = ((TakesScreenshot)
				driver).getScreenshotAs(OutputType.FILE);

		String path = "screenShots/" + getFileName(nameScreenshot);
		FileUtils.copyFile(screenshot, new File("test-output/" + path));

		Reporter.log("<a href=" + path + " target='_blank' >" +
				this.getFileName(nameScreenshot) + "</a>");

		Reporter.log("<img src=" + path  + ">");
		Reporter.log("<img src=/opensite/test-output/" + path + ">");
	}

	private String getFileName(String nameTest) throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_hh.mm.ss");
		Date date = new Date();
		return dateFormat.format(date) + "_" + nameTest + ".png";
	}



}
