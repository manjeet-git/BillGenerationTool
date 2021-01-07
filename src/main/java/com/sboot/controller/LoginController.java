package com.sboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//This controller is ment for implementing the custom login page 

@Controller
@RequestMapping("/")
public class LoginController {

	@GetMapping("login")
	public String getLogin() {
		return "login";
	}

}
