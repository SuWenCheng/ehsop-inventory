package com.alwin.eshop.inventory.dao.impl;

import com.alwin.eshop.inventory.dao.RedisDao;
import com.alwin.eshop.inventory.model.ProductInventory;
import com.alwin.eshop.inventory.util.ProductIdFormatHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class RedisDaoImpl implements RedisDao {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void removeProductInventoryCnt(ProductInventory productInventory) {
        String key  = ProductIdFormatHelper.generateRedisProductId(productInventory.getProductId());
        redisTemplate.delete(key);
        log.info("==========日志==========:已删除redis中缓存，key=" + key);
    }
}
