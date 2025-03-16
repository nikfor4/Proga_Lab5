package Collections;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс {@code Event} представляет собой событие с уникальным идентификатором, названием, минимальным возрастом и типом.
 */
public class Event {
    private static final TreeSet<Ticket> Events = new TreeSet<>();
    private static final AtomicInteger idGenerator = new AtomicInteger(1); // Генератор уникальных ID

    private final long id; // Уникальный ID, генерируется автоматически, больше 0
    private String name;   // Название события (не может быть null или пустым)
    private long minAge;   // Минимальный возраст для участия
    private EventType eventType; // Тип события (не может быть null)

    /**
     * Создает объект {@code Event} с указанным названием.
     *
     * @param name Название события (не может быть null или пустым)
     */
    public Event(String name) {
        this.id = idGenerator.getAndIncrement();
        setName(name);
    }

    /**
     * Создает объект {@code Event} с указанным названием и типом события.
     *
     * @param name      Название события (не может быть null или пустым)
     * @param eventType Тип события (не может быть null)
     */
    public Event(String name, EventType eventType) {
        this.id = idGenerator.getAndIncrement();
        setName(name);
        setEventType(eventType);
    }


    /**
     * Определяет следующее свободное ID, проверяя пропущенные значения.
         */
        private static int getNextId() {
        Set<Integer> usedIds = new HashSet<>();
            Events.forEach(event -> usedIds.add(event.getId()));

        int nextId = 1;
        while (usedIds.contains(nextId)) {
            nextId++;
        }
        return nextId;
            }
    /**
     * Создает событие с полным набором параметров.
     *
     * @param id        Уникальный идентификатор события (должен быть больше 0)
     * @param name      Название события (не может быть null или пустым)
     * @param minAge    Минимальный возраст (не может быть отрицательным)
     * @param eventType Тип события (не может быть null)
     * @throws IllegalArgumentException если параметры не соответствуют требованиям
     */
    public Event(long id, String name, long minAge, EventType eventType) {
        if (id < 1) {
            throw new IllegalArgumentException("ID должен быть больше 0");
        }
        this.id = id;
        setName(name);
        setMinAge(minAge);
        setEventType(eventType);
    }

    /**
     * Создает событие, если все параметры корректны.
     * Если хотя бы один параметр не проходит проверку, событие не создается.
     *
     * @param name      Название события
     * @param minAge    Минимальный возраст
     * @param eventType Тип события
     * @return Созданный объект {@code Event} или {@code null}, если параметры некорректны
     */
    public static Event createEvent(String name, long minAge, EventType eventType) {
        if (name == null || name.isEmpty()) {
            System.out.println("Ошибка: название события не может быть пустым.");
            return null;
        }
        if (minAge < 0) {
            System.out.println("Ошибка: минимальный возраст не может быть отрицательным.");
            return null;
        }
        if (eventType == null) {
            System.out.println("Ошибка: тип события не может быть null.");
            return null;
        }
        return new Event(getNextId(), name, minAge, eventType);
    }
    public static Event updateEvent(int id, String name, long minAge, EventType eventType) {
        if (name == null || name.isEmpty()) {
            System.out.println("Ошибка: название события не может быть пустым.");
            return null;
        }
        if (minAge < 0) {
            System.out.println("Ошибка: минимальный возраст не может быть отрицательным.");
            return null;
        }
        if (eventType == null) {
            System.out.println("Ошибка: тип события не может быть null.");
            return null;
        }
        return new Event(id, name, minAge, eventType);
    }

    /**
     * Выводит информацию о событии в консоль.
     */
    public void printEvent() {
        System.out.println("ID: " + id + ", Название: " + name + ", Мин. возраст: " + minAge + ", Тип: " + eventType);
    }

    /**
     * Выводит информацию о событии в консоль.
     *
     * @return
     */
    @Override
    public String toString() {
        return  name + "," + minAge + "," + eventType;
    }
    /**
     * Получает уникальный идентификатор события.
     *
     * @return ID события
     */
    public long getId() {
        return id;
    }

    /**
     * Получает название события.
     *
     * @return Название события
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название события.
     *
     * @param name Новое название события
     * @throws IllegalArgumentException если название пустое или null
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Название события не может быть пустым");
        }
        this.name = name;
    }

    /**
     * Получает минимальный возраст для участия в событии.
     *
     * @return Минимальный возраст
     */
    public long getMinAge() {
        return minAge;
    }

    /**
     * Устанавливает минимальный возраст.
     *
     * @param minAge Новый минимальный возраст
     * @throws IllegalArgumentException если значение отрицательное
     */
    public void setMinAge(long minAge) {
        if (minAge < 0) {
            throw new IllegalArgumentException("Минимальный возраст не может быть отрицательным");
        }
        this.minAge = minAge;
    }

    /**
     * Получает тип события.
     *
     * @return Тип события
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * Устанавливает тип события.
     *
     * @param eventType Новый тип события
     * @throws IllegalArgumentException если значение null
     */
    public void setEventType(EventType eventType) {
        if (eventType == null) {
            throw new IllegalArgumentException("Тип события не может быть null");
        }
        this.eventType = eventType;
    }
}
