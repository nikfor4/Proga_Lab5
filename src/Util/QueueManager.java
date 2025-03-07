package Util;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueManager {
    private final Queue<String> queue;
    private final int maxSize;
// frfrew
    public QueueManager(int maxSize) {
        this.maxSize = maxSize;
        this.queue = new ArrayDeque<>(maxSize);
    }

    public void add(String value) {
        if (queue.size() >= maxSize) {
            queue.poll(); // Удаляем старые команды, если очередь переполнена
        }
        queue.offer(value);
    }

    public Queue<String> getQueue() {
        return queue;
    }
}

