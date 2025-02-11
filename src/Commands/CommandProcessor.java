package Commands;

import java.util.HashMap;
import java.util.Map;

public class CommandProcessor {
    public static Map<String, Command> commands;


    public void CommandPut(String name) {
        // Словарь
        commands = new HashMap<>();
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand());
        // Выполняем команду, которую ввел пользователь
        commands.get(name).execute();

    }
    public Map<String, Command> getCommands() { // Геттер для доступа к Map
        return commands;
    }
}