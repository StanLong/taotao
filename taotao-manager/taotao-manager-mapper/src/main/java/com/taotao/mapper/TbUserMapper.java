package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbUser;

public interface TbUserMapper {
	List<TbUser> selectByType(Integer type);
}