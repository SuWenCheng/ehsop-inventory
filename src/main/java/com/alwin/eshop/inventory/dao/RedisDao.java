package com.alwin.eshop.inventory.dao;

import com.alwin.eshop.inventory.model.ProductInventory;

public interface RedisDao {

    void set(String key, String value);

    String get(String key);

    void removeProductInventoryCnt(ProductInventory productInventory);

}
