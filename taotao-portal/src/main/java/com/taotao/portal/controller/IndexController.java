package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.service.ContentService;

/**
 * 展示首页
 * @author 矢量
 *
 * 2019年8月21日
 */
@Controller
public class IndexController {
	
	@Autowired
	private ContentService contentService;

	@RequestMapping("/index")
	public String showIndex(Model model){
		String adJson = contentService.getContentList();
		model.addAttribute("ad1", adJson);
		return "index";
	}
	
	/**
	 * 出现406问题
	 *	1、查看jackson包是否存在
	 *	2、请求链接后缀不能是 html 
	 * @return
	 */
	@RequestMapping(value="/httpclient/post", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult testPost(){
		return TaotaoResult.ok();
	}
	
	@RequestMapping(value="/httpclient/postwithparam", method=RequestMethod.POST,
			produces=MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8") // produces 解决服务端响应客户端时出现乱码的问题
	@ResponseBody
	public String testPostWithParam(String username, String password){
		String result = "username: " + username + "\t" + "password: " + password;
		System.out.println(result);
		return "{username: " + username + " ,password: " + password + "}";
	}
}
