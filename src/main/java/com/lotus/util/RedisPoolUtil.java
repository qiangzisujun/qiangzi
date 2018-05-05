package com.lotus.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPoolUtil {

	private volatile static RedisPoolUtil redisPoolUtil=null;
	
	//redis服务器IP
	private static String ADDR="127.0.0.1";
	//redis的端口号
	private static Integer PROT=6379;
	//redis访问密码
	private static String AUTH="liusu9686";
	
	//可用连接实例的最大数目，默认为8；
    //如果赋值为-1，则表示不限制，如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)
    private static Integer MAX_TOTAL = 1024;
    //控制一个pool最多有多少个状态为idle(空闲)的jedis实例，默认值是8
    private static Integer MAX_IDLE = 200;
    //等待可用连接的最大时间，单位是毫秒，默认值为-1，表示永不超时。
    //如果超过等待时间，则直接抛出JedisConnectionException
    private static Integer MAX_WAIT_MILLIS = 10000;
    private static Integer TIMEOUT = 10000;
    //在borrow(用)一个jedis实例时，是否提前进行validate(验证)操作；
    //如果为true，则得到的jedis实例均是可用的
    private static Boolean TEST_ON_BORROW = true;
    private  static JedisPool jedisPool = null;
	
	public RedisPoolUtil(){
		
	}
	
	static{
		try {
			JedisPoolConfig jPoolConfig=new JedisPoolConfig();
			/*注意：
            在高版本的jedis jar包，比如本版本2.9.0，JedisPoolConfig没有setMaxActive和setMaxWait属性了
            这是因为高版本中官方废弃了此方法，用以下两个属性替换。导入commons-pool2-2.5.0
            maxActive  ==>  maxTotal
            maxWait==>  maxWaitMillis
         */
			jPoolConfig.setMaxTotal(MAX_TOTAL);
			jPoolConfig.setMaxIdle(MAX_IDLE);
			jPoolConfig.setMaxWaitMillis(MAX_WAIT_MILLIS);
			jPoolConfig.setTestOnBorrow(TEST_ON_BORROW);
			jedisPool=new JedisPool(jPoolConfig,ADDR,PROT,TIMEOUT,AUTH);
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	
	public static RedisPoolUtil getInstance(){
		if (redisPoolUtil==null) {
			synchronized (RedisPoolUtil.class) {
				if (redisPoolUtil==null){
					redisPoolUtil=new RedisPoolUtil();
				}
			}
		}
		return redisPoolUtil;
	}
	
	public synchronized static Jedis getjedis(){
		try {
            if(jedisPool != null){
            	Jedis jedis = jedisPool.getResource();
                return jedis;
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
}
