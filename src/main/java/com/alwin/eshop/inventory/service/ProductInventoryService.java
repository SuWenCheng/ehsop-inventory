package com.alwin.eshop.inventory.service;

import com.alwin.eshop.inventory.model.ProductInventory;

public interface ProductInventoryService {

    void updateProductInventory(ProductInventory productInventory);

    void removeProductInventoryCache(ProductInventory productInventory);

    ProductInventory findProductInventory(Integer productId);

    void setProductInventoryCache(ProductInventory productInventory);

    ProductInventory getProductInventoryCache(Integer productId);
}
