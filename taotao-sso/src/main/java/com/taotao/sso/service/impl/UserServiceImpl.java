package com.taotao.sso.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private TbUserMapper userMapper;

	@Override
	public TaotaoResult checkData(String content, int type) {
		
		TbUser user = new TbUser();
		
		// 1,2,3分别代表 username, phone, email
		if( 1 == type){
			user.setUsername(content);
		}else if(2 == type){
			user.setPhone(content);
		}else {
			user.setEmail(content);
		}
		List<TbUser> list = userMapper.selectByType(user);
		if(list == null || list.size() == 0){
			return TaotaoResult.ok(true);
		}
		return TaotaoResult.ok(false);
	}

	/**
	 * 新增用户
	 */
	@Override
	public TaotaoResult createUser(TbUser user) {
		user.setCreated(new Date());
		user.setUpdated(new Date());
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userMapper.createUser(user);
		return TaotaoResult.ok();
	}

}
