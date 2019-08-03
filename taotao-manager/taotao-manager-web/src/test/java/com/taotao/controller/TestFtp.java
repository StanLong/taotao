package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.common.utils.FtpUtil;

/**
 * FTP测试类
 * @author 矢量
 *
 * 2019年8月3日
 */
public class TestFtp {

	@Test
	public void testFtpClient() throws Exception{
		//创建一个ftpClient对象
		FTPClient ftpClient = new FTPClient();
		//创建ftp连接
		ftpClient.connect("192.168.235.20", 21);
		//登陆ftp服务器
		ftpClient.login("ftpuser", "ftpuser");
		//上传文件
		//第一个参数 ：服务器端文档名
		//第二个参数：上传文档的inputStream
		//读取本地文件
		FileInputStream fileInputStream = new FileInputStream(new File("D:\\1.jpg"));
		//设置上传的路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		//设置上上传文件的格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		ftpClient.storeFile("helloFtp.jpg", fileInputStream);
		//关闭连接
		ftpClient.logout();
	}
	
	/**
	 * 测试Ftp工具类
	 * @throws Exception
	 */
	@Test
	public void testFtpUtil() throws Exception{
		FileInputStream fileInputStream = new FileInputStream(new File("D:\\1.jpg"));
		FtpUtil.uploadFile("192.168.235.20", 21, "ftpuser", "ftpuser", "/home/ftpuser/www/images", "/2019/08/03", "helloFtp3.jpg", fileInputStream);
	}
}
