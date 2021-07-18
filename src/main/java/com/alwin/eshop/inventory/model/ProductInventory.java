package com.alwin.eshop.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInventory {

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "inventory_cnt")
    private Integer inventoryCnt;

}
