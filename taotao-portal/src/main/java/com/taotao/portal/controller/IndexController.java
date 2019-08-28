package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 展示首页
 * @author 矢量
 *
 * 2019年8月21日
 */
@Controller
public class IndexController {

	@RequestMapping("/index")
	public String showIndex(){
		return "index";
	}
	
	@RequestMapping(value="/httpclient/post", method=RequestMethod.POST)
	@ResponseBody
	public String testPost(){
		return "OK";
	}
	
	@RequestMapping(value="/httpclient/postwithparam", method=RequestMethod.POST)
	@ResponseBody
	public String testPostWithParam(String username, String password){
		return "username: " + username + "\t" + "password: " + password;
	}
}
