package com.foxconn.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.logging.LogFactory;

public class JsonUtil {
		
	public static String toJsonString(Object o) {
		if(o instanceof Collection) {
			StringBuffer buf = new StringBuffer("");
			Object[] objs = JSONArray.fromObject(o).toArray();
			for (int i = 0; i < objs.length; i ++) {
				if(i!=0) buf.append(",");
				buf.append(JSONObject.fromObject(objs[i]).toString());
			}
			return "{totalCount:" + objs.length + ",root:[" + buf.toString() + "]}";
		}
		return JSONObject.fromObject(o).toString();
	}
	
	public static String listToJsonString(ArrayList<String> list) {
		if(list != null) {
			StringBuffer buf = new StringBuffer("");
			buf.append("{pageDatas:");
			int length = list.size();
			int i = 0;
			for(String t : list) {
				buf.append("[{data:");
				buf.append(t);
				i++;
				if(i == length) {
					buf.append("}]}");
				} else {
					buf.append("},");
				}				
			}
			return buf.toString();
		} 
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static String transferParamsToJsonString(Map<Object,Object> m) {
		if(m != null) {
			List<String> title = (ArrayList<String>)m.get("title");
			StringBuffer buf = new StringBuffer("");
			buf.append("{pageTitle:[{");
			int length = title.size();
			int i = 0;
	    	for(String t : title) {
	    		buf.append("title:\"");
	    		buf.append(t);
	    		i++;
				if(i == length) {
					buf.append("\"}],");
				} else {
					buf.append("\"},{");
				}			
	    	}	    	
	    	buf.append("filename:\"");
	    	buf.append((String)m.get("filename"));
	    	buf.append("\",");
	    	List<ArrayList<String>> data = (List<ArrayList<String>>)m.get("data");		
			buf.append("pageDatas:[{");
			int temlength = length;
			length = data.size() * length;	
			i = 0;
			int j;
			for(ArrayList<String> te : data) {		
				j = 0;
				for(String t : te) {						
					buf.append("data" + j + ":\"");					
					buf.append(t);
					j++;
					i++;
					if(i==length) {
						buf.append("\"}]}");
					} else if(j != temlength){
						buf.append("\",");
					}			
				}	
				if(i < length) {
					buf.append("\"},{");
				}
				
			}
			return buf.toString();
		} 
		return null;
	}
	
	public static String transferPagedDataToJSon(Page page) 
	{
		StringBuffer buf = new StringBuffer();
		buf.append("{pageIndex:");
		buf.append(page.getPageIndex());
		buf.append(",pageSize:");
		buf.append(page.getPageSize());
		buf.append(",totalCount:");
		buf.append(page.getTotalCount());
		buf.append(",pageCount:");
		buf.append(page.getPageCount());
		buf.append(",pageData:[");
		int dataListSize = page.getResult().size();
		for(int i=0;i<dataListSize;i++)
		{
			buf.append("{");
			Object obj = page.getResult().get(i);
			
			Method[] methods = obj.getClass().getDeclaredMethods();
			for(int j=0;j<methods.length;j++)
			{
				Method method = methods[j];
				String methodName = method.getName();
				if(methodName.indexOf("get") != -1)
				{
					String fieldName = methodName.substring(3, methodName.length());
					Object value = null;
					try
					{
						value = method.invoke(obj, new Object[]{});
					}
					catch (IllegalArgumentException e)
					{
						LogFactory.getLog(JsonUtil.class).warn(e);
					}
					catch (IllegalAccessException e)
					{
						LogFactory.getLog(JsonUtil.class).warn(e);
					}
					catch (InvocationTargetException e)
					{
						LogFactory.getLog(JsonUtil.class).warn(e);
					}
					
					buf.append(fieldName.toUpperCase());
					buf.append(":\"");
					buf.append(value == null ? "":value.toString().replace("\\", "&#92;").replace("\n", "&#10;").replace("\r", "&#13;").replace("\"", "\'"));
					buf.append("\",");
				}
			}
			buf.delete(buf.length()-1, buf.length());
			if(i == dataListSize-1)
			{
				buf.append("}");
			}
			else
			{
				buf.append("},");
			}
		}
		buf.append("]}");
		return buf.toString();
	}
	
	/**
	 * @author f3226089
	 * @param page
	 * @return
	 * @Date 2011/6/14
	 */
	public static String transferPagedDataToJSon1(Page page) 
	{
		StringBuffer buf = new StringBuffer();
		buf.append("{total:");
		buf.append(page.getTotalCount());
		buf.append(",page:");
		buf.append(page.getPageIndex());
		buf.append(",rows:[");
		int dataListSize = page.getResult().size();
		for(int i=0;i<dataListSize;i++)
		{
			buf.append("{");
			Object obj = page.getResult().get(i);
			
			Method[] methods = obj.getClass().getDeclaredMethods();
			for(int j=0;j<methods.length;j++)
			{
				Method method = methods[j];
				String methodName = method.getName();
				if(methodName.indexOf("get") != -1)
				{
					String fieldName = methodName.substring(3, methodName.length());
					Object value = null;
					try
					{
						value = method.invoke(obj, new Object[]{});
					}
					catch (IllegalArgumentException e)
					{
						LogFactory.getLog(JsonUtil.class).warn(e);
					}
					catch (IllegalAccessException e)
					{
						LogFactory.getLog(JsonUtil.class).warn(e);
					}
					catch (InvocationTargetException e)
					{
						LogFactory.getLog(JsonUtil.class).warn(e);
					}
					
					buf.append(fieldName);
					buf.append(":\"");
					buf.append(value == null ? "":value.toString().replace("\\", "&#92;").replace("\n", "&#10;").replace("\r", "&#13;").replace("\"", "\'"));
					buf.append("\",");
				}
			}
			buf.delete(buf.length()-1, buf.length());
			if(i == dataListSize-1)
			{
				buf.append("}");
			}
			else
			{
				buf.append("},");
			}
		}
		buf.append("]}");
		return buf.toString();
	}
	
	public static String transferPagedDataToJSonWithTitle(Page page, String title) 
	{
		StringBuffer buf = new StringBuffer();
		buf.append("{pageIndex:");
		buf.append(page.getPageIndex());
		buf.append(",pageSize:");
		buf.append(page.getPageSize());
		buf.append(",titleInfo:[{titleInfo:");
		buf.append("\"" + title + "\"}]");
		buf.append(",totalCount:");
		buf.append(page.getTotalCount());
		buf.append(",pageCount:");
		buf.append(page.getPageCount());
		buf.append(",pageData:[");
		int dataListSize = page.getResult().size();
		for(int i=0;i<dataListSize;i++)
		{
			buf.append("{");
			Object obj = page.getResult().get(i);
			
			Method[] methods = obj.getClass().getDeclaredMethods();
			for(int j=0;j<methods.length;j++)
			{
				Method method = methods[j];
				String methodName = method.getName();
				if(methodName.indexOf("get") != -1)
				{
					String fieldName = methodName.substring(3, methodName.length());
					Object value = null;
					try
					{
						value = method.invoke(obj, new Object[]{});
					}
					catch (IllegalArgumentException e)
					{
						LogFactory.getLog(JsonUtil.class).warn(e);
					}
					catch (IllegalAccessException e)
					{
						LogFactory.getLog(JsonUtil.class).warn(e);
					}
					catch (InvocationTargetException e)
					{
						LogFactory.getLog(JsonUtil.class).warn(e);
					}
					
					buf.append(fieldName.toUpperCase());
					buf.append(":\"");
					buf.append(value == null ? "":value.toString().replace("\\", "&#92;").replace("\n", "&#10;").replace("\r", "&#13;").replace("\"", "\'"));
					buf.append("\",");
				}
			}
			buf.delete(buf.length()-1, buf.length());
			if(i == dataListSize-1)
			{
				buf.append("}");
			}
			else
			{
				buf.append("},");
			}
		}
		buf.append("]}");
		return buf.toString();
	}
	
	
	/**
	 *  transfer a pojo to json-string.
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static String transferObjectToJSon(Object obj) 
	{
		StringBuffer buf = new StringBuffer();
		buf.append("{");
		Method[] methods = obj.getClass().getDeclaredMethods();
		for(int j=0;j<methods.length;j++)
		{
			Method method = methods[j];
			String methodName = method.getName();
			if(methodName.indexOf("get") != -1)
			{
				String fieldName = methodName.substring(3, methodName.length());
				Object value = null;
				try
				{
					value = method.invoke(obj, new Object[]{});
				}
				catch (IllegalArgumentException e)
				{
					LogFactory.getLog(JsonUtil.class).warn(e);
				}
				catch (IllegalAccessException e)
				{
					LogFactory.getLog(JsonUtil.class).warn(e);
				}
				catch (InvocationTargetException e)
				{
					LogFactory.getLog(JsonUtil.class).warn(e);
				}
				buf.append(fieldName.toUpperCase());
				buf.append(":\"");
				buf.append(value == null ? "":value);
				buf.append("\",");
			}
		}
		buf.delete(buf.length()-1, buf.length());
		buf.append("}");
		return buf.toString();
	}
	
	
    /**
     * 对象数组转换为Json
     * @param array
     * @return
     */
    public static String array2json(Object[] array) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (array != null && array.length > 0) {
            for (Object obj : array) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }
    
    /**
     * Map集合转换为Json
     * @param map
     * @return
     */
    public static String map2json(Map<?, ?> map) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        if (map != null && map.size() > 0) {
            for (Object key : map.keySet()) {
                json.append(object2json(key));
                json.append(":");
                json.append(object2json(map.get(key)));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }
	
	/**
     * flq 转换成时间字符串
     * @param time 时间
     * @return
     */
    public static String dateToStr(Date time, String dateformat) {

        String strDate = "";
        try {
            SimpleDateFormat formatters = new SimpleDateFormat(
                    dateformat == null ? "yyyy-MM-dd HH:mm:ss" : dateformat);
            strDate = formatters.format(time);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }
    
	/**
     * 对象转换为Json
     * @param obj 
     * @return
     */
    public static String object2json(Object obj) {
        StringBuilder json = new StringBuilder();
        if (obj == null) {
//            json.append("\"\"");
            json.append(obj);
        } else if (obj instanceof String || obj instanceof Integer
                || obj instanceof Float || obj instanceof Boolean
                || obj instanceof Short || obj instanceof Double
                || obj instanceof Long || obj instanceof BigDecimal
                || obj instanceof BigInteger || obj instanceof Byte) {
        	json.append("\"").append(string2json(obj.toString())).append("\"");
        } else if(obj instanceof Date){
        	json.append("\"").append(string2json(dateToStr((Date)obj, null))).append("\"");
        } else if (obj instanceof Object[]) {
            json.append(array2json((Object[]) obj));
        } else if (obj instanceof List) {
            json.append(list2json((List<?>) obj));
        } else if (obj instanceof Map) {
            json.append(map2json((Map<?, ?>) obj));
        } else if (obj instanceof Set) {
            json.append(set2json((Set<?>) obj));
        } else {
            json.append(bean2json(obj));
        }
        return json.toString();
    }

    /**
     * 字符串转换为Json
     * @param s
     * @return
     */
    public static String string2json(String s) {
        if (s == null)
            return "";
        if(s.length()==21&&s.indexOf("-")!=-1&&s.indexOf(".0")!=-1&&s.indexOf(":")!=-1){
        	s=s.substring(0, s.length()-2);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
            case '"':
                sb.append("\\\"");
                break;
            case '\\':
                sb.append("\\\\");
                break;
            case '\b':
                sb.append("\\b");
                break;
            case '\f':
                sb.append("\\f");
                break;
            case '\n':
                sb.append("\\n");
                break;
            case '\r':
                sb.append("\\r");
                break;
            case '\t':
                sb.append("\\t");
                break;
//            case '/':
//                sb.append("\\/");
//                break;
            default:
                if (ch >= '\u0000' && ch <= '\u001F') {
                    String ss = Integer.toHexString(ch);
                    sb.append("\\u");
                    for (int k = 0; k < 4 - ss.length(); k++) {
                        sb.append('0');
                    }
                    sb.append(ss.toUpperCase());
                } else {
                    sb.append(ch);
                }
            }
        }
        return sb.toString();
    }
    
    /**
     * 对象bean转换为Json
     * @param bean
     * @return
     */
    public static String bean2json(Object bean) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        PropertyDescriptor[] props = null;
        try {
            props = Introspector.getBeanInfo(bean.getClass(), Object.class)
                    .getPropertyDescriptors();
        } catch (IntrospectionException e) {
        }
        if (props != null) {
            for (int i = 0; i < props.length; i++) {
                try {
                    String name = object2json(props[i].getName());
                    String value = object2json(props[i].getReadMethod().invoke(
                            bean));
                    json.append(name);
                    json.append(":");
                    json.append(value);
                    json.append(",");
                } catch (Exception e) {
                }
            }
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }
    
    /**
     * Set集合转为Json
     * @param set
     * @return
     */
    public static String set2json(Set<?> set) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (set != null && set.size() > 0) {
            for (Object obj : set) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }
    
    /**
     * List集合转换为Json
     * @param list
     * @return
     */
    public static String list2json(List<?> list) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (list != null && list.size() > 0) {
            for (Object obj : list) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

}
