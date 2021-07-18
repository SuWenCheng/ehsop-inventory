package com.alwin.eshop.inventory.service.impl;

import com.alwin.eshop.inventory.request.Request;
import com.alwin.eshop.inventory.request.RequestQueue;
import com.alwin.eshop.inventory.service.RequestAsyncProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;

@Service
@Slf4j
public class RequestAsyncProcessServiceImpl implements RequestAsyncProcessService {

    @Override
    public void process(Request request) {
        ArrayBlockingQueue<Request> queue = getBlockingQueue(request.getProductId());
        try {
            queue.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ArrayBlockingQueue<Request> getBlockingQueue(Integer productId) {
        RequestQueue queue = RequestQueue.getInstance();
        Integer queueSize = queue.getQueueSize();
        String key = String.valueOf(productId);
        int h;
        int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        int index = (queueSize - 1) & hash;
        log.info("==========日志==========:路由内存队列，productId={}, 队列索引={}", productId, index);
        return queue.getQueue(index);
    }
}
