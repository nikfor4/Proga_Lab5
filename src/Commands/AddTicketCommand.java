package Commands;

public class AddTicketCommand implements Command {

    private String argument;

    public AddTicketCommand(String argument) {
        this.argument = argument;
        
    }

    @Override
    public void execute() {
        System.out.println("Добавляем билет: " + argument);
    }

    @Override
    public void execute(String[] args) {

    }

    @Override
    public void PrintInfo() {
        System.out.println("Команда для добавления билета.");
    }
}