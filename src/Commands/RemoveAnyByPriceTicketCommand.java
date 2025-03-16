package Commands;

import Collections.Ticket;
import Validation.InputValidate;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Команда для удаления билета с заданной стоимостью.
 * Команда удаляет билет с наименьшей стоимостью и минимальным id, если цена соответствует введенному значению.
 */
public class RemoveAnyByPriceTicketCommand implements Command {

    private static final TreeSet<Ticket> tickets = Ticket.getTicket(1);

    /**
     * Метод выполняется при вызове команды без аргументов.
     * Выводит информацию о том, что команда требует аргумент с ценой.
     */
    @Override
    public void execute() {
        System.out.println("Команда remove_any_by_price вводится с аргументом price");
    }

    /**
     * Метод выполняется при вызове команды с аргументами.
     * Ищет и удаляет билет с указанной ценой. Удаляется первый найденный билет с минимальной ценой и минимальным id.
     * @param args массив строковых аргументов, где первый аргумент — это цена билета.
     */
    @Override
    public void execute(String[] args) {
        Iterator<Ticket> iterator = tickets.iterator();
        try {
            float price = InputValidate.getFloatInput(args[0]);  // Получаем цену из аргумента
            if (price <= 0) {
                return;
            }
            while (iterator.hasNext()) {
                Ticket ticket = iterator.next();

                if (ticket.getPrice() == price) {
                    iterator.remove();  // Удаляем билет с указанной ценой
                    System.out.println("Билет с ценой " + price + " удален\n" +
                            "██████╗░███████╗███╗░░░███╗░█████╗░██╗░░░██╗███████╗██████╗░\n" +
                            "██╔══██╗██╔════╝████╗░████║██╔══██╗██║░░░██║██╔════╝██╔══██╗\n" +
                            "██████╔╝█████╗░░██╔████╔██║██║░░██║╚██╗░██╔╝░█████╗░░██║░░██║\n" +
                            "██╔══██╗██╔══╝░░██║╚██╔╝██║██║░░██║░╚████╔╝░██╔══╝░░██║░░██║\n" +
                            "██║░░██║███████╗██║░╚═╝░██║╚█████╔╝░░╚██╔╝░░███████╗██████╔╝\n" +
                            "╚═╝░░╚═╝╚══════╝╚═╝░░░░░╚═╝░╚════╝░░░░╚═╝░░░╚══════╝╚═════╝░");
                    return;
                }
            }
            System.out.println("Ошибка: билет с такой ценой не найден.");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Метод выводит информацию о команде.
     * Информирует пользователя о том, что команда удаляет билет с заданной ценой.
     */
    @Override
    public void PrintInfo() {
        System.out.println("remove_any_by_price price - Команда удаляет билет с введенной стоимостью и наименьшим id.");
    }
}
