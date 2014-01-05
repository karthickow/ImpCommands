package com.student.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class HelloTag extends SimpleTagSupport {
	
	StringWriter sw = new StringWriter();
	private String message;
	
	public void setMessage(String msg) {
		this.message = msg;
	}
	
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		//out.println("Hello Custom Tag!");
		if(message != null) {
			// Use message from attribute
			out.println(message);
		} else {
			// Use message from the body
			getJspBody().invoke(sw);
			out.println(sw.toString());
		}
	}
}
