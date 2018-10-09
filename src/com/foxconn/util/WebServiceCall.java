package com.foxconn.util;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

public class WebServiceCall {
	
	private String url;
	private String operation;
	private Object[] args;
	
public String getStringResult() {
		
		if (url == null || "".equals(url)) {
			return null;
		}
		if (operation == null || "".equals(operation)) {
			return null;
		}
		
		String ret = "";
		Service service = new Service();
		try {
			Call call = (Call)service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(url));
			// 设置操作的名称。
			call.setOperationName(operation);
			
			for (int i=0;i<args.length;i++) {
				call.addParameter("arg" + i, XMLType.XSD_STRING, ParameterMode.IN);
			}
			call.setReturnType(XMLType.XSD_STRING);
			
			// 执行调用   
			ret = (String)call.invoke(args);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	/**
	 * 
	 * @return
	 */
	public Document getDocumentResult() {
		return getXmlDocument(getStringResult());
	}
	/**
	 * 
	 * @param strxml
	 * @return
	 */
	private Document getXmlDocument(String strxml) {
		Document myDoc = null;
		SAXBuilder sb = new SAXBuilder();
		StringReader xmlString = new StringReader(strxml);
		InputSource source = new InputSource(xmlString);
		
		try {
			myDoc = sb.build(source);
			xmlString.close();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally
		{
			xmlString.close();
		}
		return myDoc;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Object[] getArgs() {
		Object[] ret = null;
		if ( this.args != null ) {
			ret = new Object[this.args.length];
			for (int i = 0; i < this.args.length; i++) { ret[i] = this.args[i]; }
		}
		return ret;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
}
