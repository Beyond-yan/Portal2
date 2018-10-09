package com.foxconn.pojo.util;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**绑定下拉框
 * @author F3228761
 * @date : Jun 26, 2013 4:58:55 PM
 */
public class SelectBean {

	private String label;
	private String value;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ReflectionToStringBuilder.toString(this);
	}
}
