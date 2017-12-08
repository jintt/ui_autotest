package me.ele.opensite.util;

public class Config {
	public static String browser;
	
	public static int waitTime;
	
	static {
		ParseXml px = new ParseXml("config/config.xml");
		browser = px.getElementText("/config/browser");
		waitTime = Integer.valueOf(px.getElementText("/config/waitTime"));
	}		

}
