/**
 * MailController :
 * Reading and writing email
 * 
 */
package com.proximotech.zhw.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Konzerntech
 *
 */
@Controller
public class MailController {
	
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String getWebMailHomePage(HttpServletRequest request) {
		
		if(null==request.getSession().getAttribute("MY_SESSION")) {
			return "login";
		}
		return "home";
	}

}
