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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.proximotech.input.MailInput;
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
	
	@Autowired
	com.proximotech.zhw.service.RequestInput requestInput;
	
	@RequestMapping(value="/mainpage",method=RequestMethod.GET)
	public String getWebMailHomePage(HttpServletRequest request, Model model, ModelMap map) {
		
		ModelAndView mv = new ModelAndView();
		
		if(null==request.getSession().getAttribute("MY_SESSION")) {
			mv.setViewName("login");
			return "login";
		}
		
		MailInput input = (MailInput) requestInput.getInput();
		
		if(null == input.getLinkId()) 
			return "redirect:/error";
			
		
		
		
		
		Mail newMail = mailService.readBinaryFile(input.getLinkId());
		model.addAttribute("mail", newMail);
		
		
		return "home";
	}

}
