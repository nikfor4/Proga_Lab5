package Commands;

import Util.QueueManager;
import java.util.HashMap;
import java.util.Map;

public class CommandProcessor {
    public static Map<String, Command> commands;
    private final QueueManager historyManager; // Менеджер истории команд

    public CommandProcessor(QueueManager historyManager) {
        this.historyManager = historyManager;
        commands = new HashMap<>();

        // Добавляем команды
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand());
        commands.put("history", new HistoryCommand(historyManager));
    }

    public void CommandPut(String name) {

        Command command = commands.get(name);
        if (command != null) {
            historyManager.add(name); // Записываем команду в историю
            command.execute();
        } else {
            System.out.println("Неизвестная команда: " + name);
            System.out.println("Воспользуйтесь командой help, что бы узнать какие команды существуют");
        }
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
