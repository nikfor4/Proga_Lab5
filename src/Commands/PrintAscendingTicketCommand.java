package Commands;

public class PrintAscendingTicketCommand implements Command {
    @Override
    public void execute() {
        new ShowTicketsCommand().execute();
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Команда вводится без аргумента");
    }

    @Override
    public void PrintInfo() {
        System.out.println("print_ascending - вывод билетов в порядке возрастания id ");
    }
}

