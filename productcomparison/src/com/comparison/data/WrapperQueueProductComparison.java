package com.comparison.data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Catalin Margarit
 */
public class WrapperQueueProductComparison implements Serializable {
    private Queue queue = new LinkedList<String>();

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    @Override
    public String toString() {
        return "WrapperQueueProductComparison{" +
                "queue=" + queue +
                '}';
    }
}
