package com.alwin.eshop.inventory.mapper;

import com.alwin.eshop.inventory.model.ProductInventory;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInventoryMapper {

    ProductInventory findById(Integer productId);

    void updateProductInventory(ProductInventory productInventory);
}