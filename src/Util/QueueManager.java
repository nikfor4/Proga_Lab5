package Util;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Класс QueueManager используется для управления очередью строк.
 * Он предоставляет методы для добавления строк в очередь и получения самой очереди.
 * Если очередь переполняется, старые строки удаляются.
 */
public class QueueManager {
    private final Queue<String> queue;
    private final int maxSize;

    /**
     * Конструктор QueueManager инициализирует очередь с заданным максимальным размером.
     *
     * @param maxSize максимальный размер очереди
     */
    public QueueManager(int maxSize) {
        this.maxSize = maxSize;
        this.queue = new ArrayDeque<>(maxSize);
    }

    /**
     * Добавляет строку в очередь.
     * Если очередь заполнена до максимума, удаляется самая старая строка.
     *
     * @param value строка, которую нужно добавить в очередь
     */
    public void add(String value) {
        if (queue.size() >= maxSize) {
            queue.poll(); // Удаляем старые команды, если очередь переполнена
        }
        queue.offer(value);
    }

    /**
     * Возвращает очередь строк.
     *
     * @return очередь строк
     */
    public Queue<String> getQueue() {
        return queue;
    }
}
