package com.boot.common;

import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class JedisClusterConfig {
    public JedisCluster getJedisCluster() {
        Set<HostAndPort> nodes=new HashSet<>();
        nodes.add(new HostAndPort("127.0.0.1", 6379));
        return new JedisCluster(nodes,10000,20000,2,"123456", new JedisPoolConfig());
    }
}
