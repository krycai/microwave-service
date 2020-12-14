package com.allen.redis.redisson.config;

import com.allen.redis.redisson.RedissLockHelper;
import com.allen.redis.redisson.service.DistributedLocker;
import com.allen.redis.redisson.service.impl.RedissonDistributedLocker;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author xuguocai 2020/10/20 14:48
 */
@Configuration
@ConditionalOnClass(Config.class)
public class RedissonConfig {

    @Autowired
    private RedissonProperties redssionProperties;

    /**
     * 哨兵模式自动装配
     * @return
     */
    @Bean
//    @ConditionalOnProperty(name = {"spring.redis.master-name"})
    @ConditionalOnProperty(name="redisson.master-name")
    RedissonClient redissonSentinel() {
        Config config = new Config();
        SentinelServersConfig serverConfig = config.useSentinelServers().addSentinelAddress(redssionProperties.getSentinelAddresses())
                .setMasterName(redssionProperties.getMasterName())
                .setTimeout(redssionProperties.getTimeout())
                .setMasterConnectionPoolSize(redssionProperties.getMasterConnectionPoolSize())
                .setSlaveConnectionPoolSize(redssionProperties.getSlaveConnectionPoolSize());

        if(StringUtils.isNotBlank(redssionProperties.getPassword())) {
            serverConfig.setPassword(redssionProperties.getPassword());
        }
        return Redisson.create(config);
    }

    /**
     * 单机模式自动装配
     * @return
     */
    @Bean
//    @ConditionalOnProperty(name = {"spring.redis.host"})
    @ConditionalOnProperty(name="redisson.address")
    RedissonClient redissonSingle() {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(redssionProperties.getAddress())
                .setTimeout(redssionProperties.getTimeout())
                .setConnectionPoolSize(redssionProperties.getConnectionPoolSize())
                .setConnectionMinimumIdleSize(redssionProperties.getConnectionMinimumIdleSize());

        if(StringUtils.isNotBlank(redssionProperties.getPassword())) {
            serverConfig.setPassword(redssionProperties.getPassword());
        }

        return Redisson.create(config);
    }

    /**
     * 集群方式
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = {"spring.redis.cluster.nodes"})
    RedissonClient redissonCluster() {
        String[] nodes = ((String)this.redssionProperties.getCluster().get("nodes")).split(",");

        for(int i = 0; i < nodes.length; ++i) {
            nodes[i] = "redis://" + nodes[i];
        }

        Config config = new Config();
        ClusterServersConfig serverConfig =config.useClusterServers().setScanInterval(2000).addNodeAddress(nodes).setTimeout(this.redssionProperties.getTimeout())
                .setMasterConnectionPoolSize(this.redssionProperties.getMasterConnectionPoolSize())
                .setSlaveConnectionPoolSize(this.redssionProperties.getSlaveConnectionPoolSize())
               ;
        if (StringUtils.isNotBlank(this.redssionProperties.getPassword())) {
            serverConfig.setPassword(this.redssionProperties.getPassword());
        }

        return Redisson.create(config);
    }

    /**
     * 装配locker类，并将实例注入到RedissLockHelper中
     * @return
     */
    @Bean
    DistributedLocker distributedLocker(RedissonClient redissonClient) {
        DistributedLocker locker = new RedissonDistributedLocker();
        locker.setRedissonClient(redissonClient);
        RedissLockHelper.setLocker(locker);
        return locker;
    }
}