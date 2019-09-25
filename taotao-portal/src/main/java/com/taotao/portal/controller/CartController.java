package com.taotao.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.service.CartService;

/**
 * 
 * @author 矢量
 *
 * 2019年9月25日
 */
@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@RequestMapping("/add/{itemId}")
	public String addCartItem(@PathVariable Long itemId,
			@RequestParam(defaultValue="1") Integer num, HttpServletRequest request, HttpServletResponse response){
		TaotaoResult result = cartService.addCartItem(itemId, num, request, response);
		return "cart-success";
	}
}
