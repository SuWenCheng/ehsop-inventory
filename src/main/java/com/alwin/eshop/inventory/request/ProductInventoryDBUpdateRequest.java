package com.alwin.eshop.inventory.request;

import com.alwin.eshop.inventory.model.ProductInventory;
import com.alwin.eshop.inventory.service.ProductInventoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class ProductInventoryDBUpdateRequest implements Request {

    private ProductInventory productInventory;

    private ProductInventoryService productInventoryService;

    @Override
    public void process() {
        // 删除缓存库存数量
        log.info("==========日志==========:删除库存的缓存，productId="
                + productInventory.getProductId() + ", 新的库存数量=" + productInventory.getInventoryCnt());
        productInventoryService.removeProductInventoryCache(productInventory);

        // 模拟卡顿
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 修改数据库的库存
        productInventoryService.updateProductInventory(productInventory);
        log.info("==========日志==========:已修改数据库的库存，productId=" +
                + productInventory.getProductId() + ", 新的库存数量=" + productInventory.getInventoryCnt());
    }

    @Override
    public Integer getProductId() {
        return productInventory.getProductId();
    }
}
