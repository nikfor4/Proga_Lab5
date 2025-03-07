package Collections;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Ticket implements Comparable<Ticket> {
    private static final AtomicInteger idGenerator = new AtomicInteger(0); // Генератор ID

    private final int id; // Уникальный ID (генерируется автоматически)
    private String name;
    private Coordinates coordinates;
    private final LocalDateTime creationDate;
    private float price;
    private TicketType type;
    private Event event;

    public Ticket(String name, Coordinates coordinates, float price, TicketType type, Event event) {
        this.id = idGenerator.incrementAndGet();
        this.creationDate = LocalDateTime.now();
        setName(name);
        setCoordinates(coordinates);
        setPrice(price);
        this.type = type;
        this.event = event;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public Coordinates getCoordinates() { return coordinates; }
    public LocalDateTime getCreationDate() { return creationDate; }
    public float getPrice() { return price; }
    public TicketType getType() { return type; }
    public Event getEvent() { return event; }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Координаты не могут быть null");
        }
        this.coordinates = coordinates;
    }

    public void setPrice(float price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше 0");
        }
        this.price = price;
    }

    @Override
    public int compareTo(Ticket other) {
        return Integer.compare(this.id, other.id); // Сортируем по ID
    }

    @Override
    public String toString() {
        return id + "," + name + "," + coordinates + "," + creationDate + "," + price + "," + type + "," + event;
    }
}
