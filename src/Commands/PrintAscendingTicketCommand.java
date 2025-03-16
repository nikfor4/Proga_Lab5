package Commands;

/**
 * Команда {@code PrintAscendingTicketCommand} отвечает за вывод билетов в порядке возрастания их уникальных идентификаторов (ID).
 * Эта команда вызывает {@link ShowTicketsCommand} для отображения билетов.
 */
public class PrintAscendingTicketCommand implements Command {

    /**
     * Выполняет команду, выводя билеты в порядке возрастания их уникальных идентификаторов.
     * Для этого используется команда {@code ShowTicketsCommand}.
     */
    @Override
    public void execute() {
        new ShowTicketsCommand().execute();
    }

    /**
     * Выводит сообщение о том, что команда {@code print_ascending} не принимает аргументы.
     * Этот метод вызывается, если команда вызывается с аргументами.
     *
     * @param args Аргументы команды (не используются в данной реализации).
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Команда вводится без аргумента");
    }

    /**
     * Выводит описание команды {@code print_ascending}.
     * Эта команда используется для вывода билетов в порядке возрастания их уникальных идентификаторов.
     */
    @Override
    public void PrintInfo() {
        System.out.println("print_ascending - вывод билетов в порядке возрастания id ");
    }
}
