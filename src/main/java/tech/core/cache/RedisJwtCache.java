package tech.core.cache;

import org.springframework.stereotype.Component;

@Component("redisJwtCache")
public class RedisJwtCache implements IJwtCache{


    @Override
    public void put(String token, Object value, long ttlSeconds) {

    }

    @Override
    public Object get(String token) {
        return null;
    }

    @Override
    public void remove(String token) {

    }
}
