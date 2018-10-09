package com.foxconn.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class OperateConfig {
	
	static Document document=DocumentHelper.createDocument();	
	private String configFile;

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

	public Document openCofig()throws DocumentException  {
		SAXReader reader=new SAXReader();
		Document document=reader.read(new File(configFile));
		
		return document;
	}
	
	public Element getElement(String element)throws DocumentException{
		Document document=openCofig();		
		Element root=document.getRootElement();
		Element node=root.element(element);
		return node;
	}
	
	public String getNodeValue(String element)throws DocumentException{
		Element node=getElement(element);
		String text=node.getTextTrim();
		return text;
	}
	
	@SuppressWarnings("rawtypes")
	public List getElements(String element)throws DocumentException{
		Element root=openCofig().getRootElement();
		Element node=root.element("slides");
		List nodes=node.elements(element);
		
		return nodes;
	}
	
	public String getAttributeValue(String element,String attribute)throws DocumentException{
		Element node=getElement(element);
		Attribute nodeAttribute=node.attribute(attribute);
		return nodeAttribute.getText();
	}	

	public void saveConfigFile(String path)throws Exception{
		File file=new File(path);
		if(file.exists()){
			file.delete();
		}
		
		OutputFormat format=OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer= null;
		try{
			writer=new XMLWriter(new FileWriter(path));		
			writer.write(document);
			writer.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			writer.close();
		}
		
	}
	
	@SuppressWarnings("unused")
	private Element createRootElement(String name)throws Exception{	
		Element root=document.addElement(name);
		
		return root;
	}
	
	@SuppressWarnings("unused")
	private Element createElement(Element parent, String child)throws Exception{		
		return parent.addElement(child);
	}
	
	@SuppressWarnings("unused")
	private void setElementValue(Element element,String value)throws Exception{
		element.setText(value);
	}
	
	@SuppressWarnings("unused")
	private void createChildElement(Element element, String value)throws Exception{				
		Element cell= element.addElement("cell");
		cell.setText(value);
	}

}
