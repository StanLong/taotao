package com.taotao.order.service;

import com.taotao.common.pojo.TaotaoResult;

public interface RedisService {

	TaotaoResult syscContent(long categoryId);
}
