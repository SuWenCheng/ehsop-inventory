package com.alwin.eshop.inventory.service.impl;

import com.alwin.eshop.inventory.dao.RedisDao;
import com.alwin.eshop.inventory.mapper.ProductInventoryMapper;
import com.alwin.eshop.inventory.model.ProductInventory;
import com.alwin.eshop.inventory.service.ProductInventoryService;
import com.alwin.eshop.inventory.util.ProductIdFormatHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ProductInventoryServiceImpl implements ProductInventoryService {

    private final ProductInventoryMapper productInventoryMapper;
    private final RedisDao redisDao;

    @Override
    public void updateProductInventory(ProductInventory productInventory) {
        productInventoryMapper.updateProductInventory(productInventory);
    }

    @Override
    public void removeProductInventoryCache(ProductInventory productInventory) {
        redisDao.removeProductInventoryCnt(productInventory);
    }

    @Override
    public ProductInventory findProductInventory(Integer productId) {
        return productInventoryMapper.findById(productId);
    }

    @Override
    public void setProductInventoryCache(ProductInventory productInventory) {
        String key = ProductIdFormatHelper.generateRedisProductId(productInventory.getProductId());
        redisDao.set(key, String.valueOf(productInventory.getInventoryCnt()));
        log.info("==========日志==========:已更新商品库存的缓存，productId={}, 商品库存={}, key={}",
                productInventory.getProductId(), productInventory.getInventoryCnt(), key);
    }

    @Override
    public ProductInventory getProductInventoryCache(Integer productId) {
        String key = ProductIdFormatHelper.generateRedisProductId(productId);
        String productInventoryCnt = redisDao.get(key);
        if (productInventoryCnt == null || "".equals(productInventoryCnt)) {
            return null;
        }
        Integer count = Integer.parseInt(productInventoryCnt);
        ProductInventory productInventory = new ProductInventory(productId, count);
        return productInventory;
    }

}
