package com.alwin.eshop.inventory.request;

import com.alwin.eshop.inventory.model.ProductInventory;
import com.alwin.eshop.inventory.service.ProductInventoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class ProductInventoryCacheRefreshRequest implements Request {

    private Integer productId;

    private ProductInventoryService productInventoryService;

    @Override
    public void process() {
        // 从数据库中查询最新库存数量
        ProductInventory productInventory = productInventoryService.findProductInventory(productId);
        log.info("==========日志==========:已查询到商品最新的库存数量，productId={}, 商品库存={}",
                productInventory.getProductId(), productInventory.getInventoryCnt());
        // 将最新库存数量刷到缓存
        productInventoryService.setProductInventoryCache(productInventory);
    }

    @Override
    public Integer getProductId() {
        return productId;
    }
}
