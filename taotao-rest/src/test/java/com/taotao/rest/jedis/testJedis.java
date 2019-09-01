package com.taotao.rest.jedis;

import java.util.HashSet;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 *  jedis 客户端
 * @author 矢量
 *
 * 2019年9月1日
 */
public class testJedis {
	
	/**
	 * jedis 客户端（单机版）
	 */
	@Test
	public void testJedisSingle(){
		//创建客户端
		Jedis jedis = new Jedis("192.168.235.20", 6379);
		//调用 jedis 对象方法，方法名和 redis 的命令一致
		jedis.set("key1", "hello jedis");
		String string = jedis.get("key1");
		System.out.println(string);
		//关闭jedis
		jedis.close();
	}
	
	/**
	 * 使用jedis连接池
	 */
	@Test
	public void testJedisPool(){
		// 创建连接池对象
		JedisPool jedisPool = new JedisPool("192.168.235.20", 6379);
		// 从连接池中获得 jedis 对象
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get("key1");
		System.out.println(string);
		//关闭jedis
		jedis.close();
		jedisPool.close();
	}
	
	/**
	 * 测试 jedis 集群， 
	 */
	@Test
	public void testJedisCluster(){
		HashSet<HostAndPort> nods = new HashSet<>();
		nods.add(new HostAndPort("192.168.235.20", 7001));
		nods.add(new HostAndPort("192.168.235.20", 7002));
		nods.add(new HostAndPort("192.168.235.20", 7003));
		nods.add(new HostAndPort("192.168.235.20", 7004));
		nods.add(new HostAndPort("192.168.235.20", 7005));
		nods.add(new HostAndPort("192.168.235.20", 7006));
		
		// JedisCluster 自带连接池
		JedisCluster cluster = new JedisCluster(nods);
		
		cluster.set("key2", "1000");
		String str = cluster.get("key2");
		System.out.println(str);
		cluster.close();
	}
}
