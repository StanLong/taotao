package com.taotao.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.sso.dao.impl.JedisClient;
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
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	
	@Value("${SSO_SESSION_EXPIRE}")
	private Integer SSO_SESSION_EXPIRE;

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
		// md5 加密
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userMapper.createUser(user);
		return TaotaoResult.ok();
	}

	/**
	 * 用户登陆
	 */
	@Override
	public TaotaoResult userLogin(String username, String password) {
		TbUser user = new TbUser();
		user.setUsername(username);
		user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
		List<TbUser> list = userMapper.selectUser(user);
		if(null == list || list.size() == 0){
			return TaotaoResult.build(400, "用户名或密码错误!");
		}
		user = list.get(0);
		// 生成 token
		String token = UUID.randomUUID().toString();
		
		// 保存用户之前，把用户对象中的密码清空
		user.setPassword(null);
		
		// 把用户信息写入 redis
		jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
		
		// 设置session过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
		
		return TaotaoResult.ok(token);
	}

	@Override
	public TaotaoResult getUserByToken(String token) {
		String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
		if(StringUtils.isBlank(json)){
			return TaotaoResult.build(400, "此session已经过期，请重新登录");
		}
		// 更新过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token,SSO_SESSION_EXPIRE);
		return TaotaoResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
	}

}
