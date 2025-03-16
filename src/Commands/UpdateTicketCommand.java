package Commands;

import Collections.Ticket;
import Collections.Event;
import Collections.EventType;
import Collections.TicketType;
import Collections.Coordinates;
import Validation.InputValidate;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Команда для обновления существующего билета по его ID.
 * Пользователь вводит данные для обновления, и если билет с таким ID существует, его данные обновляются.
 */
public class UpdateTicketCommand implements Command {

    // Коллекция билетов
    private static final TreeSet<Ticket> tickets = Ticket.getTicket(1);

    /**
     * Выполняет команду обновления билета.
     * Если команда вызывается без аргументов, она выводит сообщение о том, что команда введена некорректно.
     *
     * @param args массив строковых аргументов (не используется).
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Команда update вводится без аргументов");
    }

    /**
     * Выполняет команду обновления билета. Запрашивает у пользователя новые данные для билета,
     * после чего обновляет существующий билет с заданным ID.
     */
    @Override
    public void execute() {
        Iterator<Ticket> iterator = tickets.iterator();
        Scanner scanner = new Scanner(System.in);
        try {
            // Запрашиваем у пользователя id билета
            int id = InputValidate.getIntInput(scanner, "Введите id билета:");
            if (id == Integer.MIN_VALUE) {
                System.out.println("Билет не был создан.");
                return;
            }

            // Запрашиваем другие данные билета
            String name = InputValidate.getInput(scanner, "Введите имя билета: ");
            if (name == null) {
                System.out.println("Билет не был создан.");
                return;
            }

            int x = InputValidate.getIntInput(scanner, "Введите координату X: ");
            if (x == Integer.MIN_VALUE) {
                System.out.println("Билет не был создан.");
                return;
            }

            int y = InputValidate.getIntInput(scanner, "Введите координату Y: ");
            if (y == Integer.MIN_VALUE) {
                System.out.println("Билет не был создан.");
                return;
            }

            Coordinates coordinates = new Coordinates(x, y);

            float price = InputValidate.getFloatInput(scanner, "Введите цену билета больше 0: ");
            if (price == Float.MIN_VALUE) {
                System.out.println("Билет не был создан.");
                return;
            }

            // Получаем текущее время для события
            LocalDateTime eventTime = LocalDateTime.now();

            // Запрашиваем тип билета
            TicketType ticketType = InputValidate.getValidTicketType(scanner, "Введите тип билета (VIP, USUAL, BUDGETARY): ");
            if (ticketType == null) {
                System.out.println("Билет не был создан.");
                return;
            }

            // Запрашиваем название события
            String eventName = InputValidate.getInput(scanner, "Введите название события: ");
            if (eventName == null) {
                System.out.println("Билет не был создан.");
                return;
            }

            int eventAge = InputValidate.getIntInputPlus(scanner, "Введите возраст для события < 0: ");
            if (eventAge == Integer.MIN_VALUE) {
                System.out.println("Билет не был создан.");
                return;
            }

            // Запрашиваем тип события
            EventType eventType = InputValidate.getValidEventType(scanner, "Введите тип события (CONCERT, FOOTBALL, BASKETBALL, OPERA, EXPOSITION): ");
            if (eventType == null) {
                System.out.println("Билет не был создан.");
                return;
            }

            // Создаем событие
            Event event = Event.createEvent(eventName, eventAge, eventType);

            // Ищем билет с указанным ID и обновляем его
            while (iterator.hasNext()) {
                Ticket ticket = iterator.next();

                if (ticket.getId() == id) {
                    iterator.remove(); // Удаляем старый билет
                    Ticket updatedTicket = Ticket.updateTicket(id, name, coordinates, price, ticketType, event, eventTime);
                    System.out.println("Билет с ID " + id + " обновлен: " + updatedTicket);
                    return;
                }
            }

            System.out.println("Ошибка: билет с ID " + id + " не найден.");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Метод выводит описание команды, информируя пользователя, что она обновляет данные билета по ID.
     */
    @Override
    public void PrintInfo() {
        System.out.println("update - Команда для обновления билета по ID.");
    }
}
