package com.groovy.test

import groovy.json.JsonSlurper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig
import redis.clients.jedis.JedisShardInfo

String redisHost = "192.168.1.106";
int redisPort = 6380;
String redisPassword = "33402.zymjw";

JedisPoolConfig config = new JedisPoolConfig()
config.testOnBorrow = false
JedisPool pool = new JedisPool(config, redisHost, redisPort, 30000, redisPassword)
Jedis jedis = pool.resource
keys = jedis.keys("*")
println keys.size()

String json = jedis.get(keys[0])

name = "name"
def c1 = new Expando()
c1."$name" = '2233'
c1.company = 'bilibili'
println c1.name

Money.metaClass."sayHello" << {
	println "Hello~"
	return 50
}
Money money = new Money(10086)
money.say('Woooo~')
money.sayHello()
