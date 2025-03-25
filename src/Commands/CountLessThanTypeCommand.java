package Commands;

import Collections.Ticket;
import Collections.TicketType;
import Validation.InputValidate;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Команда {@code СountLessThanTypeCommand} выполняет подсчет количества билетов в коллекции,
 * у которых значение поля {@code type} меньше заданного значения.
 * Тип билета сравнивается по длине строкового представления его имени.
 */
public class CountLessThanTypeCommand implements Command {

    // Коллекция билетов
    private static final TreeSet<Ticket> tickets = Ticket.getTicket(1);
    private Integer count = 0;

    /**
     * Выполняет команду без аргументов. Выводит информацию о том, что команда должна быть вызвана с аргументом типа.
     */
    @Override
    public void execute() {
        System.out.println("Команда count_less_than_type вводится без аргументов");
    }

    /**
     * Выполняет команду, которая подсчитывает количество билетов с полем {@code type},
     * длина имени которого меньше длины имени типа, переданного в аргументах.
     *
     * @param args Аргументы команды, в которых содержится тип билета для сравнения.
     */
    @Override
    public void execute(String[] args) {
        Iterator<Ticket> iterator = tickets.iterator();
        // Получаем валидный тип билета с помощью метода из InputValidate
        TicketType type = InputValidate.getValidTicketType(args[0]);

        // Если тип билета корректен
        if (type != null) {
            try {
                // Проходим по всем билетам в коллекции и считаем, сколько имеют длину имени меньше заданного типа
                while (iterator.hasNext()) {
                    Ticket ticket = iterator.next();
                    // Сравниваем длину строкового представления имени типа
                    if (ticket.getType().name().length() < type.name().length()) {
                        count++;
                    }
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
            // Выводим результат
            System.out.println("Билетов со значением поля type меньше " + type + ": " + count + " штук.");
        }
    }

    /**
     * Выводит описание команды {@code count_less_than_type}.
     * Команда используется для подсчета количества билетов с типом {@code type},
     * длина имени которого меньше длины строки типа, переданного в аргументах.
     */
    @Override
    public void PrintInfo() {
        System.out.println("count_less_than_type type - выводит количество элементов с полем type меньше заданного");
    }
}
