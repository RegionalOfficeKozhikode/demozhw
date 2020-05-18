/**
 * LoginController :
 * 
 *  deals with login, session
 */
package com.proximotech.zhw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.proximotech.input.Input;
import com.proximotech.input.MailInput;
import com.proximotech.zhw.form.LoginForm;
import com.proximotech.zhw.service.RequestInput;

/**
 * @author konzerntech
 * @since 1.0
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	RequestInput requestInput;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String getLoginForm() {
		
		return "login";
	}
	
	/**
	 * url : http://localhost:8080/disp?mailid=text.sam.mail&linkid=9009117_eml
	 * 
	 * @param mailId
	 * @param linkId
	 * @return path
	 */
	@RequestMapping(value="/disp",method=RequestMethod.GET)
	public String getLoginForm(@RequestParam("mailid") String mailId, @RequestParam("linkid") String linkId, ModelMap map) {
		
		System.out.println(" linkid : "+linkId);
		
		map.addAttribute("mailid", mailId);
		map.addAttribute("linkid", linkId);
		
		// Received the input
		Input input = new MailInput();
		input.setLinkId(linkId);
		input.setMailId(mailId);
		
		// Saving in a static variable
		
		requestInput.setInput(input);
		
		return "login";
	}
	
	
	/**
	 * 
	 * @param loginForm
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@ModelAttribute(name="loginForm") LoginForm loginForm, Model model, HttpServletRequest request, HttpSession session) {
		
		String username = loginForm.getUsername();
		String password = loginForm.getPassword();
		
		if("admin".equalsIgnoreCase(username) && "admin".equals(password)) {
			request.getSession().setAttribute("MY_SESSION", session.getId());
			return "redirect:/mainpage";
		}
		
		model.addAttribute("invalid credentials", true);
		return "login";
	}

	
	@RequestMapping(value="/start",method=RequestMethod.GET)
	public String getStartPage() {
		return "start";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String getLogoutPage(HttpServletRequest request) {
		request.getSession().invalidate();
		return "logout";
	}

}
