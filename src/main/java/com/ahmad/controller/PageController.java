package com.ahmad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ahmad.dao.UserLoginDAO;

@Controller
public class PageController {

	@Autowired
		private UserLoginDAO userloginDao;
	
	@RequestMapping(value = { "/", "/index" })
	public String home() {
		return "index";
	}

	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public String success(@RequestParam("loginEmail") String userId, @RequestParam("loginPwd") String password) {
		
		boolean value = userloginDao.loginControl(userId, password);
		if (value == true) {
			return "successful";
		}

		else {

			return "error";
		}
	}
}
