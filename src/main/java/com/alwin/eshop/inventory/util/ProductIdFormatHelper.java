package com.alwin.eshop.inventory.util;

public final class ProductIdFormatHelper {

    private static final String REDIS_PRODUCT_ID_FORMAT = "product:inventory:%s";

    public static String generateRedisProductId(Integer productId) {
        return String.format(REDIS_PRODUCT_ID_FORMAT, productId);
    }

}
