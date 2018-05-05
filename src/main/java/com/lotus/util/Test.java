package com.lotus.util;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import redis.clients.jedis.Jedis;

public class Test {

	private Jedis jedis=null;
	
	public static void main(String[] args) {
		
		Test test=new Test();
		//test.testString();
		test.testMap();
		/*BCryptPasswordEncoder encode=new BCryptPasswordEncoder();
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String str=encoder.encode("123456");
		String hashPass = encode.encode("123456");  
	    System.out.println(hashPass); 
	    System.out.println(str);*/
	}
	
	public void testString(){
		jedis=RedisPoolUtil.getjedis();
		jedis.set("name", "苏君");
		System.out.println(jedis.get("name"));
		jedis.append("name", "打算");
		System.out.println(jedis.get("name"));
		
	}
	public void testMap() {
		jedis=RedisPoolUtil.getjedis();
        //添加数据
        Map<String, String> user = new HashMap<String, String>();
        user.put("name", "chx");
        user.put("age", "100");
        user.put("email", "***@outlook.com");
        jedis.hmset("user", user);
        //取出user中的name，结果是一个泛型的List
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key是可变参数
        List<String> list = jedis.hmget("user", "name", "age", "email");
        System.out.println(list);

        //删除map中的某个键值
        jedis.hdel("user", "age");
        System.out.println("age:" + jedis.hmget("user", "age")); //因为删除了，所以返回的是null
        System.out.println("user的键中存放的值的个数:" + jedis.hlen("user")); //返回key为user的键中存放的值的个数2
        System.out.println("是否存在key为user的记录:" + jedis.exists("user"));//是否存在key为user的记录 返回true
        System.out.println("user对象中的所有key:" + jedis.hkeys("user"));//返回user对象中的所有key
        System.out.println("user对象中的所有value:" + jedis.hvals("user"));//返回map对象中的所有value

        //拿到key，再通过迭代器得到值
        Iterator<String> iterator = jedis.hkeys("user").iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.println(key + ":" + jedis.hmget("user", key));
        }
        //jedis.del("user");
        System.out.println("删除后是否存在key为user的记录:" + jedis.exists("user"));//是否存在key为user的记录

    }
}
