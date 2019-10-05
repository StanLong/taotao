package com.taotao.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.common.utils.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import com.taotao.portal.service.impl.UserServiceImpl;

public class LoginInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserServiceImpl userService;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// 返回ModelAndView之后

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// handler 执行之后， 返回 ModleAndView 之前

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// Handler 执行之前执行
		// 1、 判断用户是否登录
		// 从 cookie 中取 token
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		
		// 2、根据 token换取用户信息，调用sso系统接口
		// 如果取不到用户信息，跳转到登录页面，把用户请求的url作为参数传递给登录页面
		TbUser user = userService.getUserByToken(token);
		
		if(null == user){
			response.sendRedirect(userService.SSO_BASE_URL + userService.SSO_PAGE_LOGIN
					+ "?redirect=" + request.getRequestURL());
			// 返回 false
			return false;
		}
		request.setAttribute("user", user);
		//3、取到用户信息，放行
		return true;
	}

}
