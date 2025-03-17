package Commands;

import Util.QueueManager;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс {@code CommandProcessor} отвечает за обработку и выполнение команд.
 * Он управляет списком доступных команд и передает управление соответствующему объекту команды.
 */
public class CommandProcessor {
    /** Коллекция доступных команд, где ключ - это название команды, а значение - соответствующий объект {@code Command}. */
    public static Map<String, Command> commands;

    /** Менеджер истории команд. */
    private final QueueManager historyManager;

    /**
     * Создает объект {@code CommandProcessor} и инициализирует список доступных команд.
     *
     * @param historyManager Менеджер истории команд.
     */
    public CommandProcessor(QueueManager historyManager) {
        this.historyManager = historyManager;
        commands = new HashMap<>();

        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand());
        commands.put("history", new HistoryCommand(historyManager));
        commands.put("add", new AddTicketCommand());
        commands.put("update", new UpdateTicketCommand());
        commands.put("show", new ShowTicketsCommand());
        commands.put("print_ascending", new PrintAscendingTicketCommand());
        commands.put("remove_by_id", new RemovebyidTicketCommand());
        commands.put("clear", new ClearTicketCommand());
        commands.put("remove_greater", new RemoveGreaterTicketCommad());
        commands.put("remove_lower", new RemoveLowerTicketCommad());
        commands.put("remove_any_by_price", new RemoveAnyByPriceTicketCommand());
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("save", new SaveTicketCommand());
        commands.put("count_less_than_type", new CountLessThanTypeCommand());
        commands.put("exit", new ExitCommand());
    }

    /**
     * Выполняет команду, введенную пользователем.
     *
     * @param input Строка с командой и аргументами (если есть).
     */
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

    /**
     * Возвращает коллекцию доступных команд.
     *
     * @return {@code Map}, содержащая названия команд и соответствующие объекты {@code Command}.
     */
    public Map<String, Command> getCommands() {
        return commands;
    }
}
