package Commands;

import Collections.Ticket;
import Collections.Event;
import Collections.EventType;
import Collections.TicketType;
import Collections.Coordinates;
import Validation.InputValidate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Команда для обновления существующего билета по его ID.
 */
public class UpdateTicketCommand implements Command {
    private static final TreeSet<Ticket> tickets = Ticket.getTicket(1);

    @Override
    public void execute(String[] args) {
        System.out.println("Ошибка вводите без аргумента");
    }

    /**
     * Выполняет команду добавления нового билета путем пошагового запроса данных у пользователя.
     */
    @Override
    public void execute() {
        Iterator<Ticket> iterator = tickets.iterator();
        Scanner scanner = new Scanner(System.in);
        try {
            int id = InputValidate.getIntInput(scanner, "Введите id билета:");
            if (id == Integer.MIN_VALUE) {
                System.out.println("Билет не был создан.");
                return;
            }
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


            while (iterator.hasNext()){
                Ticket ticket = iterator.next();

                if (ticket.getId() == id) {
                    iterator.remove(); // Удаляем старый билет
                    Ticket updatedTicket = Ticket.updateTicket(id, name, coordinates, price, ticketType, event,eventTime);
                    System.out.println("Билет с ID " + id + " обновлен: " + updatedTicket);
                    return;
                }


            }
            System.out.println("Ошибка: билет с ID " + id + " не найден.");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }



    }


    @Override
    public void PrintInfo() {
        System.out.println("Update - Команда для обновления билета по ID.");
    }
}
