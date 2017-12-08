package me.ele.opensite.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseXml {
	
	private Document document;
	
	public ParseXml(String filePath){
		this.loadXml(filePath);
		
	}
	
	public void loadXml(String filePath){
		File file = new File(filePath);
		if( file.exists()){
			SAXReader saxReader = new SAXReader();
			
			try {
				document = saxReader.read(file);
				Log.logInfo("load file:"+ filePath);
			} catch (DocumentException e) {
								
				Log.logError("load file exception:"+ filePath);
			}
			
		} 
		else {
			Log.logError("file does not exist:" + filePath);
		}
		
	}
	
	public Element getElementObject(String elementPath) {
		return (Element) document.selectSingleNode(elementPath);
	}
	
	@SuppressWarnings("unchecked")
	public List getElementObjects(String elementPath) {
		return document.selectNodes(elementPath);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, String> getChildrenInfoByElement(Element element) {
		Map<String, String> map = new HashMap<String, String>();
		List<Element> children = element.elements();
		for (Element e : children) {
			map.put(e.getName(), e.getText());
		}
		return map;
	}
		
	
	public boolean isExist(String elementPath) {
		boolean flag = false;
		
		Element element = this.getElementObject(elementPath);
		if(element != null)
			flag = true;
		
		return flag;		
	}
	
	public String getElementText(String elementPath) {
		Element element = this.getElementObject(elementPath);
		if(element != null)
			return element.getTextTrim();
		else
			return null;
	}
}