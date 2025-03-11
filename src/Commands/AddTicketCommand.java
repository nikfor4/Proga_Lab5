package Commands;

import Collections.Ticket;
import Collections.Event;
import Collections.EventType;
import Collections.TicketType;
import Collections.Coordinates;

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
            String name = getInput(scanner, "Введите имя билета: ");
            int x = getIntInput(scanner, "Введите координату X: ");
            int y = getIntInput(scanner, "Введите координату Y: ");
            Coordinates coordinates = new Coordinates(x, y);
            float price = (float) (getIntInput(scanner, "Введите цену билета: "));
            LocalDateTime eventTime = getDateTimeInput(scanner, "Введите дату и время события (yyyy-MM-ddTHH:mm:ss): ");
            TicketType ticketType = getValidTicketType(scanner, "Введите тип билета (VIP, USUAL, BUDGETARY): ");
            EventType eventType = getValidEventType(scanner, "Введите тип события (CONCERT, FOOTBALL, BASKETBALL, OPERA, EXPOSITION): ");
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
     * Запрашивает ввод данных у пользователя, проверяя их на корректность.
     * Позволяет пользователю выйти из команды, введя "quit".
     *
     * @param scanner Scanner для считывания ввода пользователя.
     * @param prompt Сообщение для запроса ввода.
     * @return Корректное введенное значение.
     */
    private String getInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Выход из команды.");
                System.exit(0);
            }
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Ошибка: ввод не может быть пустым. Попробуйте снова.");
        }
    }

    /**
     * Запрашивает ввод целочисленного значения у пользователя с проверкой корректности.
     * Позволяет пользователю выйти из команды, введя "quit".
     *
     * @param scanner Scanner для считывания ввода пользователя.
     * @param prompt Сообщение для запроса ввода.
     * @return Корректное целочисленное значение.
     */
    private int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Выход из команды.");
                System.exit(0);
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное целочисленное значение.");
            }
        }
    }

    /**
     * Запрашивает ввод даты и времени у пользователя с проверкой корректности.
     * Позволяет пользователю выйти из команды, введя "quit".
     *
     * @param scanner Scanner для считывания ввода пользователя.
     * @param prompt  Сообщение для запроса ввода.
     * @return Корректное значение LocalDateTime.
     */
    private LocalDateTime getDateTimeInput(Scanner scanner, String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Выход из команды.");
                System.exit(0);
            }
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Ошибка: введите дату и время в формате yyyy-MM-ddTHH:mm:ss.");
            }
        }
    }

    /**
     * Запрашивает ввод типа билета и проверяет корректность ввода.
     * Позволяет пользователю выйти из команды, введя "quit".
     *
     * @param scanner Scanner для считывания ввода пользователя.
     * @param prompt  Сообщение для запроса ввода.
     * @return Корректное значение TicketType.
     */
    private TicketType getValidTicketType(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Выход из команды.");
                System.exit(0);
            }
            try {
                return TicketType.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: допустимые значения - VIP, USUAL, BUDGETARY. Попробуйте снова.");
            }
        }
    }

    /**
     * Запрашивает ввод типа события и проверяет корректность ввода.
     * Позволяет пользователю выйти из команды, введя "quit".
     *
     * @param scanner Scanner для считывания ввода пользователя.
     * @param prompt  Сообщение для запроса ввода.
     * @return Корректное значение EventType.
     */
    private EventType getValidEventType(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Выход из команды.");
                System.exit(0);
            }
            try {
                return EventType.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: допустимые значения - CONCERT, FOOTBALL, BASKETBALL, OPERA, EXPOSITION. Попробуйте снова.");
            }
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
