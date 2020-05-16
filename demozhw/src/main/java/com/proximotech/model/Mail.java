/**
 * 
 */
package com.proximotech.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nijesh
 *
 */
public class Mail {
	
	private String from;
	
	private String to;
	
	private String cc;
	
	private String subject;
	
	private List attachments;
	
	private String answer;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List getAttachments() {
		if(null==attachments)
			attachments = new ArrayList<String>();
		return attachments;
	}

	public void setAttachments(List attachments) {
		this.attachments = attachments;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
	

}
