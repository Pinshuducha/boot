package com.boot.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class JedisClusterConfig {
    @Bean
    public JedisCluster getJedisCluster() {
        // 连接池设置
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(200); //最大连接数
        poolConfig.setMaxIdle(50); //最大空闲连接数
        poolConfig.setMinIdle(20);  //最小空闲连接数
        poolConfig.setMaxWaitMillis(1000 * 10); // 最大等待时间 如果超时抛错
        // 节点集群设置
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("127.0.0.1", 6379));
        return new JedisCluster(nodes, 10000, 20000, 2, "123456", poolConfig);
    }
}
