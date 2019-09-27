package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;

/**
 * 
 * @author 矢量
 *
 *         2019年9月25日
 */
@Service
public class CartServiceImpl implements CartService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;

	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;

	/**
	 * 添加商品到购物车
	 */
	@Override
	public TaotaoResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
		// 先取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		CartItem cartItem = null;
		// 判断购物车商品列表中是否存在此商品
		for(CartItem cItem : itemList){
			// 如果商品存在
			if(cItem.getId() == itemId){
				// 增加商品数量
				cItem.setNum(cItem.getNum() + num);
				cartItem = cItem;
				break;
			}
		}
		if( cartItem == null){
			//取商品信息
			cartItem = new CartItem();
			// 根据商品id查询商品基本信息
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
			
			// 把 json 转换成 java 对象
			TaotaoResult result = TaotaoResult.formatToPojo(json, TbItem.class);
			if(result.getStatus() == 200){
				TbItem item = (TbItem)result.getData();
				cartItem.setId(item.getId());
				cartItem.setTitle(item.getTitle());
				cartItem.setImage(item.getImage() == null ? "" : item.getImage().split(",")[0]);
				cartItem.setNum(num);
				cartItem.setPrice(item.getPrice());
				itemList.add(cartItem);
			}
		}
		
		// 将商品信息写入Cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);
		return TaotaoResult.ok();

	}

	/**
	 * 从 cookie中取商品列表
	 * 
	 * @return
	 */
	private List<CartItem> getCartItemList(HttpServletRequest request) {
		// 从 cookie中取商品列表
		String cartJson = CookieUtils.getCookieValue(request, "TT_CART", true);
		if (null == cartJson){
			return new ArrayList<CartItem>();
		}
		try {
			List<CartItem> cartList = JsonUtils.jsonToList(cartJson, CartItem.class);
			return cartList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<CartItem>();
	}

	@Override
	public List<CartItem> showCartItem(HttpServletRequest request) {
		List<CartItem> itemList = getCartItemList(request);
		return itemList;
	}

	/**
	 * 删除购物车商品
	 */
	@Override
	public TaotaoResult deleteCartItem(Long itemId, HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> itemList = getCartItemList(request);
		// 从列表中找到商品
		for(CartItem cartItem : itemList){
			if(cartItem.getId().equals(itemId)){
				itemList.remove(cartItem);
				break;
			}
		}
		// 把购物车列表重新写入 cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);
		return TaotaoResult.ok();
	}
}
