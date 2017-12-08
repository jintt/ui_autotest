package me.ele.opensite.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Reporter;

import java.io.File;

public class Log {
	private static Logger logger;
	
	private static String filePath = "src/test/resources/log4j.properties";
	
	private static boolean flag = false;
	
	private static synchronized void getPropertyFile() {
		logger = Logger.getLogger("eleme");
		PropertyConfigurator.configure(new File(filePath).getAbsolutePath());
		flag = true;
	}
	
	private static void getFlag() {
		if (flag == false) {
			Log.getPropertyFile();
		}
	}
	
	public static void logInfo(String message) {
		Log.getFlag();
		logger.info(message);
		Reporter.log(message);
	}
	
	public static void logError(String message) {
		Log.getFlag();
		logger.error(message);
		Reporter.log(message);
	}
	
	public static void logWarn(String message) {
		Log.getFlag();
		logger.warn(message);
		Reporter.log(message);
	}

}
