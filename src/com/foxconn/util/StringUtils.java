package com.foxconn.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public class StringUtils {
	
	public static String join(List<String> list, String str) {
		if(list == null || list.isEmpty()) return "";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < list.size(); i++) {
			if(i != 0) buf.append(str);
			buf.append(list.get(i)); 
		}
		return buf.toString();
	}
	
	
	/**
	 * 分離text各個參數
	 * @param text
	 * @param split
	 * @return ArrayList<String>
	 * @Author:F3225959
	 * @Date:2011/04/20
	 */
	public static ArrayList<String> splitText(String text, char split) {
		
        int i;
        char c;
        ArrayList<String> title = new ArrayList<String>();
        
        for(i=0;i<text.length();i++) {
        	c = text.charAt(i);             
        	if(c != split) {
        		int start = i;
        		for(int j=i+1;j<text.length();j++) {
        			c = text.charAt(j);               			
        			if(c == split) {
        				int end = j;               				
        				title.add(text.substring(start,end));
        				i = j-1;
        				j = text.length()+1;
        			}             			
        		}
        	}
        }
        i = text.lastIndexOf(split);
        title.add(text.substring(i+1,text.length()));                          
        return title;
	}
	
	/**
	 * 將字符串中的中文字符進行UTF-8編碼
	 * @param chineseStr
	 * @return ArrayList<String>
	 * @Author:F3225959
	 * @Date:2011/04/22
	 */
	public static String chineseToUTF(String chineseStr) {
		  char[] charArray = chineseStr.toCharArray();
		  String utfString = "";
		  for (int i = 0; i < charArray.length; i++) {
			  if ((charArray[i] >= 0x4e00) && (charArray[i] <= 0x9fbb)) {			  
				try {
					utfString += URLEncoder.encode(String.valueOf(charArray[i]), "utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  } else {
				  utfString += charArray[i];
			  }
		  }
		  return utfString;
	}
	
	/**
	 * 將所有參數列表轉化成Map,其中title為屬性名,data為屬性參數
	 * @param address
	 * @return Map<String>
	 * @Author:F3225959
	 * @Date:2011/04/20
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<Object,Object> transferParams(String address) {
		URL url = null; 
	//	String t = "http://10.148.74.59:8080/BFLH/1111測試111_北富廠區/paramList/rlp_1303269276188265932_version1.dat";
		InputStreamReader read = null;
    	BufferedReader bufferedReader = null;
		try {
            String encoding = "GBK"; // 字符编码(可解决中文乱码问题 )

            //File file = new File("http://10.148.74.59:8080/BFLH/1111測試111_北富廠區/paramList/rlp_1303269276188265932_version1.dat");

            //if (file.isFile() && file.exists()) {
            	url = new URL(chineseToUTF(address));
            	
                read = new InputStreamReader(
                		url.openStream(), encoding);

                bufferedReader = new BufferedReader(read);
                String lineTXT = null;
                ArrayList<String> title = new ArrayList<String>();
                ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
                int i = 0;
                lineTXT = bufferedReader.readLine() == null ? lineTXT : bufferedReader.readLine();
                String fileName = lineTXT;
                while ((lineTXT = bufferedReader.readLine()) != null) {                                     
                    if(lineTXT.equals("")) break;             
                    if(i == 0) {
                    	title = splitText(lineTXT,'\t');
                    } 
                    if(i >= 1) {
                    	data.add(splitText(lineTXT,'\t'));
                    }                  
                    i++;                      
                }
                bufferedReader.close();
                read.close();          
                Map pdata = new HashMap<Object,Object>();
                pdata.put("filename", fileName);
                pdata.put("title", title);
                pdata.put("data", data);
                return pdata;
//            }else{
//                System.out.println("找不到指定的文件！");
//                return null;
//            }
        } catch (Exception e) {
            System.out.println("读取文件内容操作出错");
            e.printStackTrace();
            return null;
        }finally{
        	 try {
				bufferedReader.close();
				read.close();   
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
	
	/**
	 * 獲取文件路徑信息，返回所有文件路徑數組
	 * @param filePath
	 * @param split
	 * @return ArrayList<String>
	 * @Author:F3225959
	 * @Date:2011/03/25
	 */
	public static ArrayList<String> filePathToList(String filePath,String split, HttpServletRequest request){ 
		String tem; 
		ArrayList<String> list=new ArrayList<String>(); 
		int index = filePath.indexOf(split);
		String ptem = "D:\\FoxTool\\Files\\";
		while(index != -1) {
			tem = filePath.substring(0, index);
			tem = tem.substring(ptem.length(), tem.length());
			tem = "http://" + request.getSession().getAttribute("hostPort") + "/BFLH/" + tem;  
			tem = tem.replaceAll("\\\\", "/");
			list.add(tem);
			filePath=  filePath.substring(index+1, filePath.length());
			index = filePath.indexOf(split);
		}
		if(list.size() != 0) return list;
		return null;
	}
	
	/**
	 * 獲取文件名信息，返回所有文件名數組
	 * @param fileName
	 * @param split
	 * @return ArrayList<String>
	 * @Author:F3225959
	 * @Date:2011/03/31
	 */
	public static ArrayList<String> fileNameToList(String fileName,String split){ 
		String tem; 
		ArrayList<String> list=new ArrayList<String>(); 
		int index = fileName.indexOf(split);
		while(index != -1) {
			tem = fileName.substring(0, index);
			list.add(tem);
			fileName=  fileName.substring(index+1, fileName.length());
			index = fileName.indexOf(split);
		}
		if(list.size() != 0) return list;
		return null;
	}
	
	/**
	 * 獲取文件名+路徑信息，返回所有文件名+路徑數組
	 * 返回的list為String數組 String[0]為路徑名稱  String[1]為文件名稱
	 * @param fileName
	 * @param split
	 * @return ArrayList<String>
	 * @Author:F3225959
	 * @Date:2011/03/31
	 */
	public static ArrayList<String[]> filePathNameToList(String filePath, String splitpath, String fileName, String splitname, HttpServletRequest request) {
		ArrayList<String> listPath = filePathToList(filePath,splitpath,request);
		ArrayList<String> listName = fileNameToList(fileName,splitname);
		ArrayList<String[]> listPathName = new ArrayList<String[]>();		
		if(listPath!=null && listName!=null) {
			for(int i=0;i<listPath.size();i++) {
				String[] temp = new String[2];
				temp[0] = listPath.get(i);
				temp[1] = listName.get(i);
				listPathName.add(temp);
			}
			return listPathName;
		}
		return null;
	}
	
	
	
	/**
	 * 判断一个字体是否为空
	 * @param str
	 * @return 为空返回true,否则返回false
	 */
	public static boolean isEmpty(String str){
		if(str==null||str.equals("")){
			return true;
		}
		return false;
		
	}
	
	/**
	 * @param roleName
	 * @return
	 */
	public static String getRoleNewName(String roleName) {
		String newRoleName = "";
		String [] roleArr = roleName.split(",");
		for(int i=0;i<roleArr.length;i++){
			if(roleArr[i].equalsIgnoreCase("ROLE_ESI_MANAGER")){
				newRoleName += "ESI主管"+",";
			}else if(roleArr[i].equalsIgnoreCase("ROLE_ADMIN")){
				newRoleName += "管理員"+",";
			}else if(roleArr[i].equalsIgnoreCase("ROLE_PROJECT_MANAGER")){
				newRoleName += "專案負責人"+",";
			}else if(roleArr[i].equalsIgnoreCase("ROLE_ESI_OPERATOR")){
				newRoleName += "ESI操作人員"+",";
			}else{
				newRoleName += roleArr[i].replace("ROLE_", "")+",";
			}
		}
		if(!isEmpty(newRoleName)){
			newRoleName = newRoleName.substring(0, newRoleName.length()-1);
		}
		return newRoleName;
	}
	
	public static String getStringFromArray(String[] strArray){
		StringBuffer str = new StringBuffer();
		if(null!=strArray && strArray.length>0){
			for(int i=0;i<strArray.length;i++){
				str.append(strArray[i]).append(",");
			}
		}
		return str.toString();
	}
	
	public static String[] getArrayFromStringBySplit(String str){
		String[] personArrray =null;
		if(null!=str){
			personArrray = str.toString().split(",");
		}
		return personArrray;
	}
	
}
