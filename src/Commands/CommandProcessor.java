package Commands;

import Util.QueueManager;
import java.util.HashMap;
import java.util.Map;

public class CommandProcessor {
    public static Map<String, Command> commands;
    private final QueueManager historyManager;

    public CommandProcessor(QueueManager historyManager) {
        this.historyManager = historyManager;
        commands = new HashMap<>();

        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand());
        commands.put("history", new HistoryCommand(historyManager));
        commands.put("add", new AddTicketCommand());
        commands.put("update", new UpdateTicketCommand());
        commands.put("show", new ShowTicketsCommand());
    }

    public void CommandPut(String input) {
        String[] parts = input.split(" ", 2);
        String name = parts[0];
        String[] args = parts.length > 1 ? parts[1].split(",") : new String[0];

        Command command = commands.get(name);
        if (command != null) {
            historyManager.add(name);
            if (args.length > 0) {
                command.execute(args);
            } else {
                command.execute();
            }
        } else {
            System.out.println("Неизвестная команда: " + name);
            System.out.println("Воспользуйтесь командой help, чтобы узнать доступные команды.");
        }
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
