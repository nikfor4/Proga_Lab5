package Collections;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс Ticket представляет билет с уникальным идентификатором, именем, координатами, ценой, типом и событием.
 * Билеты автоматически получают уникальный ID и время создания.
 */
public class Ticket implements Comparable<Ticket> {
    private static final TreeSet<Ticket> tickets = new TreeSet<>();
    private static final AtomicInteger idGenerator = new AtomicInteger(0); // Генератор ID

    private final int id; // Уникальный ID (генерируется автоматически)
    private String name;
    private Coordinates coordinates;
    private final LocalDateTime creationDate; // Дата создания (устанавливается автоматически)
    private float price;
    private TicketType type;
    private Event event;
    private LocalDateTime timevent;

    /**
     * Приватный конструктор для создания объекта Ticket.
     * Используйте фабричный метод {@link #createTicket(String, Coordinates, float, TicketType, Event,LocalDateTime)}.
     *
     * @param id          Уникальный идентификатор билета.
     * @param name        Название билета.
     * @param coordinates Координаты места.
     * @param price       Цена билета (должна быть больше 0).
     * @param type        Тип билета.
     * @param event       Событие, к которому относится билет.
     */
    private Ticket(int id, String name, Coordinates coordinates, float price, TicketType type, Event event,LocalDateTime eventTime ) {
        this.id = id;
        this.creationDate = eventTime;
        this.timevent = eventTime;
        setName(name);
        setCoordinates(coordinates);
        setPrice(price);
        this.type = type;
        this.event = event;
    }
    /**
     * Определяет следующее свободное ID, проверяя пропущенные значения.
     */
    private static int getNextId() {
        Set<Integer> usedIds = new HashSet<>();
        tickets.forEach(ticket -> usedIds.add(ticket.getId()));

        int nextId = 1;
        while (usedIds.contains(nextId)) {
            nextId++;
        }
        return nextId;
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
    public static Ticket createTicket(String name, Coordinates coordinates, float price, TicketType type, Event event, LocalDateTime timevent) {
        Ticket ticketTMP = new Ticket(getNextId(), name, coordinates, price, type, event, timevent);
        tickets.add(ticketTMP);
        return ticketTMP;
    }
    public static TreeSet<Ticket> getTicket(int id) {
        return tickets;
    }
    public static Ticket updateTicket(int id, String name, Coordinates coordinates, float price, TicketType type, Event event, LocalDateTime timevent) {

                Ticket ticketTMP = new Ticket(id, name, coordinates, price, type, event, timevent);
                tickets.add(ticketTMP);
                // Обновите все необходимые поля
                return ticketTMP;  // Возвращаем обновленный объект

    }

    /** @return Уникальный идентификатор билета. */
    public int getId() { return id; }

    /** @return Название билета. */
    public String getName() { return name; }

    /** @return Координаты билета. */
    public Coordinates getCoordinates() { return coordinates; }

    /** @return Дата создания билета. */
    public LocalDateTime getCreationDate() { return creationDate; }

    /** @return Время события билета. */
    public LocalDateTime getTimevent() { return timevent; }

    /** @return Цена билета. */
    public float getPrice() { return price; }

    /** @return Тип билета. */
    public TicketType getType() { return type; }

    /**
     * @return Событие, к которому относится билет.
     */
    public String getEvent() { return event.getEventType() + "," + event.getName() + "," + event.getMinAge() + "," + event.getEventType(); }

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


    @Override
    public String toString() {
        String minutes = String.format("%02d", timevent.getMinute());
        return "id: " + id + "\nназвание: " + name + "\nкоординаты: X: " + coordinates.getX() +
                ", Y: "+ coordinates.getY() + "\nдата: "+ timevent.getDayOfMonth() + " " + timevent.getMonth() +
                " " + timevent.getYear() + "\nвремя: " + timevent.getHour() +":"+ minutes + "\nЦена: "
                + price + "\nТип билета: " + type + "\nНазвание мероприятия: " + event.getName() + "\nМинимальный возраст: " +
                event.getMinAge() + "\nТип мероприятия: " + event.getEventType();
    }
    /**
     * Преобразует билет в строку для удобного вывода.
     *
     * @return Строковое представление билета.
     */
    public String toCSV() {
        return id + "," + name + "," + coordinates + ","+ timevent + "," + price + "," + type + "," + event;
    }

}
