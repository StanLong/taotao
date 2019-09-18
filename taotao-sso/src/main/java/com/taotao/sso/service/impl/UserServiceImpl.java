package com.taotao.sso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;

/**
 * 
 * @author 矢量
 *
 * 2019年9月18日
 */
public class UserServiceImpl implements UserService {
	
	@Autowired
	private TbUserMapper userMapper;

	@Override
	public TaotaoResult checkData(String content, Integer type) {
		
		// 1,2,3分别代表 username, phone, email
		List<TbUser> list = userMapper.selectByType(type);
		if(list == null || list.size() == 0){
			return TaotaoResult.ok(true);
		}
		return TaotaoResult.ok(false);
	}

}
