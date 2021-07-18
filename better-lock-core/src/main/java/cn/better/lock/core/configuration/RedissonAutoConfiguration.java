package cn.better.lock.core.configuration;

import cn.better.lock.core.properties.BetterLockProperties;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
@ConditionalOnProperty(prefix = "spring.better.lock", name = "lock-type", havingValue = BetterLockProperties.LockType.REDIS_CLUSTER_LOCK)
public class RedissonAutoConfiguration {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    @ConditionalOnProperty(prefix = "spring.redis.cluster", name = "nodes")
    public RedissonClient redissonCluster() {
        Config config = new Config();
        config.setTransportMode(TransportMode.EPOLL);
        for (String node : redisProperties.getCluster().getNodes()) {
            config.useClusterServers().addNodeAddress(node);
        }
        return Redisson.create(config);
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring.redis.cluster", name = "sentinel")
    public RedissonClient redissonSentinel() {
        Config config = new Config();
        config.setTransportMode(TransportMode.EPOLL);
        for (String node : redisProperties.getSentinel().getNodes()) {
            config.useSentinelServers().addSentinelAddress(node);
        }
        return Redisson.create(config);
    }

}