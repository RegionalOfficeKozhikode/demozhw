/**
 * MailController :
 * Reading and writing email
 * 
 */
package com.proximotech.zhw.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proximotech.model.Mail;
import com.proximotech.zhw.service.MailService;

/**
 * @author Konzerntech
 *
 */
@Controller
public class MailController {
	
	@Autowired
	MailService mailService;
	
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String getWebMailHomePage(HttpServletRequest request, Model model) {
		
		if(null==request.getSession().getAttribute("MY_SESSION")) {
			return "login";
		}
		
		Mail newMail = mailService.readBinaryFile("9009117");
		model.addAttribute("mail", newMail);
		
		return "home";
	}

}
