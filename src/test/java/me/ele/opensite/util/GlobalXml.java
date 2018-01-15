package me.ele.opensite.util;

import java.util.Map;

public class GlobalXml {
	public  Map<String, String> global;
	
	public GlobalXml(String environment) {
		ParseXml px = new ParseXml("test-data/global.xml");
		global = px.getChildrenInfoByElement(px.getElementObject("data/"+ environment));
	}

	public  Map<String, String> getGlobal(){
		return global;
	}

}
