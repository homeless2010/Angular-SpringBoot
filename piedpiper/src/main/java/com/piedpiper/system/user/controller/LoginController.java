package com.piedpiper.system.user.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/system")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

//	@RequestMapping(method = RequestMethod.PUT)
//	public String login() {
//		logger.info("logger!!!!!!!!!!!!");
//		// return "hello world!";
//		return "hello world!";
//	}
	
	//跳转到登录表单页面
	@RequestMapping(value="login")
	public String login() {
	    return "login";
	}

	/**
	 * ajax登录请求
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="ajaxLogin",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> submitLogin(String username, String password,Model model) {
	    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
	    try {
	        
	        ShiroToken token = new ShiroToken(username, password);
	        SecurityUtils.getSubject().login(token);
	        resultMap.put("status", 200);
	        resultMap.put("message", "登录成功");

	    } catch (Exception e) {
	        resultMap.put("status", 500);
	        resultMap.put("message", e.getMessage());
	    }
	    return resultMap;
	}
	
	//跳转到主页
	@RequestMapping(value="index")
	public String index() {
	    return "index";
	}

	/**
	* 退出
	 * @return
	 */
	@RequestMapping(value="logout",method =RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> logout(){
	    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
	    try {
	        //退出
	        SecurityUtils.getSubject().logout();
	    } catch (Exception e) {
	        System.err.println(e.getMessage());
	    }
	    return resultMap;
	}
	
	@RequestMapping(value="add")
	public String add() {
	    return "add";
	}

}
