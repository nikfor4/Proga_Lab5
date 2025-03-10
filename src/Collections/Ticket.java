package Collections;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс Ticket представляет билет с уникальным идентификатором, именем, координатами, ценой, типом и событием.
 * Билеты автоматически получают уникальный ID и время создания.
 */
public class Ticket implements Comparable<Ticket> {
    private static final AtomicInteger idGenerator = new AtomicInteger(0); // Генератор ID

    private final int id; // Уникальный ID (генерируется автоматически)
    private String name;
    private Coordinates coordinates;
    private final LocalDateTime creationDate; // Дата создания (устанавливается автоматически)
    private float price;
    private TicketType type;
    private Event event;

    /**
     * Приватный конструктор для создания объекта Ticket.
     * Используйте фабричный метод {@link #createTicket(String, Coordinates, float, TicketType, Event)}.
     *
     * @param id          Уникальный идентификатор билета.
     * @param name        Название билета.
     * @param coordinates Координаты места.
     * @param price       Цена билета (должна быть больше 0).
     * @param type        Тип билета.
     * @param event       Событие, к которому относится билет.
     */
    private Ticket(int id, String name, Coordinates coordinates, float price, TicketType type, Event event) {
        this.id = id;
        this.creationDate = LocalDateTime.now();
        setName(name);
        setCoordinates(coordinates);
        setPrice(price);
        this.type = type;
        this.event = event;
    }

    /**
     * Фабричный метод для создания нового билета.
     *
     * @param name        Название билета.
     * @param coordinates Координаты места.
     * @param price       Цена билета (должна быть больше 0).
     * @param type        Тип билета.
     * @param event       Событие, к которому относится билет.
     * @return Новый объект {@link Ticket}.
     */
    public static Ticket createTicket(String name, Coordinates coordinates, float price, TicketType type, Event event) {
        return new Ticket(idGenerator.incrementAndGet(), name, coordinates, price, type, event);
    }

    /** @return Уникальный идентификатор билета. */
    public int getId() { return id; }

    /** @return Название билета. */
    public String getName() { return name; }

    /** @return Координаты билета. */
    public Coordinates getCoordinates() { return coordinates; }

    /** @return Дата создания билета. */
    public LocalDateTime getCreationDate() { return creationDate; }

    /** @return Цена билета. */
    public float getPrice() { return price; }

    /** @return Тип билета. */
    public TicketType getType() { return type; }

    /** @return Событие, к которому относится билет. */
    public Event getEvent() { return event; }

    /**
     * Устанавливает название билета.
     *
     * @param name Новое название билета (не может быть пустым).
     * @throws IllegalArgumentException если название пустое или null.
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }
        this.name = name;
    }

    /**
     * Устанавливает координаты билета.
     *
     * @param coordinates Новые координаты билета.
     * @throws IllegalArgumentException если координаты равны null.
     */
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Координаты не могут быть null");
        }
        this.coordinates = coordinates;
    }

    /**
     * Устанавливает цену билета.
     *
     * @param price Новая цена билета (должна быть больше 0).
     * @throws IllegalArgumentException если цена меньше или равна 0.
     */
    public void setPrice(float price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше 0");
        }
        this.price = price;
    }

    /**
     * Сравнивает билеты по ID.
     *
     * @param other Другой билет для сравнения.
     * @return Отрицательное число, ноль или положительное число, если этот билет меньше, равен или больше другого.
     */
    @Override
    public int compareTo(Ticket other) {
        return Integer.compare(this.id, other.id);
    }

    /**
     * Преобразует билет в строку для удобного вывода.
     *
     * @return Строковое представление билета.
     */
    @Override
    public String toString() {
        return id + "," + name + "," + coordinates + "," + creationDate + "," + price + "," + type + "," + event;
    }
}
