package com.piedpiper.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/_web/_apps/system/api")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(method = RequestMethod.PUT)
	public String login() {
		logger.info("logger!!!!!!!!!!!!");
		// return "hello world!";
		return "hello world!";
	}
}
