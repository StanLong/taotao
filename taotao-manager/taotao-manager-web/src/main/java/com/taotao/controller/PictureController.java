package com.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureService;

/**
 * 上传图片处理
 * @author 矢量
 *
 * 2019年8月3日
 */
@Controller
public class PictureController {

	@Autowired
	private PictureService pictureService;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String pictureUpload(MultipartFile uploadFile){
		Map<Object, Object> result = pictureService.uploadPicture(uploadFile);
		//为保证浏览器的兼容性 需要把 result 转换成json 格式的字符串
		String json = JsonUtils.objectToJson(result);
		return json;
	}
}
