package com.foxconn.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounter implements HttpSessionListener,ServletRequestListener {

	private static int activeSessions = 0;
	List<String> myList = new ArrayList<String>();
	HttpServletRequest request;
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		boolean rs = true;
		if(myList.size()>0){
			for(String list:myList){
				if(request.getRemoteAddr().equals(list)){
					rs = false;
					break;
				}
			}
		}if(rs){
			myList.add(request.getRemoteAddr());
			activeSessions++; 
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		myList.remove(request.getRemoteAddr());
		if(activeSessions > 0){
			activeSessions--; 
		}
			
	}
	
	public static int getActiveSessions() { 
		    return activeSessions; 
	}

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		request=(HttpServletRequest)sre.getServletRequest();

	}

}
