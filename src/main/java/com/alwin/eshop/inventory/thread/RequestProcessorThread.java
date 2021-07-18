package com.alwin.eshop.inventory.thread;

import com.alwin.eshop.inventory.request.Request;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

@Slf4j
public class RequestProcessorThread implements Callable<Boolean> {

    private ArrayBlockingQueue<Request> queue;

    public RequestProcessorThread (ArrayBlockingQueue<Request> queue) {
        this.queue = queue;
    }

    @Override
    public Boolean call() {
        try {
            while (true) {
                Request request = queue.take();
                log.info("==========日志==========:工作线程处理请求，productId={}", request.getProductId());
                request.process();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
