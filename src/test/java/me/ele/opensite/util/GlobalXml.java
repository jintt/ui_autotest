package me.ele.opensite.util;

import java.util.Map;

public class GlobalXml {
	public static Map<String, String> global;
	
	static {
		ParseXml px = new ParseXml("test-data/global.xml");
		global = px.getChildrenInfoByElement(px.getElementObject("/*"));
	}

}
