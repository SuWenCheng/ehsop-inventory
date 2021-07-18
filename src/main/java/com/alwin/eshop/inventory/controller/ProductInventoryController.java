package com.alwin.eshop.inventory.controller;

import com.alwin.eshop.inventory.model.ProductInventory;
import com.alwin.eshop.inventory.request.ProductInventoryCacheRefreshRequest;
import com.alwin.eshop.inventory.request.ProductInventoryDBUpdateRequest;
import com.alwin.eshop.inventory.request.Request;
import com.alwin.eshop.inventory.service.ProductInventoryService;
import com.alwin.eshop.inventory.service.RequestAsyncProcessService;
import com.alwin.eshop.inventory.vo.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ProductInventoryController {

    private final ProductInventoryService productInventoryService;
    private final RequestAsyncProcessService requestAsyncProcessService;

    @GetMapping("/updateProductInventory")
    public Response updateProductInventory (ProductInventory productInventory) {
        Request request = new ProductInventoryDBUpdateRequest(productInventory,
                productInventoryService);
        Response response;
        log.info("==========日志==========:接收到一个更新库存请求，productId=" + productInventory.getProductId());
        try {
            requestAsyncProcessService.process(request);
            response = new Response(Response.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response = new Response(Response.FAILURE);
        }
        return response;
    }

    @GetMapping("/getProductInventory")
    public ProductInventory getProductInventory(Integer productId) {
        ProductInventory productInventory;
        log.info("==========日志==========:接收到一个读库存请求，productId=" + productId);
        try {
            // 尝试从缓存中获取数据
            productInventory = productInventoryService.getProductInventoryCache(productId);

            // 数据不在redis缓存中
            if (productInventory == null) {

                // 把更新缓存请求交给异步队列
                ProductInventoryCacheRefreshRequest request =
                        new ProductInventoryCacheRefreshRequest(productId, productInventoryService);
                requestAsyncProcessService.process(request);

                // 循环获取结果
                long currentTime = System.currentTimeMillis();
                long endTime;
                long waitTime = 0;
                while (true) {
                    if (waitTime > 25000) {
                        break;
                    }

                    // 尝试读缓存中的库存数据
                    productInventory = productInventoryService.getProductInventoryCache(productId);

                    //如果读到了，直接返回
                    if (productInventory != null) {
                        log.info("==========日志==========:在200毫秒内读取到了redis中的库存缓存，productId={}，商品库存数量={}",
                                productId, productInventory.getInventoryCnt());

                        return productInventory;
                    }

                    // 如果没读到，等待一段时间
                    else {
                        Thread.sleep(20);
                        endTime = System.currentTimeMillis();
                        waitTime = endTime - currentTime;
                    }
                }
            }

            // 尝试从数据库中读取数据
            productInventory = productInventoryService.findProductInventory(productId);
            if (productInventory != null) {
                return productInventory;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        productInventory = new ProductInventory(productId, -1);
        return productInventory;
    }
}
