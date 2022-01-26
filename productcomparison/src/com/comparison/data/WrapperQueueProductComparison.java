package com.comparison.data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

/**
 * The type Wrapper queue product comparison.
 *
 * @author Catalin Margarit
 */
public class WrapperQueueProductComparison implements Serializable {
    private Queue queue = new LinkedList<String>();

    /**
     * Gets queue.
     *
     * @return the queue
     */
    public Queue getQueue() {
        return queue;
    }

    /**
     * Sets queue.
     *
     * @param queue the queue
     */
    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "WrapperQueueProductComparison{" +
                "queue=" + queue +
                '}';
    }
}
