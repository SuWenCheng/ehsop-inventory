package com.alwin.eshop.inventory.request;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 请求内存队列
 */
public class RequestQueue {

    private List<ArrayBlockingQueue<Request>> queues = new ArrayList<>();

    private RequestQueue() {

    }

    private static class Singleton {
        private static RequestQueue instance;
        static {
            instance = new RequestQueue();
        }
        public static RequestQueue getInstance() {
            return instance;
        }
    }
    public static RequestQueue getInstance() {
        return Singleton.getInstance();
    }

    public void addQueue(ArrayBlockingQueue queue) {
        queues.add(queue);
    }

    public Integer getQueueSize() {
        return queues.size();
    }

    public ArrayBlockingQueue<Request> getQueue(int index) {
        return queues.get(index);
    }
}
