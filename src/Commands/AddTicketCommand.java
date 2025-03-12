package Commands;

import Collections.Ticket;
import Collections.Event;
import Collections.EventType;
import Collections.TicketType;
import Collections.Coordinates;
import Validation.InputValidate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.TreeSet;
import java.time.format.DateTimeParseException;

/**
 * Команда для добавления нового билета.
 */
public class AddTicketCommand implements Command {
    private static final TreeSet<Ticket> tickets = new TreeSet<>();

    /**
     * Выполняет команду добавления нового билета путем пошагового запроса данных у пользователя.
     */
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        try {
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

            LocalDateTime eventTime = LocalDateTime.now();

            TicketType ticketType = InputValidate.getValidTicketType(scanner, "Введите тип билета (VIP, USUAL, BUDGETARY): ");
            if (ticketType == null) {
                System.out.println("Билет не был создан.");
                return;
            }

            EventType eventType = InputValidate.getValidEventType(scanner, "Введите тип события (CONCERT, FOOTBALL, BASKETBALL, OPERA, EXPOSITION): ");
            if (eventType == null) {
                System.out.println("Билет не был создан.");
                return;
            }

            Event event = new Event("Default Event", eventType);
            Ticket ticket = Ticket.createTicket(name, coordinates, price, ticketType, event, eventTime);

            if (tickets.add(ticket)) {
                System.out.println("Билет " + name + " добавлен.");
            } else {
                System.out.println("Билет уже существует: " + ticket);
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }



    /**
     * Заглушка для метода выполнения команды с аргументами, сообщает об ошибке.
     *
     * @param args Аргументы команды (не используются).
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Ошибка: используйте команду без аргументов для пошагового ввода данных.");
    }

    /**
     * Выводит информацию о команде.
     */
    @Override
    public void PrintInfo() {
        System.out.println("Add - Команда для обновления билета по ID.");
    }
}
